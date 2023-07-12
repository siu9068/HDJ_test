package kbl.test.hdj.apis.v1.patient.payload;

import kbl.test.hdj.apis.v1.patient.service.SimpleVisitInfo;
import kbl.test.hdj.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    private Long id;
    private String name;
    private String regNum;
    private String genderType;
    private String birth;
    private String phone;
    private List<SimpleVisitInfo> visits;

    public static PatientResponse of (Patient patient){
        List<SimpleVisitInfo> visits = patient.getVisits().stream().map(SimpleVisitInfo::new).toList();
        return PatientResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .regNum(patient.getRegNum())
                .genderType(patient.getGenderType().getTxt())
                .birth(patient.getBirth())
                .phone(patient.getPhone())
                .visits(visits)
                .build();
    }

}
