$('li a').each(function () {
    $(this).attr('href', $(this).attr('href') + "?page=1");
});