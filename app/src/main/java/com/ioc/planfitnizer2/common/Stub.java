package com.ioc.planfitnizer2.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Stub {

    private Map<String, String> mapa= new TreeMap<>();

    public Map<String, String> addData(){

        mapa= new TreeMap<>();

        mapa.put("pep@test.com", "123456");
        mapa.put("joan@test.com", "111111");

        return mapa;
    }

    public String[] getLogin(String user, String password){
        String[] res = new String[2];

        if(mapa.isEmpty()) addData();

        if(mapa.get(user)!=null){

            if(mapa.get(user).equalsIgnoreCase(password)) {
                res[0] = "Log successful";
                res[1] = "testtoken";
            } else {
                res[0] = "Incorrect Password";
                res[1] = "";
            }
        }else{
            res[0] = "User Not Exist";
            res[1] = "";
        }

        return res;
    }

    public void alta(String clau, String valor){

        if(mapa.get(clau)==null){
            mapa.put(clau, valor);
        }else{
            throw new Error("Clau repetida");
        }
    }

    public void modifica(String clau, String valor){
        if(mapa.get(clau)!=null){
            mapa.put(clau, valor);
        }else{
            throw new Error("Clau inexistent");
        }
    }

    public void esborra(String clau){
        if(mapa.get(clau)!=null){
            mapa.remove(clau);
        }else{
            throw new Error("Clau inexistent");
        }
    }

    public List<String> llistaTots(){
        List<String> resultat=new ArrayList<String>();
        for(Map.Entry<String,String> aux:mapa.entrySet()){
            resultat.add(aux.getValue());
        }
        return resultat;
    }


}
