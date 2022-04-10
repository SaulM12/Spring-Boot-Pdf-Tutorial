package com.example.backend.service;

import com.example.backend.entity.Reserve;
import com.example.backend.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReserveService {

    @Autowired
    ReserveRepository reserveRepository;

    public List<Reserve> list() { return reserveRepository.findAll();}

    public List<Reserve> listByUser_name(String user_name){return reserveRepository.findByUsuario_nombreUsuario(user_name);}

    public void save(Reserve reserve){reserveRepository.save(reserve);}

    public void delete(int id){ reserveRepository.deleteById(id);}
}
