package kbl.test.hdj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Hospital extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hospital_name", columnDefinition = "varchar(45) not null comment '병원명'")
    private String hospitalName;

    @Column(name = "ykiho", columnDefinition = "varchar(20) not null comment '요양기관기호'")
    private String ykiho;

    @Column(name = "doctor_name", columnDefinition = "varchar(10) not null comment '병원장명'")
    private String doctorName;
}
