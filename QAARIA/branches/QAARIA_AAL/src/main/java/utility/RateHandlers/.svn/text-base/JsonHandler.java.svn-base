package utility.RateHandlers;

import org.json.JSONArray;
import org.json.JSONObject;
import utility.ApiHandler;
import utility.GlobalProperties;

import java.util.HashMap;

/**
 * Created by joe on 2/4/16.
 */
public class JsonHandler
{
    private String environment = GlobalProperties.environment;
    public JsonHandler()
    {

    }
    public JSONArray getJSONArray(JSONObject jsonObj, String arrayName)
    {
        JSONArray array = new JSONArray();
        try {
            array = jsonObj.getJSONArray(arrayName);
        }
        catch(Exception e)
        {
            System.out.println("No JSONArray found: "+arrayName);
        }
        return array;
    }
    public HashMap<String,String> getRateScheduleCurrencies(String clientId, String authKey, String planNumber, boolean eom)
    {
        String restCall = "get_rate_schedules_for_plan";
        String jsonRatesArrayName = "rate_sched";
        if(eom)
        {
            restCall = "get_rate_schedules_for_plan_m";
            jsonRatesArrayName = "rate_scheds";
        }
        ApiHandler api = new ApiHandler();
        String rateUrl = "https://secure"+GlobalProperties.environment+"ariasystems.net/api/ws/api_ws_class_dispatcher.php?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall+"&plan_no="+planNumber;
        HashMap<String,String> map = new HashMap<String,String>();
        JSONObject response = api.makeGetCall(rateUrl);
        JSONArray rateArray = this.getJSONArray(response,jsonRatesArrayName);
        int numRates = rateArray.length();
        for(int i=0;i<numRates;i++)
        {
            JSONObject rateSchedule = rateArray.getJSONObject(i);
            map.put(rateSchedule.get("schedule_no").toString(),rateSchedule.get("schedule_currency").toString());
        }

        return map;
    }
}
