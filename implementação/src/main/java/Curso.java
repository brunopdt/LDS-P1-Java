import java.util.ArrayList;
import java.util.List;

public class Curso implements ISalvavel {
  private String nome;
  private int creditos;
  private List<Disciplina> disciplinas;

  public Curso(String nome, int creditos) {
    this.nome = nome;
    this.creditos = creditos;
    this.disciplinas = new ArrayList<>();
    ;
  }

  public Disciplina[] listarDisciplinas() {
    Disciplina[] disciplinasDoCurso = new Disciplina[disciplinas.size()];
    for (int i = 0; i < disciplinas.size(); i++) {
      disciplinasDoCurso[i] = disciplinas.get(i);
    }
    return disciplinasDoCurso;
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
