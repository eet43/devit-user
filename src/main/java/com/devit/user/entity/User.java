package com.devit.user.entity;

import com.devit.user.util.Timestamped;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

/**
 * 해당 필드값에 대한 Validation 과정은
 * Gateway 서버에서 진행 후 넘기기 때문에,
 * 별다른 검증 과정 없이 디비에 넣는다.
 */

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //외부 생성 금지
@Table(name = "user")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 식별자 값

    @Column(unique = true, columnDefinition = "BINARY(16)", name = "user_id")
    private UUID userId; //유저 식별 id 값

    @Email
    @Column(nullable = false, unique = true, length = 30)
    private String email; //유저 이메일

    @Column(nullable = false, unique = false, length = 8)
    private String nickName; //유저 닉네임 최대 8글자


//    profile :

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume; //유저 이력서 -> 한 유저당 하나의 이력서만 가능.



    /*생성 메서드 !!!! 반드시 static*/
    public static User signUp(UUID uuid, String email, String nickName) {
        User user = new User();
        user.userId = uuid;
        user.email = email;
        user.nickName = nickName;

        return user;
    }

}
