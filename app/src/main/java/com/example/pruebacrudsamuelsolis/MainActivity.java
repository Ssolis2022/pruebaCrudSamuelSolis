package com.example.pruebacrudsamuelsolis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Datos(View v) {
        Intent datos = new Intent(this, Datos.class);
        startActivity(datos);
    }

    public void Ubi(View v) {
        Intent ubi = new Intent(this, Ubi.class);
        startActivity(ubi);
    }
}