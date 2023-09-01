import java.util.HashMap;

public class Universidade {
  private HashMap<String, Aluno> alunos;
  private HashMap<String, Professor> professores;
  private HashMap<String, Secretaria> secretarias;
  private HashMap<String, Curso> cursos;

  public Universidade() {
    this.alunos = new HashMap<String, Aluno>();
    this.cursos = new HashMap<String, Curso>();
  }

  public Usuario login(String usuario, String senha) {
    HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
    usuarios.putAll(this.alunos);
    usuarios.putAll(this.professores);
    usuarios.putAll(this.secretarias);
    Usuario u = usuarios.get(usuario);
    return u;
  };

  private void verificarDuplicidade(String usuario) throws Exception {
    HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
    usuarios.putAll(this.alunos);
    usuarios.putAll(this.professores);
    usuarios.putAll(this.secretarias);

    if (usuarios.containsKey(usuario)) {
      throw new Exception("Usuário já existe");
    }
  }

  public void cadastrarAluno(Aluno a) throws Exception {
    verificarDuplicidade(a.getUsuario());
    this.alunos.put(a.getUsuario(), a);
  };

  public void cadastrarProfessor(Professor p) throws Exception {
    verificarDuplicidade(p.getUsuario());
    this.professores.put(p.getUsuario(), p);
  };

  public void cadastrarSecretaria(Secretaria s) throws Exception {
    verificarDuplicidade(s.getUsuario());
    this.secretarias.put(s.getUsuario(), s);
  };

  public void cadastrarCurso(Curso c) {
    this.cursos.put(c.getNome(), c);
  };

  public Curso[] listarCursos() {
    return (Curso[]) this.cursos.values().toArray();
  };

  public void cadastrarDisciplina(Disciplina d, Curso c) {
    c.adicionarDisciplina(d);
  };

  public void carregarDeArquivo() {
    // TODO
  };

}
