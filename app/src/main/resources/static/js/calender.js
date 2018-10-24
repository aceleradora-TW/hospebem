document.addEventListener('DOMContentLoaded', function() {
    let datepickerFields = document.getElementsByClassName('datepicker');

    for(let i = 0; i < datepickerFields.length; i++) {
        if (document.getElementById("formSolicitacao") != null) {
            seletorDataNascimento(datepickerFields[i]);
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
        yearRange: [1990, 2048],
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