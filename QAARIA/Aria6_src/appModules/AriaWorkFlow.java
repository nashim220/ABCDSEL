package appModules;

import org.json.JSONObject;
import utility.ApiHandler;

public class AriaWorkFlow
{
    public static String baseWorkFlowUrl = "https://sungarddev01.decisions.com/aria/Primary/?";
    public static JSONObject addAccountToBudgetWorkflow(String acctNum) throws Exception
    {
        ApiHandler api = new ApiHandler();
        String url = baseWorkFlowUrl+"FlowId="+getAddAccountFlowId()+"&Action=api&outputtype=JSON";
        //TODO: construct Json Body
        JSONObject parent = new JSONObject();
        parent.put("sessionid",getSessionId());
        parent.put("acct_no",acctNum);

        //TODO: make API call
        JSONObject json = api.makeSimplePostCallWithJson(parent,url);
        System.out.println(json);
        return json;
    }
    public static JSONObject kickOffWorkFlowBudgetNotification(String clientNum) throws Exception
    {
        ApiHandler api = new ApiHandler();
        String url = baseWorkFlowUrl+"FlowId="+getProcessAccountFlowId()+"&Action=api&outputtype=JSON";
        //TODO: construct Json Body
        JSONObject parent = new JSONObject();
        parent.put("sessionid",getSessionId());
        parent.put("client_no",clientNum);

        //TODO: make API call
        JSONObject json = api.makeSimplePostCallWithJson(parent,url);
        System.out.println(json);
        return json;
    }

    public static String getSessionId()
    {
        return "NS-a34a1b59-f27c-11e4-856e-00155d143e00";
    }
    public static String getAddAccountFlowId()
    {
        return "99747571-776f-11e5-856e-00155d143e00";
    }
    public static String getProcessAccountFlowId()
    {
        return "45c06b38-1835-11e5-856e-00155d143e00";
    }
}
