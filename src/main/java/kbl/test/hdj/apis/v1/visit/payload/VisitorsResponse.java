package kbl.test.hdj.apis.v1.visit.payload;

import kbl.test.hdj.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VisitorsResponse {

    private List<VisitorResponse> visitors;

    public static VisitorsResponse of (List<Visit> visitList){
        return new VisitorsResponse(visitList.stream().map(VisitorResponse::new).collect(Collectors.toList()));
    }

}
