package com.example.compliance_king;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

public class AddProductActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 672;
    private String imageFilePath;
    private Uri photoUri;

    ImageView img;
    Button btn_category, btn_m, btn_cancle, btn_accept;
    ImageButton btn_date1, btn_date3, btn_m2;
    TextView tv_category, tv_name, tv_date1, tv_date3;
    EditText et_date2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // 액션바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        img = (ImageView)findViewById(R.id.img_photo);

        tv_name = (TextView)findViewById(R.id.product_name);
        btn_category = (Button) findViewById(R.id.btn_category);
        btn_date1 = (ImageButton) findViewById(R.id.btn_date1);
        btn_date3 = (ImageButton) findViewById(R.id.btn_date3);
        btn_m = (Button)findViewById(R.id.btn_m);
        btn_m2 = (ImageButton) findViewById(R.id.btn_m2);
        btn_cancle = (Button) findViewById(R.id.btn_cancle);
        btn_accept = (Button) findViewById(R.id.btn_accept);

        tv_category = (TextView) findViewById(R.id.tv_category);
        tv_date1 = (TextView) findViewById(R.id.tv_date1);
        et_date2 = (EditText) findViewById(R.id.et_date2);
        tv_date3 = (TextView) findViewById(R.id.tv_date3);

        final Calendar cal = Calendar.getInstance();

        // 카메라 연결
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 카테고리 선택 버튼 클릭했을 때
        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] categoryArray = new String[]{"식품", "의약품", "화장품", "그 외"};
                final boolean[] checkArray = new boolean[]{false, false, false, false};
                final String[] selectCategory = {""};

                AlertDialog.Builder dlg = new AlertDialog.Builder(AddProductActivity.this);
                dlg.setSingleChoiceItems(categoryArray, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectCategory[0] = categoryArray[which];
                            }
                        });

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_category.setText(selectCategory[0]);
                    }
                });

                dlg.setNegativeButton("취소", null);

                dlg.show();
            }
        });

        // 유통기한 달력 클릭했을 때
        btn_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int y, m, d = 0;

                DatePickerDialog dialog = new DatePickerDialog(AddProductActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        String msg = String.format("%d-%d-%d", year, month + 1, date);
                        tv_date1.setText(msg);

                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                //dialog.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션

                dialog.show();
            }
        });

        // 유효기간 달력 클릭했을 때
        btn_date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int y, m, d = 0;

                DatePickerDialog dialog = new DatePickerDialog(AddProductActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%d-%d-%d", year, month + 1, date);
                        tv_date3.setText(msg);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                //dialog.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션

                dialog.show();
            }
        });

        // 유효기간 선택
        btn_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(AddProductActivity.this);
                final String[] mArray = new String[]{"일주일", "1개월", "6개월", "12개월", "24개월"};
                final int[] mArray2 = new int[] {7, 30, 180, 360, 720};
                final boolean[] checkArray = new boolean[]{false, false, false, false, false};
                final String[] selectM = {""};
                final int[] selectM2 = {0};

                dlg.setSingleChoiceItems(mArray, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectM[0] = mArray[which];
                                selectM2[0] = mArray2[which];
                            }
                        });

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String date = (String) tv_date3.getText();
                        String newDate = "";

//                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                        try {
//                            Date to = format.parse(date);
//
//                            Calendar c = Calendar.getInstance();
//                            c.setTime(to);
//                            c.add(c.DATE, selectM2[0]);
//
//                            newDate = format.format(c.getTime());
//
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//
//                        et_date2.setText(newDate);
                    }
                });

                dlg.setNegativeButton("취소", null);

                dlg.show();
            }
        });

        // 유효기간 추천

        // 취소 버튼 클릭했을 때
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

        // 확인 버튼 클릭했을 때
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);


                // 상품 정보 입력 받아서 넘기고 리스트뷰에 추가하기
                intent.putExtra("name", tv_name.getText());
                intent.putExtra("date1", tv_date1.getText());
                intent.putExtra("date2", et_date2.getText());

                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
