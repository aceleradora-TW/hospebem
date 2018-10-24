function mostra_idade() {
    let hoje = new Date();
    let dataNascimento = new Date((document.getElementById("dataNascimento").value).split("/").reverse());
    let idade = hoje.getFullYear() - dataNascimento.getFullYear();

    if (hoje.getMonth() < dataNascimento.getMonth() && hoje.getDay() < dataNascimento.getDay()){
        idade--;
    }
    alert(idade);
    return false;
}