package org.siksnaghae.fgmate.api.user.model.user;

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
@Table(name = "Users_m")
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    private String name;
    @Column(name = "infoId")
    private String infoId;
    private String email;

    @Column(name = "profileImg")
    private String profileImg;

    @Column(name = "deviceToken")
    private String deviceToken;

    protected User() {

    }

    public void setName(String name){
        this.name=name;
    }

    public void setProfileImg(String profileImg) {this.profileImg=profileImg;}

    public void setDeviceToken(String deviceToken) {this.deviceToken= deviceToken;};
}
