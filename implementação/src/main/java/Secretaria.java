public class Secretaria extends Usuario {
  public Secretaria(String nome, String sobrenome, String usuario, String senha) {
    super(nome, sobrenome, usuario, senha, true);
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public void validarDisciplina(Disciplina d) {
  };
}
