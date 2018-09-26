package edu.carlos_dev.entropia;

/**
 * Created by carlos on 04/09/2018.
 */

import java.util.HashMap;

import java.util.Map;

public class Contabilizador {

    private Map<String, Long> ocurrencias;

    public Contabilizador() {

        ocurrencias = new HashMap<String, Long>();
    }

    public void contabiliza(String linea) {
        for (int i = 0; i < linea.length(); i++) {
            if (null == ocurrencias.get("" + linea.charAt(i))) {
                ocurrencias.put("" + linea.charAt(i), 1L);
            } else {
                Long valor = ocurrencias.get("" + linea.charAt(i));
                valor++;
                ocurrencias.put("" + linea.charAt(i), valor);
            }
        }
    }

    public Map<String, Long> getOcurrencias() {

        return ocurrencias;
    }

}

