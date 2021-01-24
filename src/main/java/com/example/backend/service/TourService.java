package com.example.backend.service;


import com.example.backend.entity.Place;
import com.example.backend.entity.Tour;
import com.example.backend.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TourService {

    @Autowired
    TourRepository tourRepository;

    public List<Tour> list() {
        return tourRepository.findAll();
    }

    public Optional<Tour> getOne(int id) {
        return tourRepository.findById(id);
    }

    public List<Tour> listByPlace(int place_id) {
        return tourRepository.findByPlace_id(place_id);
    }
    public Optional<Tour> getByName(String name){
        return tourRepository.findByName(name);
    }

    public void save(Tour tour) {
        tourRepository.save(tour);
    }

    public void delete(int id) {
        tourRepository.deleteById(id);
    }

    public boolean existById(int id) {
        return tourRepository.existsById(id);
    }

    public boolean existByPlace(int id) {
        return tourRepository.existsByPlace_id(id);
    }

    public boolean existByName(String name) {
        return tourRepository.existsByName(name);
    }
}
