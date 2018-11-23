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