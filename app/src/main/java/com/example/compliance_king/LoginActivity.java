package com.example.compliance_king;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText et_id;
    EditText et_pw;

    Button btn_login;
    Button btn_join;
    Button btn_search;

    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOGIN");

        // 액션바 숨기기
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        et_id = (EditText)findViewById(R.id.id);
        et_pw = (EditText)findViewById(R.id.pw);

        btn_login = (Button)findViewById(R.id.login);
        btn_join = (Button)findViewById(R.id.join);
        btn_search = (Button)findViewById(R.id.search);

        // 로그인 버튼 눌렀을 때
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 회원가입 버튼 눌렀을 때
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = (View) View.inflate(LoginActivity.this, R.layout.dialog_join, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(LoginActivity.this);
                dlg.setTitle("Join");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", null);
                dlg.setNegativeButton("취소", null);
                dlg.show();

            }
        });


    }
}
