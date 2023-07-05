package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    EditText etEmail, etPW;

    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.text_id);
        etPW = findViewById(R.id.text_pw);

        Button LoginButton = (Button) findViewById(R.id.button_login);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                try{
//
//                }catch(Exception e){
//                    Log.e("LoginActivity", "뭔 오류인지 몰라.. ");
//                    Toast.makeText(LoginActivity.this, "이메일 또는 비밀번호를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
//                }

                String email = etEmail.getText().toString().trim();
                String pwd = etPW.getText().toString().trim();

                if (email.equals("")) {
                    Toast.makeText(LoginActivity.this, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.equals("")) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //이메일, 비밀번호 잘못 입력시 클릭하고 여기까지 들어와서 else문까지 가는 데에 3분 이상 걸리는 이유 아직 못 찾음.
                        //제대로 입력하면 if문으로 바로 들어감.

                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                            startActivity(intent);
                        } else {
                            Log.d(TAG, "이메일 혹은 비밀번호 오류");
                            Toast.makeText(LoginActivity.this, "가입되지 않은 계정이거나 정보 재입력해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Button RegisterButton = (Button) findViewById(R.id.button_register);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RegisterIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(RegisterIntent);
            }
        });
    }

    private void reload() {
        Intent SelectIntent = new Intent(getApplicationContext(), SelectActivity.class);
        startActivity(SelectIntent);
    }
}