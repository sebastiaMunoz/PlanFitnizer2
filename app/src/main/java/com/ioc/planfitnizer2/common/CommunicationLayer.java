package com.ioc.planfitnizer2.common;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ioc.planfitnizer2.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

public class CommunicationLayer extends ArrayAdapter {
    private static String tag = "APP";
    public RequestQueue requestQueue;
    public Context context;
    public int resource;

    public CommunicationLayer(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    public String[] communication(JSONObject jsonBody, String url, final String token){

        final String[] responseText = new String[2];
        requestQueue = Volley.newRequestQueue(context);
            //Will give an error if response is blank, such as when using webhook.site
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(POST, url, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(tag, response.toString());
                            String token = "";
                            String status = "";
                            try {
                                token = response.get("token").toString();
                                status = response.get("status").toString();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(token.isEmpty()){
                                Log.d(tag, status);
                                responseText[0] = status;
                            }
                            else{
                                Log.d(tag, status);
                                responseText[0] = status;
                                responseText[1] = token;

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(tag, error.getMessage(), error);
                }
            }) { //no semicolon or coma
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                   if(!token.equalsIgnoreCase("")) params.put("Authorization","Bearer "+token);
                    return params;
                }
            };

            requestQueue.add(jsonObjectRequest);

            return responseText;
        }


}
