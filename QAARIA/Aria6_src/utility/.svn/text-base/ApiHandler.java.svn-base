package utility;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.CDL;
import org.json.JSONObject;
import org.apache.commons.io.FileUtils;

import javax.xml.soap.Text;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.jar.Attributes;
import java.util.logging.*;

/**
 * Created by joe on 4/29/15.
 29-Sep2015 -- Updated the function makeSimplePostCall.Added code which will return json response.
 
 */
public class ApiHandler
{
    private CloseableHttpClient httpclient;
    private String acctNum;
    private CsvHandler csv;
    private Logger log;
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
    public JSONObject makeMultiGetCall(String url, TextArea area, String path,String restCall)
    {
        JSONObject jsonObj = new JSONObject();
        httpclient = HttpClients.createDefault();

        log.info("The URL: " + url);

        try
        {
            List<List<NameValuePair>> allParams = csv.readCSV(path);
            List<JSONObject> outputJsonList = new ArrayList<JSONObject>();
            log.info("SIZE: " + allParams.size());

            for(List<NameValuePair> param : allParams)
            {
                String finalUrl = url;
                param.add(new BasicNameValuePair("output_format", "json"));
                for(NameValuePair p : param)
                {
                    finalUrl = finalUrl+"&"+p.getName()+"="+p.getValue();

                }
                HttpGet httpGet = new HttpGet(finalUrl);
                //log.info("API GET: "+finalUrl);
                HttpEntity entity1 = httpclient.execute(httpGet).getEntity();
                String entityString = EntityUtils.toString(entity1);
                jsonObj = new JSONObject(entityString);
                Iterator<?> keys = jsonObj.keys();
                while(keys.hasNext())
                {
                    String key = (String)keys.next();
                    log.info("Key: " + key + " || Value: " + jsonObj.get(key));
                }
                //log.info("acct_no: "+jsonObj.get("acct_no"));
                outputJsonList.add(jsonObj);
                area.appendText(jsonObj.toString());
                //area.appendText("Service_No: "+jsonObj.get("service_no")+" || Taxable_ind: "+jsonObj.get("taxable_ind")+"\n");
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


            //Uncomment following lines

            area.appendText("File Saved To: "+ outputPath);
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
        log.info("The URL: " + url);
        JSONObject jsonObj = new JSONObject();
        try
        {
            HttpGet httpGet = new HttpGet(url);
            log.info("API Get: " + url);
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


    public void toggleTestAccount(String acctNum, TextArea area)
    {
        String baseUrl = "https://secure.future.stage.ariasystems.net/api/ws/api_ws_class_dispatcher.php?client_no=6000033&auth_key=gNc55QR3vDrB4y3YEk57sbvM6Spjbswt&rest_call=toggle_test_account&account_no="+acctNum+"&test_acct_ind=1&output_format=json";
        HttpPost httpPost = new HttpPost(baseUrl);
        httpclient = HttpClients.createDefault();
        try
        {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            log.info(response.getStatusLine().toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<JSONObject> makePostCall(String url, String path,String restCall)
    {
        JSONObject jsonObj = new JSONObject();
        HttpPost httpPost = new HttpPost(url);
        httpclient = HttpClients.createDefault();
        try
        {

            List<List<NameValuePair>> allParams = csv.readCSV(path);
            List<JSONObject> outputJsonList = new ArrayList<JSONObject>();
            log.info("SIZE: " + allParams.size());
            for(List<NameValuePair> param : allParams)
            {
                boolean testAcctIndExists = false;
                for(NameValuePair p : param)
                {
                    if(p.getName().equals("test_acct_ind") && restCall.equals("create_acct_complete"))
                    {
                        testAcctIndExists = true;
                        log.info("Found existing test acct status.");
                    }
                }
                param.add(new BasicNameValuePair("output_format","json"));
                if(!testAcctIndExists && restCall.equals("create_acct_complete"))
                {
                    log.info("Setting acct to Live.");
                    param.add(new BasicNameValuePair("test_acct_ind", "0"));
                }




                httpPost.setEntity(new UrlEncodedFormEntity(param));
                System.out.println("API POST: "+httpPost+httpPost.getEntity().toString());
                CloseableHttpResponse response = httpclient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String entityString = EntityUtils.toString(entity);
                jsonObj = new JSONObject(entityString);
                log.info(response.getStatusLine().toString());
                log.info(entityString);
                System.out.println(response.getStatusLine().toString() + "\n");
                System.out.println(entityString);
                outputJsonList.add(jsonObj);

            }

            //csv.convertToCsv(jsonObj, area);
//            String outputPath = csv.convertOutputToCsv(outputJsonList,path);
//            System.out.println("File saved to: "+outputPath);
            return outputJsonList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<JSONObject> makePostCall(String url, String path,String restCall, String acctNum, String date)
    {
        JSONObject jsonObj = new JSONObject();
        HttpPost httpPost = new HttpPost(url);
        httpclient = HttpClients.createDefault();
        try
        {

            List<List<NameValuePair>> allParams = csv.readCSV(path);
            List<JSONObject> outputJsonList = new ArrayList<JSONObject>();
            log.info("SIZE: " + allParams.size());
            for(List<NameValuePair> param : allParams)
            {
                boolean testAcctIndExists = false;
                for(NameValuePair p : param)
                {
                    if(p.getName().equals("test_acct_ind") && restCall.equals("create_acct_complete"))
                    {
                        testAcctIndExists = true;
                        log.info("Found existing test acct status.");
                    }
                }
                if(restCall.equals("record_usage"))
                {
                    System.out.println("Adding date to record usage: "+date);
                    param.add(new BasicNameValuePair("usage_date",date));
                }
                param.add(new BasicNameValuePair("acct_no", acctNum));
                param.add(new BasicNameValuePair("output_format","json"));
                if(!testAcctIndExists && restCall.equals("create_acct_complete"))
                {
                    log.info("Setting acct to Live.");
                    param.add(new BasicNameValuePair("test_acct_ind", "0"));
                }




                httpPost.setEntity(new UrlEncodedFormEntity(param));
                System.out.println("API POST: "+httpPost+httpPost.getEntity().toString());
                CloseableHttpResponse response = httpclient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String entityString = EntityUtils.toString(entity);
                jsonObj = new JSONObject(entityString);
                log.info(response.getStatusLine().toString());
                log.info(entityString);
                System.out.println(response.getStatusLine().toString() + "\n");
                System.out.println(entityString);
                outputJsonList.add(jsonObj);

            }

            //csv.convertToCsv(jsonObj, area);
//            String outputPath = csv.convertOutputToCsv(outputJsonList,path);
//            System.out.println("File saved to: "+outputPath);
            return outputJsonList;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<JSONObject> makeSimplePostCall(String url) throws Exception
    {
  
	   JSONObject jsonObj = new JSONObject();
	   List<JSONObject> outputJsonList = new ArrayList<JSONObject>();
	   HttpPost httpPost = new HttpPost(url);
	   httpclient = HttpClients.createDefault();
	   CloseableHttpResponse response = httpclient.execute(httpPost);
	   HttpEntity entity = response.getEntity();
	   String entityString = EntityUtils.toString(entity);
	   jsonObj = new JSONObject(entityString);
	   log.info(response.getStatusLine().toString());
	   log.info(entityString);
	   System.out.println(response.getStatusLine().toString() + "\n");
	   System.out.println(entityString);
	   outputJsonList.add(jsonObj);
	   return outputJsonList;


    }
    public JSONObject makeSimplePostCallWithJson(JSONObject jsonObj, String url) throws Exception
    {

        HttpPost httpPost = new HttpPost(url);
        StringEntity params = new StringEntity(jsonObj.toString());
        httpPost.setEntity(params);
        httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);
        JSONObject responseJson = new JSONObject(entityString);
        return responseJson;

    }

   

    public Logger getApiLogger()
    {
        return this.log;
    }
}
