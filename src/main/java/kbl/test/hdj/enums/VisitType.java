package kbl.test.hdj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum VisitType {
    ING("1","방문중"),
    END("2","종료"),
    CANCEL("3","취소");

    private final String code;
    private final String txt;

    public static VisitType of(String code) {
        return Arrays.stream(VisitType.values())
                .filter(t -> t.getCode().equals(code))
                .findAny().orElse(null);
    }
}
