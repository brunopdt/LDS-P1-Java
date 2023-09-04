import java.util.ArrayList;
import java.util.List;

public class Turma implements ISalvavel {
  private Disciplina disciplina;
  private final Professor professor;
  private List<Historico> historicos;
  private ETurmaStatus status;

  public Turma(Professor professor, Disciplina disciplina) {
    this.disciplina = disciplina;
    this.professor = professor;
    this.status = ETurmaStatus.EM_ANALISE;
    historicos = new ArrayList<>();
    professor.adicionarTurmaLecionada(this);
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public void adicionarHistoricos(Historico historico) throws IllegalArgumentException {
    if (historicos.contains(historico))
      throw new IllegalArgumentException("Aluno já matriculado");
    else if (historicos.size() >= 60)
      throw new IllegalArgumentException("Número máximo de alunos atingido");
    historicos.add(historico);
  }

  public Aluno[] listarAlunos() {
    Aluno[] alunos = new Aluno[historicos.size()];
    for (int i = 0; i < historicos.size(); i++) {
      alunos[i] = historicos.get(i).getAluno();
    }
    return alunos;
  }

  public void setStatus(ETurmaStatus es) {
    this.status = es;
  }

  public ETurmaStatus getStatus() {
    return status;
  }

  public Disciplina getDisciplina() {
    return disciplina;
  }

  public int getInscritos() {
    return historicos.size();
  }
}
