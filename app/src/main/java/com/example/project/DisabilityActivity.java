package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisabilityActivity extends AppCompatActivity {

    static public ArrayList<String> select = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disability);

        Button DisabilityButton = (Button) findViewById(R.id.button_확인);
        RadioButton button_지체장애 = (RadioButton) findViewById(R.id.button_지체장애);
        RadioButton button_뇌병변장애 = (RadioButton) findViewById(R.id.button_뇌병변장애);
        RadioButton button_시각장애 = (RadioButton) findViewById(R.id.button_시각장애);
        RadioButton button_청각장애 = (RadioButton) findViewById(R.id.button_청각장애);
        RadioButton button_언어장애 = (RadioButton) findViewById(R.id.button_언어장애);
        RadioButton button_안면장애 = (RadioButton) findViewById(R.id.button_안면장애);
        RadioButton button_신장장애 = (RadioButton) findViewById(R.id.button_신장장애);
        RadioButton button_심장장애 = (RadioButton) findViewById(R.id.button_심장장애);
        RadioButton button_간장애 = (RadioButton) findViewById(R.id.button_간장애);
        RadioButton button_호흡기장애 = (RadioButton) findViewById(R.id.button_호흡기장애);
        RadioButton button_장루요루장애 = (RadioButton) findViewById(R.id.button_장루요루장애);
        RadioButton button_뇌전증장애 = (RadioButton) findViewById(R.id.button_뇌전증장애);
        RadioButton button_지적장애 = (RadioButton) findViewById(R.id.button_지적장애);
        RadioButton button_자폐성장애 = (RadioButton) findViewById(R.id.button_자폐성장애);
        RadioButton button_정신장애 = (RadioButton) findViewById(R.id.button_정신장애);
        DisabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.clear();
                if(button_지체장애.isChecked()) {
                    select.add((String) button_지체장애.getText());
                }
                if(button_뇌병변장애.isChecked()) {
                    select.add((String) button_뇌병변장애.getText());
                }
                if(button_시각장애.isChecked()) {
                    select.add((String) button_시각장애.getText());
                }
                if(button_청각장애.isChecked()) {
                    select.add((String) button_청각장애.getText());
                }
                if(button_언어장애.isChecked()) {
                    select.add((String) button_언어장애.getText());
                }
                if(button_안면장애.isChecked()) {
                    select.add((String) button_안면장애.getText());
                }
                if(button_신장장애.isChecked()) {
                    select.add((String) button_신장장애.getText());
                }
                if(button_심장장애.isChecked()) {
                    select.add((String) button_심장장애.getText());
                }
                if(button_간장애.isChecked()) {
                    select.add((String) button_간장애.getText());
                }
                if(button_호흡기장애.isChecked()) {
                    select.add((String) button_호흡기장애.getText());
                }
                if(button_장루요루장애.isChecked()) {
                    select.add((String) button_장루요루장애.getText());
                }
                if(button_뇌전증장애.isChecked()) {
                    select.add((String) button_뇌전증장애.getText());
                }
                if(button_지적장애.isChecked()) {
                    select.add((String) button_지적장애.getText());
                }
                if(button_자폐성장애.isChecked()) {
                    select.add((String) button_자폐성장애.getText());
                }
                if(button_정신장애.isChecked()) {
                    select.add((String) button_정신장애.getText());
                }

                Intent InfoIntent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(InfoIntent);
            }
        });
    }
}

