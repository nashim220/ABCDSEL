package utility.MarketingInfo;

import java.net.URLEncoder;

/**
 * Created by joe on 4/5/16.
 */
public class Coupon
{
    private String couponCode;
    private String convertedCouponCode;
    private DiscountRule discountRule;
    private String couponDescription;
    private String couponMessage;
    private String couponStatus;
    private String numOfUses;
    private String startDate;
    private String endDate;

    public Coupon()
    {
        couponCode = "";
        couponDescription = "";
        couponMessage = "";
        couponStatus = "";
        numOfUses = "";
        startDate = "";
        endDate = "";
    }

    public String getCouponCode()
    {
        return couponCode;
    }

    public void setCouponCode(String couponCode)
    {
        this.couponCode = couponCode;
        this.convertedCouponCode = couponCode.replaceAll("%","%25").replaceAll(" ","%20");
    }

    public void print()
    {
        System.out.println("~~~~~~~~~~~~~Coupon~~~~~~~~~~~~~");
        System.out.println("Coupon Code: "+this.couponCode);
        if(this.discountRule != null)
        {
            this.discountRule.print();
        }

    }

    public String getConvertedCouponCode()
    {
        try
        {
            return URLEncoder.encode(this.couponCode, "UTF-8");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return this.couponCode;
    }

    public String getCouponStatus()
    {
        return couponStatus;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getNumOfUses()
    {
        return numOfUses;
    }

    public void setNumOfUses(String numOfUses)
    {
        this.numOfUses = numOfUses;
    }

    public void setCouponStatus(String couponStatus)
    {
        this.couponStatus = couponStatus;
    }

    public String getCouponDescription()
    {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription)
    {
        this.couponDescription = couponDescription;
    }

    public String getCouponMessage()
    {
        return couponMessage;
    }

    public void setCouponMessage(String couponMessage)
    {
        this.couponMessage = couponMessage;
    }

    public DiscountRule getDiscountRule()
    {
        return discountRule;
    }

    public void setDiscountRule(DiscountRule discountRule)
    {
        this.discountRule = discountRule;
    }

    public void setConvertedCouponCode(String convertedCouponCode)
    {
        this.convertedCouponCode = convertedCouponCode;
    }
}
