package com.example.iankearns.rituximab;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioButton dosageRadBtn[] = {(RadioButton) findViewById(R.id.dosageRadBtn0),
                (RadioButton) findViewById(R.id.dosageRadBtn1),
                (RadioButton) findViewById(R.id.dosageRadBtn2),
                (RadioButton) findViewById(R.id.dosageRadBtn3)};

        dosageRadBtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((RadioButton) v).isChecked()) {
                    threeSevenFiveMg();
                }
            }
        });


        Button visitSpc = (Button) findViewById(R.id.mainBtn1);
        visitSpc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent spcWebsite = new Intent(Intent.ACTION_VIEW);
                spcWebsite.setData(Uri.parse("http://www.medicines.ie/medicine/1749/SPC/MabThera+100mg and+500mg+Concentrate+for+Solution+for+Infusion/"));
                startActivity(spcWebsite);
            }
        });
    }

    public void threeSevenFiveMg(){
        Intent launchThreeSevenFive = new Intent(Main.this, ThreeSevenFive.class);
        startActivity(launchThreeSevenFive);
    }
}
