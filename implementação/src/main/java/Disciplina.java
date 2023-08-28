public class Disciplina implements ISalvavel {
  private String nome;
  private double valor;
  private int alunosInscritos;

  public Disciplina(String nome, double valor) {
    this.nome = nome;
    this.valor = valor;
    this.alunosInscritos = 0;
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };
}
