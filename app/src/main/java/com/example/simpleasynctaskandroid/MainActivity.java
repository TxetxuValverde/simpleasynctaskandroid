package com.example.simpleasynctaskandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT_STATE = "currentText";
    private static int contador = 0;

    public static final String MAINACTIVITY = "ASYNC_MAINACTIVITY";
    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);

        if(savedInstanceState != null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);

        Log.d(MAINACTIVITY, ++contador + ":Llamando a SimpleAsyncTask");

        new SimpleAsyncTask(mTextView, mProgressBar, contador).execute();
    }
}
