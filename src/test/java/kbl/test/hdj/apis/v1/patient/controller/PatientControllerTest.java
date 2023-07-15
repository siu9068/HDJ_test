package kbl.test.hdj.apis.v1.patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kbl.test.hdj.apis.v1.patient.payload.GetPatientsRequest;
import kbl.test.hdj.apis.v1.patient.payload.PatientResponse;
import kbl.test.hdj.apis.v1.patient.payload.SavePatientRequest;
import kbl.test.hdj.apis.v1.patient.payload.UpdatePatientRequest;
import kbl.test.hdj.apis.v1.patient.service.PatientDTO;
import kbl.test.hdj.apis.v1.patient.service.PatientService;
import kbl.test.hdj.apis.v1.patient.service.PatientsResponse;
import kbl.test.hdj.apis.v1.patient.service.SimpleVisitInfo;
import kbl.test.hdj.enums.GenderType;
import kbl.test.hdj.enums.VisitType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("환자 전체 목록 조회")
    @Test
    void getPatients() throws Exception {

        PatientDTO patientDTO1 = new PatientDTO(1L,"이광복","1234567123456",GenderType.M.getCode(),"2010-06-08","01012345678", LocalDate.now());
        PatientDTO patientDTO2 = new PatientDTO(2L,"이순덕","1234532623455",GenderType.M.getCode(),"2010-03-15","01033345678",LocalDate.now());

        given(patientService.getPatients(any()))
                .willReturn(PatientsResponse.builder()
                        .totalCount(2L)
                        .patientsList(List.of(patientDTO1, patientDTO2)).build());

        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("pageNo","0");
        requestParam.set("pageSize","10");
        requestParam.set("searchType","NAME");
        requestParam.set("searchKeyword",null);

        mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/api/v1/patients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .params(requestParam)
                )
                .andExpect(status().isOk())
                .andDo(
                        document("get-patients",
                                pathParameters(
                                        RequestDocumentation.parameterWithName("pageNo").description("현재 페이지").optional(),
                                        RequestDocumentation.parameterWithName("pageSize").description("페이지당 데이터 수량").optional(),
                                        RequestDocumentation.parameterWithName("searchType").description("검색 조건").optional(),
                                        RequestDocumentation.parameterWithName("searchKeyword").description("검색어").optional()
                                ),
                                responseFields(
                                        fieldWithPath("totalCount").description("전체 데이터 수량"),
                                        fieldWithPath("patientsList[].id").description("환자 고유값"),
                                        fieldWithPath("patientsList[].name").description("환자명"),
                                        fieldWithPath("patientsList[].regNum").description("환자등록번호"),
                                        fieldWithPath("patientsList[].genderType").description("성별"),
                                        fieldWithPath("patientsList[].birth").description("생년월일"),
                                        fieldWithPath("patientsList[].phone").description("휴대폰번호"),
                                        fieldWithPath("patientsList[].visitDate").description("방문일")
                                )
                        )
                );

    }

    @DisplayName("환자 단일 조회")
    @Test
    void getPatient() throws Exception {
        SimpleVisitInfo simpleVisitInfo = SimpleVisitInfo.builder()
            .hospitalName("송파성모내과의원")
            .visitType(VisitType.ING.getTxt())
            .updDate(LocalDateTime.now())
            .regDate(LocalDateTime.now())
            .build();

        PatientResponse patientResponse = PatientResponse.builder()
                .id(1L)
                .name("이광복")
                .birth("2003-06-08")
                .genderType("남")
                .regNum("1234566543215")
                .phone("01012345678")
                .visits(List.of(simpleVisitInfo))
                .build();

        given(patientService.getPatient(ArgumentMatchers.any()))
                .willReturn(patientResponse);

        long patientId = 1L;
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/patient/{id}",patientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document("get-patient",
                                pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("환자 고유값")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("병원명"),
                                        fieldWithPath("name").description("환자명"),
                                        fieldWithPath("birth").description("생년월일"),
                                        fieldWithPath("genderType").description("성별"),
                                        fieldWithPath("regNum").description("환자등록번호"),
                                        fieldWithPath("phone").description("휴대전화번호"),
                                        fieldWithPath("visits[].hospitalName").description("병원명"),
                                        fieldWithPath("visits[].visitType").description("방문상태"),
                                        fieldWithPath("visits[].updDate").description("수정일자"),
                                        fieldWithPath("visits[].regDate").description("등록일자")
                                )
                        )
                );
    }

    @DisplayName("환자 등록")
    @Test
    void savePatient() throws Exception{
        SavePatientRequest savePatientRequest = new SavePatientRequest(1L,"이순덕","1234532623455",GenderType.M,"2010-03-15","01033345678");

        given(patientService.savePatient(savePatientRequest))
                .willReturn(1L);

        String content = objectMapper.writeValueAsString(savePatientRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/patient")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(
                        document("post-patient",
                                requestFields(
                                        fieldWithPath("hospitalId").description("병원 고유값"),
                                        fieldWithPath("name").description("환자명"),
                                        fieldWithPath("regNum").description("환자등록번호"),
                                        fieldWithPath("genderType").description("성별"),
                                        fieldWithPath("birth").description("생년월일"),
                                        fieldWithPath("phone").description("휴대전화번호")
                                )
                        )
                );

    }

    @DisplayName("환자 수정")
    @Test
    void updatePatient() throws Exception{
        long patientId = 1L;
        UpdatePatientRequest updatePatientRequest = new UpdatePatientRequest("이순덕","1234532623455",GenderType.M,"2010-03-15","01033345678");

        given(patientService.updatePatient(patientId,updatePatientRequest))
                .willReturn(1L);

        String content = objectMapper.writeValueAsString(new UpdatePatientRequest("이순덕","1234532623455",GenderType.M,"2010-03-15","01033345678"));

        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/patient/{id}",patientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andDo(
                        document("patch-patient",
                                pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("환자 고유값")
                                ),
                                requestFields(
                                        fieldWithPath("name").description("환자명"),
                                        fieldWithPath("regNum").description("환자등록번호"),
                                        fieldWithPath("genderType").description("성별"),
                                        fieldWithPath("birth").description("생년월일"),
                                        fieldWithPath("phone").description("휴대전화번호")
                                )
                        )
                );
    }

    @DisplayName("환자 삭제")
    @Test
    void deletePatient() throws Exception{
        long patientId = 1L;

        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/patient/{id}",patientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(
                        document("delete-patient",
                                pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("환자 고유값")
                                )
                        )
                );
    }

}