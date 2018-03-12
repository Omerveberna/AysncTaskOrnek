package com.example.omerveberna.aysnctaskornek;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnCalistir;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalistir = (Button) findViewById(R.id.btnCalistir);
        btnCalistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute((Void) null);

            }
        });
    }
    public class BackgroundTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 101; i = i + 10) {
                try {
                    publishProgress(i);
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Integer currentProgress = values[0];
            progressDialog.setProgress(currentProgress);
        }

        @Override
        protected void onCancelled(Void result) {
            super.onCancelled(result);
            progressDialog.dismiss();
        }
    }
}
