package kbl.test.hdj.entity;

import jakarta.persistence.*;
import kbl.test.hdj.apis.v1.patient.payload.UpdatePatientRequest;
import kbl.test.hdj.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Patient extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(45) not null comment '환자명'")
    private String name;

    @Column(name = "reg_num", columnDefinition = "varchar(13) not null comment '환자등록번호'")
    private String regNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender_type", columnDefinition = "varchar(10) not null comment '성별코드'")
    private GenderType genderType;

    @Column(name = "birth", columnDefinition = "varchar(10) comment '생년월일'")
    private String birth;

    @Column(name = "phone", columnDefinition = "varchar(20) comment '휴대전화번호'")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", foreignKey = @ForeignKey(name = "fk_patient_hospital"))
    private Hospital hospital;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<Visit> visits = new ArrayList<>();

    public void updateInfo(UpdatePatientRequest updatePatientRequest) {
        this.name = updatePatientRequest.getName();
        this.regNum = updatePatientRequest.getRegNum();
        this.genderType = updatePatientRequest.getGenderType();
        this.birth = updatePatientRequest.getBirth();
        this.phone = updatePatientRequest.getPhone();
    }
}
