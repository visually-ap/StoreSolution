$(document).on('click', '.openImageViewer', function () {
    window.open(`/store/image/viewer?id=${$(this).data('fileid')}`, "ImageViewer"
        , 'height=980, width=1585, fullscreen=yes, status=no, menubar=no, toolbar=no, resizable=no'
    );
});
