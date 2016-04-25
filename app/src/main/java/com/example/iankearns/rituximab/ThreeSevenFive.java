package com.example.iankearns.rituximab;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.*;


public class ThreeSevenFive extends Activity {

    private EditText inputPatientWeight;
    private EditText inputPatientHeight;

    private long BMI;//body mass index
    private double BSA;//body surface area
    private double DOSE;//dose required by the patient
    private double VOLUME;//convert dose which is in milligrams to volume(mls) by dividing by 10 as there is 10mg of drug per ml
    private double RATE;//rate of administration, increases every half hour
    private double patientsWeight = 0.0;
    private double patientsHeight = 0.0;
    //Context context;

    private TextView bmiResult;
    private TextView bsaResult;
    private TextView doseResult;
    private TextView volumeResult;
    private TextView zeroFirst;
    private TextView zeroRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_seven_five);


        inputPatientWeight = (EditText) findViewById(R.id.patientWeightInput);//inflate the imput weight field
        inputPatientHeight = (EditText) findViewById(R.id.patientHeightInput);//inflate the input height field

        Button threeCalulateBtn = (Button) findViewById(R.id.threeCalBtn);//inflate the calculate button
        threeCalulateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputPatientWeight.getText().toString();
                inputPatientHeight.getText().toString();

                try {
                    if(inputPatientWeight.getText().length()> 0){//checks to see if weight field is empty
                            patientsWeight = Double.parseDouble(inputPatientWeight.getText().toString());//if field not empty turn string into double
                    } else{
                        Toast.makeText(getApplicationContext(), "Error: You didn't enter a weight!", Toast.LENGTH_SHORT).show();
                    }//if weight field is empty then a toast is displayed to remind the user to input a weight



                    if(inputPatientHeight.getText().length()> 0){
                        patientsHeight = Double.parseDouble(inputPatientHeight.getText().toString());
                    } else{
                        Toast.makeText(getApplicationContext(), "Error: You didn't enter a height!", Toast.LENGTH_SHORT).show();
                    }
                    //System.out.println("test");
                    if(patientsHeight != 0 && patientsWeight != 0) {//this checks to make sure both fields have values entered before starting calculator
                        startCalculator();
                    }
                } catch (NumberFormatException e) {
                }


            }

        });

    }

    private void startCalculator(){
        bmiResult = (TextView) findViewById(R.id.bmi);//inflates the bmi textfield
        BMI = round((double) patientsWeight / (patientsHeight * patientsHeight));//carries out calculation of weight divided by height squared
        bmiResult.setText("BMI: "+BMI+" kg/m2");//display result of calculation in the bmiResult textView

        bsaResult = (TextView) findViewById(R.id.bsa);
        BSA = sqrt(patientsWeight * ((patientsHeight * 100) / 3600));//height must be  in cm for this formula
        bsaResult.setText("BSA: "+String.format("%.2f",BSA)+" m2");

        doseResult = (TextView) findViewById(R.id.dose);//inflates the dose textfield
        DOSE = 375 * BSA;
        doseResult.setText("Dose: "+String.valueOf(round(DOSE))+"mg");// "+String.valueOf(round(DOSE/10))+" ml");

        volumeResult = (TextView) findViewById(R.id.volume);
        VOLUME = round((round(DOSE))/10);//drug is added to 500ml bag of Normal Saline
        volumeResult.setText("Vol:"+VOLUME+"ml");

        RATE = round(50/((round(DOSE))/(VOLUME+500)));

        zeroFirst = (TextView) findViewById(R.id.zeroFirst);
        zeroFirst.setText(RATE+"ml/hr");

        zeroRepeat = (TextView) findViewById(R.id.zeroRepeat);
        zeroRepeat.setText((RATE*2)+"ml/hr");
        /*rateResult = (TextView) findViewById(R.id.rate);//inflates the rate textfield
        RATE = 50/((round(DOSE))/FINALVOLUME);
        rateResult.setText("0-30 mins "+String.format("%.2f",RATE)+"ml/hr");
        */


    }

}
