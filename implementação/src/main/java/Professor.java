import java.util.List;

public class Professor extends Usuario {
  private List<Turma> turmasLecionadas;

  public Professor(String nome, String sobrenome, String usuario, String senha) {
    super(nome, sobrenome, usuario, senha, false);
    this.turmasLecionadas = null;
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public List<Turma> listarTurmasLecionadas() {
    return turmasLecionadas;
  };

  public Aluno[] visualizarAlunos(Turma turmaDoAluno) {
    for (Turma turma : turmasLecionadas) {
      if (turma.equals(turmaDoAluno) && turma.getStatus() == ETurmaStatus.ATIVA) {
        return turma.listarAlunos();
      }
    }
    return null;
  }
}
