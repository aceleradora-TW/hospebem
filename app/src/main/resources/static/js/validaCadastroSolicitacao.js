function validaCadastroSolicitacao(evento) {
    if (camposPaciente() && campoEndereco() && camposAcompanhantes() && camposSolicitante()){
        alert("Solicitação efetuada!");
        evento.currentTarget.submit();
        return true;
    }
    return false;
}

function camposPaciente() {
    let nome = document.getElementById("nome");
    let genero1 = document.getElementById("genero1");
    let genero2 = document.getElementById("genero2");
    let labelGenero = document.getElementById('labelGenero');
    let dataNascimento = document.getElementById('dataNascimento');
    let situacaoPre = document.getElementById("situacao.pre");
    let situacaoPos = document.getElementById("situacao.pos");
    let labelSituacao = document.getElementById('labelSituacao');
    let checkBoxCadeirante = document.getElementById('cadeirante');
    let pesoInput = document.getElementById('peso');
    let orgao = document.getElementById('select');
    let selectOrgao = document.getElementById('selectOrgao');
    let dataEntrada = document.getElementById('dataEntrada');

    if (nome.value === '') {
        validaInputErrado(nome);
        return false
    } else {
        nome.classList.add('input-fancy');
    }

    if (genero1.checked === false && genero2.checked === false) {
        labelGenero.classList.remove('margin-invisible');
        genero1.focus();
        return false
    } else {
        labelGenero.classList.add('margin-invisible')
    }

    if (dataNascimento.value === '') {
        validaInputErrado(dataNascimento);
        return false
    } else {
        dataNascimento.classList.add('input-fancy');
    }

    if (checkBoxCadeirante.checked === true && pesoInput.value === '') {
        return false
    }

    if (situacaoPre.checked === false && situacaoPos.checked === false) {
        labelSituacao.classList.remove('margin-invisible');
        situacaoPre.focus();
        return false
    } else {
        labelSituacao.classList.add('margin-invisible')
    }

    if (orgao.value === 'selecione') {
        selectOrgao.classList.remove('margin-invisible');
        orgao.focus();
        return false
    } else {
        selectOrgao.classList.add('margin-invisible');
    }

    if (dataEntrada.value === '') {
        validaInputErrado(dataEntrada);
        return false
    } else {
        dataEntrada.classList.add('input-fancy');
        return true
    }
}

function campoEndereco() {
    let UF = document.getElementById("uf")

    if (UF.value === '') {
        validaInputErrado(UF);
        return false
    } else {
        UF.classList.add('input-fancy');
        return true
    }
}

function camposAcompanhantes() {
    let nomeAcomp = document.querySelector(".nome");
    let genero1 = document.querySelector(".generoA1");
    let genero2 = document.querySelector(".generoA2");
    let dataNasciAcomp = document.querySelector(".data-nasc-acomp");
    let labelAcomp = document.getElementById('labelAcomp');

    if (nomeAcomp.value === '') {
        validaInputErrado(nomeAcomp);
        return false
    } else {
        nomeAcomp.classList.add('input-fancy');
    }

    if (genero1.checked === false && genero2.checked === false) {
        labelAcomp.classList.remove('margin-invisible');
        return false;
    } else {
        labelAcomp.classList.add('margin-invisible')
    }

    if (dataNasciAcomp.value === '') {
        validaInputErrado(dataNasciAcomp);
        return false;
    }
    return true;
}

function camposSolicitante(){
    let nomeSolicitante = document.getElementById('nomeSolicitante');
    let hospitalReferencia = document.getElementById('hospitalReferencia');
    let email = document.getElementById('email');
    let telefoneSolicitante = document.getElementById('telefoneSolicitante');

    if (nomeSolicitante.value === '') {
        return false
    } else {
        nomeSolicitante.classList.add('input-fancy');
    }

    if (hospitalReferencia.value === '') {
        validaInputErrado(hospitalReferencia);
        return false
    } else {
        hospitalReferencia.classList.add('input-fancy');
    }

    if (email.value === '') {
        validaInputErrado(email);
        return false
    } else {
        email.classList.add('input-fancy');
    }

    if (telefoneSolicitante.value === '') {
        validaInputErrado(telefoneSolicitante);
        return false
    } else {
        telefoneSolicitante.classList.add('input-fancy');
        return true;
    }
}

function validaInputErrado(item) {
    item.classList.remove('input-fancy');
    item.classList.add('is-danger');
    item.placeholder = 'Insira o campo requisitado';
    item.focus()
}

window.onload = function limpaInput() {
    document.getElementById('peso').value = ''
}

function pesoCadeirante() {
    let textoPeso = document.querySelector('.display-none');
    let checkBoxCadeirante = document.getElementById('cadeirante');
    let pesoInput = document.getElementById('peso');

    checkBoxCadeirante.addEventListener('change', function () {
        if (this.checked) {
            pesoInput.classList.remove('display-none');
            textoPeso.classList.remove('display-none');
            if (pesoInput.value === '') {
                validaInputErrado(pesoInput);
                return false;
            }
            return false;
        } else {
            pesoInput.classList.add('display-none');
            textoPeso.classList.add('display-none');
            return false;
        }
    });

    pesoInput.addEventListener('change', function () {
        if (this.value > 600) {
            pesoInput.value = 600;
            pesoInput.focus();
        }
    });
    return true;
}