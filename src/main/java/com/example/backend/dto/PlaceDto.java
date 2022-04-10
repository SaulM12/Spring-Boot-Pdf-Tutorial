package com.example.backend.dto;
import com.example.backend.entity.Place;

import javax.validation.constraints.NotBlank;

public class PlaceDto {

    @NotBlank
    private int id;
    private String name;
    private String description;
    private String ubication;
    private String image1;
    private String image2;
    private String image3;

    public PlaceDto() {
    }

    public PlaceDto(@NotBlank int id, String name, String description, String ubication, String image1, String image2, String image3) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ubication = ubication;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

    public PlaceDto(Place place) {
        this.id= place.getId();
        this.name = place.getName();
        this.description = place.getDescription();
        this.ubication = place.getUbication();
        this.image1 = place.getImage1();
        this.image2 = place.getImage2();
        this.image3 = place.getImage3();
    }

    public int getId() {
        return id;
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

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }
}
