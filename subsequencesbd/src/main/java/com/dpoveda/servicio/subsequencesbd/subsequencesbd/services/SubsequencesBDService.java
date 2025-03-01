package com.dpoveda.servicio.subsequencesbd.subsequencesbd.services;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

/**
 * Servicio para manejar subsecuencias.
 */
@Service
public class SubsequencesBDService {

    private static final Integer VALOR_MINIMO_COINCIDENCIAS = 0;
    private static final Integer VALOR_CONSTANTE_POSICION = 1;

    /**
     * Calcula la cantidad de subsecuencias del atributo textoABuscar dentro del atributo textoBase.
     *
     * @param textoBase La cadena base en la que se buscaran las subsecuencias.
     * @param textoABuscar La subsecuencia a buscar.
     * @return El numero de subsecuencias encontradas.
     */
    public Integer cantidadSubsequences(String textoBase, String textoABuscar) {
        // se adiciona validacion para evitar nullpointerexcepcion
        if (Strings.isBlank(textoBase) || Strings.isBlank(textoABuscar)) {
            return VALOR_MINIMO_COINCIDENCIAS;
        }
        int x = textoBase.length();
        int y = textoABuscar.length();
        int[][] arreglo = inicializarMatriz(x,y);
        // se recorren la matriz a partir de los valores ingresados
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                if (textoBase.charAt(i - VALOR_CONSTANTE_POSICION) == textoABuscar.charAt(j - VALOR_CONSTANTE_POSICION)) {
                    arreglo[i][j] = arreglo[i - VALOR_CONSTANTE_POSICION][j - VALOR_CONSTANTE_POSICION] + arreglo[i - VALOR_CONSTANTE_POSICION][j];
                } else {
                    arreglo[i][j] = arreglo[i - VALOR_CONSTANTE_POSICION][j];
                }
            }
        }
        return arreglo[x][y];
    }

    /**
     * Inicializa una matriz de enteros con un tamaño basado en los parámetros de entrada.
     *
     * @param x el tamaño de la primera dimensión de la matriz
     * @param y el tamaño de la segunda dimensión de la matriz
     * @return una matriz de enteros inicializada
     */
    private int[][] inicializarMatriz(int x, int y) {
        // se inicia la matriz con el tamanyo del texto mas 1
        int[][] arreglo = new int[x + VALOR_CONSTANTE_POSICION][y + VALOR_CONSTANTE_POSICION];
        // se carga la matriz en la primera posicion con 1
        for (int i = 0; i <= x; i++) {
            arreglo[i][0] = VALOR_CONSTANTE_POSICION;
        }
        return arreglo;
    }
}
