package kr.co.apfactory.storesolution.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// Entity에서 Table에 대한 공통 매핑 정보가 필요할 때 부모 클래스에 정의하고 상속받아 해당 필드를 사용하여 중복을 제거
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public abstract class BaseEntity {
    @CreatedBy // 데이터 생성자 자동저장
    @ManyToOne(fetch = FetchType.LAZY)
    private User insertUser;

    @CreatedDate // 데이터 생성날짜 자동저장
    private LocalDateTime insertDatetime;

    @LastModifiedBy // 데이터 수정자 자동저장
    @ManyToOne(fetch = FetchType.LAZY)
    private User updateUser;

    @LastModifiedDate // 데이터 수정날짜 자동저장
    private LocalDateTime updateDatetime;
}
