public class Historico {
  Aluno aluno;
  Turma turma;
  EStatus status;

  public Historico(Turma turma, Aluno aluno) {
    this.turma = turma;
    this.aluno = aluno;
    this.status = EStatus.CURSANDO;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public Turma getTurma() {
    return turma;
  }

  public EStatus getStatus() {
    return status;
  }
}
