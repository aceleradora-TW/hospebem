function validaUsuario(event) {
    if (camposUsuario()) {
        alert("Solicitação efetuada!")
        event.currentTarget.submit()
        return true;
    }
    return false;
}



function camposUsuario(){
    let usuario = document.getElementById('usuario')
    let tipoUsuario = document.getElementById('tipoUsuario')
    let nome = document.getElementById('nome')
    let hospitalReferencia = document.getElementById('hospitalReferencia')
    let email = document.getElementById('email')
    let telefone = document.getElementById('telefone')

    if (tipoUsuario != 'ADMINISTRADOR') {
        if (usuario.value === '') {
            validaInputErrado(usuario)
            return false
        } else
            usuario.classList.add('input-fancy')

        if (nome.value === '') {
            validaInputErrado(nome)
            return false
        } else
            nome.classList.add('input-fancy')

        if (hospitalReferencia.value === '') {
            validaInputErrado(hospitalReferencia)
            return false
        } else
            hospitalReferencia.classList.add('input-fancy')

        if (email.value === '') {
            validaInputErrado(email)
            return false
        } else
            email.classList.add('input-fancy')

        if (telefone.value === '') {
            validaInputErrado(telefone)
            return false
        } else
            telefone.classList.add('input-fancy')
    }
    return true;
}

function validaInputErrado(item) {
    item.classList.remove('input-fancy')
    item.classList.add('is-danger')
    item.placeholder = 'Insira o campo requisitado'
    item.focus()
}