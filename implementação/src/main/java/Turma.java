import java.util.ArrayList;
import java.util.List;

public class Turma implements ISalvavel {
  private String id;
  private Disciplina disciplina;
  private Professor professor;
  private List<Historico> historicos;
  private int numeroMaximoAlunos;

  public Turma(Professor professor, Disciplina disciplina){
    this.disciplina = disciplina;
    this.professor = professor;
    historicos = new ArrayList<>();
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public void adicionarHistoricos(Historico historico) throws Exception{
    if(historicos.size() <= numeroMaximoAlunos){
      historicos.add(historico);
    } else {
      throw new Exception("A turma está cheia");
    }
  }

  public boolean validarTurma(){
    if(historicos.size() < 3){
      return false;
    } else {
      return true;
    }
  }

  public Aluno[] listarAlunos(){
    List<Aluno> alunos = new ArrayList<>();
      for (Historico historico : historicos) {
        alunos.add(historico.getAluno()) ;
      }
    return null;
  }
}
