const validaFormCheckin = (evento) => {

    if (!evento) {
        return false;
    }

    let campoData = evento.srcElement.querySelector('.data-checkin');

    if (!validaCheckin(campoData)) {
        return false;
    }

    evento.currentTarget.submit();
    return true;
}

const validaCheckin = (campoDataCheckin) => {

    if (!campoDataCheckin) {
        return false;
    }

    if (campoDataCheckin.value === '') {
        campoDataCheckin.focus();
        return false;
    }

    campoDataCheckin.classList.add('input-fancy');
    return true;
}

const validaFormCheckout = (evento) => {
    if (!evento) {
        return false;
    }

    let campoData = evento.srcElement.querySelector('.data-checkout');

    if (!validaCheckout(campoData)) {
        return false;
    }

    evento.currentTarget.submit();
    return true;
}

const validaCheckout = (campoDataCheckout) => {

    if (!campoDataCheckout) {
        return false;
    }

    if (campoDataCheckout.value === '') {
        campoDataCheckout.focus();
        return false;
    }

    campoDataCheckout.classList.add('input-fancy');
    return true;
}