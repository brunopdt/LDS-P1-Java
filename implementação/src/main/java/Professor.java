import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
  private List<Turma> turmasLecionadas;

  public Professor(String nome, String sobrenome, String usuario, String senha) {
    super(nome, sobrenome, usuario, senha, false);
    this.turmasLecionadas = new ArrayList<>();
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public Turma[] listarTurmasLecionadas() {
    Turma[] turmas = new Turma[turmasLecionadas.size()];
    for (int i = 0; i < turmasLecionadas.size(); i++) {
      turmas[i] = turmasLecionadas.get(i);
    }
    return turmas;
  };

  public Aluno[] visualizarAlunos(Turma turmaDoAluno) {
    return turmaDoAluno.listarAlunos();
  }

  public void adicionarTurmaLecionada(Turma turma) {
    this.turmasLecionadas.add(turma);
  }
}
