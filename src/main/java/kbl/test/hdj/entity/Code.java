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
public class Code extends BaseTime{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code_group_id", foreignKey = @ForeignKey(name = "fk_code_code_group") ,columnDefinition = "varchar(10) not null comment '코드그룹'")
    private CodeGroup codeGroup;

    @Id
    @Column(name = "code", columnDefinition = "varchar(10) not null comment '코드'")
    private String code;

    @Column(name = "code_name", columnDefinition = "varchar(10) not null comment '코드명'")
    private String codeName;

}
