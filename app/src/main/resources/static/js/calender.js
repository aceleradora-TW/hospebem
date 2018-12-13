document.addEventListener('DOMContentLoaded', () =>{
    let  datepickerRetroativo = document.getElementsByClassName('datepicker-retroativo');
    let  datepickerFuturo = document.getElementsByClassName('datepicker-futuro');
    let  datepicker = document.getElementsByClassName('datepicker');

    for(let i = 0; i < datepickerRetroativo.length; i++) {
        if (document.getElementById("formSolicitacao") != null) {
            seletorDataNascimento(datepickerRetroativo[i]);
        }

    }

    for(let i = 0; i < datepickerFuturo.length; i++) {
        if (document.getElementById("formSolicitacao") != null) {
            seletorData(datepickerFuturo[i]);
        }
    }

    if (document.getElementById("formCheckin") != null) {
        for(let i = 0; i < datepickerRetroativo.length; i++) {
            seletorDataMenorQueHoje(datepickerRetroativo[i]);
        }
    }
    if (document.getElementById("formCheckout") != null) {
        for(let i = 0; i < datepickerRetroativo.length; i++) {
            seletorDataMenorQueHoje(datepickerRetroativo[i]);
        }
    }

    for(let i = 0; i < datepicker.length; i++) {
        if (document.getElementById("formSolicitacao") != null) {
            seletorDataPi(datepicker[i]);
        }
    }

})
const data = new Date()

const seletorDataNascimento = (elemento) => {

    return new Pikaday({
        maxDate: data,
        yearRange: [1900, data.getFullYear()],
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

const seletorData = (elemento) =>{
    return new Pikaday({
        minDate: data,
        yearRange: [data.getFullYear(), 5000],
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

const seletorDataMenorQueHoje = (elemento) =>{
    return new Pikaday({
        maxDate: data,
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

const seletorDataPi = (elemento) =>{
    return new Pikaday({
        maxDate: new Date(2100, 12, 31),
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