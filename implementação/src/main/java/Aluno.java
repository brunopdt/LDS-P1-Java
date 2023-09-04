import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {
  private Curso curso;
  private List<Historico> turmas;

  public Aluno(String nome, String sobrenome, String usuario, String senha, Curso c) {
    super(nome, sobrenome, usuario, senha, false);
    this.curso = c;
    this.turmas = new ArrayList<>();
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public void matricular(Turma turma) {
    Historico historico = new Historico(turma, this);
    try {
      turma.adicionarHistoricos(historico);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  };

  public void cancelarMatricula(Turma turma) throws NullPointerException {
    for (Historico historico : turmas) {
      if (historico.getTurma().equals(turma)) {
        historico.status = EStatus.TRANCADO;
      } else {
        throw new NullPointerException("Turma não encontrada dentre as matriculas");
      }
    }
  };

  public Turma[] listarTurmasMatriculadas() {
    return turmas.stream()
        .filter(historico -> historico.getStatus() == EStatus.CURSANDO)
        .map(Historico::getTurma)
        .toArray(Turma[]::new);
  }

  public double valorMatricula() {
    return turmas.stream()
        .mapToDouble(historico -> historico.getTurma().getDisciplina().getValor())
        .sum();
  };

  public Curso getCurso() {
    return curso;
  }
}
