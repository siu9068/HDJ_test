package kbl.test.hdj.apis.v1.patient.payload;

import kbl.test.hdj.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SavePatientRequest {
    private Long hospitalId;
    private String name;
    private String regNum;
    private GenderType genderType;
    private String birth;
    private String phone;
}
