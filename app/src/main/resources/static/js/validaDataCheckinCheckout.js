document.addEventListener('DOMContentLoaded', function() {

    const validaForm = (seletor) => (evento) => {

        evento.preventDefault()

        const validaCampo = (campoData) => {

            if (!campoData) {
                return false;
            }

            if (campoData.value === '') {
                campoData.focus();
                return false;
            }

            campoData.classList.add('input-fancy');
            return true;
        }

        if (!evento) {
            return false;
        }

        let campoData = evento.srcElement.querySelector(seletor);

        if (!validaCampo(campoData)) {
            return false;
        }

        evento.currentTarget.submit();
        return true;
    }

    let formularioCheckin = document.querySelector("#formCheckin");
    let formularioCheckout = document.querySelector("#formCheckout");

    if (formularioCheckin) {
        formularioCheckin.addEventListener("submit", validaForm('.data-checkin'));
    }

    if (formularioCheckout) {
        formularioCheckout.addEventListener("submit", validaForm('.data-checkout'));
    }
});