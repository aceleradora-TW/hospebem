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
    var labelGenero        = document.querySelector('.margin-invisible')
    var dataNascimento     = document.getElementById('dataNascimento')
    var situacaoPre        = document.getElementById("situacao.pre")
    var situacaoPos        = document.getElementById("situacao.pos")
    var labelSituacao      = document.querySelector('.situacao')
    var checkBoxCadeirante = document.getElementById('cadeirante')
    var pesoInput          = document.getElementById('peso')
    var dataEntrada        = document.getElementById('dataEntrada')
    var orgao              = document.getElementById('select')
    var selectOrgao        = document.querySelector('.orgao')

    if (nome.value === '') {
        validaInputErrado(nome)
        return false
    } else
        nome.classList.add('input-fancy')

    if (genero1.checked  === false && genero2.checked  === false) {
        labelGenero.classList.remove('margin-invisible')
        genero1.focus()
        return false
    }


    if (dataNascimento.value  === '') {
        validaInputErrado(dataNascimento)
        return false
    } else
        dataNascimento.classList.add('input-fancy')

    if (checkBoxCadeirante.checked === true && pesoInput.value === ''){
        return false
    }

    if (situacaoPre.checked === false && situacaoPos.checked === false) {
        labelSituacao.classList.remove('margin-invisible')
        situacaoPre.focus()
        return false
    }

    if (orgao.value === 'selecione') {
        selectOrgao.classList.remove('margin-invisible')
        orgao.focus()
        return false
    }  else
        selectOrgao.classList.add('margin-invisible')


    if (dataEntrada.value === '') {
        validaInputErrado(dataEntrada)
        return false
    } else
        dataEntrada.classList.add('input-fancy')

    return true
}
function camposAcompanhantes() {
     var nomeAcomp   = document.querySelector(".nome")
    var genero1            = document.getElementById("generoA1")
    var genero2            = document.getElementById("generoA2")
     var dataNasciAcomp = document.querySelector(".data-nasc-acomp")
     var labelAcomp = document.querySelector('.label-acomp')

     if (nomeAcomp.value === '') {
         nomeAcomp.placeholder='Insira o campo requisitado'
         validaInputErrado(nomeAcomp)
         return false
     } else
         nomeAcomp.classList.add('input-fancy')

     if (genero1.checked === false && genero2.checked === false) {
         labelAcomp.classList.remove('margin-invisible')
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
    item.placeholder='Insira o campo requisitado'
    item.focus()
}

window.onload = function limpaInput() {
    document.getElementById('peso').value=''
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
                validaInputErrado(pesoInput)
                return false;
            }
            return false;
        } else {
            pesoInput.classList.add('display-none');
            textoPeso.classList.add('display-none');
            return false;
        }

    })

    pesoInput.addEventListener('change', function() {
        if(this.value > 600) {
            pesoInput.value = 600;
        }
    })
    return true;
}

