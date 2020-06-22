package com.example.compliance_king;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private ArrayList<String> array;
    ListView listView;

    Button fab;
    ArrayList<ItemUseProduct> productDataList;
    ListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        setTitle("List");
        

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddProductActivity.class);
                startActivityForResult(intent, 0);

            }
        });

        // 리스트뷰
        this.InitializeProductData();

        ListView listView = (ListView) findViewById(R.id.listView1);
        final AdapterListView myAdapter = new AdapterListView(this, productDataList);

        listView.setAdapter(myAdapter);

        // 리스트뷰 항목 클릭
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

//        while (true) {
//            Date now = new Date();
//            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                if ("2020-6-8".equals(f.parse(String.valueOf(now)))) {
//                    Resources res = getResources();
//                    Intent notificationIntent = new Intent(this, NotificationActivity.class);
//                    notificationIntent.putExtra("noti_Id", 9999);
//
//                    PendingIntent contentItent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//
//                    builder.setContentTitle("setContentTitle").setContentText("setContentText").setTicker("setTicker").setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher)).setContentIntent(contentItent).setAutoCancel(true).setWhen(System.currentTimeMillis()).setDefaults(Notification.DEFAULT_ALL);
//
//
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }

    }

    private void InitializeProductData() {
        productDataList = new ArrayList<ItemUseProduct>();
        productDataList.add(new ItemUseProduct(R.drawable.ex, "메이크포렘 선크림", "2021-08-19", "2020-06-08"));
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String date1 = data.getStringExtra("date1");
            String date2 = data.getStringExtra("date2");

            productDataList.add(new ItemUseProduct(R.drawable.ex,
                    name, date1, date2));

        }
        listView.setAdapter(myAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
