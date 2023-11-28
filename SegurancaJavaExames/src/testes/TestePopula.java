package testes;

import java.util.Scanner;

import dao.LDLDAO;
import dao.SenhaDAO;
import dao.UsuarioDAO;
import dao.ValoresPadroesDAO;
/*
 * Classe Teste para popular o banco de dados nas tabelas Usuario, Senha, LDL
 */
public class TestePopula {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			// Populando tabela Usuario (salva o hash da senha)
			String login = "Danielle";
			System.out.print("login: " + login);
			String senhaUsuario = "danielle123";
			System.out.print("\nsenha: " + senhaUsuario );
			UsuarioDAO dao = new UsuarioDAO();
			dao.adiciona(login, senhaUsuario);
			System.out.println("\nGravação do usuario e senha feita no banco de dados!");
			
			// Populando tabela Senha, salvando a chave criptografa pela senha do Usuário)
			String senhaCriptografia = "524lC4d5HojnbjRjmpJLtg";
			System.out.print("Senha para criptografar a tabela Exame: " + senhaCriptografia);
			SenhaDAO daoSenha = new SenhaDAO();
			daoSenha.adiciona(senhaCriptografia, senhaUsuario);
			System.out.println("\nGravação da senha para criptografar feita no banco de dados!");
			
			// Populando tabela LDL (salva o resultado do exame criptografado pela chave)
			
			// 3 gravações na tabela para teste
			LDLDAO daoExame = new LDLDAO();
			daoExame.adiciona("45 mg/dL", 1, 1, senhaCriptografia);
			System.out.println("\nGravação do exame de LDL feita no banco de dados!");
			daoExame.adiciona("53 mg/dL", 2, 1, senhaCriptografia);
			System.out.println("\nGravação do exame de LDL feita no banco de dados!");
			daoExame.adiciona("15 mg/dL", 3, 2, senhaCriptografia);
			System.out.println("\nGravação do exame de LDL feita no banco de dados!");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
