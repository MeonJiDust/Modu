package com.example.project;

import static com.example.project.DisabilityActivity.select;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    ListView exampleList;
    ArrayList<String> titleSample, contentsSample;
    String[] titledata = new String[]{"ㄱ","ㄴ","ㄷ","ㄹ"};
    String[] contentsdata = new String[]{"ㄱ","ㄴ","ㄷ","ㄹ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ListView list = findViewById(R.id.listView);

        titleSample = new ArrayList<String>();
        contentsSample = new ArrayList<String>();


        for (int i = 0; i < titledata.length; i++) {
            titleSample.add(titledata[i]);
            contentsSample.add(contentsdata[i]);
        }

        exampleList = findViewById(R.id.listView);
        ButtonListAdapter buttonListAdapter = new ButtonListAdapter(this, titleSample, contentsSample);

        exampleList.setAdapter((ListAdapter) buttonListAdapter);


        Button 장애선택Button = (Button) findViewById(R.id.button_장애선택);
        장애선택Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DisabilityIntent = new Intent(getApplicationContext(), DisabilityActivity.class);
                startActivity(DisabilityIntent);
            }
        });
        Button 검색Button = (Button) findViewById(R.id.button_검색);
        검색Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleSample.clear();
                contentsSample.clear();
                for (int i = 0; i <= select.size(); i++) {
                    if (select.contains(contentsdata[i])) {
                        titleSample.add(titledata[i]);
                        contentsSample.add(contentsdata[i]);
                    }
                }
                exampleList.setAdapter((ListAdapter) buttonListAdapter);
            }

        });
    }
}