package com.ioc.planfitnizer2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ioc.planfitnizer2.common.CommunicationLayer;
import com.ioc.planfitnizer2.common.Helper;
import com.ioc.planfitnizer2.common.Stub;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    RequestQueue requestQueue;
    JSONObject jsonBody;
    Button loginButton;
    ProgressBar loadingProgressBar;
    String passwordEncripted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordEncripted = Helper.getMD5(password.getText().toString());

        requestQueue = Volley.newRequestQueue(this);
        loadingProgressBar = findViewById(R.id.loading);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getBaseContext(), R.string.missing_fields, Toast.LENGTH_SHORT);
                    toast.show();
                } else {

                    loadingProgressBar.setVisibility(View.VISIBLE);
                    login();
                    loadingProgressBar.setVisibility(View.INVISIBLE);

            }
            }
        });
    }


    private void login(){

        String url = //"http://localhost/api/v1/login";
        "http://virtserver.swaggerhub.com/gemmacortel/Fitnizer/1.0.0/login";
        String[] ok = new String[2];

        jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username.getText().toString());
            jsonBody.put("password", passwordEncripted);
        } catch (JSONException e) {
            e.printStackTrace();
        }
  //      CommunicationLayer comm = new CommunicationLayer(this, 0);
  //     ok = comm.communication(jsonBody, url, "");

        //per test
        Stub s = new Stub();
        ok = s.getLogin(username.getText().toString(),password.getText().toString());

        if(ok[0]!=null && ok[0].equalsIgnoreCase("Log successful")) {
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            i.putExtra("token", ok[1]);
            loginOk(ok[0]);
            startActivity(i);

            finish();
        } else {
            loginFailed(ok[0]);
        }
    }

    private void loginOk(String welcome) {
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void loginFailed(String res) {
        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
    }

}
