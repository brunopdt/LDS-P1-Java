import java.util.List;

public interface IGerenciavel {
  public void cadastrarUsuario(Usuario usuario);
  public void cadastrarCurso(Curso curso);
  public List<Curso> listarCursos();
  public void adicionarDisciplina(Disciplina disciplina, Curso curso);
  
}
