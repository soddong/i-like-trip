package com.ssafy.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@ToString
public class JwtTokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
