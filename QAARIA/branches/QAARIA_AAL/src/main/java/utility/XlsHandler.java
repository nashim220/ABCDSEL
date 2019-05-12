package utility;

import javafx.util.Pair;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import utility.BasePlanConfig.BasePlan;
import utility.MarketingInfo.Coupon;
import utility.MarketingInfo.DiscountRule;
import utility.RateHandlers.EomPlan;
import utility.RateHandlers.PlanService;
import utility.RateHandlers.ServiceRate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by joe on 9/1/15.
 */
public class XlsHandler
{
    private static Logger log;
    public static ArrayList<HashMap<String,String>> new_readXlsxFile(String filepath)
    {
        ArrayList<HashMap<String,String>> fullList = new ArrayList<HashMap<String,String>>();

        //TODO: Read in the first line as the headers for the hashmap
        try
        {
            FileInputStream file = new FileInputStream(new File(filepath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            HashMap<String,String> blankHeaderMap = new HashMap<String,String>();

            Row headerRow = sheet.getRow(0);
            //System.out.println("HeaderSize: "+headerRow.getPhysicalNumberOfCells());
            for(int x=0;x<headerRow.getPhysicalNumberOfCells();x++)
            {
                blankHeaderMap.put(headerRow.getCell(x).getStringCellValue().trim(),"");
            }
            //System.out.println("blank map size: "+blankHeaderMap.size());
            Iterator<Row> rowIterator = sheet.iterator();
            Row headRow = rowIterator.next();
            while(rowIterator.hasNext())
            {
                HashMap<String,String> currentMap = new HashMap<String,String>();//blankHeaderMap;
                Row currentRow = rowIterator.next();
                for(int x=0; x< blankHeaderMap.size();x++)
                {
//                    System.out.println("column: "+x);
                    String currentValue = "";
                    Cell currentCell = currentRow.getCell(x);

                    if(currentCell == null)
                    {
                        currentValue = "";
                    }
                    else
                    {
                        switch(currentCell.getCellType())
                        {
                            case Cell.CELL_TYPE_BLANK:
                                currentValue = "";
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                currentValue = String.valueOf((int)currentCell.getNumericCellValue());
                                break;
                            default:
                                currentValue = currentCell.getStringCellValue().toString().trim();
                                break;
                        }
                    }





                    String headerValue = headerRow.getCell(x).getStringCellValue().trim();
                    //System.out.println("Header: "+headerValue+" || Value: "+currentValue);
                    currentMap.put(headerValue,currentValue);
                }
                System.out.println("Adding Map: "+currentMap.get("userid"));
                fullList.add(currentMap);
            }
            for(HashMap<String,String> map: fullList)
            {
                System.out.println("&&&&&&&&&&&&&&&&&&&&&map&&&&&&&&&&&&&&&"+ " || "+map.get("userid"));
            }
            return fullList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return fullList;
        }


    }
    public static List<List<NameValuePair>> readXlsxFile(String filepath)
    {
        ApiHandler api = new ApiHandler();
        log = api.getApiLogger();

        List<List<NameValuePair>> allParams = new ArrayList<List<NameValuePair>>();
        boolean headerParsed = false;
        List<String> headers = new ArrayList<String>();
        try
        {
            FileInputStream file = new FileInputStream(new File(filepath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            //log.info("Reading Excel file....");
            while(rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //loop through and set the headers
                if(!headerParsed)
                {
                    //log.info("Parsing Headers");
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while(cellIterator.hasNext())
                    {
                        String value = cellIterator.next().getStringCellValue();
                        headers.add(value);
                        //log.info("Header Found || "+value);
                    }
                    headerParsed = true;
                }
                else
                {

                    Iterator<Cell> cellIterator = row.cellIterator();
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    //log.info("Parsing Values....");
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();
                        switch (cell.getCellType())
                        {

                            case Cell.CELL_TYPE_NUMERIC:
                                //log.info("Numeric Cell Found || "+cell.getNumericCellValue());
                                if(cell.getNumericCellValue() == (int)cell.getNumericCellValue())
                                {
                                    params.add(new BasicNameValuePair(headers.get(cell.getColumnIndex()),(int)cell.getNumericCellValue()+""));
                                }
                                else
                                {
                                    params.add(new BasicNameValuePair(headers.get(cell.getColumnIndex()), cell.getNumericCellValue()+""));
                                }
                                  //.put(headers.get(cell.getColumnIndex()),(int)cell.getNumericCellValue()+"");
                                break;
                            case Cell.CELL_TYPE_STRING:
                                //log.info("String Cell Found || "+cell.getStringCellValue());
                                params.add(new BasicNameValuePair(headers.get(cell.getColumnIndex()), cell.getStringCellValue()));
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                //log.info("Formula Cell Found || "+ cell.getCachedFormulaResultType());
                                //TODO: add dateformat comparator for cells with date formats.
//                                FormulaEvaluator eval = workbook.getCreationHelper().createFormulaEvaluator();
//                                if(DateUtil.isCellDateFormatted(cell))
//                                {
//
//                                }
                                params.add(new BasicNameValuePair(headers.get(cell.getColumnIndex()), cell.getStringCellValue()));
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                break;


                        }
                    }
                    allParams.add(params);
                }

            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return allParams;
    }

    public static String outputToXlsx(List<JSONObject> jsonList, String filepath)
    {
        Path p = Paths.get(filepath);
        String fileName = p.getFileName().toString();
        File myFile = new File("output_"+fileName);
        try
        {
            // Finds the workbook instance for XLSX file
            XSSFWorkbook myWorkBook = new XSSFWorkbook ();


            // Return first sheet from the XLSX workbook
            XSSFSheet mySheet = myWorkBook.createSheet("Sheet 1");
            int rowNum = 0;
            boolean headersParsed = false;
            for(JSONObject json : jsonList)
            {

                int cellNum = 0;
                Iterator<String> keys = json.keys();

                if(!headersParsed)
                {
                    Row headerRow = mySheet.createRow(rowNum);
                    rowNum++;
                    Row firstDataRow = mySheet.createRow(rowNum);
                    while(keys.hasNext())
                    {
                        //write headers to first row
                        String key = keys.next();
                        Cell cell = headerRow.createCell(cellNum);
                        cell.setCellValue((String) key);

                        //write data to second row
                        Cell dataCell = firstDataRow.createCell(cellNum);
                       if(json.get(key) instanceof  Integer)
                        {
                            String value = Integer.toString((Integer) json.get(key));
                            dataCell.setCellValue(value);
                        }else
                        {
                            dataCell.setCellValue(json.get(key).toString());
                        }

                        cellNum++;

                    }
                    headersParsed = true;
                }
                else
                {
                    Row row = mySheet.createRow(rowNum);
                    while(keys.hasNext())
                    {
                        String key = keys.next();
                        Cell cell = row.createCell(cellNum);
                        if(json.get(key) instanceof  Integer)
                        {
                            String value = Integer.toString((Integer) json.get(key));
                            cell.setCellValue(value);
                        }else
                        {
                            cell.setCellValue(json.get(key).toString());
                        }
                        cellNum++;
                    }

                }
                rowNum++;
            }
            FileOutputStream os = new FileOutputStream("output_"+fileName);
            myWorkBook.write(os);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    return myFile.getAbsolutePath();
    }

    public static String outputEomToXlsx(List<EomPlan> eomPlans, String filepath)
    {
        Path p = Paths.get(filepath);
        String fileName = p.getFileName().toString();
        File myFile = new File("output_"+fileName);
        try
        {
            XSSFWorkbook myWorkBook = new XSSFWorkbook ();
            XSSFSheet mySheet = myWorkBook.createSheet("Sheet 1");

            int rowNum = 0;
            //Write headers
            Row headerRow = mySheet.createRow(rowNum);
            headerRow.createCell(0).setCellValue("Plan Name");
            headerRow.createCell(1).setCellValue("Client Plan ID");
            headerRow.createCell(2).setCellValue("Client Service ID");
            headerRow.createCell(3).setCellValue("Usage Type Code");
            headerRow.createCell(4).setCellValue("Rate Client ID");
            headerRow.createCell(5).setCellValue("Rate Per Unit");
            headerRow.createCell(6).setCellValue("Currency");
            headerRow.createCell(7).setCellValue("Usage Unit Label");
            rowNum++;

            for(EomPlan ePlan: eomPlans)
            {
                for(PlanService pService: ePlan.getPlanServices())
                {
                    for(ServiceRate sRate: pService.getServiceRates())
                    {
                        Row currentRow = mySheet.createRow(rowNum);
                        currentRow.createCell(0).setCellValue(ePlan.getPlanName());
                        currentRow.createCell(1).setCellValue(ePlan.getClientPlanId());
                        currentRow.createCell(2).setCellValue(pService.getClientServiceId());
                        currentRow.createCell(3).setCellValue(pService.getUsageTypeCode());
                        currentRow.createCell(4).setCellValue(sRate.getClientRateScheduleId());
                        currentRow.createCell(5).setCellValue(sRate.getRatePerUnit());
                        currentRow.createCell(6).setCellValue(sRate.getCurrency());
                        currentRow.createCell(7).setCellValue(pService.getUsageUnitLabel());
                        rowNum++;
                    }
                }

            }


            FileOutputStream os = new FileOutputStream("output_"+fileName);
            myWorkBook.write(os);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return myFile.getAbsolutePath();
    }

    public static String outputEomPlanConfig(ArrayList<BasePlan>bpList , String filePath)
    {
        Path p = Paths.get(filePath);
        String fileName = p.getFileName().toString();
        File myFile = new File("output_"+fileName);
        ArrayList<String> headers = new ArrayList<String>();

        try
        {
            XSSFWorkbook myWorkBook = new XSSFWorkbook ();
            XSSFSheet sheet = myWorkBook.createSheet("PLANS");


            //Create Header row for plans
            int basePlanCell = 1;
            int rowNum = 1;
            Row headerRow = sheet.createRow(0);
            for(BasePlan bp : bpList)
            {
                headerRow.createCell(basePlanCell).setCellValue(bp.getPlanName());
                basePlanCell++;
            }

            for(String key : bpList.get(0).getBaseConfigValues().keySet())
            {
                Row row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(key);
                String currentKey = row.getCell(0).getStringCellValue();
                int planNum = 1;
                for(BasePlan bp : bpList)
                {
                    String val = bp.getBaseConfigValues().get(currentKey);
                    row.createCell(planNum).setCellValue(val);
                    planNum++;

                }
                rowNum++;
            }

            //Set supp field values
            //this sets the initial row for storing supp fields
            int suppFieldRowNum = bpList.get(0).getBaseConfigValues().size()+2;

            for(String key : bpList.get(1).getPlanSuppFields().get(0).keySet())
            {
                int suppFieldValCell = 1;

//                System.out.println("Key: "+key+" || Value: "+bpList.get(1).getPlanSuppFields().get(0).get(key));
                Row suppFieldRow = sheet.createRow(suppFieldRowNum);
                suppFieldRow.createCell(0).setCellValue(key);
                for(BasePlan bp : bpList)
                {
                    if(bp.getPlanSuppFields().size() > 0)
                    {
                        HashMap<String,String> map = bp.getPlanSuppFields().get(0);
                        suppFieldRow.createCell(suppFieldValCell).setCellValue(map.get(key));

                    }
                    suppFieldValCell++;


                }
                suppFieldRowNum++;

            }

            //Add parent plan relationships
            int pPlanRowNum = suppFieldRowNum+1;
            for(String key: bpList.get(1).getParentPlans().get(0).keySet())
            {
                int pPlanCellNum = 1;
                Row pPlanRow = sheet.createRow(pPlanRowNum);
                pPlanRow.createCell(0).setCellValue(key);
                for(BasePlan bp: bpList)
                {
                    if(bp.getParentPlans().size()> 0)
                    {
                        HashMap<String,String> map = bp.getParentPlans().get(0);
                        pPlanRow.createCell(pPlanCellNum).setCellValue(map.get(key));
                    }
                    pPlanCellNum++;
                }
                pPlanRowNum++;
            }

            //Add plan service IDs
            //Setting max rows
            int maxServices = 0;
            for(BasePlan bp : bpList)
            {
                if(bp.getServices().size() > maxServices)
                {
                    maxServices = bp.getServices().size();
                }
            }

            //create all service rows
//            ArrayList<Row> serviceRows = new ArrayList<Row>();
            for(int i = 0; i < maxServices; i++)
            {
                Row row = sheet.createRow(pPlanRowNum+i+1);
                row.createCell(0).setCellValue("client_service_id");

                for(BasePlan bp: bpList)
                {
                    ArrayList<String> servs = bp.getServices();
                    int bpInd = bpList.indexOf(bp);
//                    System.out.println("BPindex: "+bpInd);
                    if(servs.size()>i)
                    {
                        row.createCell(bpInd+1).setCellValue(servs.get(i));
                    }
//                    System.out.println("bpInd: "+bpInd);
//                    if(rowIndex < servs.size())
//                    {
//                        System.out.println("\tCreating Cell: "+servs.get(rowIndex));
//                        row.createCell(bpList.indexOf(bp)).setCellValue(servs.get(rowIndex));
//                    }

                }

            }



            FileOutputStream os = new FileOutputStream("output_"+fileName);
            myWorkBook.write(os);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return myFile.getAbsolutePath();
    }

    public static String outputEomMarketingInfo(ArrayList<Coupon> coupons,ArrayList<DiscountRule> discountRules, String filePath)
    {

        Path p = Paths.get(filePath);
        String fileName = p.getFileName().toString();
        File myFile = new File("output_"+fileName);
        try
        {
            XSSFWorkbook myWorkBook = new XSSFWorkbook ();
            XSSFSheet sheet = myWorkBook.createSheet("Coupons");
            XSSFSheet drSheet = myWorkBook.createSheet("Discount Rules");

            //Setup rows
            int primaryRowIndex = 0;
            Row headerRow = sheet.createRow(primaryRowIndex);
            primaryRowIndex++;
            Row[] rows = new Row[15];
            for(int i=0;i<rows.length;i++)
            {
                rows[i] = sheet.createRow(primaryRowIndex);
                primaryRowIndex++;
            }
            //Set up row headers
            rows[0].createCell(0).setCellValue("Coupon Code");
            rows[1].createCell(0).setCellValue("Coupon Description");
            rows[2].createCell(0).setCellValue("Coupon Message");
            rows[3].createCell(0).setCellValue("Coupon Status");
            rows[4].createCell(0).setCellValue("Number of Uses");
            rows[5].createCell(0).setCellValue("Start Date");
            rows[6].createCell(0).setCellValue("End Date");
            rows[7].createCell(0).setCellValue("Rule Number");
            rows[8].createCell(0).setCellValue("Rule Name");
            rows[9].createCell(0).setCellValue("Rule ID");
            rows[10].createCell(0).setCellValue("Rule Description");
            rows[11].createCell(0).setCellValue("Rule Discount Type");
            rows[12].createCell(0).setCellValue("Rule Amount");
            rows[13].createCell(0).setCellValue("Rule Duration");
            rows[14].createCell(0).setCellValue("Rule Max Months");



            for(Coupon coup : coupons)
            {
                int colIndex = coupons.indexOf(coup)+1;
                DiscountRule dr = coup.getDiscountRule();
                headerRow.createCell(colIndex).setCellValue(coup.getCouponCode());

                rows[0].createCell(colIndex).setCellValue(coup.getCouponCode());
                rows[1].createCell(colIndex).setCellValue(coup.getCouponDescription());
                rows[2].createCell(colIndex).setCellValue(coup.getCouponMessage());
                rows[3].createCell(colIndex).setCellValue(coup.getCouponStatus());
                rows[4].createCell(colIndex).setCellValue(coup.getNumOfUses());
                rows[5].createCell(colIndex).setCellValue(coup.getStartDate());
                rows[6].createCell(colIndex).setCellValue(coup.getEndDate());
                if(dr != null)
                {
                    rows[7].createCell(colIndex).setCellValue(dr.getRuleNumber());
                    rows[8].createCell(colIndex).setCellValue(dr.getRuleName());
                    rows[9].createCell(colIndex).setCellValue(dr.getRuleId());
                    rows[10].createCell(colIndex).setCellValue(dr.getRuleDesc());
                    rows[11].createCell(colIndex).setCellValue(dr.getDiscountType());
                    rows[12].createCell(colIndex).setCellValue(dr.getDiscountAmt());
                    rows[13].createCell(colIndex).setCellValue(dr.getRuleDurationType());
                    rows[14].createCell(colIndex).setCellValue(dr.getRuleMaxMonths());
                }




            }

            //Create Discount Rules Header Row
            Row drHeaders = drSheet.createRow(0);
            drHeaders.createCell(0).setCellValue("Discount Rule");
            drHeaders.createCell(1).setCellValue("rule_no");
            drHeaders.createCell(2).setCellValue("rule_id");
            drHeaders.createCell(3).setCellValue("description");
            drHeaders.createCell(4).setCellValue("discount_type");
            drHeaders.createCell(5).setCellValue("discount_amt");
            drHeaders.createCell(6).setCellValue("discount_currency");

            for(DiscountRule dr : discountRules)
            {
                int drRowIndex = discountRules.indexOf(dr)+1;
                Row drRow = drSheet.createRow(drRowIndex);
                drRow.createCell(0).setCellValue(dr.getRuleId());
                drRow.createCell(1).setCellValue(dr.getRuleNumber());
                drRow.createCell(2).setCellValue(dr.getRuleId());
                drRow.createCell(3).setCellValue(dr.getRuleDesc());
                drRow.createCell(4).setCellValue(dr.getDiscountType());
                drRow.createCell(5).setCellValue(dr.getDiscountAmt());
                drRow.createCell(6).setCellValue(dr.getRuleCurrency());
            }




            FileOutputStream os = new FileOutputStream("output_"+fileName);
            myWorkBook.write(os);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return myFile.getAbsolutePath();


    }

}
