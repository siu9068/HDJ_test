package kbl.test.hdj.apis.v1.patient.service;

import kbl.test.hdj.apis.v1.patient.payload.PatientResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatientsResponse {
    private Long totalCount;
    private List<PatientDTO> patientsList;
}
