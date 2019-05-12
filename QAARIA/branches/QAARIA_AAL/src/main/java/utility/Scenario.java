package utility;


import javafx.scene.control.TextArea;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by joe on 5/4/15.
 */
public class Scenario
{
    private CsvHandler csv = new CsvHandler();
    private String baseAcctName;
    private int numberOfAccounts;
    private ArrayList<String> restCalls;
    private ArrayList<String> names;
    private TextArea outputArea;
    private String baseUrl;
    private String filePath;
    private Logger log;

    public Scenario(String acctName, int numAccts, String path, TextArea area, String url)
    {
        baseAcctName = acctName;
        numberOfAccounts = numAccts;
        names = new ArrayList<String>();
        outputArea = area;
        baseUrl = url;
        filePath = path;
        log = Logger.getGlobal();

    }
    public void executeScenario(String url)
    {
        ApiHandler api = new ApiHandler();
        CsvHandler csv = new CsvHandler();

        List<List<NameValuePair>> allParams = csv.readCSV(this.filePath, this.outputArea);
        List<List<NameValuePair>> finalParams = new ArrayList<List<NameValuePair>>(allParams);

        List<NameValuePair> newParams = new ArrayList<NameValuePair>();

        for(int i=0;i<numberOfAccounts;i++)
        {

        }

        for(NameValuePair p : allParams.get(0))
        {
            newParams.add(new BasicNameValuePair(p.getName(),p.getValue()));
        }
        log.info("Before Size: " + newParams.size());

//        //replicate data based on number of accts
//        for(int i=0;i<numberOfAccounts;i++)
//        {
//            newParams.add(new BasicNameValuePair("client_acct_id", baseAcctName + "_" + i));
//            finalParams.add(newParams);
//            log.info("Adding Params: "+i);
//        }
//
//        log.info("After Size: "+finalParams.size());

        //print all the data records
        for(List<NameValuePair> param : finalParams)
        {
            outputArea.appendText("~~~~~~~~~~~~~~~~~~~RECORD START~~~~~~~~~~~~~~~~~~~~\n");
            for(NameValuePair p : param)
            {
                outputArea.appendText(p.getName()+" || "+p.getValue()+"\n");
            }
            outputArea.appendText("~~~~~~~~~~~~~~~~~~~RECORD END~~~~~~~~~~~~~~~~~~~~\n");
        }

    }

    public String getUrl()
    {
        return this.baseUrl;
    }
    public String getPath()
    {
        return this.filePath;
    }
    public TextArea getTextArea()
    {
        return this.outputArea;
    }

}
