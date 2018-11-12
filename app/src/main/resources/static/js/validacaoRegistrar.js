
validaRegistrar = (evento) => {
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

validaTipoUsuario = () => {
    let adminRadio = document.getElementById("admin")
    let assistenteRadio = document.getElementById("assistente")
    if (adminRadio.checked === false && assistenteRadio.checked === false) {
        alert('Por favor, selecione o tipo')
        return false
    }
    return true
}

validaUsuarioSenha = () => {
    let usuario = document.getElementById("first_name")
    let senha   = document.getElementById("password")

    if (usuario.value === '') {
        ValidaCampoVazio(usuario)
        return false
    } else
        usuario.classList.add('input-fancy')

    if (senha.value === '') {
        ValidaCampoVazio(senha)
        return false

    } else
        senha.classList.add('input-fancy')

    return true
}

registraAssistente = () => {
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
    });
    }

 ValidaCampoVazio = (item) => {
    alert('Preencha o campo obrigatorio!')
    item.classList.remove('input-fancy')
    item.classList.add('is-danger')
    item.focus()
}


camposAssistente = () => {
    let nomeAssis = document.getElementById('nomeAss')
    let emailAssis = document.getElementById('emailAss')
    let hospRef = document.getElementById('hospReferencia')
    let telAssis = document.getElementById('teleAssistente')
    let assistenteRadio = document.getElementById('assistente')

    if (assistenteRadio.checked === true) {
        if (nomeAssis.value === '') {
            ValidaCampoVazio(nomeAssis)
            return false

        } else
            nomeAssis.classList.add('input-fancy')

        if (emailAssis.value === '') {
            ValidaCampoVazio(emailAssis)
            return false
        } else
            emailAssis.classList.add('input-fancy')

        if (hospRef.value === '') {
            ValidaCampoVazio(hospRef)
            return false
        } else
            hospRef.classList.add('input-fancy')

        if (telAssis.value === '') {
            ValidaCampoVazio(telAssis)
            return false

        } else
            telAssis.classList.add('input-fancy')
    }


    return true
}

