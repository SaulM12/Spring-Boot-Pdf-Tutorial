package com.example.backend.dto;

import com.example.backend.entity.Place;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class TourDto {

    @NotBlank
    private String name;
    private String description;
    private String duration;
    @Min(0)
    private int disponibility;
    @Min(0)
    private Float cost;
    private String image;
    private int place;

    public TourDto() {
    }

    public TourDto(@NotBlank String name, String description, String duration, @Min(0) int disponibility, @Min(0) Float cost, String image, int place) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.disponibility = disponibility;
        this.cost = cost;
        this.image = image;
        this.place = place;
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

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
