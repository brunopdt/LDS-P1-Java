import java.util.List;

public class Secretaria extends Usuario implements IGerenciavel {
  public Secretaria(String nome, String sobrenome, String usuario, String senha) {
    super(nome, sobrenome, usuario, senha, true);
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public void validarDisciplina(Turma turma) {
  
  }

  @Override
  public void cadastrarUsuario(Usuario usuario) {
    // TODO Auto-generated method stub
    //Pensar em classe para armazenar Usuarios ou no main?
    throw new UnsupportedOperationException("Unimplemented method 'cadastrarUsuario'");
  }

  @Override
  public void cadastrarCurso(Curso curso) {
    // TODO Auto-generated method stub
    //Onde salvar esses cursos, qual classe?
    throw new UnsupportedOperationException("Unimplemented method 'cadastrarCurso'");
  }

  @Override
  public List<Curso> listarCursos() {
    // TODO Auto-generated method stub Onde 
    //Onde pegar esses cursos?
    throw new UnsupportedOperationException("Unimplemented method 'listarCursos'");
  }

  @Override
  public void adicionarDisciplina(Disciplina disciplina, Curso curso) {
    curso.adicionarDisciplina(disciplina);
  };

  public void criarTurma(Professor professor, Disciplina disciplina){
    
  }
}
