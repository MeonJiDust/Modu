package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    AlertDialog.Builder builder;

    EditText etEmail, etPW, etCheckPW, etName, etPhoneNumber, etBirth;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getInstance().getReference();

        etEmail = findViewById(R.id.etEmail);
        etPW = findViewById(R.id.etPW);
        etCheckPW = findViewById(R.id.etCheckPW);
        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etBirth = findViewById(R.id.etBirth);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString().trim();
                String pwd = etPW.getText().toString().trim();
                String pwdCheck = etCheckPW.getText().toString().trim();

                // 이메일 형식 오류, 비밀번호 6자리 이하(?), 영문포함여부(?) 등으로 인해 계정 생성 오류 발생.
                // 잘 지켜서 가입하깅!!

                if(pwd.equals(pwdCheck)){

                    Log.d(TAG, "등록버튼" + email + " , " + pwd);

                    final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
                    mDialog.setMessage("가입중입니다...");
                    mDialog.show();

                    mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //여기까지 들어오는데 5분 정도 걸리는데 그 이유 못 찾음...

                                mDialog.dismiss();

                                String name = etName.getText().toString().trim();
                                String phone = etPhoneNumber.getText().toString().trim();
                                String birth = etBirth.getText().toString().trim();
                                String pw = etPW.getText().toString().trim();

                                //firebase의 realtime database에 데이터 전송 안 됨.
                                FirebaseUser user = mAuth.getCurrentUser();
                                UserAccount account = new UserAccount();

                                account.setUid(user.getUid());
                                account.setEmail(user.getEmail());
                                account.setPassword(pw);
                                account.setName(name);
                                account.setBirth(birth);
                                account.setPhone(phone);

                                mReference.child("UserAccount").child(user.getUid()).setValue(account);
                                Intent intent = new Intent(RegisterActivity.this, RegisterDisActivity.class);
                                startActivity(intent);
                                finish();
                                //alert();

                                Toast.makeText(RegisterActivity.this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            }else{
                                Log.d(TAG, "");
                                mDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this, "비밀번호가 틀렸습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

//    private void alert() {
//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance();
//        mReference = mDatabase.getInstance().getReference();
//
//
//        ArrayList<String> list = new ArrayList<>();
//
//
//        builder = new AlertDialog.Builder(RegisterActivity.this);
//        builder.setTitle("장애를 선택하세요.");
//
//        builder.setMultiChoiceItems(R.array.disability, null, new DialogInterface.OnMultiChoiceClickListener(){
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
//                String[] items = getResources().getStringArray(R.array.disability);
//
//                if(isChecked){
//                    list.add(items[i]);
//                }else if(list.contains(items[i])){
//                    list.remove(items[i]);
//                }
//            }
//        });
//
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                mReference.child("disability name").child(mAuth.getUid()).setValue(list);
//                        Intent intent = new Intent(RegisterActivity.this, SelectActivity.class);
//                        startActivity(intent);
//                        finish();
//            }
//        });
//
////        new AlertDialog.Builder(RegisterActivity.this)
////                .setTitle("해당되는 버튼을 클릭하세요.")
////                .setSingleChoiceItems(R.array.disability, 0, new DialogInterface.OnClickListener() {
////                    // setSingleChoiceItems 가 RadioGroup, RadioButton 을 해준다.
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        list.add(items[i]); // 선택한 값 저장.
////                    }
////                })
////                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
////                    // 확인 버튼을 클릭 했을때 이벤트
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        mReference.child("disability name").child(mAuth.getUid()).setValue(list);
////                        Intent intent = new Intent(RegisterActivity.this, SelectActivity.class);
////                        startActivity(intent);
////                        finish();
////                    }
////                })
////                .setNegativeButton("취소", null)
////                .show();
//    }
}