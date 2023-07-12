package kbl.test.hdj.repository;

import kbl.test.hdj.apis.v1.patient.payload.GetPatientsRequest;
import kbl.test.hdj.apis.v1.patient.service.PatientDTO;
import kbl.test.hdj.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PatientRepositoryCustom{
    Page<PatientDTO> getPatients(GetPatientsRequest getPatientsRequest, Pageable pageable);
}
