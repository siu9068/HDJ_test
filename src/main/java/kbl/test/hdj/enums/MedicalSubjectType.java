package kbl.test.hdj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MedicalSubjectType {
    MEDICINE("01","내과"),
    OPHTHALMOLOGY("02","안과");

    private final String code;
    private final String txt;

}
