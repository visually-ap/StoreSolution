package kr.co.apfactory.storesolution.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodeTypeEnum {
    CODE_TYPE_COMMON(1L, "공통", "Common")
    , CODE_TYPE_JACKET_DESIGN(2L, "상의 디자인", "Jacket Design")
    , CODE_TYPE_PANTS_DESIGN(3L, "하의 디자인", "Pants Design")
    , CODE_TYPE_VEST_DESIGN(4L, "조끼 디자인", "Vest Design")
    , CODE_TYPE_COAT_DESIGN(5L, "코트 디자인", "Coat Design")
    , CODE_TYPE_SIZE_DESIGN(6L, "사이즈", "Size")

    , CODE_TYPE_CHECK_ITEM(7L, "검품", "Check Item")
    , CODE_TYPE_DELIVERY_COMPLETION(8L, "배송완료", "Delivery Completion")
    , CODE_TYPE_HOLIDAY(9L, "휴일", "Holiday")
    , CODE_TYPE_NOT_COMPLETE(20090L, "미완료", "Not Completed")
    , CODE_TYPE_COMPLETE(20091L, "완료", "Completed")


    , CODE_TYPE_NORMAL(10L, "일반", "Normal")
    , CODE_TYPE_FAST(11L, "지급", "Fast")
    , CODE_TYPE_VERY_FAST(12L, "초지급", "VeryFast")

    , CODE_TYPE_ORDER(13L, "직봉", "Order")
    , CODE_TYPE_WEAK_BASTING(14L, "초가봉", "WeakBasting")
    , CODE_TYPE_STRONG_BASTING(15L, "중가봉", "StrongBasting")
    , CODE_TYPE_BASTING_COMPLETION(16L, "가봉완성", "BastingCompletion")
    , CODE_TYPE_BASTING_COMPLETION_WEAK(17L, "가봉완성 - 초가봉", "BastingCompletion-Weak")
    , CODE_TYPE_BASTING_COMPLETION_STRONG(18L, "가봉완성 - 중가봉", "BastingCompletion-Strong")
    , CODE_TYPE_REPAIR(19L, "수선", "Repair")

    , CODE_TYPE_JACKET(20L, "상의", "Jacket")
    , CODE_TYPE_PANTS(21L, "하의", "Pants")
    , CODE_TYPE_VEST(22L, "조끼", "Vest")
    , CODE_TYPE_COAT(23L, "코트", "Coat")

    , CODE_TYPE_GAUGE(24L, "게이지복", "Gage")
    , CODE_TYPE_RENTAL(25L, "대여복", "Rent")

    , CODE_TYPE_ORDER_FEE(26L, "직봉", "Order")
    , CODE_TYPE_WEAK_BASTING_FEE(27L, "초가봉", "WeakBasting")
    , CODE_TYPE_STRONG_BASTING_FEE(28L, "중가봉", "StrongBasting")
    , CODE_TYPE_ORDER_AFTER_BASTING_FEE(29L, "가봉후직봉", "OAB")
    , CODE_TYPE_BASTING_COMPLETION_FEE(30L, "가봉완성", "BastingCompletion")
    , CODE_TYPE_BASTING_AGAIN(31L, "재가봉", "ReBasting")

    , CODE_TYPE_ORDER_FEE1(32L, "직봉", "Order")
    , CODE_TYPE_BASTING_FEE1(33L, "가봉", "Basting")
    , CODE_TYPE_BASTING_COMPLETION_FEE1(34L, "가봉완성", "BastingCompletion")

    , CODE_TYPE_MILEAGE_INCREASE(10001L, "충전", "Increase")
    , CODE_TYPE_MILEAGE_DECREASE(10002L, "차감", "Decrease")

    , CODE_TYPE_CHECK_AUTH_NONE(20001L, "없음", "None")
    , CODE_TYPE_CHECK_AUTH_1ST(20002L, "1차", "1st")
    , CODE_TYPE_CHECK_AUTH_FINAL(20003L, "최종", "Final")
    , CODE_TYPE_CHECK_AUTH_CAD(20004L, "CAD 완성", "Cad Completion")
    , CODE_TYPE_CHECK_AUTH_ALL(20005L, "모두", "All")

    , CODE_TYPE_ADMIN_TYPE_NORMAL(20101L, "일반", "Normal")
    , CODE_TYPE_ADMIN_TYPE_MSUIT(20102L, "대천입고", "Msuit Retrieval")   // 대천 입고
    , CODE_TYPE_ADMIN_TYPE_B_FABRIC_INBOUND(20103L, "B원단입고", "B Fabric Storage")   // B원단 도착 결정
    , CODE_TYPE_ADMIN_TYPE_B_FABRIC_OUTBOUND(20104L, "B원단출고", "B Fabric Retrieval")    // B원단 출고 여부 결정
    , CODE_TYPE_ADMIN_TYPE_B_PRODUCTION(20105L, "B생산", "B Production")   // B 생산 시작 결정
    , CODE_TYPE_ADMIN_TYPE_B_OUTBOUND(20106L, "B출고", "B Retrieval")    // B 출고일 출고 결정
    , CODE_TYPE_ADMIN_TYPE_AP_FABRIC_OUTBOUND(20107L, "AP원단출고", "AP Fabric Retrieval")  // AP -> 공장 원단 출고 결정
    , CODE_TYPE_ADMIN_TYPE_AP_INBOUND(20108L, "AP입고", "AP Storage")     // 생산 완료 후 정장, 코드 입고일 결정
    , CODE_TYPE_ADMIN_TYPE_AP_CHECK_ITEM(20109L, "AP검품", "AP Check Item")  // 검품일 결정
    , CODE_TYPE_ADMIN_TYPE_AP_RELEASE(20110L, "AP출고", "AP Release")   // 택배 출고일 결정
    , CODE_TYPE_ADMIN_TYPE_B_MANAGER(20111L, "B관리자", "B Manager")   // B 관리자

    , CODE_TYPE_HAND_WHITE_NOT_STORAGE(20200L, "핸드 화이트 미입고", "W Hand Not Storage")
    , CODE_TYPE_HAND_BLACK_NOT_STORAGE(20201L, "핸드 블랙 미입고", "B Hand Not Storage")
    , CODE_TYPE_HAND_STORAGE(20202L, "핸드 입고", "Hand Storage")
    , CODE_TYPE_HAND_COMPLETE(20203L, "핸드 완료", "Hand Completed")

    , CODE_TYPE_POSITION_CHIEF(20300L, "점장", "Chief")
    , CODE_TYPE_POSITION_MANAGER(20301L, "팀장", "Manager")
    , CODE_TYPE_POSITION_STAFF(20302L, "스태프", "Staff")

    ;

    private Long value;
    private String typeNameKo;
    private String typeNameEn;

    public static CodeTypeEnum valueOf(long value) {
        for (CodeTypeEnum theEnum : CodeTypeEnum.values()) {
            if (theEnum.value == value) {
                return theEnum;
            }
        }

        return null;
    }
}
