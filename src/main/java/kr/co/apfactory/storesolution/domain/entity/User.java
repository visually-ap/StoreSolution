package kr.co.apfactory.storesolution.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("사용자 시퀀스")
    private Long id;

    @Column(length = 20, nullable = false)
    @Comment("권한(ROLE_ADMIN : 최고 관리자, ROLE_MANAGER : 관리자, ROLE_FOFFICER : 원단관리자, ROLE_USER : 매장 관리자, ROLE_EMPLOYEE : 매장 직원)")
    private String authority;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부 (true : 삭제, false : 미삭제)")
    private Boolean deleted;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("계정 활성화 여부 (true : 활성화, false : 비활성화)")
    private Boolean enabled;

    @Column(length = 20, nullable = false)
    @Comment("로그인 아이디")
    private String loginId;

    @Column(length = 100)
    @Comment("메모")
    private String memo;

    @Column(length = 50, nullable = false)
    @Comment("휴대전화번호")
    private String mobileNumber;

    @Column(length = 120, nullable = false)
    @Comment("이름")
    private String name;

    @Column(length = 100, nullable = false)
    @Comment("비밀번호")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("매장 시퀀스")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("검수 권한")
    private CodeType checkAuth;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("관리자 구분")
    private CodeType adminType;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("직책")
    private CodeType position;

    public void updatePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void updateMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePosition(CodeType position) {
        this.position = position;
    }

    public void delete() {
        this.deleted = true;
    }
}
