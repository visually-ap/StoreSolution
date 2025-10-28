function initConsultingPartner() {
    $('#partnerPic').empty();
    $('#partnerPic').append('<option value="" selected>-- 선택 --</option>');
    $('#partnerContact').text('');
    $('#partnerMemo').text('');
}



function setConsultingPartnerData(data) {
    data.picList.forEach(function (pic) {
        $('#partnerPic').append(`
        <option value="${pic.picId}">${pic.name}</option>
        `)
    });
    $('#partnerMemo').text(data.memo == null ? '' : data.memo);
    partnerFee = data.charge == null ? 0 : data.charge;
}

$(document).on('change', '#consultingPartner', function () {
    getConsultingPartnerInfo($(this).val());
});

function getConsultingPartnerInfo(partnerId) {
    callLazyService(
        "POST"
        , `/reservation/partner/detail?customerId=${getParamValue('customerId')}&partnerId=${partnerId}`
        , {}
        , function (response) {
            initConsultingPartner();
            if (response.success) {
                setConsultingPartnerData(response.result);
            } else {
                alert(response.message);
            }
        }
    );
}

$(document).on('change', '#partnerPic', function () {
    getConsultingPartnerPicInfo($(this).val());
});

function getConsultingPartnerPicInfo(picId) {
    callLazyService(
        "POST"
        , `/reservation/pic/detail?customerId=${getParamValue('customerId')}&picId=${picId}`
        , {}
        , function (response) {
            if (response.success) {
                setConsultingPartnerPicContact(response.result);
            } else {
                alert(response.message);
            }
        }
    );
}

function setConsultingPartnerPicContact(data) {
    $('#partnerContact').text(data.contact == null ? '' : addDashToMobileNumber(data.contact));
}
