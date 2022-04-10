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
import com.example.backend.service.PdfService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/place")
@CrossOrigin(origins="http://localhost:4200")
public class PlaceController {

    private final PlaceService placeService;
    private final PdfService pdfService;
    
    @Autowired
    public PlaceController(PlaceService placeService, PdfService pdfService) {
        this.placeService = placeService;
        this.pdfService = pdfService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Place>> list(){
        List<Place> list  = placeService.list();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id")int id){
        Optional<Place> place =  placeService.getOne(id);
        if (!place.isPresent())
                return new ResponseEntity<>(new Message("No existe econtrado"), HttpStatus.NOT_FOUND);
        Place foundPlace = place.get();
        return new ResponseEntity<>(foundPlace, HttpStatus.OK);
    }
    @GetMapping("/detailname/{name}")
    public ResponseEntity<Object> getByName(@PathVariable("name")String name){
        Optional<Place> optionalPlace= placeService.getByName(name);
        if (!optionalPlace.isPresent())
            return new ResponseEntity<>(new Message("No existe este lugar"), HttpStatus.NOT_FOUND);
        Place place = optionalPlace.get();
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody PlaceDto placeDto){
        if(StringUtils.isBlank(placeDto.getName()))
            return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(placeService.existByName(placeDto.getName()))
            return new ResponseEntity<>(new Message("Ese lugar ya existe"), HttpStatus.BAD_REQUEST);
        Place place= new Place(placeDto.getName(),placeDto.getDescription(),placeDto.getUbication(),placeDto.getImage1()
        , placeDto.getImage2(), placeDto.getImage3());
        placeService.save(place);
        return new ResponseEntity<>(new Message("Lugar creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestBody PlaceDto placeDto){
        Optional<Place> placeOptional= placeService.getOne(id);
        if (!placeOptional.isPresent())
            return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(placeService.existByName(placeDto.getName()) && placeService.getByName(placeDto.getName()).get().getId() != id)
            return new ResponseEntity<>(new Message("Ese lugar ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(placeDto.getName()))
            return new ResponseEntity<>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Place place= placeOptional.get();
        place.setName(placeDto.getName());
        place.setDescription(placeDto.getDescription());
        place.setUbication(placeDto.getUbication());
        place.setImage1(placeDto.getImage1());
        place.setImage2(placeDto.getImage2());
        place.setImage3(placeDto.getImage3());
        placeService.save(place);
        return new ResponseEntity<>(new Message("Lugar actualizado"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id")int id){
        if (!placeService.existById(id))
            return new ResponseEntity<>(new Message("No existe"), HttpStatus.NOT_FOUND);
        placeService.delete(id);
        return new ResponseEntity<>(new Message("Lugar eliminado"), HttpStatus.OK);
    }

    @GetMapping("/pdf")
    public void downloadPdf(HttpServletResponse response){
        try{
            Path file = Paths.get(pdfService.generatePlacesPdf().getAbsolutePath());
            if (Files.exists(file)){
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename"+ file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
