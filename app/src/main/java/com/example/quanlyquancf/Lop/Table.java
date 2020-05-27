package com.example.quanlyquancf.Lop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_table);


        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_table, null);
        tb = (TableLayout) findViewById(R.id.tb1);

        setTable();

    }
    private void setMenu()
    {
//        final DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
//        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });

//        NavigationView navigationView=findViewById(R.id.navigationView);
//        navigationView.setItemIconTintList(null);
//
//        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);
//        NavigationUI.setupWithNavController(navigationView,navController);
//
//        final TextView textView=(TextView)findViewById(R.id.textTile);
//        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
//            @Override
//            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//                textView.setText(destination.getLabel());
//            }
//        });
    }
    private void setTable()
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
