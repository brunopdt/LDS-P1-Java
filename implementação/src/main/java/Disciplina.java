import java.util.ArrayList;
import java.util.List;

public class Disciplina implements ISalvavel {
  private String nome;
  private double valor;
  private List<Turma> turmas;

  public Disciplina(String nome, double valor) {
    this.nome = nome;
    this.valor = valor;
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
    return turmas.toArray(null);
  }

  public void adicionarTurma(Turma turma) {
    turmas.add(turma);
  }
}
