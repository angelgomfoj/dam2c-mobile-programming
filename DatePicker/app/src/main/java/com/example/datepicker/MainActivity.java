package com.example.datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        MyDialog.respuestaMyDialog {

    @Override
    public void onRespuesta(String s) {
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG
        ).show();
    }
    public void click(View v){
        MyDialog ds=new MyDialog();
        ds.show(getFragmentManager(),"Mi diálogo");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}