package com.example.quanlyquancf;


import android.app.ProgressDialog;
import android.content.Intent;
import android.drm.ProcessedData;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.quanlyquancf.AdapterR.PageAdapter;
import com.example.quanlyquancf.DoiTuong.User;
import com.example.quanlyquancf.Fragment.tabBan;
import com.example.quanlyquancf.Fragment.tabBanTrong;
import com.example.quanlyquancf.Lop.Home;
import com.example.quanlyquancf.Lop.LoginAccount;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent s = new Intent(getApplicationContext(),LoginAccount.class);
        startActivity(s);
        //setContentView(R.layout.activity_login_account);


        }

    }

