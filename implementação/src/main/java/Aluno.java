import java.util.HashMap;
import java.util.List;

public class Aluno extends Usuario {
  private Curso curso;
  private List<Turma> turmasMatriculadas;
  private HashMap<String, Disciplina> disciplinasRealizadas;

  public Aluno(String nome, String sobrenome, String usuario, String senha) {
    super(nome, sobrenome, usuario, senha, false);
    this.curso = null;
    this.turmasMatriculadas = null;
    this.disciplinasRealizadas = null;
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public void matricular(Turma t) {
  };

  public void cancelarMatricula(Turma t) {
  };

  public void listarTurmasMatriculadas() {
  };

  public double valorMatricula() {
    return 0;
  };
}
