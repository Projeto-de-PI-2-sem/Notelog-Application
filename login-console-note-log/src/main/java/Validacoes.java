public class Validacoes {
    String emailCorreto = "fabio@note.log";
    String senhaCorreta = "fabio123";
    String resposta;
    String verificacar(String email, String senha) {
        if (email.equals(emailCorreto) && senha.equals(senhaCorreta)) {
            resposta = "Sucesso! Estamos te redirecionando, por favor aguarde...";
        } else {
            resposta = "Email e/ou senha incorretos, tente novamente!";
        }
        return resposta;
    }
}
