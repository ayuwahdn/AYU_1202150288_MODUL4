package com.example.android.ayu_1202150288_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //ketika diklik dia pindah ke class ListNama
    public void listNama(View view) {
        Intent intent = new Intent(MainActivity.this, ListNama.class);
        startActivity(intent);
    }

    //ketika diklik dia pindah ke class cariGambar
    public void cariGambar(View view) {
        Intent i = new Intent(MainActivity.this, CariGambar.class);
        startActivity(i);
    }
}