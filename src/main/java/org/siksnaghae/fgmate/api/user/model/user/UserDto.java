package org.siksnaghae.fgmate.api.user.model.user;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;

    private String id;

    private String email;

    private String status;


    @QueryProjection
    public UserDto(User user){
        this.name=user.getName();
        this.id =user.getInfoId();
        this.status=user.getStatus();
    }
}
