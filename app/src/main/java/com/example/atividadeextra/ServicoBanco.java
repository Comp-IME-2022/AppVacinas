package com.example.atividadeextra;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicoBanco extends Service {
    public ServicoBanco() {
    }
    private final IBinder mBinder = new MyBinder();
    public class MyBinder extends Binder {
        ServicoBanco getService() {
            return ServicoBanco.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public Map<String, String> getInformacoes(String nome){
        Map<String, String> vacinas = new HashMap<>();
        switch(nome){
            case "CoronaVac - Sinovac": vacinas.put("eficacia", "50,38%");
                vacinas.put("tipo", "Vírus inativado"); break;
            case "Oxford/AstraZeneca": vacinas.put("eficacia", "90%");
                vacinas.put("tipo", "Vetor Viral"); break;
            case "Moderna": vacinas.put("eficacia", "94,1%");
                vacinas.put("tipo", "mRNA"); break;
            case "Johnson": vacinas.put("eficacia", "ND");
                vacinas.put("tipo", "Vetor viral"); break;
            case "Pfizer/BioNTech": vacinas.put("eficacia", "95%");
                vacinas.put("tipo", "mRNA"); break;
            default:
                throw new IllegalStateException("Unexpected value: " + nome);
        }
        return vacinas;
    }
}