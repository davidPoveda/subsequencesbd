package com.dpoveda.servicio.subsequencesbd.subsequencesbd.services;

import com.dpoveda.servicio.subsequencesbd.subsequencesbd.model.Subsequences;
import com.dpoveda.servicio.subsequencesbd.subsequencesbd.repository.SubsequencesBDRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para manejar subsecuencias.
 */
@Service
public class SubsequencesBDService {

    private static final Integer VALOR_MINIMO_COINCIDENCIAS = 0;
    private static final Integer VALOR_CONSTANTE_POSICION = 1;

    @Autowired
    private SubsequencesBDRepository subsequencesBDRepository;

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

        // se buscan los registros que existan en base de datos y que cumplan las dos condiciones
        if (textoBase.length() < 50 || textoABuscar.length() < 50) {
            // se realiza la busqueda de la subsequences si ya existe
            Subsequences subsequencesReturn = consultarSubsequenceList(textoBase, textoABuscar);
            if (subsequencesReturn != null) {
                return subsequencesReturn.getCantidad();
            }
        }
        int cantidadSubsequences = calcularCantidadSubsequences(textoBase, textoABuscar);
        // se almacena los datos en BBDD paso a metodo
        almacenarRegistroSubsequence(textoBase,textoABuscar, cantidadSubsequences);
        return cantidadSubsequences;
    }

    /**
     * Metodo encargado de realizar el calculo de la cantidad de subsequences
     * @param textoBase
     * @param textoABuscar
     * @return
     */
    private int calcularCantidadSubsequences(String textoBase, String textoABuscar) {
        int x = textoBase.length();
        int y = textoABuscar.length();
        int[][] arreglo = inicializarMatriz(x,y);
        // se recorren la matriz a partir de los valores ingresados prueba de escritorio
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
     * Metodo encargado de almacenar el registro subsequences
     * @param textoBase
     * @param textoABuscar
     * @param valor
     */
    private void almacenarRegistroSubsequence(String textoBase, String textoABuscar, int valor) {
        Subsequences subsequencias = new Subsequences();
        subsequencias.setSecuenciaBase(textoBase);
        subsequencias.setSecuenciaBusqueda(textoABuscar);
        subsequencias.setCantidad(valor);
        subsequencesBDRepository.save(subsequencias);
    }

    /**
     * Metodo encargado de recuperar la subsequeces si existe para los datos de entrada
     * @param textoBase
     * @param textoABuscar
     * @return
     */
    private Subsequences consultarSubsequenceList(String textoBase, String textoABuscar) {
        List<Subsequences> lista = subsequencesBDRepository.findBySubsequencesABuscar(textoBase, textoABuscar);
        return lista != null && !lista.isEmpty() ? lista.get(0) : null;
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
