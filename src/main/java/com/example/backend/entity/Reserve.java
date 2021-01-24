package com.example.backend.entity;


import com.example.backend.security.entity.Usuario;

import javax.persistence.*;

@Entity
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int persons;
    private float Total;
    private float Iva;
    private String Name;
    @ManyToOne
    private Tour tour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_name", referencedColumnName = "nombreUsuario")
    private Usuario usuario;

    public Reserve() {
    }

    public Reserve(int persons, float total, float iva, String name, Tour tour, Usuario usuario) {
        this.persons = persons;
        Total = total;
        Iva = iva;
        Name = name;
        this.tour = tour;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
