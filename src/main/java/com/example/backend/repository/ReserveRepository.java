package com.example.backend.repository;


import com.example.backend.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository <Reserve, Integer> {
    List<Reserve> findByUsuario_nombreUsuario(String user_name);
    boolean existsByUsuario_nombreUsuario(String user_name);

}
