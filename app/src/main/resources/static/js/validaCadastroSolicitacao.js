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
    var peso           = document.getElementById("peso")
    var ehCadeirante   = document.getElementById("cadeirante.sim")
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

    if (ehCadeirante.checked === true) {
        if (peso.value === '') {
            alert('Por favor, preencha o campo peso')
            validaInputErrado(peso)
            return false
        } else
            peso.classList.add('input-fancy')
    }

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