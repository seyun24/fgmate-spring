package org.siksnaghae.fgmate.api.auth.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AuthDto {
    private Long userId;
    private String jwt;
    private String loginInfo;
}
