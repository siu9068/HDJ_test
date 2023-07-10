package kbl.test.hdj.apis.v1.visit.service;

import kbl.test.hdj.apis.v1.visit.payload.SaveVisitRequest;
import kbl.test.hdj.apis.v1.visit.payload.UpdateVisitRequest;
import kbl.test.hdj.apis.v1.visit.payload.VisitorResponse;
import kbl.test.hdj.apis.v1.visit.payload.VisitorsResponse;
import kbl.test.hdj.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class VisitServiceImpl implements VisitService{

    private final VisitRepository visitRepository;

    @Override
    public VisitorsResponse getVisitors() {

        return null;
    }

    @Override
    public VisitorResponse getVisitor(String id) {
        return null;
    }

    @Override
    public Long saveVisit(SaveVisitRequest saveVisitRequest) {
        return null;
    }

    @Override
    public void deleteVisit(String id) {

    }

    @Override
    public Long updateVisit(String id, UpdateVisitRequest updateVisitRequest) {
        return null;
    }


}
