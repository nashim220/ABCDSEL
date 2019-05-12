package utility.RateHandlers;

import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import utility.*;
import utility.BasePlanConfig.BasePlan;
import utility.MarketingInfo.Coupon;
import utility.MarketingInfo.DiscountRule;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is my description lulz
 */
public class RateHandler
{
    private String restCall = "get_client_plans_all";
    private ApiHandler api;
    private JSONArray plans;
    public RateHandler()
    {
        api = new ApiHandler();
    }

    public ArrayList<EomPlan> readRates(String clientId, String authKey, boolean eom)
    {

        if(eom)
        {
            this.restCall = "get_client_plans_all_m";
        }


//        clientId = "6000076";
//        authKey = "7RKnupEJM8b3u9jx9tMqAF9uBvjBKvEg";
        String fullUrl = "https://secure"+GlobalProperties.environment+"ariasystems.net/api/ws/api_ws_class_dispatcher.php?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall+"&include_all_rate_schedules=true";
        System.out.println("fullURL: "+fullUrl);
        JSONObject obj = api.makeGetCall(fullUrl);
        ArrayList<EomPlan> eomPlans = new ArrayList<EomPlan>();

        //get all_client_plan_dtls
        if(eom)
        {
            plans = obj.getJSONArray("all_client_plan_dtls");
        }
        else
        {
            System.out.println("Non-EOM environment found!");
            plans = obj.getJSONArray("all_client_plans");
        }
        System.out.println(plans.length() + "Plans Found!");
        for(int i=0; i<plans.length();i++)
        {
            JSONObject plan = plans.getJSONObject(i);
            EomPlan ePlan = new EomPlan();

            ePlan.setClientPlanId(plan.getString("client_plan_id"));
            ePlan.setCurrency(plan.getString("currency_cd"));
            ePlan.setPlanDescription(plan.get("plan_desc").toString());
            ePlan.setPlanName(plan.get("plan_name").toString());
            ePlan.setPlanNumber(plan.get("plan_no").toString());

            //get the currency for each rate schedule
            JsonHandler jsonHandler = new JsonHandler();
            HashMap<String,String> currencyMap = jsonHandler.getRateScheduleCurrencies(clientId, authKey,ePlan.getPlanNumber(),eom);

            //Set plan services
            JSONArray planServices = plan.getJSONArray("plan_services");
            int numPlans = planServices.length();
            ArrayList<PlanService> planServicesList = new ArrayList<PlanService>();
            for(int x = 0; x < numPlans; x++)
            {
                JSONObject service = planServices.getJSONObject(x);
                PlanService pService = new PlanService();
                pService.setClientServiceId(service.get("client_service_id").toString());
                pService.setUsageTypeCode(service.get("usage_type_code").toString());
                pService.setUsageUnitLabel(service.get("usage_unit_label").toString());

                //Set plan Service rates
                JSONArray serviceRates = service.getJSONArray("all_plan_service_rates");
                int numServiceRates = serviceRates.length();
                ArrayList<ServiceRate> serviceRatesList = new ArrayList<ServiceRate>();
                for(int z =0; z<numServiceRates;z++)
                {
                    JSONObject serviceRate = serviceRates.getJSONObject(z);
                    String scheduleCurrency = currencyMap.get(serviceRate.get("schedule_no").toString());

                    //get individual service rate
                    JSONArray currencyRateArray = serviceRate.getJSONArray("plan_service_rates");
                    for(int w = 0; w<currencyRateArray.length();w++)
                    {
                        ServiceRate sRate = new ServiceRate();
                        JSONObject currServiceRate = currencyRateArray.getJSONObject(w);
                        sRate.setClientRateScheduleId(currServiceRate.get("client_rate_schedule_id").toString());
                        sRate.setRatePerUnit(currServiceRate.getDouble("rate_per_unit"));
                        sRate.setCurrency(scheduleCurrency);
                        serviceRatesList.add(sRate);
                    }

                }
                pService.setServiceRates(serviceRatesList);

                planServicesList.add(pService);
            }
            ePlan.setPlanServices(planServicesList);
            eomPlans.add(ePlan);

        }

        for(EomPlan e : eomPlans)
        {
            e.printEomPlan();
        }
//        XlsHandler xlsHandler = new XlsHandler();
//        String outputFileName = xlsHandler.outputEomToXlsx(eomPlans,"uatCompare.xlsx");
        return eomPlans;
//        scenarioOutputTextArea.appendText("Output saved to: "+outputFileName);

    }

    public void old_readPlanConfig(String clientId, String authKey, boolean isEom)
    {
        ArrayList<HashMap<String,String>> testHashList = new ArrayList<HashMap<String,String>>();
        ArrayList<ArrayList<Pair<String,String>>> fullPairList = new ArrayList<ArrayList<Pair<String,String>>>();
        String fullUrl = "https://secure"+GlobalProperties.environment+"ariasystems.net/api/ws/api_ws_class_dispatcher.php?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall+"&include_all_rate_schedules=true";
        if(isEom)
        {
            this.restCall = "get_client_plans_all_m";
            fullUrl = "https://secure"+GlobalProperties.environment+"ariasystems.net/api/ws/api_ws_class_dispatcher.php?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall+"&include_all_rate_schedules=true";
        }
        System.out.println("fullURL: "+fullUrl);
        JSONObject obj = api.makeGetCall(fullUrl);

        if(isEom)
        {
            plans = obj.getJSONArray("all_client_plan_dtls");
        }
        else
        {
            System.out.println("Non-EOM environment found!");
            plans = obj.getJSONArray("all_client_plans");
        }

        for(int i=0; i<plans.length();i++)
        {
            JSONObject plan = plans.getJSONObject(i);
            ArrayList<Pair<String,String>> planPairList = new ArrayList<Pair<String,String>>();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for(String key: plan.keySet())
            {
                HashMap<String,String> testMap = new HashMap<String,String>();
                if(plan.get(key) instanceof JSONArray)
                {
                    JSONArray array = plan.getJSONArray(key);
                    for(int x=0; x<array.length();x++)
                    {
                        JSONObject arrObj = array.getJSONObject(x);
                        for(String arrObjKey: arrObj.keySet())
                        {
                            if(arrObj.get(arrObjKey) instanceof JSONArray)
                            {
                                JSONArray arrObjArr = arrObj.getJSONArray(arrObjKey);
                                for(int z=0;z<arrObjArr.length();z++)
                                {
                                    JSONObject arrObjArrObj = arrObjArr.getJSONObject(z);
                                    for(String arrObjArrKey : arrObjArrObj.keySet())
                                    {
                                        if(arrObjArrObj.get(arrObjArrKey) instanceof JSONArray)
                                        {
                                            JSONArray thirdObjArr = arrObjArrObj.getJSONArray(arrObjArrKey);
                                            for(int y =0; y<thirdObjArr.length();y++)
                                            {
                                                JSONObject thirdObj = thirdObjArr.getJSONObject(y);
                                                for(String thirdObjKey: thirdObj.keySet())
                                                {
                                                    if(testMap.containsKey(thirdObjKey))
                                                    {
                                                        System.out.println("Hashmap already contains: "+thirdObjKey);
                                                    }
                                                    else
                                                    {
                                                        testMap.put(thirdObjKey,thirdObj.get(thirdObjKey).toString());
                                                    }
                                                    planPairList.add(new Pair<String,String>(thirdObjKey,thirdObj.get(thirdObjKey).toString()));
                                                }
                                            }

                                        }else
                                        {

                                            if(testMap.containsKey(arrObjArrKey))
                                            {
                                                System.out.println("Hashmap already contains: "+arrObjArrKey);
                                            }
                                            else
                                            {
                                                testMap.put(arrObjArrKey,arrObjArrObj.get(arrObjArrKey).toString());
                                            }
                                            planPairList.add(new Pair<String,String>(arrObjArrKey,arrObjArrObj.get(arrObjArrKey).toString()));
                                        }

                                    }
                                }
                            }else
                            {
                                if(testMap.containsKey(arrObjKey))
                                {
                                    System.out.println("Hashmap already contains: "+arrObjKey);
                                }
                                else
                                {
                                    testMap.put(arrObjKey,arrObj.get(arrObjKey).toString());
                                }
                                planPairList.add(new Pair<String,String>(arrObjKey,arrObj.get(arrObjKey).toString()));
//                                System.out.println("\t\tKey: "+arrObjKey+" || Value: "+arrObj.get(arrObjKey));
                            }

                        }

                    }
                }
                else
                {
                    if(testMap.containsKey(key))
                    {
                        System.out.println("Hashmap already contains: "+key);
                    }
                    else
                    {
                        testMap.put(key,plan.get(key).toString());
                    }
                    planPairList.add(new Pair<String,String>(key,plan.get(key).toString()));
//                    System.out.println("\tKey: "+key+" || Value: "+plan.get(key));
                }
                fullPairList.add(planPairList);
            }
//            testHashList.add(testMap);

        }
        for(ArrayList<Pair<String,String>> plan : fullPairList)
        {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~numPairs: "+plan.size()+"~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for(Pair<String,String> p : plan)
            {
                System.out.println("\tKey: "+p.getKey()+" || Value: "+p.getValue());
            }
        }


    }

    public ArrayList<BasePlan> readPlanConfig(String clientId, String authKey, boolean isEom)
    {
        String fullUrl = "https://secure.current.stage.ariasystems.net/api/ws/api_ws_class_dispatcher.php?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall+"&include_all_rate_schedules=true";
        String planSuppFieldsName = "";
        String parentPlansFieldsName = "";
        String planServicesName = "";
        if(isEom)
        {
            this.restCall = "get_client_plans_all_m";
            planSuppFieldsName = "plan_supp_fields";
            parentPlansFieldsName = "parent_plans";
            planServicesName = "plan_services";
            fullUrl = "https://secure"+GlobalProperties.environment+"ariasystems.net/api/ws/api_ws_class_dispatcher.php?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall+"&include_all_rate_schedules=true";
        }
        System.out.println("fullURL: "+fullUrl);
        JSONObject obj = api.makeGetCall(fullUrl);

        if(isEom)
        {
            plans = obj.getJSONArray("all_client_plan_dtls");
        }
        else
        {
            System.out.println("Non-EOM environment found!");
            plans = obj.getJSONArray("all_client_plans");
        }
        ArrayList<BasePlan> bpList = new ArrayList<BasePlan>();
        for(int i = 0; i < plans.length(); i++)
        {
            JSONObject plan = plans.getJSONObject(i);
            BasePlan bp = new BasePlan();
            for(String key: plan.keySet())
            {
                //check to see if object is an array
                if(plan.get(key) instanceof JSONArray)
                {
                    if(key.equals(planSuppFieldsName))
                    {
                        JSONArray suppArray = plan.getJSONArray(key);
                        for(int x =0; x<suppArray.length(); x++)
                        {
                            HashMap<String,String> suppMap = new HashMap<String,String>();
                            JSONObject suppObj = suppArray.getJSONObject(x);
                            for(String suppKey: suppObj.keySet())
                            {
                                suppMap.put(suppKey,suppObj.get(suppKey).toString());
                            }
                            bp.addItemToSuppFields(suppMap);

                        }
                    }
                    if(key.equals(parentPlansFieldsName))
                    {
                        JSONArray parentPlansArray = plan.getJSONArray(key);
                        for(int x = 0; x<parentPlansArray.length();x++)
                        {
                            HashMap<String,String> pPlanMap = new HashMap<String,String>();
                            JSONObject pPlanObj = parentPlansArray.getJSONObject(x);
                            for(String pPlanKey: pPlanObj.keySet())
                            {
                                pPlanMap.put(pPlanKey,pPlanObj.get(pPlanKey).toString());
                            }
                            bp.addItemToParentPlans(pPlanMap);
                        }

                    }

                    if(key.equals(planServicesName))
                    {
                        JSONArray planServiceArray = plan.getJSONArray(key);
                        ArrayList<String> serviceList = new ArrayList<String>();
                        for(int x = 0; x < planServiceArray.length(); x++)
                        {
                            JSONObject serviceObj = planServiceArray.getJSONObject(x);
                            String clientServiceId = serviceObj.get("client_service_id").toString();
                            bp.addClientServiceId(clientServiceId);

                        }

                    }
                }
                else
                {
                    bp.addItemToBaseConfig(key,plan.get(key).toString());
                }
            }
            bpList.add(bp);
        }

        for(BasePlan myPlan : bpList)
        {
            myPlan.printPlan();
        }
        return bpList;

    }

    /**
     * This is my marketing info description Hi Eldon
     */
    public void readMarketingInfo(String clientId, String authKey,String baseFileName, boolean isEom)
    {
        //environment = "current";

        ArrayList<Coupon> coupons = new ArrayList<Coupon>();
        String fullUrl = "https://admintools"+GlobalProperties.environment+"ariasystems.net/AdminTools.php/Dispatcher?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall;
        if(isEom)
        {
            this.restCall = "get_coupons";
            fullUrl = "https://admintools"+GlobalProperties.environment+"ariasystems.net/AdminTools.php/Dispatcher?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall;
        }
        System.out.println("fullURL: "+fullUrl);
        JSONObject response = api.makeGetCall(fullUrl);
        JSONArray couponCodes = response.getJSONArray("coupons");

        for(int i=0;i<couponCodes.length();i++)
        {
            Coupon coup = new Coupon();
            JSONObject coupon = couponCodes.getJSONObject(i);
            coup.setCouponCode(coupon.get("coupon_cd").toString());
            coup.setCouponDescription(coupon.get("coupon_desc").toString());
            try
            {
                coup.setCouponMessage(coupon.get("coupon_msg").toString());
                coup.setCouponStatus(coupon.get("status_ind").toString());
                coup.setNumOfUses(coupon.get("no_of_uses").toString());
                coup.setStartDate(coupon.get("start_date").toString());
                coup.setEndDate(coupon.get("end_date").toString());
            }
            catch(Exception e)
            {

            }

//            coup.setCouponCode(coup.getCouponCode().replaceAll(" ","%20"));
//            coup.setCouponCode(coupon.get("coupon_cd").toString().replaceAll(" ","%20"));
//            String encodedCoupCode = coup.getConvertedCouponCode();
            restCall = "get_coupon_details";
            String couponUrl = "https://admintools"+GlobalProperties.environment+"ariasystems.net/AdminTools.php/Dispatcher?output_format=json&client_no=" + clientId + "&auth_key=" + authKey + "&rest_call=" + restCall + "&coupon_cd="+coup.getConvertedCouponCode();
            JSONObject couponResponse = api.makeGetCall(couponUrl);
            JSONArray discountRulesArray = couponResponse.getJSONArray("discount_rule");
            if(discountRulesArray.length() == 1)
            {
                JSONObject currentRule = discountRulesArray.getJSONObject(0);
                DiscountRule discRule = new DiscountRule();
                discRule.setRuleNumber(currentRule.get("rule_no").toString());
                discRule.setRuleName(currentRule.get("rule_name").toString());
                discRule.setRuleDesc(currentRule.get("description").toString());
                discRule.setDiscountType(currentRule.get("discount_type").toString());
                discRule.setDiscountAmt(currentRule.get("amount").toString());
                discRule.setRuleDurationType(currentRule.get("duration_type_ind").toString());
                try
                {

                    discRule.setRuleMaxMonths(currentRule.get("max_applicable_months").toString());
//                    System.out.println("Max months found!");
                }
                catch(Exception e)
                {
                    discRule.setRuleMaxMonths("Infinite");
//                    System.out.println("NO MAX Months");
                }
                coup.setDiscountRule(discRule);

            }



            coupons.add(coup);

        }

        for(Coupon c: coupons)
        {
            c.print();
        }

        restCall = "get_discount_rules";
        fullUrl = "https://admintools"+GlobalProperties.environment+"ariasystems.net/AdminTools.php/Dispatcher?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall;
        JSONObject discountResponse = api.makeGetCall(fullUrl);
        JSONArray discountArray = discountResponse.getJSONArray("discount_rules");
        ArrayList<DiscountRule> discountRules = new ArrayList<DiscountRule>();
        for(int i=0; i< discountArray.length();i++)
        {

            DiscountRule dr = new DiscountRule();
            JSONObject discount = discountArray.getJSONObject(i);
            String ruleNumber = discount.get("rule_no").toString();

            //make API call for rule details
            restCall = "get_discount_rule_details";
            fullUrl = "https://admintools"+GlobalProperties.environment+"ariasystems.net/AdminTools.php/Dispatcher?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall+"&rule_no="+ruleNumber;
            System.out.println("THIS IS FOR DR: "+fullUrl);
            JSONObject drObject = api.makeGetCall(fullUrl);
            try
            {
                dr.setRuleCurrency(drObject.getJSONArray("discount_rules").getJSONObject(0).get("currency").toString());
            }
            catch(Exception e)
            {
                dr.setRuleCurrency("");
            }


            dr.setRuleId(discount.get("rule_no").toString());
            dr.setRuleNumber(ruleNumber);
            dr.setDiscountAmt(discount.get("amount").toString());
            dr.setDiscountType(discount.get("discount_type").toString());
            dr.setRuleId(discount.get("rule_id").toString());
            dr.setRuleDesc(discount.get("description").toString());
            discountRules.add(dr);

        }

        for(DiscountRule dr: discountRules)
        {
            dr.print();
        }

//        get the coupon groups and group no. get_coupon_groups (admin)
        restCall = "get_coupon_groups";
        fullUrl = "https://admintools"+GlobalProperties.environment+"ariasystems.net/AdminTools.php/Dispatcher?output_format=json&client_no="+clientId+"&auth_key="+authKey+"&rest_call="+restCall;
        JSONObject couponGroupsResponse = api.makeGetCall(fullUrl);
        System.out.println(couponGroupsResponse);
        JSONArray couponGroupsArray = couponGroupsResponse.getJSONArray("coupon_groups");
        for(int i=0;i<couponGroupsArray.length();i++)
        {
            JSONObject couponGroup = couponGroupsArray.getJSONObject(i);
            //TODO: add the coupon group properties to the coupon group option list.

        }

//        get the coupon group details get_coupon_group_details (admin)


        XlsHandler xls = new XlsHandler();
        xls.outputEomMarketingInfo(coupons,discountRules,baseFileName);




    }
}
