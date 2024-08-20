package org.siksnaghae.fgmate.api.auth.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDto {
    private String token;
    private String authFg;
    private String deviceToken;
}
