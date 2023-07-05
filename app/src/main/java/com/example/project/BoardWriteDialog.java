package com.example.project;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BoardWriteDialog extends AlertDialog implements View.OnClickListener {

    Button ok , cancle;
    public String resultCode = "cancle";
    Context mContext;
    String titleText = "";
    String boardText = "";
    EditText title , board;
    public BoardWriteDialog(Context context) {
        super(context);
        mContext = context;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_board_write);

        title = findViewById(R.id.tv_title);
        board = findViewById(R.id.tv);

        ok = findViewById(R.id.dialog_ok);
        ok.setOnClickListener(this);
        cancle = findViewById(R.id.dialog_cancle);
        cancle.setOnClickListener(this);
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
