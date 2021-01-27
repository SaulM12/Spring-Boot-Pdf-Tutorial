package com.example.backend.controller;

import com.example.backend.dto.Message;
import com.example.backend.dto.TourDto;
import com.example.backend.entity.Place;
import com.example.backend.entity.Tour;
import com.example.backend.service.PlaceService;
import com.example.backend.service.TourService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tour")
@CrossOrigin(origins="http://localhost:4200")
public class TourController {

    @Autowired
    TourService tourService;
    @Autowired
    PlaceService placeService;

    @GetMapping("/list")
    public ResponseEntity<List<Tour>> list(){
        List<Tour> list= tourService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<Tour> getById(@PathVariable("id")int id){
        if(!tourService.existById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Tour tour = tourService.getOne(id).get();
        return new ResponseEntity(tour,HttpStatus.OK);
    }
    @GetMapping("/place/{place_id}")
    public ResponseEntity<Tour> getByPlace_id(@PathVariable("place_id")int place_id){
        if (!tourService.existByPlace(place_id))
        return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        List<Tour> list= (List<Tour>) tourService.listByPlace(place_id);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TourDto tourDto){
        Place place = placeService.getOne(tourDto.getPlace().getId()).get();
        if(StringUtils.isBlank(tourDto.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(tourService.existByName(tourDto.getName()))
            return new ResponseEntity(new Message("Ese tour ya existe"), HttpStatus.BAD_REQUEST);
        if(tourDto.getDisponibility()<=0)
            return new ResponseEntity(new Message("Debe asignar un número de cupos válido"), HttpStatus.BAD_REQUEST);
        if(tourDto.getCost()==null || tourDto.getCost()<=0)
            return new ResponseEntity(new Message("El costo del tour no puede ser 0"), HttpStatus.BAD_REQUEST);

        Tour tour = new Tour(tourDto.getName(),tourDto.getDescription(),tourDto.getDuration(),tourDto.getDisponibility()
        ,tourDto.getCost(),tourDto.getImage(),place);
        tourService.save(tour);

        return new ResponseEntity(new Message("Tour creado"),HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody TourDto tourDto){
        if (!tourService.existById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(tourService.existByName(tourDto.getName()) && tourService.getByName(tourDto.getName()).get().getId() != id)
            return new ResponseEntity(new Message("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(tourDto.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(tourDto.getDisponibility()<0)
            return new ResponseEntity(new Message("Debe asignar un número de cupos válido"), HttpStatus.BAD_REQUEST);
        if(tourDto.getCost()==null || tourDto.getCost()<=0)
            return new ResponseEntity(new Message("El costo del tour no puede ser 0"), HttpStatus.BAD_REQUEST);

        Tour tour= tourService.getOne(id).get();
        tour.setName(tourDto.getName());
        tour.setDescription(tourDto.getDescription());
        tour.setDisponibility(tourDto.getDisponibility());
        tour.setDuration(tourDto.getDuration());
        tour.setCost(tourDto.getCost());
        tour.setImage(tourDto.getImage());
        tourService.save(tour);
        return new ResponseEntity(new Message("Tour actualizado"), HttpStatus.OK);
    }
    @DeleteMapping("/delet/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!tourService.existById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        tourService.delete(id);
        return new ResponseEntity(new Message("Tour eliminado"), HttpStatus.OK);
    }

}
