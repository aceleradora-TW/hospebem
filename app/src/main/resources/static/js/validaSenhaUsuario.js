function validaSenhaUsuario(event) {
    if (validarCampoSenhaUsuario()){
        event.currentTarget.submit()
        return true
    }
    return false
}

function validarCampoSenhaUsuario() {
    var senha = document.getElementById('passwordUser')

    if (senha.value === '') {
        alert('Informe uma senha para autenticar')
        senha.classList.remove('input-fancy')
        senha.classList.add('is-danger')
        return false
    }
    return true
}