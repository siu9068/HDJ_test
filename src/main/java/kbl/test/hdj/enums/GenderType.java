package kbl.test.hdj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderType {

    M("M","남"),
    F("F","여");

    private final String code;
    private final String txt;
}
