function validaCadastroSolicitacao(evento) {
    if (camposPaciente()) {
        if(camposAcompanhantes()) {
            alert("Solicitação efetuada!")
            evento.currentTarget.submit()
            return true
        }
    }

    return false
}

function camposPaciente() {
    var nome =  document.getElementById("nome")
    var genero1 = document.getElementById("genero1")
    var genero2 = document.getElementById("genero2")
    var dataNascimento =  document.getElementById("dataNascimento")
    var peso = document.getElementById("peso")
    var ehCadeirante = document.getElementById("cadeirante.sim")
    var situacaoPre = document.getElementById("situacao.pre")
    var situacaoPos = document.getElementById("situacao.pos")
    var dataEntrada = document.getElementById("dataEntrada")


    if (nome.value  === '') {
        alert('Por favor, preencha o campo nome')
        nome.classList.remove('input-fancy')
        nome.classList.add('is-danger')
        nome.focus()
        return false
    } else
        nome.classList.add('input-fancy')


    if (genero1.checked  === false && genero2.checked  === false) {
        alert('Por favor, selecione o gênero do paciente')
        genero1.focus();
        return false;
    }

    if (dataNascimento.value  === '') {
        alert('Por favor, preencha a data de nascimento')
        dataNascimento.classList.remove('input-fancy')
        dataNascimento.classList.add('is-danger')
        dataNascimento.focus()
        return false
    } else
        dataNascimento.classList.add('input-fancy')

    if (ehCadeirante.checked  === true) {
        if (peso.value === '') {
            alert('Por favor, preencha o campo peso')
            peso.classList.remove('input-fancy')
            peso.classList.add('is-danger')
            peso.focus();
            return false;
        } else
            peso.classList.add('input-fancy')
    }

    if (situacaoPre.checked === false &&
        situacaoPos.checked === false) {
        alert('Por favor, selecione a situacao do transplante')
        situacaoPre.focus()
        return false;
    }

    if (dataEntrada.value === ''){
        alert('Por favor, preencha a data de entrada')
        dataEntrada.classList.remove('input-fancy')
        dataEntrada.classList.add('is-danger')
        dataEntrada.focus()
        return false
    } else
        dataEntrada.classList.add('input-fancy')

    return true
}

 function camposAcompanhantes(){
     if (document.querySelector(".nome").value == false) {
         alert('Por favor, preencha o campo nome do acompanhante 1');
         document.querySelector(".nome").focus();
         return false;
     }
     if (document.querySelectorAll(".genero")[0].checked == false &&
        document.querySelectorAll(".genero")[1].checked == false) {
         alert('Por favor, selecione o gênero do acompanhante 1');
         return false;
     }
     if(document.querySelector(".data").value == false){
         alert('Por favor, preencha a data de nascimento do acompanhante');
         document.querySelector(".data").focus();
         return false;
     }

     return true;
}


