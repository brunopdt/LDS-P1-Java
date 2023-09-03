import java.util.HashMap;

public class Universidade {
  private HashMap<String, Aluno> alunos;
  private HashMap<String, Professor> professores;
  private HashMap<String, Secretaria> secretarias;
  private HashMap<String, Curso> cursos;

  public Universidade() {
    this.alunos = new HashMap<String, Aluno>();
    this.professores = new HashMap<String, Professor>();
    this.secretarias = new HashMap<String, Secretaria>();
    this.cursos = new HashMap<String, Curso>();
  }

  public Usuario login(String usuario, String senha) {
    HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
    try {
      usuarios.putAll(this.alunos);
      usuarios.putAll(this.professores);
      usuarios.putAll(this.secretarias);
    } catch (Exception e) {
      return null;
    }
    Usuario u = usuarios.get(usuario);
    return u.getSenha().equals(senha) ? u : null;
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

  public void CarregarCursos() {
    Curso engSoftware = new Curso("EngSoftware", 12, null);
    Curso engConp = new Curso("Eng Coputação", 12, null);
    Curso engCivil = new Curso("eng Civil", 12, null);
    Curso engElet = new Curso("Eng Eletrica", 12, null);
    Curso engMec = new Curso("Eng Mecanica", 12, null);
    Curso engQui = new Curso("Eng Quimica", 12, null);
    this.cursos.put("Engenharia de Software", engSoftware);
    this.cursos.put("Engenharia Computação", engConp);
    this.cursos.put("Engenharia Civil", engCivil);
    this.cursos.put("Engenharia Elétrica", engElet);
    this.cursos.put("Engenharia Mecânica", engMec);
    this.cursos.put("Engenharia Química", engQui);
  }

  public Curso[] listarCursos() {
    CarregarCursos();
    Curso[] cursosArray = new Curso[this.cursos.size()];
    int i = 0;
    for (Curso curso : this.cursos.values()) {
      cursosArray[i] = curso;
      i++;
    }
    return cursosArray;
  };

  public void cadastrarDisciplina(Disciplina d, Curso c) {
    c.adicionarDisciplina(d);
  };

  public void carregarDeArquivo() {
    // TODO
  };

}
