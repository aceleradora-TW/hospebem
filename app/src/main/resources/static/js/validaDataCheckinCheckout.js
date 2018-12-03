const validaForm = (seletor) => (evento) => {
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

formularioCheckin.addEventListener("submit", validaForm('.data-checkin'));
formularioCheckout.addEventListener("submit", validaForm('.data-checkout'));