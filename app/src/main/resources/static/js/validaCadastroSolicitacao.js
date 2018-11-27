function validaCadastroSolicitacao(evento) {
    if (camposPaciente() && camposAcompanhantes() && camposSolicitante()) {
        alert("Solicitação efetuada!")
        evento.currentTarget.submit()
        return true;
    }
    return false;
}

function camposPaciente() {
    var nome = document.getElementById("nome")
    var genero1 = document.getElementById("genero1")
    var genero2 = document.getElementById("genero2")
    var labelGenero = document.getElementById('labelGenero')
    var dataNascimento = document.getElementById('dataNascimento')
    var situacaoPre = document.getElementById("situacao.pre")
    var situacaoPos = document.getElementById("situacao.pos")
    var labelSituacao = document.getElementById('labelSituacao')
    var checkBoxCadeirante = document.getElementById('cadeirante')
    var pesoInput = document.getElementById('peso')
    var dataEntrada = document.getElementById('dataEntrada')
    var orgao = document.getElementById('select')
    var selectOrgao = document.getElementById('selectOrgao')

    if (nome.value === '') {
        validaInputErrado(nome)
        return false
    } else
        nome.classList.add('input-fancy')

    if (genero1.checked === false && genero2.checked === false) {
        labelGenero.classList.remove('margin-invisible')
        genero1.focus()
        return false
    } else {
        labelGenero.classList.add('margin-invisible')
    }


    if (dataNascimento.value === '') {
        validaInputErrado(dataNascimento)
        return false
    } else
        dataNascimento.classList.add('input-fancy')

    if (checkBoxCadeirante.checked === true && pesoInput.value === '') {
        return false
    }

    if (situacaoPre.checked === false && situacaoPos.checked === false) {
        labelSituacao.classList.remove('margin-invisible')
        situacaoPre.focus()
        return false
    } else {
        labelSituacao.classList.add('margin-invisible')
    }

    if (orgao.value === 'selecione') {
        selectOrgao.classList.remove('margin-invisible')
        orgao.focus()
        return false
    } else
        selectOrgao.classList.add('margin-invisible')


    if (dataEntrada.value === '') {
        validaInputErrado(dataEntrada)
        return false
    } else
        dataEntrada.classList.add('input-fancy')

    return true
}

function camposAcompanhantes() {
    var nomeAcomp = document.querySelector(".nome")
    var genero1 = document.querySelector(".generoA1")
    var genero2 = document.querySelector(".generoA2")
    var dataNasciAcomp = document.querySelector(".data-nasc-acomp")
    var labelAcomp = document.getElementById('labelAcomp')

    if (nomeAcomp.value === '') {
        nomeAcomp.placeholder = 'Insira o campo requisitado'
        validaInputErrado(nomeAcomp)
        return false
    } else
        nomeAcomp.classList.add('input-fancy')

    if (genero1.checked === false && genero2.checked === false) {
        labelAcomp.classList.remove('margin-invisible')
        return false;
    } else {
        labelAcomp.classList.add('margin-invisible')
    }

    if (dataNasciAcomp.value === '') {
        nomeAcomp.placeholder = 'Insira o campo requisitado'
        validaInputErrado(dataNasciAcomp)
        return false;

    }
    return true;

}

function camposSolicitante(){
    var nomeSolicitante = document.getElementById('nomeSolicitante')
    var hospitalReferencia = document.getElementById('hospitalReferencia')
    var email = document.getElementById('email')
    var telefoneSolicitante = document.getElementById('telefoneSolicitante')

    if (nomeSolicitante.value === '') {
        nomeSolicitante.placeholder = 'Insira o campo requisitado'
        validaInputErrado(nomeSolicitante)
        return false
    } else
        nomeSolicitante.classList.add('input-fancy')

    if (hospitalReferencia.value === '') {
        hospitalReferencia.placeholder = 'Insira o campo requisitado'
        validaInputErrado(hospitalReferencia)
        return false
    } else
        hospitalReferencia.classList.add('input-fancy')

    if (email.value === '') {
        email.placeholder = 'Insira o campo requisitado'
        validaInputErrado(email)
        return false
    } else
        email.classList.add('input-fancy')

    if (telefoneSolicitante.value === '') {
        telefoneSolicitante.placeholder = 'Insira o campo requisitado'
        validaInputErrado(telefoneSolicitante)
        return false
    } else
        telefoneSolicitante.classList.add('input-fancy')

    return true;
}

function validaInputErrado(item) {
    item.classList.remove('input-fancy')
    item.classList.add('is-danger')
    item.placeholder = 'Insira o campo requisitado'
    item.focus()
}

window.onload = function limpaInput() {
    document.getElementById('peso').value = ''
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

    pesoInput.addEventListener('change', function () {
        if (this.value > 600) {
            pesoInput.value = 600;
        }
    })
    return true;
}

