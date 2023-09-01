import java.util.List;

public class Curso implements ISalvavel {
  private String nome;
  private int creditos;
  private List<Disciplina> disciplinas;

  public Curso(String nome, int creditos, List<Disciplina> disciplinas) {
    this.nome = nome;
    this.creditos = creditos;
    this.disciplinas = disciplinas;
  }

  public List<Disciplina> listarDisciplinas() {
    return disciplinas;
  };

  public void salvarEmArquivo() {
    // TODO
  };

  public void adicionarDisciplina(Disciplina disciplina) {
    disciplinas.add(disciplina);
  }

  public String getNome() {
    return nome;
  }

  public int getCreditos() {
    return creditos;
  }
}
