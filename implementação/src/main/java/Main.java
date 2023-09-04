import java.util.List;
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
		System.out.println("1 - Matricular em uma Turma"); // LISTAR AS TURMAS COM RESPECTIVAS MATÉRIAS E PERGUNTAR QUAL
		// QUER MATRICULAR, EX: T1-MATEMATICA, T2-PORTUGUES
		// ---------
		// OBS: COLOCAR UM TRY CATCH CASO O VALOR DE CRÉDITOS EXCEDA
		// O
		// MÁXIMO PERMITIDO
		System.out.println("2 - Cancelar Matricula");
		System.out.println("3 - Listar Turmas Matriculadas");
		System.out.println("4 - Valor Matricula");
		System.out.println("5 - Sair");
	}

	public static void menuProfessor() {
		System.out.println("1 - Listar Turmas");
		System.out.println("2 - Lista Alunos por Turma"); // LISTA AS TURMAS E PEDE PRA ESCOLHER QUAL QUER VER ALUNOS
		System.out.println("3 - Sair");
	}

	public static void menuSecretaria() {
		System.out.println("1 - Listar Cursos");
		System.out.println("2 - Adicionar Curso");
		System.out.println("3 - Adicionar Disciplina a um curso existente");
		System.out.println("4 - Adicionar Turma a uma disciplina existente"); // LISTAR OS PROFESSORES E AS DISCIPLINAS
		// E
		// PERGUNTAR QUAL ADICIONAR
		System.out.println("5 - Validar turmas");// PERGUNTAR PRIMEIRO QUAL CURSO ELA QUER VER, DEPOIS LISTAR AS TURMAS
		// COM
		// O STATUS ""-->EM ANÁLISE<--"" E PERGUNTAR SE QUER CANCELAR OU
		// APROVAR (MUDAR O STATUS DA DISCIPLINA)
		// -------OBS: MOSTRAR APENAS AS DISCIPLINAS -->EM ANÁLISE<-- QUE O
		// MÉTODO
		// (validarTurma) RETORNOU FALSE. IMPRIMIR MOSTRANDO O NOME DA
		// TURMA,
		// DISCIPLINA E O NÚMERO DE INSCRITOS, EX: T1 - MATEMATICA - 3
		// INSCRITOS
		System.out.println("6 - Sair");
	}

	public static void cadastrarSecretaria() {
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

	public static void cadastrarProfessor() {
		System.out.println("Digite o nome do professor: ");
		String nome = sc.next();
		System.out.println("Digite o sobrenome do professor: ");
		String sobrenome = sc.next();
		System.out.println("Digite o nome de usuário do professor: ");
		String usuario = sc.next();
		System.out.println("Digite a senha do professor: ");
		String senha = sc.next();
		Professor professor = new Professor(nome, sobrenome, usuario, senha);

		try {
			universidade.cadastrarProfessor(professor);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
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

	public static void login() {
		System.out.println("Digite o nome de usuário: ");
		String usuario = sc.next();
		System.out.println("Digite a senha: ");
		String senha = sc.next();
		usuarioLogado = universidade.login(usuario, senha);
		if (usuarioLogado != null) {
			if (usuarioLogado instanceof Aluno) {
				execAluno();
			} else if (usuarioLogado instanceof Professor) {
				menuProfessor();
			} else if (usuarioLogado instanceof Secretaria) {
				menuSecretaria();
			}
		} else {
			System.out.println("Usuário ou senha inválidos");
		}
	}

	public static void logout() {
		usuarioLogado = null;
		iniciarAplicacao();
	}

	public static void execAluno() {
		int opcao = 0;
		menuAluno();
		opcao = sc.nextInt();
		switch (opcao) {
			case 1:
				matricular();
				break;
			case 2:
				cancelarMatricula();
				break;
			case 3:
				listarTurmasMatriculadas();
				break;
			case 4:
				valorMatricula();
				break;
			case 5:
				logout();
				break;
			default:
				System.out.println("Opção inválida");
				break;
		}
	}

	private static void matricular() {
		System.out.println("Escolha a disciplina do seu curso que deseja se matricular: ");
		for (int i = 0; i < ((Aluno) usuarioLogado).getCurso().listarDisciplinas().length; i++) {
			System.out.println(i + 1 + " - " + ((Aluno) usuarioLogado).getCurso().listarDisciplinas()[i].getNome());
		}

		int opcao = sc.nextInt();

		try {
			((Aluno) usuarioLogado).matricular(
					((Aluno) usuarioLogado).getCurso().listarDisciplinas()[opcao - 1].listarTurmas()[0]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		execAluno();
	}

	private static void cancelarMatricula() {
		System.out.println("Escolha a disciplina que deseja cancelar a matrícula: ");
		listarTurmasMatriculadas();
		int opcao = sc.nextInt();
		try {
			((Aluno) usuarioLogado).cancelarMatricula(((Aluno) usuarioLogado).listarTurmasMatriculadas()[opcao - 1]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarTurmasMatriculadas() {
		int index = 1;
		System.out.println("Disciplinas cursando no momento: ");
		for (Turma turma : ((Aluno) usuarioLogado).listarTurmasMatriculadas()) {
			System.out.println(index + " - " + turma.getDisciplina().getNome());
			index++;
		}
		execAluno();
	}

	private static void valorMatricula() {
		System.out.println("Valor da matrícula: " + ((Aluno) usuarioLogado).valorMatricula());
		execAluno();
	}

	private static void popularCursos() {
		Curso engSoftware = new Curso("EngSoftware", 12, null);
		Curso engConp = new Curso("Eng Coputação", 12, null);
		Curso engCivil = new Curso("eng Civil", 12, null);
		Curso engElet = new Curso("Eng Eletrica", 12, null);
		Curso engQui = new Curso("Eng Quimica", 12, null);
		universidade.cadastrarCurso(engSoftware);
		universidade.cadastrarCurso(engConp);
		universidade.cadastrarCurso(engCivil);
		universidade.cadastrarCurso(engElet);
		universidade.cadastrarCurso(engQui);

		for (int i = 0; i < universidade.listarCursos().length; i++) {
			Disciplina disci1Disciplina = new Disciplina("Algoritimos", 20);
			Disciplina disci2Disciplina = new Disciplina("Banco de Dados", 20);
			Disciplina disci3Disciplina = new Disciplina("Estrutura de Dados", 20);
			Disciplina disci4Disciplina = new Disciplina("Programação Orientada a Objetos", 20);
			Disciplina disci5Disciplina = new Disciplina("Programação Web", 20);
			Disciplina disci6Disciplina = new Disciplina("Redes", 20);
			Disciplina disci7Disciplina = new Disciplina("Sistemas Operacionais", 20);
			Disciplina disci8Disciplina = new Disciplina("Teste de Software", 20);
			Disciplina disci9Disciplina = new Disciplina("Engenharia de Software", 20);
			Disciplina disci10Disciplina = new Disciplina("Matemática", 20);
			Disciplina disci11Disciplina = new Disciplina("Física", 20);
			Disciplina disci12Disciplina = new Disciplina("Química", 20);
			Disciplina disci13Disciplina = new Disciplina("Cálculo", 20);
			universidade.cadastrarDisciplina(disci1Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci2Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci3Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci4Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci5Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci6Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci7Disciplina, universidade.listarCursos()[i]);

			universidade.cadastrarDisciplina(disci8Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci9Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci10Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci11Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci12Disciplina, universidade.listarCursos()[i]);
			universidade.cadastrarDisciplina(disci13Disciplina, universidade.listarCursos()[i]);
		}
	}

	public static void iniciarAplicacao() {
		int opcao = 0;
		do {
			menuInicial();
			opcao = sc.nextInt();
			switch (opcao) {
				case 1:
					login();
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
		popularCursos();
		iniciarAplicacao();

		sc.close();
	}

}