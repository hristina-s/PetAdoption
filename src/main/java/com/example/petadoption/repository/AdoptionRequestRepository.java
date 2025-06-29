package com.example.petadoption.repository;

import com.example.petadoption.model.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {

    //@Query("select r from AdoptionRequest r join r.user u where u.username = :username ")
    List<AdoptionRequest> findAdoptionRequestsByUserUsername(@Param("username") String username);
}
