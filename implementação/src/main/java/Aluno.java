import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Aluno extends Usuario {
  private Curso curso;
  private List<Historico> turmas;
  private final int MAXIMO_TURMAS = 6;

  public Aluno(String nome, String sobrenome, String usuario, String senha, Curso c) {
    super(nome, sobrenome, usuario, senha, false);
    this.curso = c;
    this.turmas = new ArrayList<>();
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public void matricular(Turma turma) throws IllegalArgumentException {
    if (turmas.size() >= MAXIMO_TURMAS)
      throw new IllegalArgumentException("Número máximo de turmas atingido");
    else if (turmas.contains(turma))
      throw new IllegalArgumentException("Aluno já matriculado");
    else if (this.getCurso().getCreditos() >= this.somaCreditosMatriculados())
      throw new IllegalArgumentException("Número máximo de créditos atingido");

    Historico historico = new Historico(turma, this);
    turmas.add(historico);
    turma.adicionarHistoricos(historico);
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

  public double somaCreditosMatriculados() {
    return Arrays.stream(listarTurmasMatriculadas())
        .mapToDouble(turma -> turma.getDisciplina().getCreditos())
        .sum();
  }

  public Turma[] listarTurmasMatriculadas() {
    Turma[] turmasMatriculadas = turmas.stream()
        .filter(historico -> historico.getStatus() == EStatus.CURSANDO)
        .map(Historico::getTurma)
        .toArray(Turma[]::new);
    return turmasMatriculadas;
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
