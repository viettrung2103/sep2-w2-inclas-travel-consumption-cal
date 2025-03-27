package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class FuelController {

    @FXML
    private Button enBtn;

    @FXML
    private Button faBtn;

    @FXML
    private Button frBtn;

    @FXML
    private Button calculateBtn;

    @FXML
    private Button jaBtn;

    @FXML
    private Label lblDistance;

    @FXML
    private Label lblFuel;

    @FXML
    private Label lblResult;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField tfDistance;

    @FXML
    private TextField tfFuel;

    ResourceBundle rb;
    double result;

    @FXML
    public void initialize() {
        setLanguage(new Locale("en", "US"));
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat dateFormat = getTheCurrentLocaleDateTimeFormatString();
        String localeTimeString = getCurrentTimeInLocaleLanguage();
        System.out.println(localeTimeString);
    }

    @FXML
    void enBtnClick() {
        setLanguage(new Locale("en", "US"));
        System.out.println(getCurrentTimeInLocaleLanguage());
    }

    @FXML
    void faBtnClick() {
        setLanguage(new Locale("fa", "IR"));
        System.out.println(getCurrentTimeInLocaleLanguage());
    }

    @FXML
    void frBtnClick() {
        setLanguage(new Locale("fr", "FR"));
        System.out.println(getCurrentTimeInLocaleLanguage());
    }

    @FXML
    void jaBtnClick() {
        setLanguage(new Locale("ja", "JP"));
        System.out.println(getCurrentTimeInLocaleLanguage());
    }

    @FXML
    void calculateBtnClick() {
        try{
            double distance = Double.parseDouble(tfDistance.getText());
            double fuel = Double.parseDouble(tfFuel.getText());
            if (distance <= 0 || fuel <= 0) {
                lblResult.setText(rb.getString("error"));
                return;
            }
            double consumption = (fuel / distance) * 100;
            this.result = consumption;
            DecimalFormat df = new DecimalFormat("#0.00");
            String formattedConsumption = df.format(consumption);

//            String outputPrice = MessageFormat.format(rb.getString("result.label"),String.format("%.2f", consumption));
//
////            lblResult.setText(rb.getString("result.label") + ": " + formattedConsumption);
//            lblResult.setText(outputPrice);

            displayResult();
        } catch (NumberFormatException e) {
            lblResult.setText(rb.getString("invalid.input"));
        } catch (Exception e) {
            lblResult.setText(rb.getString("invalid.input"));
            e.printStackTrace();
        }
    }


    private void setLanguage(Locale locale) {
//        lblResult.setText("");
        try {
            rb = ResourceBundle.getBundle("messages", locale);
            lblDistance.setText(rb.getString("distance.label"));
            lblFuel.setText(rb.getString("fuel.label"));
            calculateBtn.setText(rb.getString("calculate.button"));
            lblTitle.setText(rb.getString("title.label"));
            enBtn.setText(rb.getString("english"));
            faBtn.setText(rb.getString("persian"));
            frBtn.setText(rb.getString("french"));
            jaBtn.setText(rb.getString("japanese"));
            displayResult();


//            if (lblResult.getText().equals("")) {
//                DecimalFormat df = new DecimalFormat("#0.00");
//                String formattedBMI = df.format(bmi);
//                lblResult.setText(rb.getString("result") + ": "+formattedBMI);
//            }
        }catch(MissingResourceException e) {
            e.printStackTrace();
            lblResult.setText("Error loading resources error");
        }

    }

    static SimpleDateFormat getTheCurrentLocaleDateTimeFormatString() {
        return (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
    }

    static String getCurrentTimeInLocaleLanguage() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = getTheCurrentLocaleDateTimeFormatString();
        return dateFormat.format(calendar.getTimeInMillis());
    }

    public void displayResult(){
        if (!lblResult.equals("")){

            String outputPrice = MessageFormat.format(rb.getString("result.label"),String.format("%.2f", this.result));

//            lblResult.setText(rb.getString("result.label") + ": " + formattedConsumption);
            lblResult.setText(outputPrice);
        }

    }

}
