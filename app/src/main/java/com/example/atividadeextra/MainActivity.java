package com.example.atividadeextra;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    private ServicoBanco banco;
    Intent intent;
    Spinner spVacina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spVacina = (Spinner)findViewById(R.id.spVacina);
        String[] vacinas = getResources().getStringArray(R.array.vacinas);
        spVacina.setAdapter(new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            vacinas
        ));

        Spinner spinner = (Spinner) findViewById(R.id.spVacina);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,
            R.array.vacinas,
            android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = new Intent(this, ServicoBanco.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ServicoBanco.MyBinder b = (ServicoBanco.MyBinder) iBinder;
        banco = b.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) { unbindService(this); }

    public void botao(View v){
        EditText teEficacia = (EditText)findViewById(R.id.teEficacia);

        EditText teTipo = (EditText)findViewById(R.id.teTipo);
        String vacinaSelecionada = (String)spVacina.getSelectedItem();

        Map<String, String> informacoes = banco.getInformacoes(vacinaSelecionada);
        teEficacia.setText(informacoes.get("eficacia"));
        teTipo.setText(informacoes.get("tipo"));
    }
}