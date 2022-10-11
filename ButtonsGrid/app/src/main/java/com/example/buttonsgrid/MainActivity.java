package com.example.buttonsgrid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridLayout g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         g = (GridLayout) findViewById(R.id.gl1);
        createButtons(g);
    }

    public void createButtons(GridLayout g){

        List<Button> listaBotones = new ArrayList<>();
        Button b;
        Random r = new Random();
        int bId;
        for (int i = 0; i < 16; i++) {
            b = new Button(this);
            b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            bId = View.generateViewId();
            b.setId(bId);
            b.setText("Boton "+i);
            b.setBackgroundColor(Color.rgb( r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            listaBotones.add(b);
            if(i<15){
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (Button bx:listaBotones) {
                            bx.setBackgroundColor(Color.BLUE);
                        }
                    }
                });
            }else{
                b.setText("Reset");
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (Button bx:listaBotones) {
                            bx.setBackgroundColor(Color.rgb( r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                        }
                    }
                });
            }
            g.addView(b,i);
        }
    }
}