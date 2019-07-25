package com.example.junior_memoria_interna;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView cajaCedula, cajaNombres, cajaApellidos, cajaDatos;
    Button botonLeer, botonEscribir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonEscribir=(Button)findViewById(R.id.btnEscribirMI);
        botonLeer=(Button)findViewById(R.id.btnLeerMI);
        cajaCedula=(TextView)findViewById(R.id.txtCedulaMI);
        cajaNombres=(TextView)findViewById(R.id.txtNombresMI);
        cajaApellidos=(TextView)findViewById(R.id.txtApellidosMI);
        cajaDatos=(TextView)findViewById(R.id.txtDatosMI);


        botonEscribir.setOnClickListener(this);
        botonLeer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnEscribirMI:

                try {
                    OutputStreamWriter escritor = new OutputStreamWriter(openFileOutput("archiv02.txt", Context.MODE_APPEND));
                    escritor.write(cajaCedula.getText().toString()+","+cajaApellidos.getText().toString()+","+cajaNombres.getText().toString());
                    escritor.close();

                }catch (Exception ex){

                    Log.e("Archivo MI","Error en el archivo de escritura");
                }
                break;
            case R.id.btnLeerMI:
                try {

                    BufferedReader lector = new BufferedReader(new InputStreamReader(openFileInput("archiv02.txt")));
                    String datos = lector.readLine();
                    String [] listaPersonas = datos.split(";");
                    for(int i = 0; i < listaPersonas.length; i++){

                        cajaDatos.append(listaPersonas[i].split(",")[0] + " " +  listaPersonas[i].split(",")[1] + " " +
                                listaPersonas[i].split(",")[2]);
                    }


                    lector.close();

                }catch (Exception ex){

                    Log.e("Archivo MI","Error en la lectura del archivo" + ex.getMessage());
                }
                break;
        }

    }
}
