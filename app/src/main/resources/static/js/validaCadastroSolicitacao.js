function validaCadastroSolicitacao() {
    campos_paciente();
    campos_acompanhante_um();
    campos_acompanhante_dois();
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

 function campos_acompanhante_um(){
     if (document.getElementById("acompanhantes0.nome").value == false) {
         alert('Por favor, preencha o campo nome do acompanhante 1');
         document.getElementById("acompanhantes0.nome").focus();
         return false;
     }
     if (document.getElementById("acompanhantes0.genero1").checked == false &&
        document.getElementById("acompanhantes0.genero2").checked == false) {
         alert('Por favor, selecione o gênero do acompanhante 1');
         return false;
     }
     if(document.getElementById("acompanhantes0.dataNascimento").value == false){
         alert('Por favor, preencha a data de nascimento do acompanhante');
         document.getElementById("acompanhantes0.dataNascimento").focus();
         return false;
     }
 }

 function campos_acompanhante_dois(){
     if (document.getElementById("acompanhantes1.nome").value != false) {
         if (document.getElementById("acompanhantes1.genero1").checked == false &&
             document.getElementById("acompanhantes1.genero2").checked == false) {
             alert('Por favor, selecione o gênero do acompanhante 2');
             return false;
         }
     }

     if (document.getElementById("acompanhantes1.dataNascimento").value == false) {
         alert('Por favor, preencha a data de nascimento do acompanhante 2');
         document.getElementById("acompanhantes1.dataNascimento").focus();
         return false;
     }
}