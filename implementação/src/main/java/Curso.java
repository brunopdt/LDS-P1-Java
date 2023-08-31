import java.util.HashMap;
import java.util.List;

public class Curso implements ISalvavel {
  private String nome;
  private int creditos;
  private List<Disciplina> disciplinas;

  public List<Disciplina> listarDisciplinas() {
    return disciplinas;
  };

  public void salvarEmArquivo() {
    // TODO
  };

  public void adicionarDisciplina(Disciplina disciplina){
    disciplinas.add(disciplina);
  }
}
