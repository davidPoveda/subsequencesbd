package com.dpoveda.servicio.subsequencesbd.subsequencesbd.controller;

import com.dpoveda.servicio.subsequencesbd.subsequencesbd.services.SubsequencesBDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar las subsecuencias.
 */
@RestController
@RequestMapping("/subsequences")
public class SubsequencesBDController {

    // atributo que representa el servicio encargado de calcular la subsecuencias
    @Autowired
    public SubsequencesBDService subsequencesBDService;

    /**
     * Servicio rest tipo get para obtener la cantidad de subsecuencias distintas.
     *
     * @param tipoS La cadena fuente.
     * @param tipoT La subsecuencia a buscar.
     * @return ResponseEntity con la cantidad de subsecuencias distintas.
     */
    @GetMapping("/algoritmo/{tipoS}/{tipoT}")
    public ResponseEntity distinctSubSequences(@PathVariable String tipoS, @PathVariable String tipoT) {

        return ResponseEntity.ok().body(
                subsequencesBDService.cantidadSubsequences(tipoS, tipoT));
    }
}
