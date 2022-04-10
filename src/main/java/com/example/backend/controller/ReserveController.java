package com.example.backend.controller;


import com.example.backend.dto.Message;
import com.example.backend.dto.ReserveDto;
import com.example.backend.entity.Reserve;
import com.example.backend.entity.Tour;
import com.example.backend.security.entity.Usuario;
import com.example.backend.security.service.UsuarioService;
import com.example.backend.service.ReserveService;
import com.example.backend.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserve")
@CrossOrigin(origins = "http://localhost:4200")
public class ReserveController {

    @Autowired
    ReserveService reserveService;
    @Autowired
    TourService tourService;
    @Autowired
    UsuarioService usuarioService;



    @GetMapping("/list")
    public ResponseEntity<List<Reserve>> list(){
        List<Reserve> list= reserveService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/user/{user_name}")
    public ResponseEntity<Reserve> getByUser_name(@PathVariable("user_name") String user_name){
        List<Reserve> list= (List<Reserve>) reserveService.listByUser_name(user_name);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create( @RequestBody ReserveDto reserveDto){
        Tour tour = tourService.getOne(reserveDto.getTour().getId()).get();
        Usuario usuario =usuarioService.getByNombreUsuario(reserveDto.getUsuario().getNombreUsuario()).get();
        float total = tour.getCost() * reserveDto.getPersons();
        float iva= (float) (total-total/1.12);
        if(reserveDto.getPersons() > tour.getDisponibility())
            return new ResponseEntity(new Message("Nuestra disponibilidad es menor a su petición"), HttpStatus.BAD_REQUEST);
        if(reserveDto.getPersons()<=0)
            return new ResponseEntity(new Message("El número de personas no puede ser 0"), HttpStatus.BAD_REQUEST);
        Reserve reserve = new Reserve(tour.getName(),reserveDto.getPersons(),total,iva,usuario.getNombre(),usuario.getEmail(),tour,usuario);
        reserveService.save(reserve);
        return new ResponseEntity(new Message("Reserva realizada"),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        reserveService.delete(id);
        return new ResponseEntity(new Message("Hemos cancelado su reserva"), HttpStatus.OK);
    }
}
