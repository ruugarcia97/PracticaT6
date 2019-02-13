package com.example.ruugarcia.practicatema6;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import static android.Manifest.permission.CALL_PHONE;

public class PantallaPersonas extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE =  1;
    ImageView img1, img2, img3, img4, img5, img6;
    SharedPreferences p;
    String correo, llamar;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_personas);
        img1 = findViewById(R.id.imagen1);
        img2 = findViewById(R.id.imagen2);
        img3 = findViewById(R.id.imagen3);
        img4 = findViewById(R.id.imagen4);
        img5 = findViewById(R.id.imagen5);
        img6 = findViewById(R.id.imagen6);


        img1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                correo="c1"; llamar="t1";
                registerForContextMenu(img1);
                return false;
            }
        });
        img2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                correo="c2"; llamar="t2";
                registerForContextMenu(img2);
                return false;
            }
        });
        img3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                correo="c3"; llamar="t3";
                registerForContextMenu(img3);
                return false;
            }
        });
        img4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                correo="c4"; llamar="t4";
                registerForContextMenu(img4);
                return false;
            }
        });
        img5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                correo="c4"; llamar="t5";
                registerForContextMenu(img5);
                return false;
            }
        });
        img6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                correo="c6"; llamar="t6";
                registerForContextMenu(img6);
                return false;
            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_pantalla_personas, menu);
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_configuracion_personas, menu);
        MenuBuilder m = (MenuBuilder) menu;
        m.setOptionalIconsVisible(true);
        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String cadena="";
        p = getSharedPreferences("configuracion", Context.MODE_PRIVATE);
        switch(item.getItemId())
        {
            case R.id.menuLlamar:
                cadena = p.getString(llamar, null);
                if(cadena==null)
                {
                    Toast.makeText(this, "No hay telefono registrado", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                    {
                        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(cadena));
                        startActivity(i);
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(cadena));
                        startActivity(intent);

                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + cadena));
                        startActivity(callIntent);
                    }
                    else
                    {
                        requestPermissions(new String[]{CALL_PHONE}, 1);
                    }

                    if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.CALL_PHONE) !=
                            PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                    else
                        {

                    }

                }
                break;
            case R.id.menuCorreo:
                cadena = p.getString(correo, null);
                if(cadena==null)
                {
                    Toast.makeText(this, "No hay correo registrado", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{cadena});
                    startActivity(emailIntent);
                }
                break;
        }
        return super.onContextItemSelected(item);
    }
    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent2 = new Intent(this, ConfiguracionPersona.class);
        startActivity(intent2);
        return false;
    }
}
