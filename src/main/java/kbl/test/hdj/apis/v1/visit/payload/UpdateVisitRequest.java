package kbl.test.hdj.apis.v1.visit.payload;

import kbl.test.hdj.enums.VisitType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateVisitRequest {
    private VisitType visitType;
}
