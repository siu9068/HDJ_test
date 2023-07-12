package kbl.test.hdj.apis.v1.patient.service;

import kbl.test.hdj.apis.v1.patient.payload.GetPatientsRequest;
import kbl.test.hdj.apis.v1.patient.payload.PatientResponse;
import kbl.test.hdj.apis.v1.patient.payload.SavePatientRequest;
import kbl.test.hdj.apis.v1.patient.payload.UpdatePatientRequest;

public interface PatientService {

    Long savePatient(SavePatientRequest savePatientRequest);

    Long updatePatient(Long id, UpdatePatientRequest updatePatientRequest);

    void deletePatient(Long id);

    PatientResponse getPatient(Long id);

    PatientsResponse getPatients(GetPatientsRequest getPatientsRequest);
}
