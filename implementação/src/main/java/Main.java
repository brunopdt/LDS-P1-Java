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
		System.out.println("4 - Ver as Disciplinas de um curso específico");
		System.out.println("5 - Adicionar Turma a uma disciplina existente");
		System.out.println("6 - Validar turmas");
		System.out.println("7 - Sair");
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

		Secretaria secretaria = new Secretaria(nome, sobrenome, usuario, senha);
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
					execSecretaria();
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Usuário ou senha inválidos");
			login();
		}
	}

	private static void execSecretaria() {
		int opcao = 0;
		System.out.println("\n");
		menuSecretaria();
		opcao = sc.nextInt();
		switch (opcao) {
			case 1:
				listarCursos();
				execSecretaria();
				break;
			case 2:
				adicionarCurso();
				execSecretaria();
				break;
			case 3:
				adicionarDisciplina();
				execSecretaria();
				break;
			case 4:
				listarDisciplinasPorCurso();
				execSecretaria();
				break;
			case 5:
				adicionarTurma();
				execSecretaria();
				break;
			case 6:
				validarTurma();
				execSecretaria();
				break;
			case 7:
				logout();
				break;
			default:
				System.out.println("Opção inválida");
				break;
		}
	}

	private static void listarDisciplinasPorCurso() {
		System.out.println("Escolha o curso que deseja ver as disciplinas: ");
		listarCursos();
		int opcao = sc.nextInt();
		Curso curso = universidade.listarCursos()[opcao - 1];

		System.out.println("\n");
		for (int i = 0; i < curso.listarDisciplinas().length; i++) {
			System.out.println(i + 1 + " - " + curso.listarDisciplinas()[i].getNome());
		}
	}

	private static void validarTurma() {
		System.out.println("Escolha o curso que deseja validar as turmas: ");
		listarCursos();
		int opcao = sc.nextInt();
		Curso curso = universidade.listarCursos()[opcao - 1];

		System.out.println("Escolha a disciplina que deseja ver as turmas: ");
		for (int i = 0; i < curso.listarDisciplinas().length; i++) {
			System.out.println(i + 1 + " - " + curso.listarDisciplinas()[i].getNome());
		}
		opcao = sc.nextInt();
		Disciplina disciplina = curso.listarDisciplinas()[opcao - 1];

		if (disciplina.listarTurmasEmAnalise().length == 0) {
			System.out.println("Não há turmas para validar");
			return;
		}

		System.out.println("Turmas que precisam da sua atenção: (escolha uma para validar) ");

		for (int i = 0; i < disciplina.listarTurmasEmAnalise().length; i++) {
			if (disciplina.listarTurmasEmAnalise()[i].getStatus() == ETurmaStatus.EM_ANALISE)
				System.out.println(i + 1 + " - " + disciplina.listarTurmas()[i].getDisciplina().getNome() + " - "
						+ disciplina.listarTurmas()[i].getInscritos() + " inscrito(s)");
		}
		opcao = sc.nextInt();
		Turma turma = disciplina.listarTurmas()[opcao - 1];

		System.out.println("1 - Ativar");
		System.out.println("2 - Cancelar");
		opcao = sc.nextInt();
		if (opcao == 1) {
			((Secretaria) usuarioLogado).validarTurma(turma, ETurmaStatus.ATIVA);
		} else if (opcao == 2) {
			((Secretaria) usuarioLogado).validarTurma(turma, ETurmaStatus.CANCELADA);
		} else {
			System.out.println("Opção inválida");
		}
	}

	private static void adicionarTurma() {
		System.out.println("Escolha o curso que deseja adicionar a turma: ");
		listarCursos();
		int opcao = sc.nextInt();
		Curso curso = universidade.listarCursos()[opcao - 1];
		System.out.println("Escolha a disciplina que deseja adicionar a turma: ");
		for (int i = 0; i < curso.listarDisciplinas().length; i++) {
			System.out.println(i + 1 + " - " + curso.listarDisciplinas()[i].getNome());
		}
		opcao = sc.nextInt();
		Disciplina disciplina = curso.listarDisciplinas()[opcao - 1];
		System.out.println("Escolha o professor que lecionará a turma: ");
		for (int i = 0; i < universidade.listarProfessores().length; i++) {
			System.out.println(i + 1 + " - " + universidade.listarProfessores()[i].getNomeCompleto());
		}
		opcao = sc.nextInt();
		Professor professor = universidade.listarProfessores()[opcao - 1];
		Turma turma = new Turma(professor, disciplina);
		disciplina.adicionarTurma(turma);
	}

	private static void adicionarDisciplina() {
		sc.nextLine();
		System.out.println("Escolha o curso que deseja adicionar a disciplina: ");
		listarCursos();
		int opcao = sc.nextInt();
		Curso curso = universidade.listarCursos()[opcao - 1];
		sc.nextLine();
		System.out.println("Digite o nome da disciplina: ");
		String nome = sc.nextLine();
		System.out.println("Digite o valor da disciplina: ");
		double valor = sc.nextDouble();
		System.out.println("Digite a quantidade de créditos da disciplina: ");
		int creditos = sc.nextInt();
		Disciplina disciplina = new Disciplina(nome, valor, creditos);

		universidade.cadastrarDisciplina(disciplina, curso);

	}

	private static void adicionarCurso() {
		sc.nextLine();
		System.out.println("Digite o nome do curso: ");
		String nome = sc.nextLine();
		System.out.println("Digite a quantidade de créditos do curso: ");
		int creditos = sc.nextInt();
		Curso curso = new Curso(nome, creditos);
		universidade.cadastrarCurso(curso);
	}

	private static void listarCursos() {
		System.out.println("Cursos cadastrados: ");
		for (int i = 0; i < universidade.listarCursos().length; i++) {
			System.out.println(i + 1 + " - " + universidade.listarCursos()[i].getNome());
		}
	}

	public static void logout() {
		usuarioLogado = null;
		iniciarAplicacao();
	}

	public static void execProf() {
		int opcao = 0;
		System.out.println("\n");
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
		for (Aluno aluno : ((Professor) usuarioLogado)
				.visualizarAlunos(turma)) {
			System.out.println(aluno.getNomeCompleto());
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
		System.out.println("\n");
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
		} catch (IllegalArgumentException e) {
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
			System.out.println(i + 1 + " - " + turmas[i].getDisciplina().getNome());
		}
	}

	private static void valorMatricula() {
		System.out.println("Valor da matrícula: " + ((Aluno) usuarioLogado).valorMatricula());
	}

	private static void popularCursos() {
		Disciplina disci1Disciplina = new Disciplina("Algoritmos", 1, 1);
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
		for (int i = 0; i < universidade.listarCursos().length - 1; i++) {
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
		disci7Disciplina
				.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci7Disciplina));
		disci8Disciplina
				.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci8Disciplina));
		disci9Disciplina
				.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci9Disciplina));
		disci10Disciplina
				.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci10Disciplina));
		disci11Disciplina
				.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci11Disciplina));
		disci12Disciplina
				.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci12Disciplina));
		disci13Disciplina
				.adicionarTurma(new Turma((Professor) universidade.login("maria", "123"), disci13Disciplina));
	}

	private static void popularUsuarios() {
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
			universidade.cadastrarSecretaria(new Secretaria("Ana", "Silva", "ana", "123"));
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
		try {
			aluno.matricular(aluno.getCurso().listarDisciplinas()[0].listarTurmas()[0]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		popularUsuarios();
		popularCursos();
		popularMatriculas();
		iniciarAplicacao();

		sc.close();
	}

}