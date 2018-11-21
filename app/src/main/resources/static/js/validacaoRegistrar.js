const validaRegistrar = (evento) => {
    if (validaTipoUsuario() === true && validaCamposAssistente() === true && validaUsuarioSenha() === true) {
        alert("registro efetuado!")
        evento.currentTarget.submit()
        return true
    }
    return false
}

const validaTipoUsuario = () => {
    let adminRadio = document.getElementById("ADMINISTRADOR")
    let assistenteRadio = document.getElementById("ASSISTENTE_SOCIAL")
    if (adminRadio.checked === false && assistenteRadio.checked === false) {
        alert('Por favor, selecione o tipo')
        return false
    }
    return true
}

const validaUsuarioSenha = () => {
    let usuario = document.getElementById("first_name")
    let senha   = document.getElementById("password")

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
    let radioNome = document.getElementById('ASSISTENTE_SOCIAL')
    let radioAdmin = document.getElementById('ADMINISTRADOR')
    radioNome.addEventListener('change', function () {

        if (this.checked === true){
            esconderCampos.classList.remove('display-none')
        }
    })

    radioAdmin.addEventListener('change', function () {
        if (this.checked === true){
            esconderCampos.classList.add('display-none')
        }
    })
    }

const validaCampoVazio = (item) => {
    alert('Preencha o campo obrigatorio!')
    item.classList.remove('input-fancy')
    item.classList.add('is-danger')
    item.focus()
}

const validaCamposAssistente = () => {
    let nomeAssistente = document.getElementById('nomeAssistente')
    let emailAssistente = document.getElementById('emailDaAssistente')
    let hospitalReferencia = document.getElementById('hospitalDeReferencia')
    let telefoneAssistente = document.getElementById('telefoneAssistente')
    let radioNome = document.getElementById('ASSISTENTE_SOCIAL')

    if (radioNome.checked === true) {
        if (nomeAssistente.value === '') {
            validaCampoVazio(nomeAssistente)
            return false
        } else
            nomeAssistente.classList.add('input-fancy')

        if (emailAssistente.value === '') {
            validaCampoVazio(emailAssistente)
            return false
        } else
            emailAssistente.classList.add('input-fancy')

        if (hospitalReferencia.value === '') {
            validaCampoVazio(hospitalReferencia)
            return false
        } else
            hospitalReferencia.classList.add('input-fancy')

        if (telefoneAssistente.value === '') {
            validaCampoVazio(telefoneAssistente)
            return false
        } else
            telefoneAssistente.classList.add('input-fancy')
    }
    return true
}

