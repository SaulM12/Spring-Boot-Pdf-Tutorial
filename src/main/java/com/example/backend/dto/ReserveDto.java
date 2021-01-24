package com.example.backend.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class ReserveDto {
    @Min(1)
    private int persons;
    @Min(0)
    private float Total;
    @Min(0)
    private float Iva;
    @NotBlank
    private String Name;
    private int tour;
    private String usuario;

    public ReserveDto() {
    }

    public ReserveDto(@Min(1) int persons, @Min(0) float total, @Min(0) float iva, @NotBlank String name, int tour, String usuario) {
        this.persons = persons;
        Total = total;
        Iva = iva;
        Name = name;
        this.tour = tour;
        this.usuario = usuario;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }

    public float getIva() {
        return Iva;
    }

    public void setIva(float iva) {
        Iva = iva;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
