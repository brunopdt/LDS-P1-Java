public class Secretaria extends Usuario implements IGerenciavel {
  private Universidade universidade;

  public Secretaria(String nome, String sobrenome, String usuario, String senha, Universidade u) {
    super(nome, sobrenome, usuario, senha, true);
    this.universidade = u;
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public void validarDisciplina(Turma turma) {
    turma.setStatus(turma.validarTurma() ? ETurmaStatus.ATIVA : ETurmaStatus.EM_ANALISE);
  }

  @Override
  public void cadastrarUsuario(Usuario usuario) throws Exception {
    Class<? extends Usuario> classeUsuario = usuario.getClass();

    switch (classeUsuario.getSimpleName()) {
      case "Secretaria":
        universidade.cadastrarSecretaria((Secretaria) usuario);
        break;
      case "Professor":
        universidade.cadastrarProfessor((Professor) usuario);
        break;
      case "Aluno":
        universidade.cadastrarAluno((Aluno) usuario);
        break;
      default:
        // Lógica para lidar com outros tipos de usuários ou erro.
        break;
    }
  }

  @Override
  public void cadastrarCurso(Curso curso) {
    universidade.cadastrarCurso(curso);
  }

  @Override
  public Curso[] listarCursos() {
    return universidade.listarCursos();
  }

  @Override
  public void adicionarDisciplina(Disciplina disciplina, Curso curso) {
    curso.adicionarDisciplina(disciplina);
  };

  public void criarTurma(Professor professor, Disciplina disciplina) {
  }
}
