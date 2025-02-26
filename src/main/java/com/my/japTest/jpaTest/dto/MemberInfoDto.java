package com.my.japTest.jpaTest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberInfoDto {
    private String name;
    private String groupName;
    private String enterName;

}
