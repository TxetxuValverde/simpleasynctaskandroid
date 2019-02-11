package com.example.simpleasynctaskandroid;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask
    extends AsyncTask<Void, Integer, String> {

    private int id;

    public static final String SIMPLEASYNCTASK = "ASYNC_SIMPLEASYNCTASK";
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar pb, int id){
        this.id = id;
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(SIMPLEASYNCTASK, id + ":Cambiando interfaz de usuario");

        mTextView.get().setText(s);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d(SIMPLEASYNCTASK, id + ":Inicio de background");

        Random r = new Random();

        int n = r.nextInt(11);

        int s = n * 200;

        for(int i = 10; i <= 100; i+=10) {
            //Pausa de s milisegundos
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Fin de pausa de s milisegundos

            publishProgress(i);
        }

        Log.d(SIMPLEASYNCTASK, id + ":Fin de background");

        return "Awake at last after sleeping for " + s + " milliseconds";
    }
}
