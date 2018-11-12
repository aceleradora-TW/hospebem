function validaCadastroSolicitacao(evento) {
    if (camposPaciente()) {
        if (camposAcompanhantes()) {
                alert("Solicitação efetuada!")
                evento.currentTarget.submit()
                return true
        }
    }
    return false
}

function camposPaciente() {
    var nome               = document.getElementById("nome")
    var genero1            = document.getElementById("genero1")
    var genero2            = document.getElementById("genero2")
    var dataNascimento     = document.getElementById("dataNascimento")
    var situacaoPre        = document.getElementById("situacao.pre")
    var situacaoPos        = document.getElementById("situacao.pos")
    var checkBoxCadeirante = document.getElementById('cadeirante');
    var pesoInput          = document.getElementById('peso');
    var dataEntrada        = document.getElementById("dataEntrada")
    if (nome.value === '') {
        validaInputErrado(nome)
        return false
    } else {
        nome.setCustomValidity('')
        nome.classList.add('input-fancy')
    }

    if (genero1.checked  === false && genero2.checked  === false) {
        alert('marque algo')
        genero1.focus()
        return false

    }
    if (dataNascimento.value  === '') {
        validaInputErrado(dataNascimento)
        return false
    } else {
        dataNascimento.classList.add('input-fancy')

    }
    if (checkBoxCadeirante.checked === true && pesoInput.value === ''){
        return false
    }

    if (situacaoPre.checked === false && situacaoPos.checked === false) {
        // alert('Por favor, selecione a situacao do transplante')
        item.setCustomValidity('Campo obrigatório para solicitação.');
        situacaoPre.focus()
        return false
    }

    if (dataEntrada.value === '') {
        alert('Por favor, preencha a data de entrada')
        validaInputErrado(dataEntrada)
        return false

    } else {
        dataEntrada.classList.add('input-fancy')
    }

    return true
}
function camposAcompanhantes() {
     var nomeAcomp   = document.querySelector(".nome")
     var generoAcomp = document.querySelectorAll(".genero")
     var dataNasciAcomp = document.querySelector(".data-nasc-acomp")
     if (nomeAcomp.value === '') {
         nomeAcomp.placeholder='Insira o campo requisitado'
         validaInputErrado(nomeAcomp)
         return false
     } else {
         nomeAcomp.classList.add('input-fancy')

     }
     if (generoAcomp[0].checked === false && generoAcomp[1].checked === false) {
         alert('Por favor, selecione o gênero do acompanhante 1');
         return false;

     }
     if (dataNasciAcomp.value === '') {
         nomeAcomp.placeholder='Insira o campo requisitado'
         validaInputErrado(dataNasciAcomp)
         return false;

     }
     return true;

}
function validaInputErrado(item) {
    item.classList.remove('input-fancy')
    item.classList.add('is-danger')
    item.focus()
}

window.onload = function limpaInput() {
    document.getElementById('peso').value='';
}

function pesoCadeirante() {
    var textoPeso = document.querySelector('.display-none')
    var checkBoxCadeirante = document.getElementById('cadeirante');
    var pesoInput = document.getElementById('peso');

    checkBoxCadeirante.addEventListener('change', function () {
        if (this.checked) {
            pesoInput.classList.remove('display-none');
            textoPeso.classList.remove('display-none');
            if (pesoInput.value === '') {
                // pesoInput.setAttribute('required', 'required');
                // pesoInput.setCustomValidity('Informe o peso do hospede');
                validaInputErrado(pesoInput)
                return false;
            }

            return false;

        } else {
            pesoInput.classList.add('display-none');
            textoPeso.classList.add('display-none');
            return false;
        }

    });


    pesoInput.addEventListener('change', function() {
        if(this.value > 600) {
            pesoInput.value = 600;
        }
    });
    return true;
}

function requiredInput(item) {
    if (item.value === '') {
        item.setCustomValidity('Campo obrigatório para solicitação.');
    } else {
        item.setCustomValidity('');
    }

}

function requiredRadio(item) {
    if (item.checked === true) {
        item.setCustomValidity('Campo obrigatório para solicitação.');
    } else {
        item.setCustomValidity('');
    }
}

