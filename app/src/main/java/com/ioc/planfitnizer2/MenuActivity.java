package com.ioc.planfitnizer2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.ioc.planfitnizer2.common.Helper;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_tools)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //si l'usuari prem el boto de enrera dins aquets menu, es farà logput
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        final boolean[] res = {true};
        final boolean[] salir = {false};
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            res[0] = false;
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Exit")
                    .setMessage("Are you sure?")
                    .setNegativeButton(android.R.string.cancel, null)//sin listener
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            //Salir
                             salir[0] = true;

                            String token = MenuActivity.this.getIntent().getStringExtra("token");
                            if (Helper.logout(token, MenuActivity.this)){
                                Intent i = new Intent(MenuActivity.this, MainActivity.class);
                                i.putExtra("token", token);
                                startActivity(i);
                                MenuActivity.this.finish();
                                res[0] = true;
                            }
                        }
                    }).show();
        }
        if(res[0]) {
            return super.onKeyDown(keyCode, event);
        }
        else {
            return res[0];
        }
    }
}
