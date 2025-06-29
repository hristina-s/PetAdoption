//package com.example.petadoption.config;
//
//import com.example.petadoption.enumeration.Gender;
//import com.example.petadoption.enumeration.PetType;
//import com.example.petadoption.model.Pet;
//import com.example.petadoption.repository.PetRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final PetRepository petRepository;
//
//    public DataInitializer(PetRepository petRepository) {
//        this.petRepository = petRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//            Pet pet1 = new Pet("Luna", "3", Gender.FEMALE, PetType.CAT, null, null);
//            Pet pet2 = new Pet("Karo", "4", Gender.MALE, PetType.DOG, null, null);
//
//            petRepository.save(pet1);
//            petRepository.save(pet2);
//
//    }
//}
