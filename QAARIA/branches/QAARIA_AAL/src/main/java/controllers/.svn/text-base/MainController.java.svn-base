package controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import jdk.nashorn.internal.objects.Global;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import utility.*;
import utility.RateHandlers.*;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MainController implements Initializable
{
    private Logger UILogger;
    private String baseURL = "https://secure.future.stage.ariasystems.net/api/ws/api_ws_class_dispatcher.php?";
    private String adminURL = "https://admintools.future.stage.ariasystems.net/api/ws/api_ws_class_dispatcher.php?";
    private String uatURL =  "https://secure.current.stage.ariasystems.net/api/ws/api_ws_class_dispatcher.php?";
    private String activeTab = "api";
    //@FXML private ScenariosController scenariosTabPage;
    @FXML
    private DatePicker usageDatePicker;
    @FXML
    private Button sendButton,chooseFileButton,scenarioDataBtn,recordUsageBtn,deactivateBtn;
    @FXML
    private TextField authKeyField,filePathText,clientNumField,userIdTextField,scenarioDataTxt,recordUsageTxt,baseAcctNameTxt,deactivateTxtField,quickUsageAcctNum,usageUnitsTextField;
    @FXML
    private ComboBox<String> apiComboBox,scenarioDropDown,usageTypeCombo;
    @FXML
    private javafx.scene.control.TextArea outputTextArea,scenarioOutputTextArea,quickUsageOutputArea;
    @FXML
    private Label userIdLabel;
    @FXML private Tab scenarioTab,apiTab,usageTab;
    @FXML private CheckBox immortalAcctCb, uatCheckBox;
    @FXML private ProgressIndicator progressInd;
    private ApiHandler api = new ApiHandler();
    JsonHandler jHandle = new JsonHandler();
    private boolean UAT_ENABLED = false;
    private Logger log;
    String defaultClientId;
    String defaultAuthKey;
    Properties properties;


    /*
        Initialization for the UI
     */
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        GlobalProperties.environment = ".";

        JsonHandler jHandle = new JsonHandler();
        //Construct Logger for UI
        UILogger = this.constructUILogger();
        //Setting up properties file and default values;
        properties = new Properties();
        String currPath = System.getProperty("user.dir");
        UILogger.info("Current Base Directory: "+currPath);
        try{
            properties.load(new FileInputStream(currPath+ "/src/Properties/properties.props"));
            defaultClientId = properties.get("devClientID").toString();
            defaultAuthKey = properties.get("devAuthKey").toString();
        }catch(Exception e){
            defaultClientId = "";
            defaultAuthKey = "";
            //UILogger.info("Error loading props file");
            e.printStackTrace();
        }
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


        if(UAT_ENABLED)
        {
            baseURL = uatURL;
        }

        clientNumField.setText(defaultClientId);
        authKeyField.setText(defaultAuthKey);

        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'Main.fxml'.";
        userIdLabel.setVisible(false);
        userIdTextField.setVisible(false);
        progressInd.setVisible(false);
        this.populateDropDowns();
        runApi();

        /*
            Action: On Tab Selection
         */
        scenarioTab.setOnSelectionChanged(new EventHandler<javafx.event.Event>()
        {
            @Override
            public void handle(Event event)
            {
                if(scenarioTab.isSelected())
                {
                    deactivateBtn.setVisible(false);
                    deactivateTxtField.setVisible(false);
                    runScenario();
                }

            }
        });
        apiTab.setOnSelectionChanged(new EventHandler<Event>()
        {
            @Override
            public void handle(Event event)
            {

                runApi();
            }
        });
        usageTab.setOnSelectionChanged(new EventHandler<Event>()
        {
            @Override
            public void handle(Event event)
            {
                populateUsageDropDown();
                runQuickUsage();
            }
        });
        chooseFileButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String fileDirectory = runFileChooser(chooseFileButton);
                filePathText.setText(fileDirectory);

            }
        });



    }
    /*
        Populates Quick Usage Drop Down
     */
    private void populateUsageDropDown()
    {
        usageTypeCombo.getItems().clear();
        usageTypeCombo.getItems().addAll("Windows_Server_2008_R2","IPV6_Usage","vCPU","MS_SQL_Server_Standard");
    }
    /*
        Populates the Scenario and api DropDowns
     */
    private void populateDropDowns()
    {
        //get dropdowns from file
        apiComboBox.getItems().clear();
        String fullCalls = properties.getProperty("apiCalls").toString();
        for(String call: fullCalls.split(","))
        {
            apiComboBox.getItems().add(call);
        }
//        apiComboBox.getItems().addAll("get_acct_details_all", "create_acct_complete", "update_acct_complete", "record_usage", "assign_supp_plan", "cancel_supp_plan","get_service_details","get_acct_plan_unit_instance_all","set_session","gen_invoice","manage_pending_invoice", "settle_account_balance", "update_acct_supp_fields","record_external_payment", "update_acct_plan_unit_instance","cancel_supp_plan");
        scenarioDropDown.getItems().clear();
        scenarioDropDown.getItems().addAll("Regression 1","Regression 2", "Regression 3","Billing & AR Report", "Create Account with CC","Deactivate Accounts", "Get All Plans", "Get Plans EOM", "Get Plan Config EOM", "Get Plan Config", "EOM Marketing Info");

    }
    /*
        Scenario Tab Execution
     */
    private void runScenario()
    {
        scenarioDataBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String filePath = runFileChooser(scenarioDataBtn);
                scenarioDataTxt.setText(filePath);

            }
        });

        recordUsageBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String filePath = runFileChooser(recordUsageBtn);
                recordUsageTxt.setText(filePath);
            }
        });

        sendButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String selectedScenario = scenarioDropDown.getValue();
                int scenarioIndex = scenarioDropDown.getSelectionModel().getSelectedIndex();
                scenarioOutputTextArea.appendText("Starting scenario '"+selectedScenario+"'....\n");
                System.out.println("Index Selected: "+scenarioIndex);
                runSpecificScenario(scenarioIndex);
            }
        });
        scenarioDropDown.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String selectedScenario = scenarioDropDown.getValue();
                int scenarioIndex = scenarioDropDown.getSelectionModel().getSelectedIndex();
                System.out.println("Index Selected: "+scenarioIndex);

                if(scenarioDropDown.getValue().toString().equals("Deactivate Accounts"))
                {
                    deactivateTxtField.setVisible(true);
                    deactivateBtn.setVisible(true);
                }
                else
                {
                    deactivateBtn.setVisible(false);
                    deactivateTxtField.setVisible(false);
                }
            }
        });
        deactivateBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String filePath = runFileChooser(deactivateBtn);
                deactivateTxtField.setText(filePath);
            }
        });


    }
    /*
        Quick Usage Execution
     */
    private void runQuickUsage()
    {
        //UILogger.info("Usage Tab Selected");
        sendButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String usageAcctNum = quickUsageAcctNum.getText();
                String usageAmount = usageUnitsTextField.getText();
                String selectedUsageType = usageTypeCombo.getValue();
                String clientNo = clientNumField.getText();
                String authKey = authKeyField.getText();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String usageDate = usageDatePicker.getValue().format(dateFormat);
//                quickUsageOutputArea.appendText("Date: "+usageDate);

                String fullUrl = baseURL+"client_no="+clientNo+"&auth_key="+authKey+"&acct_no="+usageAcctNum+"&rest_call=record_usage&usage_type_code="+selectedUsageType.toString()+"&usage_units="+usageAmount+"&usage_date="+usageDate+"&output_format=json";
                quickUsageOutputArea.appendText("Making call: "+fullUrl+"\n");
                try
                {
                    quickUsageOutputArea.appendText(api.makeSimplePostCall(fullUrl));
                }
                catch(Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    quickUsageOutputArea.appendText(e.toString());
                    alert.showAndWait();
                }

            }
        });

    }
    private void runApi()
    {
        sendButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                outputTextArea.clear();
                String authKey = "";
                String clientNum ="";
                String restCall = "";
                progressInd.setVisible(true);
                try{
                    Thread.sleep(150);
                }
                catch(Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                    //e.printStackTrace();
                }
                if(apiTab.isSelected())
                {
                    if(authKeyField.getText().trim().equals("") || clientNumField.getText().trim().equals("") || filePathText.getText().trim().equals("") && !(apiComboBox.getValue().toString().equals("get_acct_details_all")))
                    {
//                        Alert alert = new Alert(Alert.AlertType.WARNING);
//                        alert.setTitle("Missing Required Fields");
//                        alert.setHeaderText("Missing Required Fields!");
//                        alert.setContentText("Please verify Authorization Key, Client Number, File Location, and API Method are defined!");
//
//                        alert.showAndWait();
                    }
                    else
                    {
                        authKey = authKeyField.getText();
                        clientNum = clientNumField.getText();
                        restCall = apiComboBox.getValue();
                        String outputPath = filePathText.getText();


                        if(restCall.equals("create_acct_complete_m"))
                        {
                            System.out.println("Creating new JSON acct");
                            ArrayList<HashMap<String,String>> fullMapList = XlsHandler.new_readXlsxFile(filePathText.getText());
                            ArrayList<JSONObject> joList = new ArrayList<JSONObject>();
                            ArrayList<JSONObject> callList = new ArrayList<JSONObject>();
                            for(HashMap<String,String> map : fullMapList)
                            {

                                JsonAcct jAcct = new JsonAcct(map,clientNum,authKey);
                                JSONObject jo = jAcct.getJSONObject();
                                System.out.println("Making call with: "+jo);
                                callList.add(jo);
                                try
                                {
                                    StringEntity ent = new StringEntity(jo.toString());
                                    String response = api.makeSimplePostWithJson(baseURL, ent);
                                    joList.add(new JSONObject(response));


                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }

                                String oPath = XlsHandler.outputToXlsx(joList,outputPath);
//                                for(JSONObject r : joList)
//                                {
//                                    if(r.get("error_msg").equals("OK"))
//                                    {
//                                        outputTextArea.appendText("Acct: "+r.get("acct_no")+ " || "+ callList.get(joList.indexOf(r)));
//                                    }
//                                    else
//                                    {
//                                        outputTextArea.appendText("Acct: "+r.get("error_msg")+ " || "+ callList.get(joList.indexOf(r)));
//                                    }
//                                }
                                outputTextArea.appendText("File saved to: "+oPath);


                            }
                            //JsonAcct jsonAcct = new JsonAcct(fullMapList.get(0));
                            //JSONObject jo = jsonAcct.getJSONObject();
                            //System.out.println("Making call with: "+jo);
//                            DataGenerator.saveJsonAcct(jsonAcct);




                        }
                        else if(restCall.contains("get_acct_details_all"))
                        {

                            String acctNum = userIdTextField.getText();
                            String fullUrl = baseURL + "client_no=" + clientNum + "&auth_key=" + authKey + "&rest_call=" + restCall + "&acct_no=" + acctNum + "&output_format=json";
                            System.out.println(fullUrl);
                            JSONObject response = api.makeGetCall(fullUrl);
                            outputTextArea.appendText(response.toString());

                        }
                        else if(restCall.equals("get_service_details"))
                        {

                            new CsvHandler().readCSV(filePathText.getText(),outputTextArea);
                            //String fullUrl = "https://admintools.future.stage.ariasystems.net/AdminTools.php/Dispatcher?" + "client_no=" + clientNum + "&auth_key=" + authKey + "&rest_call=" + restCall;
                            //JSONObject response = api.makeMultiGetCall(fullUrl,outputTextArea,filePathText.getText());
                        }
                        else if(restCall.equals("get_acct_plan_unit_instance_all"))
                        {
                            String fullUrl = baseURL + "client_no=" + clientNum + "&auth_key=" + authKey + "&rest_call=" + restCall;
                            api.makeMultiGetCall(fullUrl,outputTextArea,filePathText.getText(),restCall);
                        }
                        else
                        {
                            restCall = apiComboBox.getValue();
                            String fullUrl = baseURL + "client_no=" + clientNum + "&auth_key=" + authKey + "&rest_call=" + restCall;
                            outputTextArea.appendText("Making call: "+fullUrl+"\n");
                            final String finalRestCall = restCall;
                            final String finalPath = filePathText.getText();
//                            JSONObject response = api.makePostCall(fullUrl,filePathText.getText(),outputTextArea,restCall);

                            Platform.runLater(() -> api.makePostCall(fullUrl,filePathText.getText(),outputTextArea,finalRestCall, progressInd));

                        }

                        //clean up fields
//                        filePathText.clear();
                    }
                }
                else
                {
                    runScenario();
                }



            }
        });

        chooseFileButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {

                String fileDirectory = runFileChooser(chooseFileButton);
                //log.info("Setting file directory: "+fileDirectory);
                filePathText.setText(fileDirectory);
                outputTextArea.clear();
            }
        });

        apiComboBox.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String apiValue = apiComboBox.getValue();

                if(apiComboBox.getValue().toString().contains("create_acct_complete"))
                {
                    userIdLabel.setVisible(true);
                    userIdTextField.setVisible(true);
                }
                if(apiValue.contains("get_acct_details_all"))
                {
                    userIdLabel.setText("Acct No:");
                    userIdTextField.setVisible(true);
                    userIdLabel.setVisible(true);
                }
                else
                {
                    userIdLabel.setVisible(false);
                    userIdTextField.setVisible(false);
                }
            }
        });

        uatCheckBox.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if(uatCheckBox.isSelected())
                {
                    GlobalProperties.environment = ".current.stage.";
                    System.out.println("Environment switched to: "+GlobalProperties.environment);
                    UAT_ENABLED = true;
                    baseURL = uatURL;
                    clientNumField.clear();
                    clientNumField.setText(properties.get("uatClientID").toString());
                    authKeyField.clear();
                    authKeyField.setText(properties.get("uatAuthKey").toString());
                }
                else
                {
                    GlobalProperties.environment = ".future.stage.";
                    UAT_ENABLED = false;
                    baseURL = "https://secure.future.stage.ariasystems.net/api/ws/api_ws_class_dispatcher.php?";
                    clientNumField.clear();
                    clientNumField.setText(defaultClientId);
                    authKeyField.clear();
                    authKeyField.setText(defaultAuthKey);
                    System.out.println("Environment switched to: "+GlobalProperties.environment);
                }
            }
        });

    }
    private String runFileChooser(Button btn)
    {
        FileChooser fileChooser = new FileChooser();
        filePathText.clear();
        //UILogger.info("Loading file chooser");
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv","*.csv");
//        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Select Data File to Upload");
        File selectedFile = fileChooser.showOpenDialog(btn.getScene().getWindow());
        String fileDirectory = selectedFile.getAbsolutePath();
        return fileDirectory;

    }
    private void runSpecificScenario(int index)
    {

            switch(index)
            {
                case 3 :
                    try
                    {
                        Scenario ccScenario = new Scenario(baseAcctNameTxt.getText(),9,"DataFiles/create_acct_with_cc.csv",scenarioOutputTextArea,baseURL);
                        ccScenario.executeScenario(baseURL);
                        break;
                        //String combinedUrl = baseURL+"client_no="+clientNumField.getText()+"&auth_key="+authKeyField.getText()+"&rest_call=create_acct_complete";
                        //api.makePostCall(combinedUrl,"DataFiles/create_acct_master.csv",scenarioOutputTextArea,"create_acct_complete");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        break;
                    }
                case 6:
                    RateHandler rHandler = new RateHandler();
                    new XlsHandler().outputEomToXlsx(rHandler.readRates(clientNumField.getText(),authKeyField.getText(),false),baseAcctNameTxt.getText());
                  break;
                case 7:
                    String clientId = clientNumField.getText();
                    String authKey = authKeyField.getText();
                    rHandler = new RateHandler();
                    new XlsHandler().outputEomToXlsx(rHandler.readRates(clientId,authKey,true),baseAcctNameTxt.getText());
                    break;
                case 8:
                    RateHandler rHandle = new RateHandler();
                    clientId = clientNumField.getText();
                    authKey = authKeyField.getText();
                    new XlsHandler().outputEomPlanConfig(rHandle.readPlanConfig(clientId, authKey,true), baseAcctNameTxt.getText());
                    break;
                case 9:
                    rHandle = new RateHandler();
                    System.out.println("This will be the plan config for A6");
                    break;
                case 10:
                    clientId = clientNumField.getText();
                    authKey = authKeyField.getText();
                    rHandle = new RateHandler();
                    rHandle.readMarketingInfo(clientId,authKey,baseAcctNameTxt.getText(),true);
                }
    }

    public void updateText(final String textToOutput)
    {
        Task task = new Task<Void>()
        {

            @Override
            public Void call() throws Exception
            {
                final ApiHandler api = new ApiHandler();
                Platform.runLater(new Runnable()
                {

                    @Override
                    public void run()
                    {

                        try {
                            updateMessage("Making Call: " + textToOutput + "\n");
                            updateMessage("This is a test message\n");
                            JSONObject jResponse = api.makeGetCall(textToOutput);
                            updateMessage("Status Label: " + jResponse.get("status_label") + " || Error Msg: " + jResponse.get("error_msg") + "\n\n");
                            //UILogger.info(jResponse.toString() + "\n\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                Thread.sleep(2000);
                return null;
            }


        };

        //outputTextArea.add
        task.messageProperty().addListener((obs, oldMessage, newMessage) -> {
            outputTextArea.appendText(newMessage);
        });

        task.setOnSucceeded(e -> {
            outputTextArea.textProperty().unbind();
        });

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    private Logger constructUILogger()
    {
        //Logger Values
        Logger log  = Logger.getGlobal();

        try
        {
            Handler handler = new FileHandler("UI-Logger.log",true);
            handler.setEncoding("UTF-8");
            handler.setFormatter(new SimpleFormatter());
            log.addHandler(handler);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return log;
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    }
}
