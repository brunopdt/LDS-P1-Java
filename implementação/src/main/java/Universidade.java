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

    for (int i = 0; i < this.cursos.size(); i++) {
      Disciplina disci1Disciplina = new Disciplina("Algoritimos", 20);
    Disciplina disci2Disciplina = new Disciplina("Banco de Dados", 20);
    Disciplina disci3Disciplina = new Disciplina("Estrutura de Dados", 20);
    Disciplina disci4Disciplina = new Disciplina("Programação Orientada a Objetos", 20);
    Disciplina disci5Disciplina = new Disciplina("Programação Web", 20);
    Disciplina disci6Disciplina = new Disciplina("Redes", 20);
    Disciplina disci7Disciplina = new Disciplina("Sistemas Operacionais", 20);
    Disciplina disci8Disciplina = new Disciplina("Teste de Software", 20);
    Disciplina disci9Disciplina = new Disciplina("Engenharia de Software", 20);
    Disciplina disci10Disciplina = new Disciplina("Matemática", 20);
    Disciplina disci11Disciplina = new Disciplina("Física", 20);
    Disciplina disci12Disciplina = new Disciplina("Química", 20);
    Disciplina disci13Disciplina = new Disciplina("Cálculo", 20);
    this.cursos.get(i).adicionarDisciplina(disci1Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci2Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci3Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci4Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci5Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci6Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci7Disciplina);
      
    this.cursos.get(i).adicionarDisciplina(disci8Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci9Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci10Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci11Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci12Disciplina);
    this.cursos.get(i).adicionarDisciplina(disci13Disciplina);


    }
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
