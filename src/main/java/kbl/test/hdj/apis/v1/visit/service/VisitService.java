package kbl.test.hdj.apis.v1.visit.service;

import kbl.test.hdj.apis.v1.visit.payload.SaveVisitRequest;
import kbl.test.hdj.apis.v1.visit.payload.UpdateVisitRequest;
import kbl.test.hdj.apis.v1.visit.payload.VisitorResponse;
import kbl.test.hdj.apis.v1.visit.payload.VisitorsResponse;

public interface VisitService {
    VisitorsResponse getVisitors();

    VisitorResponse getVisitor(Long id);

    Long saveVisit(SaveVisitRequest saveVisitRequest);

    void deleteVisit(Long id);

    Long updateVisit(Long id, UpdateVisitRequest updateVisitRequest);

}
