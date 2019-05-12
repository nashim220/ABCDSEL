package utility;

import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.*;

/**
 * Created by joe on 4/29/15.
 *
 * Base Class for All API transactions. This class also distinguishes individual thread processes.
 */
public class ApiHandler
{
    private CloseableHttpClient httpclient;
    private XlsHandler xls;
    private CsvHandler csv;
    private Logger log;
    private int THREAD_COUNT = 50;

    /*
        Constructor: Establishes Global Logger.
     */
    public ApiHandler()
    {
        csv = new CsvHandler();
        log = Logger.getGlobal();

        try
        {
            Handler handler = new FileHandler("AriaApiTransactions.log",true);
            handler.setEncoding("UTF-8");
            handler.setFormatter(new SimpleFormatter());
            log.addHandler(handler);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        log.setLevel(Level.ALL);
    }

    /*
        Input: pre-formatted base URL (with included rest_call)
        Output: Returns JSON Entity as a String
     */
    public String makeSimplePostCall(String url) throws Exception
    {
        HttpPost httpPost = new HttpPost(url);
        httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);
        return entityString;

    }
    /*
        Arguments:
            url: Pre-Formatted base url with rest_call included.
            area: JavaFX TextArea to display response output
            path: Absolute path to the the file to process.
            restCall: The rest_call parameter for the current API call:
                NOTE: this is only used to determine POST vs GET logic.
     */
    public JSONObject makeMultiGetCall(String url, TextArea area, String path,String restCall)
    {
        JSONObject jsonObj = new JSONObject();
        httpclient = HttpClients.createDefault();
        //log.info("~~~~~~~~~~|| Starting makeMultiGetCall ||~~~~~~~~~~~~");
        try
        {
            List<List<NameValuePair>> allParams = xls.readXlsxFile(path);
            List<JSONObject> outputJsonList = new ArrayList<JSONObject>();

            for(List<NameValuePair> param : allParams)
            {
                String finalUrl = url;
                param.add(new BasicNameValuePair("output_format", "json"));
                for(NameValuePair p : param)
                {
                    finalUrl = finalUrl+"&"+p.getName()+"="+p.getValue();

                }

                HttpGet httpGet = new HttpGet(finalUrl);
                HttpEntity entity1 = httpclient.execute(httpGet).getEntity();
                String entityString = EntityUtils.toString(entity1);
                jsonObj = new JSONObject(entityString);
                Iterator<?> keys = jsonObj.keys();

                while(keys.hasNext())
                {
                    String key = (String)keys.next();
                    //log.info("Key: " + key + " || Value: " + jsonObj.get(key));
                }

                outputJsonList.add(jsonObj);
                area.appendText(jsonObj.toString());

            }

            httpclient.close();
            String outputPath = path;
            if(restCall.equals("get_acct_plan_unit_instance_all"))
            {
                outputPath = csv.convertAcctInstanceToCsv(outputJsonList,path);
            }
            else
            {
                outputPath = csv.convertOutputToCsv(outputJsonList,path);
            }

            area.appendText("File Saved To: "+ outputPath);
            //log.info("~~~~~~~~~~|| Ending MakeMultiGetCall || ~~~~~~~~~~");
            return jsonObj;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return jsonObj;
        }
    }
    public JSONObject makeGetCall(final String baseUrl)
    {

        final String url = baseUrl;
        httpclient = HttpClients.createDefault();
        //log.info("The URL: " + url);
        JSONObject jsonObj = new JSONObject();
        try
        {
            HttpGet httpGet = new HttpGet(url);
            //log.info("API Get: "+url);
            HttpEntity entity1 = httpclient.execute(httpGet).getEntity();
            String jsonResponse = EntityUtils.toString(entity1);
            jsonObj = new JSONObject(jsonResponse);
            httpclient.close();
            //csv.convertToCsv(jsonObj, area);
            return jsonObj;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return jsonObj;
        }

    }

    public void makePostCall(String url, String path, TextArea area, String restCall,ProgressIndicator progInd)
    {
        try {
            List<Future<JSONObject>> responses = new ArrayList<Future<JSONObject>>();

            ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
            List<List<NameValuePair>> allApiRequests = xls.readXlsxFile(path);
            ArrayList<List<NameValuePair>> allChildren = new ArrayList<List<NameValuePair>>();

            for (List<NameValuePair> apiRequest : allApiRequests)
            {
                if(isChildAccount(apiRequest))
                {
                    allChildren.add(apiRequest);
                }
                else
                {
                    Future<JSONObject> response = executor.submit(new ApiThread(apiRequest, url, restCall));
                    responses.add(response);
                }

            }
            executor.shutdown();
            while (!executor.isTerminated()) {

            }
            Thread.sleep(10000);
            executor = Executors.newFixedThreadPool(THREAD_COUNT);
            for(List<NameValuePair> child: allChildren)
            {
                Future<JSONObject> response = executor.submit(new ApiThread(child, url, restCall));
                responses.add(response);
            }
            executor.shutdown();
            List<JSONObject> outputList = new ArrayList<JSONObject>();
            for(Future<JSONObject> resp : responses)
            {
                outputList.add(resp.get());
                area.appendText(resp.get()+"\n");
            }

            String outputPath = xls.outputToXlsx(outputList,path);
            area.appendText("OUTPUT SAVED TO "+outputPath+"\n");
            progInd.setVisible(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            progInd.setVisible(false);
        }

    }

    /*
        Returns the current Logger for the API-Handler
     */
    public Logger getApiLogger()
    {
        return this.log;
    }

    /*
        Determines if acct being loaded is a child acct or a parent acct.
     */
    public boolean isChildAccount(List <NameValuePair> acctList)
    {
        for(NameValuePair p : acctList)
        {
            if(p.getName().equals("senior_acct_user_id"))
            {
                String parentUserId = p.getValue();
                if(parentUserId.isEmpty())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

        }
        return true;
    }

    public String makeSimplePostWithJson(String baseUrl, StringEntity params)
    {
        HttpClient httpClient = HttpClientBuilder.create().build();
        String jsonResponse = "";
        try
        {
            HttpPost request = new HttpPost(baseUrl);
//            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setHeader("Content-Type", "application/json; charset=UTF-8");
            request.setEntity(params);
            HttpEntity response = httpClient.execute(request).getEntity();
            jsonResponse = EntityUtils.toString(response);
//            JSONObject jsonObj = new JSONObject(jsonResponse);
            System.out.println(jsonResponse);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}
