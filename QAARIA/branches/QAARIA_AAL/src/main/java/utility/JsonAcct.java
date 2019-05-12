package utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joe on 4/18/16.
 */
public class JsonAcct
{
    private JSONObject jo;
    private List<String> baseAcctList = Arrays.asList("userid","first_name","last_name","work_phone","acct_currency","company_name","address1","city","state_prov","postal_cd","phone","email","retroactive_start_date","client_seq_func_group_id", "country", "address2", "locality", "notify_method","alt_bill_day");
    private List<String> mpiList = Arrays.asList("client_plan_instance_id","plan_instance_idx","plan_instance_units","resp_level_cd","dunning_group_idx","billing_group_idx", "client_plan_id","plan_instance_units","po_num");
    private List<String> billingContactList = Arrays.asList("billing_first_name","billing_last_name","billing_email","billing_company_name","billing_work_phone","billing_address1","billing_address2","billing_city","billing_locality","billing_state_prov","billing_country","billing_postal_cd");
    private List<String> billingGrpList = Arrays.asList("company_name","first_name","last_name","address1","city","state_prov","postal_cd","phone","work_phone","email","country", "address2", "locality");
    private List<String> couponList = Arrays.asList();
    private String onlyParentSuppPlan = "Managed_Cloud_Workplace";
    public JsonAcct(HashMap<String,String> map, String clientNum, String authKey)
    {
        jo = new JSONObject();
        //jo.put("client_no", "6000076");
        jo.put("client_no", clientNum   );
        //jo.put("auth_key","7RKnupEJM8b3u9jx9tMqAF9uBvjBKvEg");
        jo.put("auth_key",authKey);
        jo.put("rest_call","create_acct_complete_m");
        jo.put("output_format","json");

        //Pre-Defined Fields
        map.put("client_seq_func_group_id",map.get("client_functional_acct_group_id"));

        //Remove blanks
        ArrayList<String> keysToRemove = new ArrayList<String>();

        for(String key: map.keySet())
        {
            if(map.get(key).equals(""))
            {

                keysToRemove.add(key);
            }
            //find if the supp field is co_code
            if(map.get(key).toLowerCase().equals("co_code"))
            {
                //get the corresponding keynum
                String keynum = key.substring(key.length()-3);
                String currentCoCode = map.get("acct_supp_field_value"+keynum);
                //if the length of the value string is 1, add a preceding 0
                if(currentCoCode.length() ==1)
                {
                    map.put("acct_supp_field_value"+keynum,"0"+currentCoCode);
                }
            }
        }

        for(String key: keysToRemove)
        {
            //System.out.println("Removing: "+key);
            map.remove(key);
        }

        //Account Info
        JSONArray acctArray = new JSONArray();
        JSONObject acctRow = new JSONObject();
        for(String field: baseAcctList)
        {
            acctRow.put(field,map.get(field));
            if(field.equals("userid"))

            {
                System.out.println("JsonAcct: "+map.get(field));
            }

        }


        //Coupon Stuff
        JSONObject couponCode = new JSONObject();
        JSONArray couponCodeArray = new JSONArray();
        JSONObject ccObj = new JSONObject();

        ccObj.put("coupon_codes",map.get("coupon_codes"));
        couponCodeArray.put(ccObj);
        couponCode.put("coupon_codes_row",couponCodeArray);
        acctRow.put("coupon_codes",couponCode);


        //Functional Account Group Stuff
        JSONObject funcAcctGroup = new JSONObject();
        JSONArray funcAcctGroupArray = new JSONArray();
        JSONObject funcAcctGroupRow = new JSONObject();

        funcAcctGroupRow.put("client_functional_acct_group_id",map.get("client_functional_acct_group_id"));
        funcAcctGroupArray.put(funcAcctGroupRow);
        funcAcctGroup.put("functional_acct_group_row", funcAcctGroupArray);
        acctRow.put("functional_acct_group",funcAcctGroup);


        //MPI stuff
        JSONObject mpi = new JSONObject();
        JSONArray mpiRow = new JSONArray();
        JSONObject mpiRowObj = new JSONObject();

        for(String mpiField: mpiList)
        {
            mpiRowObj.put(mpiField,map.get(mpiField));
        }


        //Supp Plans go under MPI stuff
        JSONObject suppPlan = new JSONObject();
        JSONArray suppPlanArray = new JSONArray();

        //Supplemental Field Stuff
        JSONObject suppFields = new JSONObject();
        JSONArray suppFieldsArray = new JSONArray();

        //Get the number of times supp_client_plan_id appears
        for(String key: map.keySet())
        {
            if(key.contains("supp_parent_client_plan_id"))
            {
                //Loops through and add all the supp field names and values
                JSONArray planSuppFieldsArray = new JSONArray();
                JSONObject planSuppFieldRow = new JSONObject();
                JSONObject suppPlanRow = new JSONObject();
                String suppKeyIndex = key.substring(key.length()-3);

                for(String suppKey: map.keySet())
                {

                    if(suppKey.contains("plan_supp_field_name"))
                    {
                        JSONObject planSuppField = new JSONObject();
                        String currentSuppKey = suppKey;
                        String propSuppKeyInd = currentSuppKey.substring(currentSuppKey.length()-3);
                        String currentSuppFieldValueKey = "plan_supp_field_value"+propSuppKeyInd;
                        planSuppField.put("field_name",map.get(currentSuppKey));
                        planSuppField.put("field_value",map.get(currentSuppFieldValueKey));
                        planSuppFieldsArray.put(planSuppField);

                    }
                    //loop through and get the additional
                    if(suppKey.contains("supp_parent_supp_field_name"+suppKeyIndex))
                    {
                        System.out.println("SUPPKEYCONTAINS: "+suppKey+" || INDEX: "+suppKeyIndex);
                        JSONObject addlFieldObj = new JSONObject();
                        //Add the key and value to the current plan supp field Array;
                        String addlKeyIndex = suppKey.substring(suppKey.length()-6);
                        String field = map.get(suppKey);
                        String value = map.get("supp_parent_supp_field_value"+addlKeyIndex);
                        try{

                            if(value.equals(null))
                            {
                                System.out.println("null value found");
                            }
                            addlFieldObj.put("field_name",field);
                            addlFieldObj.put("field_value",value);
                            planSuppFieldsArray.put(addlFieldObj);
                        }
                        catch(NullPointerException e)
                        {
                            System.out.println("No Value found for: "+field);

                        }

                    }
                }


                planSuppFieldRow.put("plan_instance_fields_info_row",planSuppFieldsArray);
                suppPlanRow.put("plan_instance_fields_info",planSuppFieldRow);
                //System.out.println("Adding supp plan! "+map.get(key));

                //if its the only parent supp plan, then reference it and save the index somehow
                String currentSuppPlan = map.get(key);
                if(currentSuppPlan.trim().equals(onlyParentSuppPlan))
                {
                    //System.out.println("Found parent supp plan");
                    JSONObject childSuppPlanObject = new JSONObject();
                    JSONArray childArray = new JSONArray();
                    for(String childKey: map.keySet())
                    {
                        if(childKey.contains("child_supp_client_plan_id"))
                        {
                            //Get the only parent plan Id from the stored reference
                            //add all the children to a sub row
                            JSONObject actualChildPlan = new JSONObject();

                            actualChildPlan.put("client_plan_id",map.get(childKey));
                            actualChildPlan.put("plan_instance_units","1");
                            actualChildPlan.put("plan_instance_fields_info",planSuppFieldRow);

                            //System.out.println("Adding Child Supp Plan: "+actualChildPlan);
                            childArray.put(actualChildPlan);


                        }
                    }
                    //System.out.println("Adding arraysize: "+childArray.length());
                    if(childArray.length() >=1)
                    {
                        childSuppPlanObject.put("supp_plan_row",childArray);
                        suppPlanRow.put("supp_plan",childSuppPlanObject);
                    }

                }






                String indexNum = key.substring(key.length()-3);
                String correspondingInstKey = "supp_parent_plan_instance_units"+indexNum;
                String planInstanceId = "supp_parent_client_plan_instance_id"+indexNum;
                System.out.println("ADDING supp_parent_client_plan_instance_id: "+planInstanceId+" | "+ map.get(planInstanceId));
                suppPlanRow.put("client_plan_id", map.get(key));
                suppPlanRow.put("plan_instance_units", map.get(correspondingInstKey));
                suppPlanRow.put("client_plan_instance_id",map.get(planInstanceId));
                suppPlanArray.put(suppPlanRow);
            }


            //find number of supp fields and add them to base acctRow
            if(key.contains("supp_field_name") && !(key.contains("plan")) && !(key.contains("parent")))
            {
                String sFieldIndex = key.substring(key.length()-3);
                String correspondingValueKey = "acct_supp_field_value"+sFieldIndex;
                JSONObject suppFieldRow = new JSONObject();
                try{
                    String mapValue = map.get(correspondingValueKey);
                    if(mapValue.equals("null"))
                    {

                    }
                    suppFieldRow.put("supp_field_name",map.get(key));
                    suppFieldRow.put("supp_field_value",mapValue);
                    suppFieldsArray.put(suppFieldRow);
                }
                catch(NullPointerException e)
                {
                    System.out.println("No Value found for "+map.get(key));
                }




            }

        }

        suppFields.put("supp_field_row",suppFieldsArray);
        acctRow.put("supp_field",suppFields);

        if(suppPlanArray.length() >=1)
        {
            suppPlan.put("supp_plan_row",suppPlanArray);
            mpiRowObj.put("supp_plan",suppPlan);
        }





        //Billing group stuff
        JSONObject billingGroup = new JSONObject();
        JSONArray billingGroupArray = new JSONArray();
        JSONObject billingGroupRow = new JSONObject();

        billingGroupRow.put("billing_group_idx", map.get("billing_group_idx"));
        billingGroupRow.put("billing_group_name", map.get("billing_group_name"));
        billingGroupRow.put("billing_group_description",  map.get("billing_group_description"));
        billingGroupRow.put("notify_method", Integer.valueOf(map.get("notify_method")));
        billingGroupRow.put("primary_payment_method_idx", map.get("payment_method_idx"));
        billingGroupRow.put("statement_template", map.get("statement_template"));
//        billingGroupRow.put("payment_terms_name", map.get("payment_terms_name"));

        for(String billingKey : billingContactList)
        {
            billingKey = billingKey.replace("billing_","");
            billingGroupRow.put(billingKey,map.get(billingKey));
        }
//        billingGroupRow.put("primary_client_payment_method_id", "null");

        //TODO: Add the following fields based on env data
        /*
            notify_template_group: (client_group_id) under notification template group has to match Func acct group id
            all contact info: matches billing ifo
         */

        billingGroupArray.put(billingGroupRow);
        billingGroup.put("billing_group_row", billingGroupArray);
        acctRow.put("billing_group", billingGroup);

        //Payment Method Stuff
        JSONObject paymentMethod = new JSONObject();
        JSONArray paymentMethodArray = new JSONArray();
        JSONObject paymentMethodRow = new JSONObject();

        paymentMethodRow.put("pay_method_type", map.get("pay_method_type"));
        paymentMethodRow.put("pay_method_name", map.get("pay_method_name"));
        paymentMethodRow.put("pay_method_description", map.get("pay_method_description"));
        paymentMethodRow.put("payment_method_idx", map.get("payment_method_idx"));
//        paymentMethodRow.put("client_pay_method_cdid", "NET_30_Joe");

        for(String pymtKey: billingContactList)
        {
            pymtKey = pymtKey.replace("billing_","");
            paymentMethodRow.put(pymtKey,map.get(pymtKey));
        }

        paymentMethodArray.put(paymentMethodRow);
        paymentMethod.put("payment_method_row", paymentMethodArray);
        acctRow.put("payment_method",paymentMethod);



        //Dunning group stuff
        JSONObject dunningGroup = new JSONObject();
        JSONArray dunningGroupArray = new JSONArray();
        JSONObject dunningGroupRow = new JSONObject();

        dunningGroupRow.put("dunning_group_idx", map.get("dunning_group_idx"));
        dunningGroupRow.put("dunning_group_name", "dunn group name");
        dunningGroupRow.put("dunning_group_description", "dunn group desc");

        dunningGroupArray.put(dunningGroupRow);
        dunningGroup.put("dunning_group_row", dunningGroupArray);
        acctRow.put("dunning_group",dunningGroup);



        mpiRow.put(mpiRowObj);
        mpi.put("master_plans_detail_row",mpiRow);
        acctRow.put("master_plans_detail",mpi);

        acctArray.put(acctRow);
        JSONObject acctObj = new JSONObject();
        acctObj.put("acct_row",acctArray);


        jo.put("acct",acctObj);



    }

    public JSONObject getJSONObject()
    {
        return jo;
    }

}
