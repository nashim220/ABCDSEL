package utility.RateHandlers;

import java.util.ArrayList;

/**
 * Created by joe on 3/10/16.
 */
public class EomPlan
{
    private String currency;
    private ArrayList<PlanService> planServices;
    private String planDescription;
    private String planName;
    private String clientPlanId;
    private String planNumber;
    public EomPlan()
    {

    }

    public String getCurrency()
    {
        return currency;
    }
    public void printEomPlan()
    {
        System.out.println("Plan Name: "+this.planName);
        System.out.println("Plan Desc: "+this.planDescription);
        System.out.println("Plan Currency: "+this.currency);
        System.out.println("Client Plan ID: "+this.clientPlanId);
        System.out.println("Plan Number: "+this.planNumber);
        System.out.println("~~~~~~~~~~~~~Plan Service~~~~~~~~~~~");
        for(PlanService p : this.planServices)
        {
            System.out.println("\tClient Service ID: "+p.getClientServiceId());
            System.out.println("\tUsage Type Code: "+p.getUsageTypeCode());
            System.out.println("\t~~~~~~~~~~~~~Plan Service Rates~~~~~~~~~~~");
            for(ServiceRate sRate : p.getServiceRates())
            {
                System.out.println("\t\tRate Client ID: "+sRate.getClientRateScheduleId());
                System.out.println("\t\tRate Per Unit: "+sRate.getRatePerUnit());
                System.out.println("\t\tCurrency: "+sRate.getCurrency());
            }
            System.out.println();

        }
        System.out.println();
    }
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public ArrayList<PlanService> getPlanServices()
    {
        return planServices;
    }

    public void setPlanServices(ArrayList<PlanService> planServices)
    {
        this.planServices = planServices;
    }

    public String getPlanDescription()
    {
        return planDescription;
    }

    public void setPlanDescription(String planDescription)
    {
        this.planDescription = planDescription;
    }

    public String getPlanName()
    {
        return planName;
    }

    public void setPlanName(String planName)
    {
        this.planName = planName;
    }

    public String getClientPlanId()
    {
        return clientPlanId;
    }

    public void setClientPlanId(String clientPlanId)
    {
        this.clientPlanId = clientPlanId;
    }

    public String getPlanNumber()
    {
        return planNumber;
    }

    public void setPlanNumber(String planNumber)
    {
        this.planNumber = planNumber;
    }
}
