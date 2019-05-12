package utility;

import javafx.scene.control.TextArea;
import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by joe on 5/1/15.
 */
public class CsvHandler
{

    Logger log = Logger.getGlobal();

    public CsvHandler()
    {
        log.setLevel(Level.ALL);
    }

    //Current Output to CSV that is used
    public String convertOutputToCsv(List<JSONObject> outputList, String path)
    {


        try
        {
            Path p = Paths.get(path);
            String fileName = p.getFileName().toString();
            File file = new File("output_"+fileName);
            String outputPath = file.getAbsolutePath();
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath(),true);
            boolean headersAlreadyStored = false;
            String completeOutput = "";
            for(JSONObject jsonObj : outputList)
            {
                Iterator<String> keys = jsonObj.keys();
                String headers = "";
                String data = "";


                while(keys.hasNext())
                {
                    String key = (String)keys.next();
                    if(!(keys.hasNext()))
                    {
                        headers += key;
                        data += jsonObj.get(key);
                    }
                    else
                    {
                        headers += key +",";
                        data += jsonObj.get(key) + ",";
                    }
                }
                if(!headersAlreadyStored)
                {
                    data = data.replaceAll("null","");
                    completeOutput = headers +"\n"+data;
                    headersAlreadyStored = true;
                }
                else
                {
                    data = data.replaceAll("null","");
                    completeOutput = completeOutput +"\n"+data;
                }
            }
            FileUtils.writeStringToFile(file, completeOutput);
            return outputPath;

        }
        catch (Exception e)
        {

            e.printStackTrace();
            return "no Output path";
        }
    }
    public String convertAcctInstanceToCsv(List<JSONObject> jsonObjList, String path) throws Exception
    {
        Path p = Paths.get(path);
        String fileName = p.getFileName().toString();
        File file = new File("output_"+fileName);
        String outputPath = file.getAbsolutePath();
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath(),true);
        boolean headersAlreadyStored = false;
        String completeOutput = "";
        String headers = "acct_no,client_plan_unit_inst_id";
        for(JSONObject jsonObj : jsonObjList)
        {
            String data = "";
            String acctNum = jsonObj.get("acct_no").toString();
            JSONArray allPlanUnitInstances = jsonObj.getJSONArray("all_plan_unit_instances");
            int arraySize = allPlanUnitInstances.length();
            for(int i = 0;i<arraySize;i++)
            {
                JSONObject arrayObj = allPlanUnitInstances.getJSONObject(i);
                JSONArray subArray = arrayObj.getJSONArray("plan_unit_instance");
                int subArraySize = subArray.length();
                for(int x = 0;x<subArraySize;x++)
                {
                    JSONObject subArrayObj = subArray.getJSONObject(x);
                    String instance = subArrayObj.get("client_plan_unit_inst_id").toString();
                    data = acctNum+","+instance;
                    if(!headersAlreadyStored)
                    {
                        data = data.replaceAll("null","");
                        completeOutput = headers +"\n"+data;
                        headersAlreadyStored = true;
                    }
                    else
                    {
                        data = data.replaceAll("null","");
                        completeOutput = completeOutput +"\n"+data;
                    }
                }

            }

        }
        FileUtils.writeStringToFile(file, completeOutput);
        return outputPath;

    }
    //depreciated
    public void convertToCsv(JSONObject jsonObj, TextArea area)
    {
        try
        {
            Date date = new Date();
            String headers = "";
            String data = "";
            Iterator<String> keys = jsonObj.keys();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File file = new File("output"+timeStamp+ ".csv");

            while(keys.hasNext())
            {
                String key = (String)keys.next();
                if(!(keys.hasNext()))
                {
                    headers += key;
                    data += jsonObj.get(key);
                }
                else
                {
                    headers += key +",";
                    data += jsonObj.get(key) + ",";
                }

            }

            //Clean up and write to file
            data = data.replaceAll("null","");
            String complete = headers +"\n"+data;
            FileUtils.writeStringToFile(file, complete);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public List<List<NameValuePair>> readCSV(String path,TextArea area)
    {
        BufferedReader fileReader = null;
        String line = "";
        String[] headers;
        String COMMA_DELIM =",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        List<List<NameValuePair>> allParams = new ArrayList<List<NameValuePair>>();
        try
        {

            fileReader = new BufferedReader(new FileReader(path));
            String headerLine = fileReader.readLine();
            headers = headerLine.split(COMMA_DELIM);
            //line = fileReader.readLine();

            while((line=fileReader.readLine())!=null)
            {
                String[] tokens = line.split(COMMA_DELIM,-1);

                log.info("headers Size: "+headers.length + "|| tokens Size: "+tokens.length);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                area.appendText("============Record Start============\n");
                for(int i=0;i<headers.length;i++)
                {
                    //check date format
                    if(headers[i].contains("date") && tokens[i].contains("/"))
                    {
                        log.info("csv date value: "+tokens[i]);
                        tokens[i] = convertDate(tokens[i]);
                        log.info("New token: "+tokens[i]);

                    }
                    if(!(headers[i].equals("")))
                    {
                        area.appendText("Key: " + headers[i] + " || Value: " + tokens[i] + "\n");
                        params.add(new BasicNameValuePair(headers[i],tokens[i]) );
                    }

                }
                area.appendText("============Record End==============\n\n");
                allParams.add(params);
                //log.info(params);
            }
            return allParams;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return allParams;
        }
    }

    public String convertDate(String oldDate) throws Exception
    {
        final String OLD_FORMAT = "mm/dd/yyyy";
        final String NEW_FORMAT = "yyyy-mm-dd";
        String newDateString;
        String dateToken = oldDate;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(dateToken);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        log.info("This is a logger test! "+newDateString);
        return newDateString;


    }

}
