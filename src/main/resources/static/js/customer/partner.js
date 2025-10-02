function initConsultingPartner() {
    $('#partnerPic').text('');
    $('#partnerContact').text('');
    $('#partnerMemo').text('');
}

function getConsultingPartnerInfo(partnerId) {
    callLazyService(
        "POST"
        , `/reservation/popup/partner/detail?customerId=${getParamValue('customerId')}&partnerId=${partnerId}`
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

function setConsultingPartnerData(data) {
    $('#partnerPic').text(data.pic == null ? '' : data.pic);
    $('#partnerContact').text(data.contact == null ? '' : data.contact);
    $('#partnerMemo').text(data.memo == null ? '' : data.memo);
    $('.purchaseCharge').text(`${data.charge == null ? '0' : data.charge}%`)
}

$(document).on('change', '#consultingPartner', function () {
    getConsultingPartnerInfo($(this).val());
});
