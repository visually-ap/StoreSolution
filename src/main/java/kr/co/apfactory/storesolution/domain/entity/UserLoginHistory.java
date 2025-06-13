package kr.co.apfactory.storesolution.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "tb_user_login_history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class UserLoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("사용자 로그인 내역 시퀀스")
    private Long id;

    @Column(columnDefinition = "tinyint", nullable = false)
    @Comment("로그인 이벤트 (1 : 로그인, 2 : 로그아웃)")
    private Integer event;

    @Column(length = 50)
    @Comment("접속 아이피")
    private String ipAddress;

    @Column(columnDefinition = "datetime default current_timestamp", nullable = false)
    @Comment("로그인, 로그아웃 일시")
    private LocalDateTime loginDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("사용자 시퀀스")
    private User user;
}
