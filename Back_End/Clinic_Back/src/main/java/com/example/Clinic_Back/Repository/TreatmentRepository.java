
package com.example.Clinic_Back.Repository;

import com.example.Clinic_Back.Entity.Treatment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends MongoRepository<Treatment, String> {
    List<Treatment> findByPatientId(String patientId);
}
