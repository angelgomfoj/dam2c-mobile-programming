package com.example.intent1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int SELECCIONA_PROVINCIA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String[] elementos = {"Cantabria", "Cuenca", "Ceuta"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void pulsado(View v){
        Intent intent = new Intent(this, Activity2.class);
        startActivityForResult(intent,SELECCIONA_PROVINCIA);
    }
    //recibe los resultados
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView t = (TextView) findViewById(R.id.textView);
        if (requestCode == SELECCIONA_PROVINCIA) {
            if (resultCode == RESULT_OK) {
// se seleccionó correctamente la provincia
                t.setText("Se ha seleccionado:\n" + data.getStringExtra("PROVINCIA"));
            }
        }
    }
    }