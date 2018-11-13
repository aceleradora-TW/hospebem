
const validaRegistrar = (evento) => {
    if (validaTipoUsuario()){
        if (camposAssistente()) {
            if (validaUsuarioSenha()) {
                alert("registro efetuado!")
                evento.currentTarget.submit()
                return true
            }
        }
    }
    return false
}

const validaTipoUsuario = () => {
    let adminRadio = document.getElementById("admin")
    let assistenteRadio = document.getElementById("assistente")
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
    let radioNome = document.getElementById('assistente')
    let radioAdmin = document.getElementById('admin')
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

const camposAssistente = () => {
    let nomeAssis = document.getElementById('nomeAss')
    let emailAssis = document.getElementById('emailAss')
    let hospitalReferencia = document.getElementById('hospReferencia')
    let telefoneAssistente = document.getElementById('teleAssistente')
    let assistenteRadio = document.getElementById('assistente')

    if (assistenteRadio.checked === true) {
        if (nomeAssis.value === '') {
            validaCampoVazio(nomeAssis)
            return false
        } else
            nomeAssis.classList.add('input-fancy')

        if (emailAssis.value === '') {
            validaCampoVazio(emailAssis)
            return false
        } else
            emailAssis.classList.add('input-fancy')

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

