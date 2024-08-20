package org.siksnaghae.fgmate.api.fridge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.siksnaghae.fgmate.common.BaseEntity;

import javax.persistence.*;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Refrigerators")
@AllArgsConstructor
@Builder
public class Refrigerator extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refrigeratorId")
    private Long refrigeratorId;

    private String refrigeratorName;

    private Long ownerId;

    protected Refrigerator() {

    }

    public void setName(String refrigeratorName){
        this.refrigeratorName=refrigeratorName;
    }
}
