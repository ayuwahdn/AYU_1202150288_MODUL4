package com.example.android.ayu_1202150288_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by Annisa Ayu Wahdini on 3/17/2018.
 */

public class ListNama extends AppCompatActivity {
    private ListView mListView;
    private ProgressBar mProgressBar;
    private AddItemToListView mAddItemToListView;
    private Button mStartAsyncTask;

    //buat array users
    private String [] mMahasiswa = {"Afrodit", "Apollo", "Ares", "Athena", "Demeter",
            "Olimpus", "Hades", "Hera", "Hermes", "Zeus",
            "Poseidon", "Uranus", "Khronos", "Nemesis", "Dionisos"};


    //inisialisasi array dan semua komponen yang akan digunakan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.listView);
        mStartAsyncTask = (Button) findViewById(R.id.button_startAsyncTask);
        mListView.setVisibility(View.GONE);

        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //setAdapter terhadap ListView dengan menggunakan array
        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute(); //melakukan eksekusi ketika button diklik
            }
        });
    }

    //menambahkan item ke listview
    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListNama.this);

        @Override
        protected void onPreExecute() {
            //melakukan eksekusi progress dialog sebelum method onPostExecute dijalankan

            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //for progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialogInterface.dismiss();
                }
            });
            mProgressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //untuk melakukan aktivitas dibackground menggunakan AsyncTask

            for (String item : mMahasiswa){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

    @Override
    protected void onProgressUpdate(String... values) {
        //Method ini digunakan untuk menghitung presentase progress dialog
        mAdapter.add(values[0]);

        Integer current_status = (int) ((counter/(float)mMahasiswa.length)*100);
        mProgressBar.setProgress(current_status);

        //set progress only working for horizontal loading
        mProgressDialog.setProgress(current_status);

        //set message will not working when using horizontal loading
        mProgressDialog.setMessage(String.valueOf(current_status+"%"));
        counter++;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //Method ini untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan

        //buat nyembunyiin progress bar
        mProgressBar.setVisibility(View.GONE);

        //buat remove progress dialog
        mProgressDialog.dismiss();
        mListView.setVisibility(View.VISIBLE);
    }
    }
}
