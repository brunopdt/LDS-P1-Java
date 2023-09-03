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
		System.out.println("2 - Cancelar Matricula"); // MOSTRAR QUAIS HISTORICOS ESTAO COM O STATUS E PERGUNTAR QUAL
														// QUER
														// TRANCAR (MUDAR O STATUS)
		System.out.println("3 - Listar Turmas Matriculadas"); // LISTAR HISTORICOS COM O STATUS ATIVO - PEGANDO A
																// PROPRIEDADE TURMA
		System.out.println("4 - Valor Matricula"); // SOMAR O VALOR DE TODOS OS HISTÓRICOS COM O STATUS ATIVO E PEGAR O
													// VALOR DA TURMA
		System.out.println("5 - Sair");
	}

	public static void menuProfessor() {
		int opcao;
		System.out.println("1 - Listar Turmas");
		System.out.println("2 - Lista Alunos por Turma"); // LISTA AS TURMAS E PEDE PRA ESCOLHER QUAL QUER VER ALUNOS
		System.out.println("3 - Sair");
		opcao = sc.nextInt();
		switch (opcao) {
			case 1:
				for (int i = 0; i < universidade.listarCursos().length; i++) {
					System.out.println(i + 1 + " - " + universidade.listarCursos()[i].getNome());
				}
				menuProfessor();
				/*
				 * case 2:
				 * for (int i = 0; i < universidade.listarCursos().length; i++) {
				 * System.out.println(i + 1 + " - " + universidade.listarCursos()[i].getNome());
				 * }
				 * int opcao4 = sc.nextInt();
				 * Curso curso2 = universidade.listarCursos()[opcao4 - 1];
				 * for (int i = 0; i < curso2.listarDisciplinas().length; i++) {
				 * System.out.println(i + 1 + " - " + curso2.listarDisciplinas()[i].getNome());
				 * }
				 * int opcao5 = sc.nextInt();
				 * Disciplina disciplina2 = curso2.listarDisciplinas()[opcao5 - 1];
				 * for (int i = 0; i < disciplina2.listarTurmas().length; i++) {
				 * System.out.println(i + 1 + " - " + disciplina2.listarTurmas()[i].getId());
				 * }
				 * int opcao6 = sc.nextInt();
				 * Turma turma = disciplina2.listarTurmas()[opcao6 - 1];
				 * for (int i = 0; i < turma.listarAlunos().length; i++) {
				 * System.out.println(i + 1 + " - " + turma.listarAlunos()[i].getNome());
				 * }
				 * break;
				 */
			case 3:
				break;
			default:
				System.out.println("Opção inválida");
				break;
		}

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
			return; // Sai da função se ocorrer uma exceção
		}
		
		System.out.println("Escolha o curso para este professor:");
		Curso[] cursos = universidade.listarCursos();
		for (int i = 0; i < cursos.length; i++) {
			System.out.println(i + 1 + " - " + cursos[i].getNome());
		}
		int opcaoCurso = sc.nextInt();
		
		if (opcaoCurso >= 1 && opcaoCurso <= cursos.length) {
			Curso cursoSelecionado = cursos[opcaoCurso - 1];
			
			List<Disciplina> disciplinas = cursoSelecionado.listarDisciplinas();
			System.out.println("Escolha a disciplina para este professor:");
			for (int i = 0; i < disciplinas.size(); i++) {
				System.out.println(i + 1 + " - " + disciplinas.get(i));
			}
			int opcaoDisciplina = sc.nextInt();
			
			if (opcaoDisciplina >= 1 && opcaoDisciplina <= disciplinas.size()) {
				Disciplina disciplinaSelecionada = disciplinas.get(opcaoDisciplina - 1);
				Turma turma = new Turma(professor, disciplinaSelecionada);
				
				// Agora você pode fazer o que precisa com a turma criada
				// turma.adicionarProfessor(professor);
				
				System.out.println("Professor cadastrado com sucesso na turma.");
			} else {
				System.out.println("Opção de disciplina inválida.");
			}
		} else {
			System.out.println("Opção de curso inválida.");
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
				menuAluno();
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
		iniciarAplicacao();

		sc.close();
	}

}