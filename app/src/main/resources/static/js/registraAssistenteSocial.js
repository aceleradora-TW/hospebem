function registraAssistente() {
    var nomeInput = document.getElementById('nomeAss')
    var textoAssistenteSocial = document.querySelector('.display-none')
    var radioNome = document.getElementById('assistente')
    var radioAdmin = document.getElementById('admin')

    radioNome.addEventListener('change', function () {
        if (this.checked === true){
            textoAssistenteSocial.classList.remove('display-none')
            nomeInput.classList.remove('display-none')
        }
        else {
            textoAssistenteSocial.classList.add('display-none')
            nomeInput.classList.add('display-none')
        }
    })

    radioAdmin.addEventListener('change', function () {
        if (this.checked === true){
            textoAssistenteSocial.classList.add('display-none')
            nomeInput.classList.add('display-none')
        }
    })


}