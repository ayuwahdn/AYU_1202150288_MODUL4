package com.example.android.ayu_1202150288_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Annisa Ayu Wahdini on 3/17/2018.
 */

public class CariGambar extends AppCompatActivity {
    private EditText eCari;
    private Button btnCari;
    private ImageView ivCari;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        //inisialisasi variabel
        eCari = (EditText) findViewById(R.id.cari_gambar);
        btnCari = (Button) findViewById(R.id.btnCari);
        ivCari = (ImageView) findViewById(R.id.iv_cari);
    }

    public void cariGambar(View view) {

        loadImageInit();
    }

    private void loadImageInit(){
        String ImgUrl = eCari.getText().toString();
        //AsyncTask mencari gambar di internet
        new LoadGambar().execute(ImgUrl);
    }

    private class LoadGambar extends AsyncTask<String, Void, Bitmap> {
        //melakukan eksekusi progress dialog sebelum method onPostExecute dijalankan

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //buat progress dialog
            progressDialog = new ProgressDialog(CariGambar.this);

            //judul progress dialog
            progressDialog.setTitle("Cari Gambar");
            //setting message progress dialog
            progressDialog.setMessage("Loading Gambar");
            //menampilkan progress dialog
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //melakukan aktivitas dibackground menggunakan AsyncTask
            Bitmap bitmap = null;
            try {
                // mendownload gambar dari url
                URL url = new URL(params[0]);
                // mengkonversikan gambar ke bitmap (decode to bitmap)
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
            super.onPostExecute(bitmap);
            //nampung gambar ke imageView dan menampilkannya
            ivCari.setImageBitmap(bitmap);

            //menghilangkan progress dialog
            progressDialog.dismiss();


        }
    }
}
