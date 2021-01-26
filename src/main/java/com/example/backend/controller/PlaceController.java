package com.example.backend.controller;

import com.example.backend.dto.Message;
import com.example.backend.dto.PlaceDto;
import com.example.backend.entity.Place;
import com.example.backend.service.PlaceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
@CrossOrigin(origins="http://localhost:4200")
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @GetMapping("/list")
    public ResponseEntity<List<Place>> list(){
        List<Place> list  = placeService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Place> getById(@PathVariable("id")int id){
        if (!placeService.existById(id))
                return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Place place = placeService.getOne(id).get();
        return new ResponseEntity(place, HttpStatus.OK);
    }
    @GetMapping("/detailname/{name}")
    public ResponseEntity<Place> getByName(@PathVariable("name")String name){
        if (!placeService.existByName(name))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Place place = placeService.getByName(name).get();
        return new ResponseEntity(place, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PlaceDto placeDto){
        if(StringUtils.isBlank(placeDto.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(placeService.existByName(placeDto.getName()))
            return new ResponseEntity(new Message("Ese lugar ya existe"), HttpStatus.BAD_REQUEST);
        Place place= new Place(placeDto.getName(),placeDto.getDescription(),placeDto.getUbication(),placeDto.getImage1()
        , placeDto.getImage2(), placeDto.getImage3());
        placeService.save(place);
        return new ResponseEntity(new Message("Lugar creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody PlaceDto placeDto){
        if (!placeService.existById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(placeService.existByName(placeDto.getName()) && placeService.getByName(placeDto.getName()).get().getId() != id)
            return new ResponseEntity(new Message("Ese lugar ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(placeDto.getName()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Place place= placeService.getOne(id).get();
        place.setName(placeDto.getName());
        place.setDescription(placeDto.getDescription());
        place.setUbication(placeDto.getUbication());
        place.setImage1(placeDto.getImage1());
        place.setImage2(placeDto.getImage2());
        place.setImage3(placeDto.getImage3());
        placeService.save(place);
        return new ResponseEntity(new Message("Lugar actualizado"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if (!placeService.existById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        placeService.delete(id);
        return new ResponseEntity(new Message("Lugar eliminado"), HttpStatus.OK);
    }
}
