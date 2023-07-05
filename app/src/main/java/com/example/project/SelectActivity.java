package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    Button btnLogOut, btnExample;
    EditText editText, editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Button BoardButton = (Button) findViewById(R.id.button_baord);
        BoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BoardIntent = new Intent(getApplicationContext(), BoardActivity.class);
                startActivity(BoardIntent);
            }
        });

        Button InfoButton = (Button) findViewById(R.id.button_info);
        InfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InfoIntent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(InfoIntent);
            }
        });

        database = FirebaseDatabase.getInstance();

        //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
        //현재 연결은 데이터베이스에만 딱 연결해놓고
        //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
        databaseReference = database.getReference();

        mAuth = FirebaseAuth.getInstance();

        btnLogOut = findViewById(R.id.btn_logout);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

//    private void example(String name, String info) {
//
//        databaseReference.child("zoo").child("test").setValue("test");
//    }

    private void logout() {

        mAuth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}