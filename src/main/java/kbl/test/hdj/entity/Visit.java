package kbl.test.hdj.entity;

import jakarta.persistence.*;
import kbl.test.hdj.converter.VisitTypeConverter;
import kbl.test.hdj.enums.VisitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Visit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "reg_date", columnDefinition = "datetime not null comment '접수일시'")
    private LocalDateTime regDate;

    @Convert(converter = VisitTypeConverter.class)
    @Column(name = "visit_type", columnDefinition = "varchar(10) not null comment '방문상태코드'")
    private VisitType visitType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", foreignKey = @ForeignKey(name = "fk_visit_patient"))
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", foreignKey = @ForeignKey(name = "fk_visit_hospital"))
    private Hospital hospital;
}