
function validaRegistroAssistente(evento) {
        if (camposAssistente()) {
        alert("registro efetuado!")
        evento.currentTarget.submit()
        return true
    }
    return false
}


function registraAssistente() {
    var nomeInput = document.getElementById('nomeAss')
    var textoAssistenteSocial = document.querySelector('.display-none')
    var radioNome = document.getElementById('assistente')
    var radioAdmin = document.getElementById('admin')
    var emailInput = document.getElementById('emailAss')
    var textoEmail = document.getElementById('emailAssistente')
    var hospitalReferenciaInput = document.getElementById('hospReferencia')
    var textoHospitalReferencia= document.getElementById('hospitalReferencia')
    var telefoneAssistenteInput= document.getElementById('teleAssistente')
    var textotelefoneAssistente= document.getElementById('telefoneAss')
    radioNome.addEventListener('change', function () {
        if (this.checked === true){
            textoAssistenteSocial.classList.remove('display-none')
            emailInput.classList.remove('display-none')
            nomeInput.classList.remove('display-none')
            textoEmail.classList.remove('display-none')
            hospitalReferenciaInput.classList.remove('display-none')
            textoHospitalReferencia.classList.remove('display-none')
            telefoneAssistenteInput.classList.remove('display-none')
            textotelefoneAssistente.classList.remove('display-none')
        }
        else {
            textoAssistenteSocial.classList.add('display-none')
            emailInput.classList.add('display-none')
            nomeInput.classList.add('display-none')
            textoEmail.classList.add('display-none')
            hospitalReferenciaInput.classList.add('display-none')
            textoHospitalReferencia.classList.add('display-none')
            telefoneAssistenteInput.classList.add('display-none')
            textotelefoneAssistente.classList.add('display-none')
        }
    })

    radioAdmin.addEventListener('change', function () {
        if (this.checked === true){
            textoAssistenteSocial.classList.add('display-none')
            emailInput.classList.add('display-none')
            nomeInput.classList.add('display-none')
            textoEmail.classList.add('display-none')
            hospitalReferenciaInput.classList.add('display-none')
            textoHospitalReferencia.classList.add('display-none')
            telefoneAssistenteInput.classList.add('display-none')
            textotelefoneAssistente.classList.add('display-none')
        }
    });
    }

function ValidaCampoVazio(item) {

    item.focus()
}

function camposAssistente() {
    var nomeAssis = document.getElementById('nomeAss')
    var emailAssis = document.getElementById('emailAss')
    var hospRef = document.getElementById('hospReferencia')
    var telAssis = document.getElementById('teleAssistente')
    var usuario = document.getElementById("first_name")
    var senha = document.getElementById("password")

            if (nomeAssis.value === '') {
                alert('Por favor, preencha o campo nome'),

                    ValidaCampoVazio(nomeAssis)
                return false

            } else
                nomeAssis.classList.add('input-fancy')


            if (emailAssis.value === '') {
                alert('Por favor, preencha o email')
                ValidaCampoVazio(emailAssis)
                return false

            } else
                emailAssis.classList.add('input-fancy')

            if (hospRef.value === '') {
                alert('Por favor, preencha o hospital de referência')
                ValidaCampoVazio(hospRef)
                return false

            } else
                hospRef.classList.add('input-fancy')

            if (telAssis.value === '') {
                alert('Por favor, preencha o telefone')
                ValidaCampoVazio(telAssis)
                return false

            } else
                telAssis.classList.add('input-fancy')

    if (usuario.value === '') {
        alert('Por favor, preencha o campo usuário'),

            ValidaCampoVazio(usuario)
        return false

    } else
        usuario.classList.add('input-fancy')

    if (senha.value === '') {
        alert('Por favor, preencha o campo senha'),

            ValidaCampoVazio(senha)
        return false

    } else
        senha.classList.add('input-fancy')
    return true
}