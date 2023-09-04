import java.util.ArrayList;
import java.util.List;

public class Disciplina implements ISalvavel {
  private String nome;
  private double valor;
  private int creditos;
  private List<Turma> turmas;
  private int inscritos;

  public Disciplina(String nome, double valor, int creditos) {
    this.nome = nome;
    this.valor = valor;
    this.creditos = creditos;
    this.inscritos = 0;
    this.turmas = new ArrayList<>();
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public double getValor() {
    return this.valor;
  }

  public String getNome() {
    return this.nome;
  }

  public Turma[] listarTurmas() {
    List<Turma> turmaList = new ArrayList<>();
    for (Turma turma : this.turmas) {
      turmaList.add(turma);
    }
    Turma[] turmaArray = new Turma[turmaList.size()];
    for (int i = 0; i < turmaList.size(); i++) {
      turmaArray[i] = turmaList.get(i);
    }
    return turmaArray;
  }

  public Turma[] listarTurmasEmAnalise() {
    List<Turma> turmasEmAnalise = new ArrayList<>();
    for (Turma turma : this.turmas) {
      if (turma.getStatus() == ETurmaStatus.EM_ANALISE) {
        turmasEmAnalise.add(turma);
      }
    }
    Turma[] turmas = new Turma[turmasEmAnalise.size()];
    for (int i = 0; i < turmasEmAnalise.size(); i++) {
      turmas[i] = turmasEmAnalise.get(i);
    }
    return turmas;
  }

  public void adicionarTurma(Turma turma) {
    turmas.add(turma);
  }

  public int getCreditos() {
    return this.creditos;
  }
}
