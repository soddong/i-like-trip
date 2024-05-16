package com.ssafy.member.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberUpdateDto {
    private String password;
    private String name;
    private String email;
    private String location;
    private int profileNo;
    private String profileImg;
}
