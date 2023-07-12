package kbl.test.hdj.apis.v1.patient.controller;

import kbl.test.hdj.apis.v1.patient.payload.GetPatientsRequest;
import kbl.test.hdj.apis.v1.patient.payload.PatientResponse;
import kbl.test.hdj.apis.v1.patient.payload.SavePatientRequest;
import kbl.test.hdj.apis.v1.patient.payload.UpdatePatientRequest;
import kbl.test.hdj.apis.v1.patient.service.PatientService;
import kbl.test.hdj.apis.v1.patient.service.PatientsResponse;
import kbl.test.hdj.apis.v1.visit.payload.VisitorsResponse;
import kbl.test.hdj.payload.ResponseEntityCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/patient")
    public ResponseEntity<?> savePatient(@RequestBody SavePatientRequest savePatientRequest){
        try {
            Long id = patientService.savePatient(savePatientRequest);
            return ResponseEntityCustom.of(HttpStatus.CREATED,id);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody UpdatePatientRequest updatePatientRequest){
        try {
            Long result = patientService.updatePatient(id,updatePatientRequest);
            return ResponseEntityCustom.of(HttpStatus.CREATED,result);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @DeleteMapping("/patient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id){
        try {
            patientService.deletePatient(id);
            return ResponseEntityCustom.of(HttpStatus.NO_CONTENT,null);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<?> getPatient(@PathVariable Long id){
        try {
            PatientResponse patientResponse = patientService.getPatient(id);
            return ResponseEntityCustom.of(HttpStatus.OK,patientResponse);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @GetMapping("/patients")
    public ResponseEntity<?> getPatients(@RequestBody GetPatientsRequest getPatientsRequest){
        try {
            PatientsResponse patientsResponse = patientService.getPatients(getPatientsRequest);
            return ResponseEntityCustom.of(HttpStatus.OK,patientsResponse);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.NOT_FOUND,e.getMessage());
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

}
