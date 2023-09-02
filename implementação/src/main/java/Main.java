import java.util.Scanner;

public class Main {
	public static Universidade universidade = new Universidade();
	public static Usuario usuarioLogado;
	public static Scanner sc = new Scanner(System.in);

	public static void menuInicial() {
		System.out.println("1 - Login");
		System.out.println("2 - Cadastrar");
		System.out.println("3 - Sair");
	}

	public static void menuCadastro() {
		System.out.println("1 - Aluno");
		System.out.println("2 - Professor");
		System.out.println("3 - Secretária");
	}

	public static void menuAluno() {
		System.out.println("1 - Matricular em uma Turma");
		System.out.println("2 - Cancelar Matricula");
		System.out.println("3 - Listar Turmas Matriculadas");
		System.out.println("4 - Valor Matricula");
		System.out.println("5 - Sair");
	}

	public static void menuProfessor() {
		System.out.println("1 - Listar Turmas");
		System.out.println("2 - Lista Alunos por Turma");
		System.out.println("3 - Sair");
	}

	public static void menuSecretaria() {
		System.out.println("1 - Listar Cursos");
		System.out.println("2 - Adicionar Curso");
		System.out.println("3 - Adicionar Disciplina a um curso existente");
		System.out.println("4 - Adicionar Turma a uma disciplina existente");
		System.out.println("5 - Validar turmas");
		System.out.println("6 - Sair");
	}

	public static void cadastrarSecretaria(){
		System.out.println("Digite o nome da secretaria: ");
		String nome = sc.next();
		System.out.println("Digite o sobrenome da secretaria: ");
		String sobrenome = sc.next();
		System.out.println("Digite o nome de usuário da secretaria: ");
		String usuario = sc.next();
		System.out.println("Digite a senha da secretaria: ");
		String senha = sc.next();
		
		Secretaria secretaria = new Secretaria(nome, sobrenome, usuario, senha, universidade);
		try {
			universidade.cadastrarSecretaria(secretaria);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void cadastrarProfessor(){
		System.out.println("Digite o nome do professor: ");
		String nome = sc.next();
		System.out.println("Digite o sobrenome do professor: ");
		String sobrenome = sc.next();
		System.out.println("Digite o nome de usuário do professor: ");
		String usuario = sc.next();
		System.out.println("Digite a senha do professor: ");
		String senha = sc.next();
		System.out.println("Escolha o curso do professor: ");

		for (int i = 0; i < universidade.listarCursos().length; i++) {
			System.out.println(i + 1 + " - " + universidade.listarCursos()[i].getNome());
		}

		int opcao = sc.nextInt();
		Curso curso = universidade.listarCursos()[opcao - 1];

		Professor professor = new Professor(nome, sobrenome, usuario, senha);
		try {
			universidade.cadastrarProfessor(professor);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void cadastrarAluno() {
		System.out.println("Digite o nome do aluno: ");
		String nome = sc.next();
		System.out.println("Digite o sobrenome do aluno: ");
		String sobrenome = sc.next();
		System.out.println("Digite o nome de usuário do aluno: ");
		String usuario = sc.next();
		System.out.println("Digite a senha do aluno: ");
		String senha = sc.next();
		System.out.println("Escolha o curso do aluno: ");

		for (int i = 0; i < universidade.listarCursos().length; i++) {
			System.out.println(i + 1 + " - " + universidade.listarCursos()[i].getNome());
		}

		int opcao = sc.nextInt();
		Curso curso = universidade.listarCursos()[opcao - 1];

		Aluno aluno = new Aluno(nome, sobrenome, usuario, senha, curso);
		try {
			universidade.cadastrarAluno(aluno);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void cadastrar() {
		int opcao = 0;
		menuCadastro();
		opcao = sc.nextInt();
		switch (opcao) {
			case 1:
				cadastrarAluno();
				break;
			case 2:
				 cadastrarProfessor(); 
				break;
			case 3:
				 cadastrarSecretaria(); 
				break;
			default:
				System.out.println("Opção inválida");
				break;
		}
	}

	public static void iniciarAplicacao() {
		int opcao = 0;
		do {
			menuInicial();
			opcao = sc.nextInt();
			switch (opcao) {
				case 1:
					// login(); COLOCAR METODO DE LOGIN
					break;
				case 2:
					cadastrar();
					break;
				case 3:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida");
					break;
			}
		} while (opcao != 3);
	}

	public static void main(String[] args) {
		iniciarAplicacao();

		sc.close();
	}

}