package kbl.test.hdj.repository;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kbl.test.hdj.apis.v1.patient.payload.GetPatientsRequest;
import kbl.test.hdj.apis.v1.patient.service.PatientDTO;
import kbl.test.hdj.entity.QPatient;
import kbl.test.hdj.enums.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    QPatient patient = QPatient.patient;

    private BooleanExpression searchKeyword(SearchType searchType, String searchKeyword){
        switch (searchType){
            case NAME -> {
                return patient.name.contains(searchKeyword);
            }
            case BIRTH -> {
                return patient.birth.contains(searchKeyword);
            }
            case REG_NUM -> {
                return patient.regNum.contains(searchKeyword);
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public Page<PatientDTO> getPatients(GetPatientsRequest getPatientsRequest, Pageable pageable) {
        List<PatientDTO> content = getPatientList(getPatientsRequest, pageable);
        Long count = getPatientsCount(getPatientsRequest);
        return new PageImpl<>(content, pageable, count);
    }

    private Long getPatientsCount(GetPatientsRequest getPatientsRequest) {
        return jpaQueryFactory.select(
                        patient.count()
                ).from(patient)
                .where(searchKeyword(getPatientsRequest.getSearchType(), getPatientsRequest.getSearchKeyword()))
                .fetchOne();
    }

    private List<PatientDTO> getPatientList(GetPatientsRequest getPatientsRequest, Pageable pageable) {
        StringTemplate formattedDate = Expressions.stringTemplate(
                "FORMATDATE({0}, {1})"
                , patient.regDate
                , ConstantImpl.create("%Y-%m-%d"));

        return jpaQueryFactory.select(Projections.constructor(PatientDTO.class,
                        patient.id,
                        patient.name,
                        patient.genderType,
                        patient.birth,
                        patient.phone,
                        formattedDate.as("regDate")
                )).from(patient)
                .where(searchKeyword(getPatientsRequest.getSearchType(), getPatientsRequest.getSearchKeyword()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
