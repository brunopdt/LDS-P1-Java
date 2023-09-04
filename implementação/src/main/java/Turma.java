import java.util.ArrayList;
import java.util.List;

public class Turma implements ISalvavel {
  private Disciplina disciplina;
  private Professor professor;
  private List<Historico> historicos;
  private int numeroMaximoAlunos;
  private int numeroMinimoAlunos;
  private ETurmaStatus status;

  public Turma(Professor professor, Disciplina disciplina) {
    this.disciplina = disciplina;
    this.professor = professor;
    this.numeroMaximoAlunos = 60;
    this.numeroMinimoAlunos = 3;
    this.status = ETurmaStatus.EM_ANALISE;
    historicos = new ArrayList<>();
    professor.adicionarTurmaLecionada(this);
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public void adicionarHistoricos(Historico historico) {
    historicos.add(historico);
  }

  public boolean validarTurma() {
    return (historicos.size() < numeroMinimoAlunos || historicos.size() > numeroMaximoAlunos);
  }

  public Aluno[] listarAlunos() {
    List<Aluno> alunos = new ArrayList<>();
    for (Historico historico : historicos) {
      if (historico.getStatus() == EStatus.CURSANDO)
        alunos.add(historico.getAluno());
    }
    return alunos.toArray(null);
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
}
