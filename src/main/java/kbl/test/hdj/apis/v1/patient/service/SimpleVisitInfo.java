package kbl.test.hdj.apis.v1.patient.service;

import kbl.test.hdj.entity.Visit;
import kbl.test.hdj.enums.VisitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleVisitInfo {
    private String hospitalName;
    private LocalDateTime regDate;
    private LocalDateTime updDate;
    private String visitType;

    public SimpleVisitInfo(Visit visit){
        this.hospitalName = visit.getHospital().getHospitalName();
        this.regDate = visit.getRegDate();
        this.updDate = visit.getUpdDate();
        this.visitType = visit.getVisitType().getTxt();
    }
}
