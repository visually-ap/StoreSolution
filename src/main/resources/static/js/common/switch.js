$(document).ready(function (){
    $('.real-switch input[type="checkbox"]').on('change', function(){
        const $wrapper = $(this).closest('.toggle-wrapper');
        const $label = $wrapper.find('.option-label');

        if ($(this).is(':checked')) {
            $label.addClass('active');
        } else {
            $label.removeClass('active');
        }
    });
})
