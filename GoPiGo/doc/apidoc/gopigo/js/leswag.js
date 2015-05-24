var LeSwag = {
    init: function() {
        $('.leswag_operation_full').hide();

        $('.leswag_operation_signature').click(function() {
            $(this).siblings('.leswag_operation_full').slideToggle();
        });

        $('span.leswag_model').each(function() {
            var model_id = $(this).attr('data-model-id');

            if ($('#leswag_model_' + model_id).length) {
                $(this).children('a').click(function() {
                    var model_id = $(this).parent().attr('data-model-id');
                    var schema_html = $('#leswag_model_' + model_id).html();
                    var place_holder = $(this).parents('p').siblings('.leswag_schema_placeholder');

                    place_holder.hide();
                    place_holder.html(schema_html);
                    place_holder.slideToggle();

                    $(this).hide();
                });
            } else {
                $(this).children('span a').hide();
            }
        });

        $('p.leswag_return_value');

        $('select.leswag_code_sample_language_select').change(function() {
            var language = $('option:selected', this).val();
            var place_holder = $(this).parent().parent().children('.leswag_code_sample_placeholder');

            if (language === '') {
                place_holder.html('');
            } else {
                var code_sample = $('#leswag_code_sample_' + language).html();
                place_holder.html(code_sample);
            }
        }).change();
    }
};

$(document).ready(function() {
    LeSwag.init();
});
