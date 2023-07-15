package kbl.test.hdj.apis.v1.visit.payload;

import kbl.test.hdj.enums.VisitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVisitRequest {
    private VisitType visitType;
}
