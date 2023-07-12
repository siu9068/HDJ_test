package kbl.test.hdj.repository;

import kbl.test.hdj.entity.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, PatientRepositoryCustom {
    @EntityGraph(attributePaths = {"hospital"})
    Optional<Patient> findById(Long id);
}
