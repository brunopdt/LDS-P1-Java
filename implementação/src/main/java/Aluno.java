import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Aluno extends Usuario {
  private Curso curso;
  private List<Historico> turmas;
 

  public Aluno(String nome, String sobrenome, String usuario, String senha) {
    super(nome, sobrenome, usuario, senha, false);
    this.curso = null;
    this.turmas = new ArrayList<>();
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  
  public void matricular(Turma turma) {
      Historico historico = new Historico(turma, this);
      try{
      turma.adicionarHistoricos(historico);
      } catch(Exception e){
        System.out.println(e.getMessage());
      }
  };

  public void cancelarMatricula(Turma turma) throws NullPointerException {
    for (Historico historico : turmas) {
      if (historico.getTurma().equals(turma)) {
        historico.status = EStatus.TRANCADO; //Remove o historico da lista da Turma ou não?
      }else{
        throw new NullPointerException("Turma não encontrada dentre as matriculas");
      }
      
    }
  };

  public void listarTurmasMatriculadas() {
  };

  public double valorMatricula() {
    return 0;
  };
}
