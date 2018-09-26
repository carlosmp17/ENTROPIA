package edu.carlos_dev.entropia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcularEntropia(View view){
        EditText editText = (EditText) findViewById(R.id.texto);
        String texto= editText.getText().toString();

        texto = texto.replaceAll("\\s", "");
        texto = texto.toUpperCase();
        int numeroCaracteres = texto.length();

        Contabilizador contabilizador = new Contabilizador();
        contabilizador.contabiliza(texto);

        Map<String, Long> ocurrencias = contabilizador.getOcurrencias();
        Set<String> keys = ocurrencias.keySet();
        Iterator<String> iterator = keys.iterator();
        String key = null;

        int numeroSimbolos = 2;
        double entropia = 0;
        String unidad ="";
        boolean bandera = false;

            while (iterator.hasNext()) {
                key = iterator.next();
                double probabilidadAsociada = (double) ocurrencias.get(key) / numeroCaracteres;
                entropia += (probabilidadAsociada) * (log2((double) 1 / probabilidadAsociada, numeroSimbolos));

                if (ocurrencias.get(key) > 1){
                    bandera = true;
                }
            }

        unidad = (bandera)?" binits":" bits";

        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        Toast.makeText(getBaseContext(), "Entropía: " + entropia + unidad,Toast.LENGTH_LONG).show();

    }

    public static double logb(double a, double b)
    {
        return Math.log(a) / Math.log(b);
    }

    public static double log2(double a, int base)
    {
        return logb(a, base);
    }
}
