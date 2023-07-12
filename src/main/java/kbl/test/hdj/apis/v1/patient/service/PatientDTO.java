package kbl.test.hdj.apis.v1.patient.service;

import com.querydsl.core.annotations.QueryProjection;
import kbl.test.hdj.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@Getter
public class PatientDTO {
    private Long id;
    private String name;
    private String regNum;
    private String genderType;
    private String birth;
    private String phone;
    private LocalDate visitDate;

    @QueryProjection
    public PatientDTO(Long id, String name, String regNum, String genderType, String birth, String phone, LocalDate visitDate) {
        this.id = id;
        this.name = name;
        this.regNum = regNum;
        this.genderType = GenderType.valueOf(genderType).getTxt();
        this.birth = birth;
        this.phone = phone;
        this.visitDate = visitDate;
    }
}
