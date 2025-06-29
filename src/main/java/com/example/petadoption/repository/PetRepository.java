package com.example.petadoption.repository;

import com.example.petadoption.enumeration.Gender;
import com.example.petadoption.enumeration.PetType;
import com.example.petadoption.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    //@Query("select pet from Pet pet join pet.shelter s where s.id = :authorId" )
    List<Pet> findPetsByShelterId(@Param("shelterId") Long shelterId);
    List<Pet> findPetByType(PetType type);
    List<Pet> findPetsByGender(Gender gender);
}
