package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterDisActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    static public ArrayList<String> select = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dis);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getInstance().getReference();

        ArrayList<String> list = new ArrayList<>();

        Button DisabilityButton = (Button) findViewById(R.id.btn_확인);
        CheckBox button_지체장애 = (CheckBox) findViewById(R.id.btn_지체장애);
        CheckBox button_뇌병변장애 = (CheckBox) findViewById(R.id.btn_뇌병변장애);
        CheckBox button_시각장애 = (CheckBox) findViewById(R.id.btn_시각장애);
        CheckBox button_청각장애 = (CheckBox) findViewById(R.id.btn_청각장애);
        CheckBox button_언어장애 = (CheckBox) findViewById(R.id.btn_언어장애);
        CheckBox button_안면장애 = (CheckBox) findViewById(R.id.btn_안면장애);
        CheckBox button_신장장애 = (CheckBox) findViewById(R.id.btn_신장장애);
        CheckBox button_심장장애 = (CheckBox) findViewById(R.id.btn_심장장애);
        CheckBox button_간장애 = (CheckBox) findViewById(R.id.btn_간장애);
        CheckBox button_호흡기장애 = (CheckBox) findViewById(R.id.btn_호흡기장애);
        CheckBox button_장루요루장애 = (CheckBox) findViewById(R.id.btn_장루요루장애);
        CheckBox button_뇌전증장애 = (CheckBox) findViewById(R.id.btn_뇌전증장애);
        CheckBox button_지적장애 = (CheckBox) findViewById(R.id.btn_지적장애);
        CheckBox button_자폐성장애 = (CheckBox) findViewById(R.id.btn_자폐성장애);
        CheckBox button_정신장애 = (CheckBox) findViewById(R.id.btn_정신장애);
        DisabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.clear();
                if (button_지체장애.isChecked()) {
                    list.add("지체장애");
                }else{
                    list.remove("지체장애");
                }
                if (button_뇌병변장애.isChecked()) {
                    list.add("뇌병변장애");
                }else{
                    list.remove("뇌병변장애");
                }
                if (button_시각장애.isChecked()) {
                    list.add("시각장애");
                }else{
                    list.remove("시각장애");
                }
                if (button_청각장애.isChecked()) {
                    list.add("청각장애");
                }else{
                    list.remove("청각장애");
                }
                if (button_언어장애.isChecked()) {
                    list.add("언어장애");
                }else{
                    list.remove("언어장애");
                }
                if (button_안면장애.isChecked()) {
                    list.add("안면장애");
                }else{
                    list.remove("안면장애");
                }
                if (button_신장장애.isChecked()) {
                    list.add("신장장애");
                }else{
                    list.remove("신장장애");
                }
                if (button_심장장애.isChecked()) {
                    list.add("심장장애");
                }else{
                    list.remove("심장장애");
                }
                if (button_간장애.isChecked()) {
                    list.add("간장애");
                }else{
                    list.remove("간장애");
                }
                if (button_호흡기장애.isChecked()) {
                    list.add("호흡기장애");
                }else{
                    list.remove("호흡기장애");
                }
                if (button_장루요루장애.isChecked()) {
                    list.add("장루요루장애");
                }else{
                    list.remove("장루요루장애");
                }
                if (button_뇌전증장애.isChecked()) {
                    list.add("뇌전증장애");
                }else{
                    list.remove("뇌전증장애");
                }
                if (button_지적장애.isChecked()) {
                    list.add("지적장애");
                }else{
                    list.remove("지적장애");
                }
                if (button_자폐성장애.isChecked()) {
                    list.add("자폐성장애");
                }else{
                    list.remove("자폐성장애");
                }
                if (button_정신장애.isChecked()) {
                    list.add("정신장애");
                }else{
                    list.remove("정신장애");
                }
                mReference.child("disability name").child(mAuth.getUid()).setValue(list);
                Intent intent = new Intent(RegisterDisActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}