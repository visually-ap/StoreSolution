$(function () {
    $.validator.setDefaults({
        onkeyup: false,
        onclick: false,
    });

    $.validator.addMethod("regexp", function (value, element, regexp) {
        return new RegExp(regexp).test(value);
    });

    $.validator.addMethod("regexp1", function (value, element, regexp) {
        if (isEmpty(value)) {
            return true;
        } else {
            return new RegExp(regexp).test(value);
        }
    });
});

function callValidationFormService($form, rules, messages, callback) {
    $form.validate({
        rules: rules,
        onfocusout: false,
        messages: messages,
        showErrors: function (errorMap, errorList) {
            if (errorList.length > 0) {
                const firstError = errorList[0];

                // 딱 한 번만 alert
                if (!$(firstError.element).data('shown')) {
                    alert(firstError.message);
                    $(firstError.element).data('shown', true);
                }

                // 나중에 다시 검증할 수 있도록 초기화
                setTimeout(() => {
                    $(firstError.element).data('shown', false);
                }, 100);
            }
        },
        submitHandler: function(form) {
            if (callback != null) {
                return callback(form);
            }
        }
    });
}

const validationObject = {
    nowPassword: {
        rules : {
            required: true,
        }
        , message: {
            required: "현재비밀번호는 필수 입력 항목입니다.",
        }
    }
    , password: {
        rules: {
            required: true,
            regexp: "^[a-zA-Z0-9!@#$%^&*]{4,20}$",
        },
        message: {
            required: "비밀번호는 필수 입력 항목입니다.",
            regexp: "비밀번호는 4~20자의 영문,숫자,특수기호(!,@,#,$,%,^,&,*)로 입력해 주세요."
        },
    }
    , confirmPassword: {
        rules: {
            required: true,
            equalTo: "#password",
        },
        message: {
            required: "비밀번호확인은 필수 입력 항목입니다.",
            equalTo: "비밀번호와 동일하게 입력해 주세요.",
        },
    }
    , loginId: {
        rules : {
            required: true,
            regexp: "^[a-z0-9]{4,12}$",
        }
        , message: {
            required: "아이디는 필수 입력 항목입니다.",
            regexp: "아이디는 4~12자의 영어소문자, 숫자로 입력해 주세요.",
        }
    }
    , loginIdCheck: {
        rules : {
            required: true,
            equalTo: "Y",
        }
        , message: {
            required: "아이디는 필수 입력 항목입니다.",
            equalTo: "아이디 중복체크를 진행해 주세요.",
        }
    }
    , name: {
        rules : {
            required: true,
            regexp: "^.{1,30}$",
        }
        , message: {
            required: "이름은 필수 입력 항목입니다.",
            regexp: "이름은 30자 이내로 입력해 주세요.",
        }
    }
    , mobileNumber: {
        rules : {
            required: true,
            regexp: "^01[016-9][0-9]{3,4}[0-9]{4}$",
        }
        , message: {
            required: "휴대전화번호는 필수 입력 항목입니다.",
            regexp: "올바른 형식의 휴대전화번호를 입력해 주세요.",
        }
    }
    , position: {
        rules : {
            required: true,
        }
        , message: {
            required: "직책을 선택해 주세요.",
        }
    }

    , customerName1: {
        rules : {
            required: true,
            regexp: "^.{1,10}$",
        }
        , message: {
            required: "고객명1은 필수 입력 항목입니다.",
            regexp: "고객명1은 10자 이내로 입력해 주세요.",
        }
    }
    , customerMobile1: {
        rules : {
            required: true,
            regexp: "^01[016-9][0-9]{3,4}[0-9]{4}$",
        }
        , message: {
            required: "휴대전화번호는 필수 입력 항목입니다.",
            regexp: "연락처1에 올바른 형식의 휴대전화번호를 입력해 주세요.",
        }
    }
    , customerName2: {
        rules : {
            required: false,
            regexp1: "^.{1,10}$",
        }
        , message: {
            regexp1: "고객명2은 10자 이내로 입력해 주세요.",
        }
    }
    , customerMobile2: {
        rules : {
            required: false,
            regexp1: "^01[016-9][0-9]{3,4}[0-9]{4}$",
        }
        , message: {
            regexp1: "연락처2에 올바른 형식의 휴대전화번호를 입력해 주세요.",
        }
    }
    , reservationManager: {
        rules : {
            required: true,
        }
        , message: {
            required: "예약담당을 선택해 주세요.",
        }
    }
    , consultingManager: {
        rules : {
            required: true,
        }
        , message: {
            required: "상담담당을 선택해 주세요.",
        }
    }
    , consultingDate: {
        rules : {
            required: true,
        }
        , message: {
            required: "상담예약일을 선택해 주세요.",
        }
    }
    , consultingHour: {
        rules : {
            required: true,
        }
        , message: {
            required: "상담예약시간(시)를 선택해 주세요.",
        }
    }
    , consultingMinute: {
        rules : {
            required: true,
        }
        , message: {
            required: "상담예약시간(분)을 선택해 주세요.",
        }
    }
}

