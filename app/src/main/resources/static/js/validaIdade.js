function mostra_idade() {
    var hoje = new Date();
    var dataNascimento = new Date((document.getElementById("dataNascimento").value).split("/").reverse());
    var idade = hoje.getFullYear() - dataNascimento.getFullYear();

    if (hoje.getMonth() < dataNascimento.getMonth() && hoje.getDay() < dataNascimento.getDay()){
        idade--;
    }
    alert(idade);
    return false;
}