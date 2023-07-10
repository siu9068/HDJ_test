package kbl.test.hdj.apis.v1.visit.payload;

import kbl.test.hdj.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class VisitorResponse {
    private String hospitalName;
    private String patientName;
    private String regNum;
    private String genderType;
    private String birth;
    private String visitType;
    private LocalDateTime regDate;
    private LocalDateTime updDate;
    public VisitorResponse(Visit visit) {
        this.hospitalName = visit.getHospital().getHospitalName();
        this.patientName = visit.getPatient().getName();
        this.regNum = visit.getPatient().getRegNum();
        this.genderType = visit.getPatient().getGenderType().getTxt();
        this.birth = visit.getPatient().getBirth();
        this.visitType = visit.getVisitType().getTxt();
        this.regDate = visit.getRegDate();
        this.updDate = visit.getUpdDate();
    }
}
