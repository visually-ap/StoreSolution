package kr.co.apfactory.storesolution.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ParentCodeEnum {
    /**
     * 정장
     */
    // 공통
    // 제작방식
    SUIT_COMMON_MAKING_METHOD(1, "1101", 1L, 1L, "제작방법", "MakingMethod", "MakingMethod", "MakingMethod", "MakingMethod")
    // 연령대
    , SUIT_COMMON_AGE(2, "1102", 1L, 1L, "연령대", "Age", "Age", "Age", "Age")
    // 선택단추
    , SUIT_COMMON_BUTTON(3, "1103", 1L, 1L, "선택단추", "Button", "Button", "Button", "Button")
    // 선택안감
    , SUIT_COMMON_LINING(4, "1104", 1L, 1L, "선택안감", "Lining", "Lining", "Lining", "Lining")
    // 일정
    , SUIT_COMMON_SCHEDULE(5, "1105", 1L, 1L, "일정", "Schedule", "Schedule", "Schedule", "Schedule")

    // 상의
    // 스타일
    , SUIT_JACKET_STYLE(6, "1201", 1L, 2L, "스타일", "Style", "Style", "Style", "Style")
    // 단추갯수
    , SUIT_JACKET_BUTTON_COUNT(7, "1202", 1L, 2L, "단추갯수", "ButtonCount", "ButtonCount", "ButtonCount", "ButtonCount")
    // 가슴포켓
    , SUIT_JACKET_CHEST_POCKET(8, "1203", 1L, 2L, "가슴포켓", "ChestPocket", "ChestPocket", "ChestPocket", "ChestPocket")
    // 어깨
    , SUIT_JACKET_SHOULDER(9, "1204", 1L, 2L, "어깨", "Shoulder", "Shoulder", "Shoulder", "Shoulder")
    // 앞주머니
    , SUIT_JACKET_FRONT_POCKET(10, "1205", 1L, 2L, "앞주머니", "FrontPocket", "FrontPocket", "FrontPocket", "FrontPocket")
    // 좌패드
    , SUIT_JACKET_LEFT_PAD(11, "1206", 1L, 2L, "패드(좌)", "LeftPad", "LeftPad", "LeftPad", "LeftPad")
    // 뒤트림
    , SUIT_JACKET_BACK_SLIT(12, "1207", 1L, 2L, "뒷트임", "BackSlit", "BackSlit", "BackSlit", "BackSlit")
    // 우패드
    , SUIT_JACKET_RIGHT_PAD(13, "1208", 1L, 2L, "패드(우)", "RightPad", "RightPad", "RightPad", "RightPad")
    // 소매단추
    , SUIT_JACKET_SLEEVE_BUTTON(14, "1209", 1L, 2L, "소매단추", "SleeveButton", "SleeveButton", "SleeveButton", "SleeveButton")
    // 라펠
    , SUIT_JACKET_LAPEL(15, "1210", 1L, 2L, "라펠", "Lapel", "Lapel", "Lapel", "Lapel")
    // 소매
    , SUIT_JACKET_SLEEVE(16, "1211", 1L, 2L, "소매", "Sleeve", "Sleeve", "Sleeve", "Sleeve")
    // 턱시도
    , SUIT_JACKET_TUXEDO(17, "1212", 1L, 2L, "턱시도", "Tuxedo", "Tuxedo", "Tuxedo", "Tuxedo")
    // AMF 호시
    , SUIT_JACKET_AMF(18, "1213", 1L, 2L, "(AMF)호시", "AMF", "AMF", "AMF", "AMF")
    // 라펠큐
    , SUIT_JACKET_LAPELQ(19, "1214", 1L, 2L, "라펠", "LapelQ", "LapelQ", "LapelQ", "LapelQ")
    // 안감
    , SUIT_JACKET_LINING(20, "1215", 1L, 2L, "안감", "Lining", "Lining", "Lining", "Lining")
    // 체크원단
    , SUIT_JACKET_CHECK_FABRIC(21, "1216", 1L, 2L, "체크원단", "CheckFabric", "CheckFabric", "CheckFabric", "CheckFabric")
    // 추가옵션
    , SUIT_JACKET_ADD_OPTION(22, "1217", 1L, 2L, "추가옵션", "AddOption", "AddOption", "AddOption", "AddOption")

    // 하의
    // 벨트고리
    , SUIT_PANTS_BELT_LOOP(23, "1301", 1L, 3L, "벨트고리", "BeltLoop", "BeltLoop", "BeltLoop", "BeltLoop")
    // 주머니
    , SUIT_PANTS_POCKET(24, "1302", 1L, 3L,"주머니", "Pocket", "Pocket", "Pocket", "Pocket")
    // 밑단모양
    , SUIT_PANTS_HEM_SHAPE(25, "1303", 1L, 3L,"밑단모양", "HemShape", "HemShape", "HemShape", "HemShape")
    // 밑단두께
    , SUIT_PANTS_HEM_THICKNESS(26, "1304", 1L, 3L,"밑단두깨", "HemThickness", "HemThickness", "HemThickness", "HemThickness")
    // 비죠
    , SUIT_PANTS_FLAP(27, "1305", 1L, 3L,"비죠", "Flap", "Flap", "Flap", "Flap")
    // 비죠길이
    , SUIT_PANTS_FLAP_LENGTH(28, "1306", 1L, 3L,"길이", "FlapLength", "FlapLength", "FlapLength", "FlapLength")
    // 봉제라인
    , SUIT_PANTS_SEWING_LINE(29, "1307", 1L, 3L,"봉제라인", "SewingLine", "SewingLine", "SewingLine", "SewingLine")
    // 체크원단
    , SUIT_PANTS_CHECK_FABRIC(30, "1308", 1L, 3L,"체크원단", "CheckFabric", "CheckFabric", "CheckFabric", "CheckFabric")
    // 추가옵션
    , SUIT_PANTS_ADD_OPTION(31, "1309", 1L, 3L,"추가옵션", "AddOption", "AddOption", "AddOption", "AddOption")
    // 굴신
    , SUIT_PANTS_FRONT_ROUND(32, "1310", 1L, 3L,"굴신", "FrontRound", "FrontRound", "FrontRound", "FrontRound")
    // 반신
    , SUIT_PANTS_BACK_ROUND(33, "1311", 1L, 3L,"반신", "BackRound", "BackRound", "BackRound", "BackRound")
    // O자
    , SUIT_PANTS_O(34, "1312", 1L, 3L,"O자", "O Shape", "O Shape", "O Shape", "O Shape")
    // X자
    , SUIT_PANTS_X(35, "1313", 1L, 3L,"X자", "X Shape", "X Shape", "X Shape", "X Shape")

    // 조끼
    // 뒷판
    , SUIT_VEST_BACK(36, "1401", 1L, 4L, "뒷판", "Back", "Back", "Back", "Back")
    // 앞주머니
    , SUIT_VEST_FRONT_POCKET(37, "1402", 1L, 4L,"앞주머니", "FrontPocket", "FrontPocket", "FrontPocket", "FrontPocket")
    // 가슴포켓
    , SUIT_VEST_CHEST_POCKET(38, "1403", 1L, 4L, "가슴포켓", "ChestPocket", "ChestPocket", "ChestPocket", "ChestPocket")
    // 호시
    , SUIT_VEST_AMF(39, "1404", 1L, 4L, "호시", "AMF", "AMF", "AMF", "AMF")
    // 호시
    , SUIT_VEST_LAPEL(40, "1405", 1L, 4L, "라펠", "Lapel", "Lapel", "Lapel", "Lapel")

    /**
     * 코트
     */
    // 공통
    // 제작방식
    , COAT_COMMON_MAKING_METHOD(40, "2101", 2L, 1L, "제작방법", "MakingMethod", "MakingMethod", "MakingMethod", "MakingMethod")
    // 연령대
    , COAT_COMMON_AGE(41, "2102", 2L, 1L, "연령대", "Age", "Age", "Age", "Age")
    // 선택단추
    , COAT_COMMON_BUTTON(42, "2103", 2L, 1L, "선택단추", "Button", "Button", "Button", "Button")
    // 선택안감
    , COAT_COMMON_LINING(43, "2104", 2L, 1L, "선택안감", "Lining", "Lining", "Lining", "Lining")
    , COAT_COMMON_SCHEDULE(44, "2105", 2L, 1L, "일정", "Schedule", "Schedule", "Schedule", "Schedule")

    // 코트
    // 스타일
    , COAT_COAT_STYLE(45, "2501", 2L, 5L, "스타일", "Style", "Style", "Style", "Style")
    // 단추갯수
    , COAT_COAT_BUTTON_COUNT(46, "2502", 2L, 5L,"단추갯수", "ButtonCount", "ButtonCount", "ButtonCount", "ButtonCount")
    // 가슴포켓
    , COAT_COAT_CHEST_POCKET(47, "2503", 2L, 5L, "가슴포켓", "ChestPocket", "ChestPocket", "ChestPocket", "ChestPocket")
    // 어깨
    , COAT_COAT_SHOULDER(48, "2504", 2L, 5L, "어깨", "Shoulder", "Shoulder", "Shoulder", "Shoulder")
    // 앞주머니
    , COAT_COAT_FRONT_POCKET(49, "2505", 2L, 5L, "앞주머니", "FrontPocket", "FrontPocket", "FrontPocket", "FrontPocket")
    // 패드 좌
    , COAT_COAT_LEFT_PAD(50, "2506", 2L, 5L, "패드(좌)", "LeftPad", "LeftPad", "LeftPad", "LeftPad")
    // 뒷트임
    , COAT_COAT_BACK_SLIT(51, "2507", 2L, 5L, "뒷트임", "BackSlit", "BackSlit", "BackSlit", "BackSlit")
    // 패드 우
    , COAT_COAT_RIGHT_PAD(52, "2508", 2L, 5L, "패드(우)", "RightPad", "RightPad", "RightPad", "RightPad")
    // 소매단추
    , COAT_COAT_SLEEVE_BUTTON(53, "2509", 2L, 5L, "소매단추", "SleeveButton", "SleeveButton", "SleeveButton", "SleeveButton")
    // 라펠
    , COAT_COAT_LAPEL(54, "2510", 2L, 5L, "라펠", "Lapel", "Lapel", "Lapel", "Lapel")
    // 소매
    , COAT_COAT_SLEEVE(55, "2511", 2L, 5L, "소매", "Sleeve", "Sleeve", "Sleeve", "Sleeve")
    // 라펠큐
    , COAT_COAT_LAPELQ(56, "2512", 2L, 5L, "라펠", "LapelQ", "LapelQ", "LapelQ", "LapelQ")
    // 호시
    , COAT_COAT_AMF(57, "2513", 2L, 5L, "(AMF)호시", "AMF", "AMF", "AMF", "AMF")
    // 체크원단
    , COAT_COAT_CHECK_FABRIC(58, "2514", 2L, 5L, "체크원단", "CheckFabric", "CheckFabric", "CheckFabric", "CheckFabric")
    // 안감
    , COAT_COAT_LINING(59, "2515", 2L, 5L, "안감", "Lining", "Lining", "Lining", "Lining")
    // 추가옵션
    , COAT_COAT_ADD_OPTION(60, "2516", 2L, 5L,"AddOption", "AddOption", "AddOption", "AddOption", "AddOption")

    // 상의 사이즈
    , SUIT_JACKET_SIZE_FRONT_ROUND(61, "1601", 1L, 6L, "굴신", "FrontRound", "FrontRound", "FrontRound", "FrontRound")
    , SUIT_JACKET_SIZE_BACK_ROUND(62, "1602", 1L, 6L, "반신", "BackRound", "BackRound", "BackRound", "BackRound")
    , SUIT_JACKET_SIZE_DROPPING_SHOULDER(63, "1603", 1L, 6L, "하견", "DroopingShoulder", "DroopingShoulder", "DroopingShoulder", "DroopingShoulder")
    , SUIT_JACKET_SIZE_TOP_SHOULDER(64, "1604", 1L, 6L, "상견", "TopShoulder", "TopShoulder", "TopShoulder", "TopShoulder")
    , SUIT_JACKET_SIZE_ARM_HOLE(65, "1605", 1L, 6L, "암홀(진동)", "ArmHole", "ArmHole", "ArmHole", "ArmHole")
    , SUIT_JACKET_SIZE_BACK_NECK(66, "1606", 1L, 6L, "뒷목점이동", "BackNeck", "BackNeck", "BackNeck", "BackNeck")
    , SUIT_JACKET_SIZE_UPPER_ARM(67, "1607", 1L, 6L, "상박", "UpperArm", "UpperArm", "UpperArm", "UpperArm")
    , SUIT_JACKET_SIZE_MIDDLE_ARM(68, "1608", 1L, 6L, "중박", "MiddleArm", "MiddleArm", "MiddleArm", "MiddleArm")
    , SUIT_JACKET_SIZE_LOWER_ARM(69, "1609", 1L, 6L, "하박", "LowerArm", "LowerArm", "LowerArm", "LowerArm")
    , SUIT_JACKET_SIZE_FRONT_LENGTH(70, "1610", 1L, 6L, "앞기장", "FrontLength", "FrontLength", "FrontLength", "FrontLength")
    , SUIT_JACKET_SIZE_BACK_CENTER_MOVE(71, "1611", 1L, 6L, "뒷중심(이동)", "BackCenterMove", "BackCenterMove", "BackCenterMove", "BackCenterMove")
    , SUIT_JACKET_SIZE_BACK_CENTER_SHORTEN(72, "1612", 1L, 6L, "뒷중심(줄임)", "BackCenterShorten", "BackCenterShorten", "BackCenterShorten", "BackCenterShorten")
    , SUIT_JACKET_SIZE_FIRST_BUTTON(73, "1613", 1L, 6L, "첫단추", "FirstButton", "FirstButton", "FirstButton", "FirstButton")
    , SUIT_JACKET_SIZE_NECK_LINE(74, "1614", 1L, 6L, "목둘레", "NeckLine", "NeckLine", "NeckLine", "NeckLine")
    , SUIT_JACKET_SIZE_HEM_LINE(75, "1615", 1L, 6L, "도련선", "HemLine", "HemLine", "HemLine", "HemLine")
    , SUIT_JACKET_SIZE_VEST_VZONE(76, "1616", 1L, 6L, "조끼(역V존)", "VestVZone", "VestVZone", "VestVZone", "VestVZone")
    , SUIT_JACKET_SIZE_CHEST_LINE(77, "1617", 1L, 6L, "우찌", "ChestLine", "ChestLine", "ChestLine", "ChestLine")
    , SUIT_JACKET_SIZE_HIP_GIRTH(78, "1618", 1L, 6L, "하동", "HipGirth", "HipGirth", "HipGirth", "HipGirth")
    // 코트 사이즈
    , SUIT_COAT_SIZE_FRONT_ROUND(79, "2601", 2L, 6L, "굴신", "FrontRound", "FrontRound", "FrontRound", "FrontRound")
    , SUIT_COAT_SIZE_BACK_ROUND(80, "2602", 2L, 6L, "반신", "BackRound", "BackRound", "BackRound", "BackRound")
    , SUIT_COAT_SIZE_DROPPING_SHOULDER(81, "2603", 2L, 6L, "하견", "DroopingShoulder", "DroopingShoulder", "DroopingShoulder", "DroopingShoulder")
    , SUIT_COAT_SIZE_TOP_SHOULDER(82, "2604", 2L, 6L, "상견", "TopShoulder", "TopShoulder", "TopShoulder", "TopShoulder")
    , SUIT_COAT_SIZE_ARM_HOLE(83, "2605", 2L, 6L, "암홀(진동)", "ArmHole", "ArmHole", "ArmHole", "ArmHole")
    , SUIT_COAT_SIZE_BACK_NECK(84, "2606", 2L, 6L, "뒷목점이동", "BackNeck", "BackNeck", "BackNeck", "BackNeck")
    , SUIT_COAT_SIZE_UPPER_ARM(85, "2607", 2L, 6L, "상박", "UpperArm", "UpperArm", "UpperArm", "UpperArm")
    , SUIT_COAT_SIZE_MIDDLE_ARM(86, "2608", 2L, 6L, "중박", "MiddleArm", "MiddleArm", "MiddleArm", "MiddleArm")
    , SUIT_COAT_SIZE_LOWER_ARM(87, "2609", 2L, 6L, "하박", "LowerArm", "LowerArm", "LowerArm", "LowerArm")
    , SUIT_COAT_SIZE_FRONT_LENGTH(88, "2610", 2L, 6L, "앞기장", "FrontLength", "FrontLength", "FrontLength", "FrontLength")
    , SUIT_COAT_SIZE_BACK_CENTER_MOVE(89, "2611", 2L, 6L, "뒷중심(이동)", "BackCenterMove", "BackCenterMove", "BackCenterMove", "BackCenterMove")
    , SUIT_COAT_SIZE_BACK_CENTER_SHORTEN(90, "2612", 2L, 6L, "뒷중심(줄임)", "BackCenterShorten", "BackCenterShorten", "BackCenterShorten", "BackCenterShorten")
    , SUIT_COAT_SIZE_FIRST_BUTTON(91, "2613", 2L, 6L, "첫단추", "FirstButton", "FirstButton", "FirstButton", "FirstButton")
    , SUIT_COAT_SIZE_NECK_LINE(92, "2614", 2L, 6L, "목둘레", "NeckLine", "NeckLine", "NeckLine", "NeckLine")
    , SUIT_COAT_SIZE_HEM_LINE(93, "2615", 2L, 6L, "도련선", "HemLine", "HemLine", "HemLine", "HemLine")
    , SUIT_COAT_SIZE_VEST_VZONE(94, "2616", 2L, 6L, "조끼(역V존)", "VestVZone", "VestVZone", "VestVZone", "VestVZone")
    , SUIT_COAT_SIZE_CHEST_LINE(95, "2617", 2L, 6L, "우찌", "ChestLine", "ChestLine", "ChestLine", "ChestLine")
    , SUIT_COAT_SIZE_HIP_GIRTH(96, "2618", 2L, 6L, "하동", "HipGirth", "HipGirth", "HipGirth", "HipGirth")
    ;
    private int sequence;
    private String codeValue;
    private Long part;
    private Long type;
    private String codeNameKo;
    private String codeNameEn;
    private String codeNameCn;
    private String codeNameJp;
    private String codeNameIn;


    public static ParentCodeEnum valueOf(long value) {
        for (ParentCodeEnum theEnum : ParentCodeEnum.values()) {
            if (theEnum.sequence == value) {
                return theEnum;
            }
        }

        return null;
    }
}
