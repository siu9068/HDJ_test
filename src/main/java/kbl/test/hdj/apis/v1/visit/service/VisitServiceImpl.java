package kbl.test.hdj.apis.v1.visit.service;

import kbl.test.hdj.apis.v1.visit.payload.SaveVisitRequest;
import kbl.test.hdj.apis.v1.visit.payload.UpdateVisitRequest;
import kbl.test.hdj.apis.v1.visit.payload.VisitorResponse;
import kbl.test.hdj.apis.v1.visit.payload.VisitorsResponse;
import kbl.test.hdj.entity.Patient;
import kbl.test.hdj.entity.Visit;
import kbl.test.hdj.repository.PatientRepository;
import kbl.test.hdj.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class VisitServiceImpl implements VisitService{

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;

    @Override
    public VisitorsResponse getVisitors() {
        List<Visit> visitList = visitRepository.findAll();
        return VisitorsResponse.of(visitList);
    }

    @Override
    public VisitorResponse getVisitor(Long id) {
        Visit visit = visitRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 id에 해당하는 방문자가 없습니다."));
        return new VisitorResponse(visit);
    }

    @Override
    @Transactional
    public Long saveVisit(SaveVisitRequest saveVisitRequest) {
        Patient patient = patientRepository.findById(saveVisitRequest.getPatientId()).orElseThrow(
                ()-> new IllegalArgumentException("해당 id에 해당하는 환자가 존재하지 않습니다."));
        Visit visit = Visit.builder()
                .visitType(saveVisitRequest.getVisitType())
                .patient(patient)
                .hospital(patient.getHospital())
                .build();
        return visitRepository.save(visit).getId();
    }

    @Override
    @Transactional
    public void deleteVisit(Long id) {
        Visit visit = visitRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 id에 해당하는 방문자가 없습니다."));
        visitRepository.delete(visit);
    }

    @Override
    @Transactional
    public Long updateVisit(Long id, UpdateVisitRequest updateVisitRequest) {
        Visit visit = visitRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 id에 해당하는 방문자가 없습니다."));
        visit.updateVisitType(updateVisitRequest.getVisitType());
        return visitRepository.save(visit).getId();
    }


}
