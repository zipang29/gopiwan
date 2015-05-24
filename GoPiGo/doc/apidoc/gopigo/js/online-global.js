$(document).ready(function() {
    // rel='external' links to open a new window
    $('a[rel=external]').each(function() {
        $(this).attr('target', '_blank');
    });

    // rel='tooltip' elements to open a bootstrap tooltip
    $('[rel=tooltip]').each(function() {
        $(this).tooltip();
    });

    // rel='popover' elements to open a bootstrap popover
    $('[rel=popover]').each(function() {
        $(this).popover();
    });

    // check/uncheck all
    $('table .check_all').on('change', null, function() {
        var checkboxes = $(this).closest('table').find('td > input[type=checkbox]');

        if ($(this).prop('checked') === true)
            checkboxes.prop('checked', true);
        else
            checkboxes.removeAttr('checked');
    });

    // disable double-click on submit buttons
    $('form').submit(function(e) {
        $(this).find(':submit .force-single-submit').on('click', null, function(e) {
            e.preventDefault();
        });
    });
    $('div.disappear').each(function() {
        $( "div.disappear" ).delay( 5000 ).slideUp( 300 ).fadeOut( 300 );
    });
});
