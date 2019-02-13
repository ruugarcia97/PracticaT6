package com.example.ruugarcia.practicatema6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ConfiguracionPersona extends AppCompatActivity {
    SharedPreferences shPr;
    SharedPreferences.Editor editor;
    EditText [] txtPhone = new EditText[6];
    EditText [] txtCElectronico = new EditText[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_persona);
        txtPhone[0] = findViewById(R.id.txtT1);
        txtPhone[1] = findViewById(R.id.txtT2);
        txtPhone[2] = findViewById(R.id.txtT3);
        txtPhone[3] = findViewById(R.id.txtT4);
        txtPhone[4]= findViewById(R.id.txtT5);
        txtPhone[5] = findViewById(R.id.txtT6);

        txtCElectronico[0] = findViewById(R.id.txtC1);
        txtCElectronico[1] = findViewById(R.id.txtC2);
        txtCElectronico[2] = findViewById(R.id.txtC3);
        txtCElectronico[3] = findViewById(R.id.txtC4);
        txtCElectronico[4] = findViewById(R.id.txtC5);
        txtCElectronico[5] = findViewById(R.id.txtC6);
        shPr= getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor= shPr.edit();
        recoger_datos();

    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guardar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        recoger_preferencias();
        Toast.makeText(this, "Los datos se han guardado conrrectamente", Toast.LENGTH_LONG).show();
        return false;
    }
    public void recoger_preferencias()
    {
        int contador=1;
        for (int j=0;j<txtCElectronico.length;j++)
        {
            String tipo_telf = "telefono_contacto";
            String tipo_correo = "correo_contacto";
            tipo_telf += contador;
            tipo_correo += contador;
            String temp_telf = txtPhone[j].getText().toString();
            String temp_correo = txtCElectronico[j].getText().toString();
            editor.putString(tipo_telf, temp_telf);
            editor.putString(tipo_correo, temp_correo);
            editor.commit();
            contador++;
        }

    }
    public void recoger_datos()
    {
        String telefono,correo;
        int contador=1;
        for (int j=0;j<txtCElectronico.length;j++)
        {
            String tipo_telf = "telefono_contacto";
            String tipo_correo = "correo_contacto";
            tipo_telf += contador;
            tipo_correo += contador;
            String temp_telf=shPr.getString(tipo_telf,null);
            String temp_correo=shPr.getString(tipo_correo,null);
            txtPhone[j].setText(temp_telf);
            txtCElectronico[j].setText(temp_correo);
            contador++;
        }
    }
}
