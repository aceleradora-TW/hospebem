function validaSenhaUsuario(event) {/registrar
    if (validarCampoSenhaUsuario()){
        event.currentTarget.submit()
        return true
    }
    return false
}

function validarCampoSenhaUsuario() {
    let senha = document.getElementById('passwordUser')

    if (senha.value === '') {
        senha.placeholder = 'Insira o campo requisitado'
        senha.classList.remove('input-fancy')
        senha.classList.add('is-danger')
        senha.focus()
        return false
    }
    return true
}