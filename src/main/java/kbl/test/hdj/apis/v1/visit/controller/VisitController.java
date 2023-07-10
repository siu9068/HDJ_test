package kbl.test.hdj.apis.v1.visit.controller;

import kbl.test.hdj.apis.v1.visit.payload.SaveVisitRequest;
import kbl.test.hdj.apis.v1.visit.payload.UpdateVisitRequest;
import kbl.test.hdj.apis.v1.visit.payload.VisitorResponse;
import kbl.test.hdj.apis.v1.visit.payload.VisitorsResponse;
import kbl.test.hdj.apis.v1.visit.service.VisitService;
import kbl.test.hdj.payload.ResponseEntityCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class VisitController {

    private final VisitService visitService;

    @GetMapping("/visitors")
    public ResponseEntity<?> getVisitors(){
        try {
            VisitorsResponse visitorsResponse = visitService.getVisitors();
            return ResponseEntityCustom.of(HttpStatus.OK,visitorsResponse);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @GetMapping("/visit/{id}")
    public ResponseEntity<?> getVisitor(@PathVariable Long id){
        try {
            VisitorResponse visitorResponse = visitService.getVisitor(id);
            return ResponseEntityCustom.of(HttpStatus.OK,visitorResponse);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @PostMapping("/visit")
    public ResponseEntity<?> saveVisit(@RequestBody SaveVisitRequest saveVisitRequest){
        try {
            Long id = visitService.saveVisit(saveVisitRequest);
            return ResponseEntityCustom.of(HttpStatus.CREATED,id);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @PatchMapping("/visit/{id}")
    public ResponseEntity<?> updateVisit(@PathVariable Long id, @RequestBody UpdateVisitRequest updateVisitRequest){
        try {
            Long result = visitService.updateVisit(id,updateVisitRequest);
            return ResponseEntityCustom.of(HttpStatus.CREATED,result);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @DeleteMapping("/visit/{id}")
    public ResponseEntity<?> deleteVisit(@PathVariable Long id){
        try {
            visitService.deleteVisit(id);
            return ResponseEntityCustom.of(HttpStatus.NO_CONTENT,null);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

}
