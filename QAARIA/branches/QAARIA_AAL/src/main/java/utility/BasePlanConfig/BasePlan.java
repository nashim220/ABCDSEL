package utility.BasePlanConfig;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by joe on 3/25/16.
 */
public class BasePlan
{
    private HashMap<String,String> baseConfigValues;
    private ArrayList<HashMap<String,String>> planSuppFields;
    private ArrayList<HashMap<String,String>> parentPlans;
    private ArrayList<String> services;
    public BasePlan()
    {
        baseConfigValues = new HashMap<String,String>();
        planSuppFields = new ArrayList<HashMap<String,String>>();
        parentPlans = new ArrayList<HashMap<String,String>>();
        services = new ArrayList<String>();

    }

    public HashMap<String, String> getBaseConfigValues()
    {
        return baseConfigValues;
    }

    public void setBaseConfigValues(HashMap<String, String> baseConfigValues)
    {
        this.baseConfigValues = baseConfigValues;
    }

    public ArrayList<HashMap<String, String>> getPlanSuppFields()
    {
        return planSuppFields;
    }

    public void setPlanSuppFields(ArrayList<HashMap<String, String>> planSuppFields)
    {
        this.planSuppFields = planSuppFields;
    }

    public ArrayList<HashMap<String, String>> getParentPlans()
    {
        return parentPlans;
    }

    public void setParentPlans(ArrayList<HashMap<String, String>> parentPlans)
    {
        this.parentPlans = parentPlans;
    }
    public void addItemToBaseConfig(String key, String value)
    {
        this.baseConfigValues.put(key,value);
    }
    public void addItemToSuppFields(HashMap<String,String> map)
    {
        this.planSuppFields.add(map);
    }
    public void addItemToParentPlans(HashMap<String,String> map)
    {
        this.parentPlans.add(map);
    }

    public String getPlanName()
    {
        return this.baseConfigValues.get("plan_name");
    }
    public void printPlan()
    {
        System.out.println("~~~~~~~~~~~~~~~~"+this.getPlanName()+"BASE CONFIG~~~~~~~~~~~~~~~~~~");
        for(String key: this.baseConfigValues.keySet())
        {
            System.out.println("\tKEY: "+key+" || VALUE: "+this.baseConfigValues.get(key));
        }
        for(HashMap<String,String> map: this.planSuppFields)
        {
            System.out.println("\t~~~~~~~~~~~~~~~~~SUPP PLAN~~~~~~~~~~~~~~~");
            for(String key: map.keySet())
            {
                System.out.println("\t\tKEY: "+key+" || VALUE: "+map.get(key));
            }
        }
        for(HashMap<String,String> map: this.parentPlans)
        {
            System.out.println("\t~~~~~~~~~~~~~~~~~PARENT PLAN~~~~~~~~~~~~~~~");
            for(String key: map.keySet())
            {
                System.out.println("\t\tKEY: "+key+" || VALUE: "+map.get(key));
            }
        }
        for(String id: this.services)
        {
            System.out.println("SERVICE ID: "+id);
        }
        System.out.println();
    }

    public ArrayList<String> getServices()
    {
        return services;
    }

    public void setServices(ArrayList<String> services)
    {
        this.services = services;
    }

    public void addClientServiceId(String id)
    {
        this.services.add(id);
    }
}
