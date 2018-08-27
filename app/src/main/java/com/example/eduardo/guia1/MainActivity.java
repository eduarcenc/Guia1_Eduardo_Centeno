package com.example.eduardo.guia1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView lblEntrada;
    private TextView lblResultado;
    private ArrayList<Button> lstNumeros;
    private Button btnAc,btnDel,btnIgual;
    private ArrayList<Button> lstOperaciones;
    private boolean opPulsada = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblEntrada      = (TextView) findViewById(R.id.lblEntrada);
        lblResultado    = (TextView) findViewById(R.id.lblResultado);
        btnAc           = (Button)   findViewById(R.id.btnAC);
        btnDel          = (Button)   findViewById(R.id.btnDEL);
        btnIgual        = (Button)   findViewById(R.id.btnIGUAL);

        lstNumeros = new ArrayList<>();
        lstNumeros.add( (Button) findViewById(R.id.btn9));
        lstNumeros.add( (Button) findViewById(R.id.btn8));
        lstNumeros.add( (Button) findViewById(R.id.btn7));
        lstNumeros.add( (Button) findViewById(R.id.btn6));
        lstNumeros.add( (Button) findViewById(R.id.btn5));
        lstNumeros.add( (Button) findViewById(R.id.btn4));
        lstNumeros.add( (Button) findViewById(R.id.btn3));
        lstNumeros.add( (Button) findViewById(R.id.btn2));
        lstNumeros.add( (Button) findViewById(R.id.btn1));
        lstNumeros.add( (Button) findViewById(R.id.btn0));
        lstNumeros.add( (Button) findViewById(R.id.btnP));

     
        lstOperaciones = new ArrayList<>();
        lstOperaciones.add( (Button) findViewById(R.id.btnSUM) );
        lstOperaciones.add( (Button) findViewById(R.id.btnRES) );
        lstOperaciones.add( (Button) findViewById(R.id.btnX));
        lstOperaciones.add( (Button) findViewById(R.id.btnDiv));

        //eventos onClic
        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblEntrada.setText("");
                lblResultado.setText("");
                opPulsada=true;
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarUltimo();
            }
        });
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lblResultado.setText(Parser.evaluar(lblEntrada.getText().toString()));
            }
        });
        initNumeros();
        initOperaciones();
    }

    private void initNumeros(){

        for (final Button btn:lstNumeros){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  
                    lblEntrada.setText(lblEntrada.getText().toString() + btn.getText().toString());
                    opPulsada=false;
                }
            });
        }
    }
    private void initOperaciones(){
     
        for (final Button btn:lstOperaciones){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!opPulsada){
                        
                        lblEntrada.setText(lblEntrada.getText().toString() + btn.getText().toString());
                        opPulsada=true;
                    }
                }
            });
        }
    }
    private void eliminarUltimo(){
        
        String str = lblEntrada.getText().toString();
        if (str != null && str.length() > 0 ) {/
            str = str.substring(0, str.length() - 1); 
            if(str.length()>0)
                opPulsada = esOperacion((str.substring(str.length()-1,str.length())));
            else
                opPulsada = true;
        }
        lblEntrada.setText(str);
    }

    private boolean esOperacion(String txt){
        for (final Button btn:lstOperaciones){
            if(btn.getText().equals(txt)){
                return true;
            }
        }
        return false;
    }

}
