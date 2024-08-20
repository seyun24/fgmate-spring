package org.siksnaghae.fgmate.api.fridge.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefrigeratorDto {
    private String refrigeratorName;
    private Long refrigeratorId;
    private Long ownerFg;

    @QueryProjection
    public RefrigeratorDto(String refrigeratorName, Long refrigeratorId, Long ownerFg){
        this.refrigeratorName=refrigeratorName;
        this.refrigeratorId=refrigeratorId;
        this.ownerFg =ownerFg;
    }
}
