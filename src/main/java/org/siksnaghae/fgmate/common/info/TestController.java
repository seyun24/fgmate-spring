package org.siksnaghae.fgmate.common.info;

import lombok.RequiredArgsConstructor;
import org.siksnaghae.fgmate.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    @GetMapping("/token")
    public String getJwtToken(@RequestParam Long userId) {
        return JwtUtil.createJwt(userId);
    }
}
