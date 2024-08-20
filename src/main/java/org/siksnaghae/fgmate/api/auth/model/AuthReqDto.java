package org.siksnaghae.fgmate.api.auth.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthReqDto {
    private String id;
    private String email;
    private String deviceToken;
}
