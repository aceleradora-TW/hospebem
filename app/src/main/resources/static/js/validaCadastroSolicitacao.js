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
    var nome           = document.getElementById("nome")
    var genero1        = document.getElementById("genero1")
    var genero2        = document.getElementById("genero2")
    var dataNascimento = document.getElementById("dataNascimento")
    var situacaoPre    = document.getElementById("situacao.pre")
    var situacaoPos    = document.getElementById("situacao.pos")
    var dataEntrada    = document.getElementById("dataEntrada")

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
     var nomeAcomp      = document.querySelector(".nome")
     var generoAcomp    = document.querySelectorAll(".genero")
     var dataNasciAcomp = document.querySelector(".data-nasc-acomp")

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
    var pesoInput = document.getElementById('peso');
    var textoPeso = document.querySelector('.display-none')
    var checkBoxCadeirante = document.getElementById('cadeirante');

    checkBoxCadeirante.addEventListener('change', function() {
        if(this.checked) {
            pesoInput.classList.remove('display-none');
            textoPeso.classList.remove('display-none');

        } else {
            pesoInput.classList.add('display-none');
            textoPeso.classList.add('display-none');

        }
    });

    pesoInput.addEventListener('change', function() {
        if(this.value > 600) {
            pesoInput.value = 600;
        }
    });
}



