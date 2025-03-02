package com.dpoveda.servicio.subsequencesbd.subsequencesbd.model;


import javax.persistence.*;

@Entity
@Table(name="subsequence")
public class Subsequences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "SEQUENCE_BASE")
    private String secuenciaBase;

    @Column(name = "SEQUENCE_SEARCH")
    private String secuenciaBusqueda;

    @Column(name = "AMOUNT")
    private Integer cantidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecuenciaBase() {
        return secuenciaBase;
    }

    public void setSecuenciaBase(String secuenciaBase) {
        this.secuenciaBase = secuenciaBase;
    }

    public String getSecuenciaBusqueda() {
        return secuenciaBusqueda;
    }

    public void setSecuenciaBusqueda(String secuenciaBusqueda) {
        this.secuenciaBusqueda = secuenciaBusqueda;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
