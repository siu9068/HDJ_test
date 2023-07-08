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
@Table(name = "code_group")
public class CodeGroup extends BaseTime{

    @Id
    @Column(name = "code_group_id", columnDefinition = "varchar(10) not null comment '코드그룹'")
    private String codeGroupId;

    @Column(name = "code_group_name", columnDefinition = "varchar(10) not null comment '코드그룹명'")
    private String codeGroupName;

    @Column(name = "comment", columnDefinition = "varchar(10) not null comment '설명'")
    private String comment;

}


