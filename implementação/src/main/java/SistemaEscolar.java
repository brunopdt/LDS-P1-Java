import java.util.HashMap;

public class SistemaEscolar {
  private HashMap<String, Usuario> usuarios;
  private HashMap<String, Curso> cursos;

  public SistemaEscolar() {
    this.usuarios = new HashMap<String, Usuario>();
    this.cursos = new HashMap<String, Curso>();
  }

  public void login(String usuario, String senha) {
  };

  public void cadastrarUsuario(Usuario u) {
  };

  public void cadastrarCurso(Curso c) {
  };

  public Curso[] listarCursos() {
    return null;
  };

  public void cadastrarDisciplina(Disciplina d, Curso c) {
  };

  public void carregarDeArquivo() {
  };

}
