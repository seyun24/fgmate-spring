package org.siksnaghae.fgmate.api.group.model;

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
@Table(name = "RefrigeratorGroups")
@AllArgsConstructor
@Builder
public class RefrigeratorGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId")
    private Long groupId;

    private Long refrigeratorId;

    private Long userId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "refrigeratorId")
//    private Refrigerator refrigerator;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId")
//    private User user;

    protected RefrigeratorGroup(){

    }
}

