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
    })


}