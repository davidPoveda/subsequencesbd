package com.dpoveda.servicio.subsequencesbd.subsequencesbd.repository;

import com.dpoveda.servicio.subsequencesbd.subsequencesbd.model.Subsequences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubsequencesBDRepository extends JpaRepository<Subsequences, String> {

    @Query("SELECT sb FROM Subsequences sb WHERE sb.secuenciaBase = ?1 AND sb.secuenciaBusqueda = ?2")
    List<Subsequences> findBySubsequencesABuscar(String textoBase, String textoABuscar);
}
