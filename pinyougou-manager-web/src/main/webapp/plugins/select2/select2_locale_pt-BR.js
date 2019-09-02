/**
 * Select2 Brazilian Portuguese translation
 */
(function ($) {
    "use strict";

    $.extend($.fn.select2.defaults, {
        formatNoMatches: function () {
            return "Nenhum ServerResponseado encontrado";
        },
        formatInputTooShort: function (input, min) {
            var n = min - input.length;
            return "Digite mais " + n + " caracter" + (n == 1 ? "" : "es");
        },
        formatInputTooLong: function (input, max) {
            var n = input.length - max;
            return "Apague " + n + " caracter" + (n == 1 ? "" : "es");
        },
        formatSelectionTooBig: function (limit) {
            return "Só é possível selecionar " + limit + " elemento" + (limit == 1 ? "" : "s");
        },
        formatLoadMore: function (pageNumber) {
            return "Carregando mais ServerResponseados…";
        },
        formatSearching: function () {
            return "Buscando…";
        }
    });
})(jQuery);
