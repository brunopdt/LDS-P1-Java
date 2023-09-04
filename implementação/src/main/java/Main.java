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
		System.out.println("1 - Matricular em uma Turma"); // VALIDAR OS CRÉDITOS E THROW EXCEPTION
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
		try {
			usuarioLogado = universidade.login(usuario, senha);
			if (usuarioLogado != null) {
				if (usuarioLogado instanceof Aluno) {
					execAluno();
				} else if (usuarioLogado instanceof Professor) {
					execProf();
				} else if (usuarioLogado instanceof Secretaria) {
					menuSecretaria();
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Usuário ou senha inválidos");
			login();
		}
	}

	public static void logout() {
		usuarioLogado = null;
		iniciarAplicacao();
	}

	public static void execProf() {
		int opcao = 0;
		menuProfessor();
		opcao = sc.nextInt();
		switch (opcao) {
			case 1:
				listarTurmas();
				execProf();
				break;
			case 2:
				listarAlunosPorTurma();
				execProf();
				break;
			case 3:
				logout();
				break;
			default:
				System.out.println("Opção inválida");
				break;
		}
	}

	private static void listarAlunosPorTurma() {
		listarTurmas();
		System.out.println("Escolha a turma: ");
		int opcao = sc.nextInt();
		Turma turma = ((Professor) usuarioLogado).listarTurmasLecionadas()[opcao - 1];
		System.out.println("Alunos da turma de: " + turma.getDisciplina().getNome());
		try {
			for (Aluno aluno : ((Professor) usuarioLogado)
					.visualizarAlunos(turma)) {
				System.out.println(aluno.getNomeCompleto());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarTurmas() {
		int index = 1;
		System.out.println("Turmas ministradas: ");
		Turma[] turmasDoProf = ((Professor) usuarioLogado).listarTurmasLecionadas();
		for (Turma turma : turmasDoProf) {
			System.out.println(index + " - " + turma.getDisciplina().getNome());
			index++;
		}
	}

	public static void execAluno() {
		int opcao = 0;
		menuAluno();
		opcao = sc.nextInt();
		switch (opcao) {
			case 1:
				matricular();
				execAluno();
				break;
			case 2:
				cancelarMatricula();
				execAluno();
				break;
			case 3:
				listarTurmasMatriculadas();
				execAluno();
				break;
			case 4:
				valorMatricula();
				execAluno();
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
		Turma[] turmas = ((Aluno) usuarioLogado).listarTurmasMatriculadas();
		System.out.println("Disciplinas cursando no momento: ");
		for (int i = 0; i < turmas.length; i++) {
			System.out.println(i + " - " + turmas[i].getDisciplina().getNome());
		}
	}

	private static void valorMatricula() {
		System.out.println("Valor da matrícula: " + ((Aluno) usuarioLogado).valorMatricula());
	}

	private static void popularCursos() {
		for (int i = 0; i < universidade.listarCursos().length - 1; i++) {
			Disciplina disci1Disciplina = new Disciplina("Algoritimos", 1, 1);
			Disciplina disci2Disciplina = new Disciplina("Banco de Dados", 1, 1);
			Disciplina disci3Disciplina = new Disciplina("Estrutura de Dados", 1, 1);
			Disciplina disci4Disciplina = new Disciplina("Programação Orientada a Objetos", 1, 1);
			Disciplina disci5Disciplina = new Disciplina("Programação Web", 1, 1);
			Disciplina disci6Disciplina = new Disciplina("Redes", 1, 1);
			Disciplina disci7Disciplina = new Disciplina("Sistemas Operacionais", 1, 1);
			Disciplina disci8Disciplina = new Disciplina("Teste de Software", 1, 1);
			Disciplina disci9Disciplina = new Disciplina("Engenharia de Software", 1, 1);
			Disciplina disci10Disciplina = new Disciplina("Matemática", 1, 1);
			Disciplina disci11Disciplina = new Disciplina("Física", 1, 1);
			Disciplina disci12Disciplina = new Disciplina("Química", 1, 1);
			Disciplina disci13Disciplina = new Disciplina("Cálculo", 1, 1);
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

			disci1Disciplina
					.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci1Disciplina));
			disci2Disciplina
					.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci2Disciplina));
			disci3Disciplina
					.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci3Disciplina));
			disci4Disciplina
					.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci4Disciplina));
			disci5Disciplina
					.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci5Disciplina));
			disci6Disciplina
					.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci6Disciplina));
		}
	}

	private static void popularUsuaios() {
		Curso engSoftware = new Curso("EngSoftware", 12);
		Curso engConp = new Curso("Eng Coputação", 12);
		Curso engCivil = new Curso("eng Civil", 12);
		Curso engElet = new Curso("Eng Eletrica", 12);
		Curso engQui = new Curso("Eng Quimica", 12);
		universidade.cadastrarCurso(engSoftware);
		universidade.cadastrarCurso(engConp);
		universidade.cadastrarCurso(engCivil);
		universidade.cadastrarCurso(engElet);
		universidade.cadastrarCurso(engQui);

		try {
			universidade.cadastrarAluno(new Aluno("João", "Silva", "joao", "123", universidade.listarCursos()[0]));
			universidade.cadastrarProfessor(new Professor("Maria", "Silva", "maria", "123"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void iniciarAplicacao() {
		int opcao = 0;

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

	}

	private static void popularMatriculas() {
		Aluno aluno = (Aluno) universidade.login("joao", "123");
		aluno.matricular(aluno.getCurso().listarDisciplinas()[0].listarTurmas()[0]);
	}

	public static void main(String[] args) {
		popularUsuaios();
		popularCursos();
		popularMatriculas();
		iniciarAplicacao();

		sc.close();
	}

}