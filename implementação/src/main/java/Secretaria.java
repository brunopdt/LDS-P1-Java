public class Secretaria extends Usuario {

  public Secretaria(String nome, String sobrenome, String usuario, String senha) {
    super(nome, sobrenome, usuario, senha, true);
  }

  @Override
  public void salvarEmArquivo() {
    // TODO
  };

  public void validarTurma(Turma turma, ETurmaStatus status) {
    turma.setStatus(status);
  }
}
