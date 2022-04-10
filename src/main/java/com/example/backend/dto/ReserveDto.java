package com.example.backend.dto;


import com.example.backend.security.dto.NuevoUsuario;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class ReserveDto {
    private String tourName;
    @Min(0)
    private int persons;
    private float total;
    private float iva;
    private String name;
    private String mail;
    private TourDto tour;
    private NuevoUsuario usuario;

    public ReserveDto() {
    }

    public ReserveDto(String tourName, @Min(0) int persons, float total, float iva, String name, String mail, TourDto tour, NuevoUsuario usuario) {
        this.tourName = tourName;
        this.persons = persons;
        this.total = total;
        this.iva = iva;
        this.name = name;
        this.mail = mail;
        this.tour = tour;
        this.usuario = usuario;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public TourDto getTour() {
        return tour;
    }

    public void setTour(TourDto tour) {
        this.tour = tour;
    }

    public NuevoUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(NuevoUsuario usuario) {
        this.usuario = usuario;
    }
}
