package com.example.backend.entity;


import com.example.backend.security.entity.Usuario;

import javax.persistence.*;

@Entity
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tourName;
    private int persons;
    private float total;
    private float iva;
    private String name;
    private String mail;
    @ManyToOne
    private Tour tour;
    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "nombreUsuario")
    private Usuario usuario;

    public Reserve() {
    }

    public Reserve(String tourName, int persons, float total, float iva, String name, String mail, Tour tour, Usuario usuario) {
        this.tourName = tourName;
        this.persons = persons;
        this.total = total;
        this.iva = iva;
        this.name = name;
        this.mail = mail;
        this.tour = tour;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
