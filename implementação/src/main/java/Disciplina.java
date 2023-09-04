import java.util.ArrayList;
import java.util.List;

public class Disciplina implements ISalvavel {
  private String nome;
  private double valor;
  private int creditos;
  private List<Turma> turmas;

  public Disciplina(String nome, double valor, int creditos) {
    this.nome = nome;
    this.valor = valor;
    this.creditos = creditos;
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
    Turma[] turmas = new Turma[this.turmas.size()];
    for (int i = 0; i < this.turmas.size(); i++) {
      turmas[i] = this.turmas.get(i);
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
