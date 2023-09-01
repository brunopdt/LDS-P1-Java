
public interface IGerenciavel {
  public void cadastrarUsuario(Usuario usuario) throws Exception;

  public void cadastrarCurso(Curso curso);

  public Curso[] listarCursos();

  public void adicionarDisciplina(Disciplina disciplina, Curso curso);

}
