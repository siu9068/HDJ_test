package kbl.test.hdj.repository;

import kbl.test.hdj.entity.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @EntityGraph(attributePaths = {"hospital"})
    Optional<Patient> findById(Long id);
}
