package kbl.test.hdj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MedicalCareType {
    ING("D","약처방"),
    END("T","검사");

    private final String code;
    private final String txt;
}
