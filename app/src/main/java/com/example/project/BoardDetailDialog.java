package com.example.project;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BoardDetailDialog extends AlertDialog implements View.OnClickListener {

    Button ok ;
    public String resultCode = "ok";
    Context mContext;
    String titleText = "";
    String dateText = "";
    String boardText = "";
    String nameText = "";
    TextView title ,date, board, name;
    public BoardDetailDialog(Context context , String title, String date, String board, String name) {
        super(context);
        mContext = context;
        titleText = title;
        dateText = date;
        boardText = board;
        nameText = name;
    }
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_board_detail);

        title = findViewById(R.id.tv_title);
        date = findViewById(R.id.date_title);
        board = findViewById(R.id.tv);
        name = findViewById(R.id.name);
        title.setText(titleText);
        date.setText(dateText);
        board.setText(boardText);
        name.setText("작성자: " + nameText);
        ok = findViewById(R.id.dialog_ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       if(v == ok){
           resultCode = "ok";
           if(!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(board.getText().toString())){
               titleText = title.getText().toString();
               boardText = board.getText().toString();
               dismiss();
           }else {
               Toast.makeText(mContext,"제목과 내용을 입력해주세요.",Toast.LENGTH_SHORT);
           }

       } else {
           resultCode = "cancle";
           dismiss();
       }
    }
}
