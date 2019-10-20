package com.ioc.planfitnizer2.common;

import android.content.Context;
import android.content.Intent;

import com.ioc.planfitnizer2.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helper {

    //per encriptar en MD5
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean logout(String token, Context context) {
        boolean res = false;

        String url = "http://localhost/api/v1/logout";
        String[] ok = new String[2];

        JSONObject jsonBody = new JSONObject();
/**
        try {
            jsonBody.put("token", token );
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
 //       CommunicationLayer comm = new CommunicationLayer(context, 1);
 //       ok = comm.communication(jsonBody, url, token);

        ok[0] =  "Logout successful";
        ok[1] = "testtoken";

        if(ok[0].equalsIgnoreCase("Logout successful")) {
            res = true;


        }

        return res;
    }

}
