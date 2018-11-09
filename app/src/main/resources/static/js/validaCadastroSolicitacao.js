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
    let nome           = document.getElementById("nome")
    let genero1        = document.getElementById("genero1")
    let genero2        = document.getElementById("genero2")
    let dataNascimento = document.getElementById("dataNascimento")
    let situacaoPre    = document.getElementById("situacao.pre")
    let situacaoPos    = document.getElementById("situacao.pos")
    let dataEntrada    = document.getElementById("dataEntrada")

    if (nome.value  === '') {
        alert('Por favor, preencha o campo nome')
        validaInputErrado(nome)
        return false

    } else
        nome.classList.add('input-fancy')


    if (genero1.checked  === false && genero2.checked  === false) {
        alert('Por favor, selecione o gênero do paciente')
        genero1.focus()
        return false
    }

    if (dataNascimento.value  === '') {
        alert('Por favor, preencha a data de nascimento')
        validaInputErrado(dataNascimento)
        return false

    } else
        dataNascimento.classList.add('input-fancy')

    if (situacaoPre.checked === false && situacaoPos.checked === false) {
        alert('Por favor, selecione a situacao do transplante')
        situacaoPre.focus()
        return false
    }

    if (dataEntrada.value === '') {
        alert('Por favor, preencha a data de entrada')
        validaInputErrado(dataEntrada)
        return false

    } else
        dataEntrada.classList.add('input-fancy')

    return true
}

 function camposAcompanhantes() {
     let nomeAcomp      = document.querySelector(".nome")
     let generoAcomp    = document.querySelectorAll(".genero")
     let dataNasciAcomp = document.querySelector(".data-nasc-acomp")

     if (nomeAcomp.value === '') {
         alert('Por favor, preencha o campo nome do acompanhante 1')
         validaInputErrado(nomeAcomp)
         return false

     } else
         nomeAcomp.classList.add('input-fancy')

     if (generoAcomp[0].checked === false && generoAcomp[1].checked === false) {
         alert('Por favor, selecione o gênero do acompanhante 1');
         return false;
     }

     if (dataNasciAcomp.value === '') {
         alert('Por favor, preencha a data de nascimento do acompanhante');
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
                pesoInput.setAttribute('required', 'required');
                pesoInput.setCustomValidity('Informe o peso do hospede');
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