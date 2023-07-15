package kbl.test.hdj.apis.v1.visit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kbl.test.hdj.apis.v1.visit.payload.SaveVisitRequest;
import kbl.test.hdj.apis.v1.visit.payload.UpdateVisitRequest;
import kbl.test.hdj.apis.v1.visit.payload.VisitorResponse;
import kbl.test.hdj.apis.v1.visit.payload.VisitorsResponse;
import kbl.test.hdj.apis.v1.visit.service.VisitService;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(VisitController.class)
class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitService visitService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("방문자 목록 조회")
    @Test
    void getVisitors() throws Exception {
        VisitorResponse visitorResponse1 = VisitorResponse.builder()
                .hospitalName("송파성모내과의원")
                .patientName("이광복")
                .visitType("방문중")
                .birth("2003-06-08")
                .genderType("남")
                .regNum("1234566543215")
                .regDate(LocalDateTime.now())
                .updDate(LocalDateTime.now())
                .build();

        VisitorResponse visitorResponse2 = VisitorResponse.builder()
                .hospitalName("일산윌내과의원")
                .patientName("이광복")
                .visitType("종료")
                .birth("2003-06-08")
                .genderType("남")
                .regNum("1234566543215")
                .regDate(LocalDateTime.now())
                .updDate(LocalDateTime.now())
                .build();

        given(visitService.getVisitors())
                .willReturn(VisitorsResponse.builder()
                        .visitors(List.of(visitorResponse1, visitorResponse2)).build());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/visitors")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(
                        document("get-visitors",
                                responseFields(
                                        fieldWithPath("visitors[].hospitalName").description("병원명"),
                                        fieldWithPath("visitors[].patientName").description("환자명"),
                                        fieldWithPath("visitors[].visitType").description("방문상태코드"),
                                        fieldWithPath("visitors[].birth").description("생년월일"),
                                        fieldWithPath("visitors[].genderType").description("성별"),
                                        fieldWithPath("visitors[].regNum").description("환자등록번호"),
                                        fieldWithPath("visitors[].regDate").description("등록일"),
                                        fieldWithPath("visitors[].updDate").description("수정일")
                                )
                        )
                );

    }

    @DisplayName("방문자 단일 조회")
    @Test
    void getVisitor() throws Exception {
        VisitorResponse visitorResponse = VisitorResponse.builder()
                .hospitalName("송파성모내과의원")
                .patientName("이광복")
                .visitType("방문중")
                .birth("2003-06-08")
                .genderType("남")
                .regNum("1234566543215")
                .regDate(LocalDateTime.now())
                .updDate(LocalDateTime.now())
                .build();

        given(visitService.getVisitor(ArgumentMatchers.any()))
                .willReturn(visitorResponse);

        long visitId = 1L;
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/visit/{id}",visitId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document("get-visitor",
                                pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("방문자 고유값")
                                ),
                                responseFields(
                                        fieldWithPath("hospitalName").description("병원명"),
                                        fieldWithPath("patientName").description("환자명"),
                                        fieldWithPath("visitType").description("방문상태코드"),
                                        fieldWithPath("birth").description("생년월일"),
                                        fieldWithPath("genderType").description("성별"),
                                        fieldWithPath("regNum").description("환자등록번호"),
                                        fieldWithPath("regDate").description("등록일"),
                                        fieldWithPath("updDate").description("수정일")
                                )
                        )
                );
    }

    @DisplayName("방문자 등록")
    @Test
    void saveVisit() throws Exception {
        SaveVisitRequest saveVisitRequest = new SaveVisitRequest(1L, VisitType.ING);

        given(visitService.saveVisit(saveVisitRequest))
                .willReturn(any());

        String content = objectMapper.writeValueAsString(new SaveVisitRequest(1L, VisitType.ING));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/visit")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(
                        document("post-visit",
                                requestFields(
                                        fieldWithPath("patientId").description("환자 고유값"),
                                        fieldWithPath("visitType").description("방문 상태 코드")

                                )
                        )
                );
    }

    @DisplayName("방문자 수정")
    @Test
    void updateVisit() throws Exception{
        long visitId = 1L;
        UpdateVisitRequest updateVisitRequest = new UpdateVisitRequest(VisitType.CANCEL);

        given(visitService.updateVisit(visitId,updateVisitRequest))
                .willReturn(1L);

        String content = objectMapper.writeValueAsString(new UpdateVisitRequest(VisitType.CANCEL));

        mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/v1/visit/{id}",visitId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andDo(
                        document("patch-visit",
                                pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("방문자 고유값")
                                ),
                                requestFields(
                                        fieldWithPath("visitType").description("방문 상태 코드")
                                )
                        )
                );
    }

    @DisplayName("방문자 삭제")
    @Test
    void deleteVisit() throws Exception{
        long visitId = 1L;

        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/visit/{id}",visitId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(
                        document("delete-visit",
                                pathParameters(
                                        RequestDocumentation.parameterWithName("id").description("방문자 고유값")
                                )
                        )
                );
    }
}