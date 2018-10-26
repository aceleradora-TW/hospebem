function validaCadastroSolicitacao(evento) {
    evento.preventDefault();
    campos_paciente();
    campos_acompanhantes();
    return false;
}

function campos_paciente() {
    if (document.getElementById("nome").value == false) {
        alert('Por favor, preencha o campo nome');
        document.getElementById("nome").focus();
        return false;
    }

    if (document.getElementById("genero1").checked == false &&
        document.getElementById("genero2").checked == false) {
        alert('Por favor, selecione o gênero do paciente');
        return false;
    }

    if (document.getElementById("dataNascimento").value == false) {
        alert('Por favor, preencha a data de nascimento');
        document.getElementById("dataNascimento").focus();
        return false;
    }

    if (document.getElementById("cadeirante.sim").checked == true) {
        if (document.getElementById("peso").value == false) {
            alert('Por favor, preencha o campo peso');
            document.getElementById("peso").focus();
            return false;
        }
    }

    if (document.getElementById("situacao.pre").checked == false &&
        document.getElementById("situacao.pos").checked == false) {
        alert('Por favor, selecione a situacao do transplante');
        return false;
    }

    if (document.getElementById("dataEntrada").value == false){
        alert('Por favor, preencha a data de entrada');
        document.getElementById("dataEntrada").focus();
        return false;
    }
}

 function campos_acompanhantes(){
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
}


