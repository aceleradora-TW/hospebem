document.addEventListener('DOMContentLoaded', function() {
    var  datepickerRetroativo = document.getElementsByClassName('datepicker-retroativo');
    var  datepickerFuturo = document.getElementsByClassName('datepicker-futuro');

    for(var i = 0; i < datepickerRetroativo.length; i++) {
        if (document.getElementById("formSolicitacao") != null) {
            seletorDataNascimento(datepickerRetroativo[i]);
        }

    }
    for(var i = 0; i < datepickerFuturo.length; i++) {
        if (document.getElementById("formSolicitacao") != null) {
            seletorData(datepickerFuturo[i]);
        }
    }

    for(let i = 0; i < datepickerRetroativo.length; i++) {
        if (document.getElementById("formCheckin") != null) {
            seletorDataMenorQueHoje(datepickerRetroativo[i]);
        }
    }

    for(let i = 0; i < datepickerRetroativo.length; i++) {
        if (document.getElementById("formCheckout") != null) {
            seletorDataMenorQueHoje(datepickerRetroativo[i]);
        }
    }
})

function seletorDataNascimento(elemento) {
    return new Pikaday({
        maxDate: new Date(),
        yearRange: [1900, 2100],
        field: elemento,
        format: 'DD/MM/YYYY',
        i18n: {
            previousMonth : 'Mês Anterior',
            months        : ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
            weekdays      : ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
            weekdaysShort : ['Dom','Seg','Ter','Qua','Qui','Sex','Sab']
        }
    });
}

function seletorData(elemento) {
    return new Pikaday({
        minDate: new Date(),
        yearRange: [1990, 2048],
        field: elemento,
        format: 'DD/MM/YYYY',
        i18n: {
            previousMonth: 'Mês Anterior',
            months: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
            weekdays: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
            weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab']
        }
    });

}

function seletorDataMenorQueHoje(elemento) {
    return new Pikaday({
        maxDate: new Date(),
        yearRange: [1900, 2100],
        field: elemento,
        format: 'DD/MM/YYYY',
        i18n: {
            previousMonth : 'Mês Anterior',
            months        : ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
            weekdays      : ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
            weekdaysShort : ['Dom','Seg','Ter','Qua','Qui','Sex','Sab']
        }
    });
}

function seletorDataMaiorQueHoje(elemento) {
    return new Pikaday({
        minDate: new Date(),
        yearRange: [1990, 2048],
        field: elemento,
        format: 'DD/MM/YYYY',
        i18n: {
            previousMonth: 'Mês Anterior',
            months: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
            weekdays: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
            weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab']
        }
    });

}