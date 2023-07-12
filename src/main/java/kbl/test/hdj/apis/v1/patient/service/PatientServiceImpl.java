package kbl.test.hdj.apis.v1.patient.service;

import kbl.test.hdj.apis.v1.patient.payload.GetPatientsRequest;
import kbl.test.hdj.apis.v1.patient.payload.PatientResponse;
import kbl.test.hdj.apis.v1.patient.payload.SavePatientRequest;
import kbl.test.hdj.apis.v1.patient.payload.UpdatePatientRequest;
import kbl.test.hdj.entity.Hospital;
import kbl.test.hdj.entity.Patient;
import kbl.test.hdj.paging.PageObject;
import kbl.test.hdj.repository.HospitalRepository;
import kbl.test.hdj.repository.PatientRepository;
import kbl.test.hdj.utils.CommonUtil;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;


    @Override
    @Transactional
    public Long savePatient(SavePatientRequest savePatientRequest) {
        Hospital hospital = hospitalRepository.findById(savePatientRequest.getHospitalId())
                .orElseThrow(()->new IllegalArgumentException("병원 id가 존재하지 않습니다."));
        Patient patient = Patient.builder()
                .hospital(hospital)
                .name(savePatientRequest.getName())
                .regNum(CommonUtil.getRandomString(0,13))
                .birth(savePatientRequest.getBirth())
                .phone(savePatientRequest.getPhone())
                .genderType(savePatientRequest.getGenderType())
                .build();
        return patientRepository.save(patient).getId();
    }

    @Override
    @Transactional
    public Long updatePatient(Long id, UpdatePatientRequest updatePatientRequest) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("환자 id가 존재하지 않습니다."));
        patient.updateInfo(updatePatientRequest);
        return patientRepository.save(patient).getId();
    }

    @Override
    @Transactional
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("환자 id가 존재하지 않습니다."));
        patientRepository.delete(patient);
    }

    @Override
    public PatientResponse getPatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("환자 id가 존재하지 않습니다."));
        return PatientResponse.of(patient);
    }

    @Override
    public PatientsResponse getPatients(GetPatientsRequest getPatientsRequest) {
        Pageable pageable = PageRequest.of(getPatientsRequest.getPageNo() + PageObject.INIT_PAGE,getPatientsRequest.getPageSize());
        Page<PatientDTO> patientList = patientRepository.getPatients(getPatientsRequest,pageable);
        if(patientList.getContent().isEmpty()){
            throw new NoSuchElementException("검색결과가 없습니다.");
        }
        return PatientsResponse.builder()
                .totalCount(patientList.getTotalElements())
                .patientsList(patientList.getContent())
                .build();
    }
}
