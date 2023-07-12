package kbl.test.hdj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchType {
    NAME("NAME","이름"),
    REG_NUM("REG_NUM","환자등록번호"),
    BIRTH("BIRTH","생년월일");

    private final String code;
    private final String txt;
}
