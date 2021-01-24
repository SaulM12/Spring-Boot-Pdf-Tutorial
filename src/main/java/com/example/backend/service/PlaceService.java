package com.example.backend.service;

import com.example.backend.entity.Place;
import com.example.backend.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    public List<Place> list(){
        return placeRepository.findAll();
    }

    public Optional<Place> getOne(int id){
        return placeRepository.findById(id);
    }

    public  Optional<Place> getByName(String name){
        return placeRepository.findByName(name);
    }

    public void save(Place place){
        placeRepository.save(place);
    }
    public void delete(int id){
        placeRepository.deleteById(id);
    }

    public boolean existById(int id){
        return placeRepository.existsById(id);
    }

    public boolean existByName(String name){
        return placeRepository.existsByName(name);
    }


}
