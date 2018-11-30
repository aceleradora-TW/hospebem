const validaRegistrar = (evento) => {
    if (validaTipoUsuario() === true && validaCamposAssistente() === true && validaUsuarioSenha() === true) {
        alert("Registro efetuado!")
        evento.currentTarget.submit()
        return true
    }
    return false
}

const validaTipoUsuario = () => {
    let labelCargo = document.getElementById('labelCargo')
    let adminRadio = document.getElementById("ADMINISTRADOR")
    let assistenteRadio = document.getElementById("ASSISTENTE_SOCIAL")
    if (adminRadio.checked === false && assistenteRadio.checked === false) {
        labelCargo.classList.remove('margin-invisible')
        return false
    }else{
        labelCargo.classList.add('margin-invisible')
    }
    return true
}

const validaUsuarioSenha = () => {
    let usuario = document.getElementById("first-name")
    let senha = document.getElementById("password")

    if (usuario.value === '') {
        validaCampoVazio(usuario)
        return false
    } else
        usuario.classList.add('input-fancy')

    if (senha.value === '') {
        validaCampoVazio(senha)
        return false
    } else
        senha.classList.add('input-fancy')
    return true
}

const registraAssistente = () => {
    let esconderCampos = document.querySelector('.div-escondida')
    let radioAssistente = document.getElementById('ASSISTENTE_SOCIAL')
    let radioAdmin = document.getElementById('ADMINISTRADOR')

    radioAssistente.addEventListener('change', function () {
        if (this.checked) {
            esconderCampos.classList.remove('display-none')
        }
    })

    radioAdmin.addEventListener('change', function () {
        if (this.checked) {
            esconderCampos.classList.add('display-none')
        }
    })
}

const validaCampoVazio = (item) => {
    item.classList.remove('input-fancy')
    item.classList.add('is-danger')
    item.placeholder = 'Insira o campo requisitado'
    item.focus()
}

const validaCamposAssistente = () => {
    let nomeAssistente = document.getElementById('nomeAssistente')
    let emailAssistente = document.getElementById('emailDaAssistente')
    let hospitalReferencia = document.getElementById('hospitalDeReferencia')
    let telefoneAssistente = document.getElementById('telefoneAssistente')
    let radioAssistente = document.getElementById('ASSISTENTE_SOCIAL')
    if (radioAssistente && radioAssistente.checked) {
        let campos = [nomeAssistente, emailAssistente, hospitalReferencia, telefoneAssistente]
        for (let i = 0; i < campos.length; i++) {
            if (!campos[i].value) {
                validaCampoVazio(campos[i])
                return false;
            }
            campos[i].classList.add('input-fancy')
        }
        return true
    }
    return true
}

