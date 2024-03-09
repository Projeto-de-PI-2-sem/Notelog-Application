import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Validacoes validacoes = new Validacoes();

        System.out.println("Email: ");
        String email = sc.nextLine();

        System.out.println("Senha: ");
        String senha = sc.nextLine();

        validacoes.verificacar(email, senha);
        System.out.println(validacoes.resposta);
    }
}
