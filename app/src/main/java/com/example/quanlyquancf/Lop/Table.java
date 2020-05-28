package com.example.quanlyquancf.Lop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.quanlyquancf.R;
import com.google.android.material.navigation.NavigationView;

public class Table extends AppCompatActivity {

    TableLayout tb;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_table);

        Toolbar toolbar = findViewById(R.id.tlb1);
//        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_table, null);
        tb = (TableLayout) findViewById(R.id.tb1);
        SetTable();

    }
    private void SetMenu()
    {

    }
    private void SetTable()
    {
        for(int i = 1;i<=30;i+=2) {
            int temp = i+1;
            Button myButton = new Button(this);
            myButton.setText("Bàn " + i);
            myButton.setHeight(80);
            myButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_bo_tron_nut_2,0,0,0);
            Button myButton2 = new Button(this);
            myButton2.setText("Bàn " +  temp);
            myButton2.setHeight(80);
            myButton2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_bo_tron_nut_2,0,0,0);
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    200, 1f);
            params.setMargins(10,10,10,10);
            myButton.setLayoutParams(params);
            myButton2.setLayoutParams(params);
            TableRow tr1 = new TableRow(this);
            tr1.addView(myButton, params);
            tr1.addView(myButton2, params);
            tb.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        }
    }
}
