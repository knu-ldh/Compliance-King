package com.example.compliance_king;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    String ids = null;

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

        et_id = (EditText) findViewById(R.id.id);
        et_pw = (EditText) findViewById(R.id.pw);

        btn_login = (Button) findViewById(R.id.login);
        btn_join = (Button) findViewById(R.id.join);
        btn_search = (Button) findViewById(R.id.search);

        // 로그인 버튼 눌렀을 때
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

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

                EditText et_name = (EditText) dialogView.findViewById(R.id.dlg_name);
                EditText et_birth = (EditText) dialogView.findViewById(R.id.dlg_birth);
                RadioGroup rg_gender = (RadioGroup) dialogView.findViewById(R.id.dlg_gender);
                EditText et_email = (EditText) dialogView.findViewById(R.id.dlg_email);
                EditText et_id = (EditText) dialogView.findViewById(R.id.dlg_id);
                EditText et_pw = (EditText) dialogView.findViewById(R.id.dlg_pw);
                EditText et_pwcheck = (EditText) dialogView.findViewById((R.id.dlg_pwcheck));
                Button id_check = (Button) dialogView.findViewById(R.id.dlg_check);

                id_check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getData("http://220.68.27.145/PHP_connection.php");
                        ArrayList<HashMap<String, String>> idList = new ArrayList<HashMap<String, String>>();

                    }
                });
                dlg.setPositiveButton("확인", null);
                dlg.setNegativeButton("취소", null);
                dlg.show();

            }
        });

    }

    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();
                } catch (Exception e){
                    return "";
                }
            }

            @Override
            protected void onPostExecute(String result) {
                ids = result;
            }
        }
    }
}
