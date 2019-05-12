package utility;

import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * Created by joe on 5/20/15.
 */
public class ApiThread implements Callable
{
    private List<NameValuePair> allParams;
    private String url;
    private String restCall;
    private CloseableHttpClient httpclient;
    private String responseString;
    private Thread t;
    private TextArea area;
    Logger log = Logger.getGlobal();


    public ApiThread(List<NameValuePair> passedParams, String passedUrl, String passedRestCall)
    {
        allParams = passedParams;
        url = passedUrl;
        restCall = passedRestCall;
    }

    @Override
    public JSONObject call()
    {

        JSONObject jsonObj = new JSONObject();
        HttpPost httpPost = new HttpPost(url);
        httpclient = HttpClients.createDefault();
        try {
            List<JSONObject> outputJsonList = new ArrayList<JSONObject>();
                boolean testAcctIndExists = false;
                for (NameValuePair p : allParams) {
                    if (p.getName().equals("test_acct_ind") && restCall.equals("create_acct_complete")) {
                        testAcctIndExists = true;
                        log.info("Found existing test acct status.");
                    }
                }
                allParams.add(new BasicNameValuePair("output_format", "json"));
                if (!testAcctIndExists) {
                    //log.info("Setting acct to Live.");
                    //allParams.add(new BasicNameValuePair("test_acct_ind", "0"));
                }


                httpPost.setEntity(new UrlEncodedFormEntity(allParams));
                //log.info(httpPost.getURI().toString());
                //log.info("API POST: " + httpPost + httpPost.getEntity().toString());
                CloseableHttpResponse response = httpclient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String entityString = EntityUtils.toString(entity);
                jsonObj = new JSONObject(entityString);
                //log.info(response.getStatusLine().toString());
                log.info(entityString);
                responseString = entityString;
//                area.appendText(responseString+"\n");
                httpclient.close();
                Thread.sleep(50);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return jsonObj;
    }
    public String getResponseString()
    {
        return this.responseString;
    }
}
