package com.example.backend.entity;



import javax.persistence.*;

@Entity
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String duration;
    private int disponibility;
    private Float cost;
    private String image;
    @ManyToOne
    private Place place;

    public Tour() {
    }

    public Tour(String name, String description, String duration, int disponibility, Float cost,String image, Place place) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.disponibility = disponibility;
        this.cost = cost;
        this.place = place;
        this.image= image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(int disponibility) {
        this.disponibility = disponibility;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
