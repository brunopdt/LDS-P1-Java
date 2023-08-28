
public abstract class Usuario implements ISalvavel {
  protected String nome;
  protected String sobrenome;
  protected String usuario;
  protected String senha;
  protected boolean isAdmin;

  public Usuario(String nome, String sobrenome, String usuario, String senha, boolean isAdmin) {
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.usuario = usuario;
    this.senha = senha;
    this.isAdmin = isAdmin;
  }

  public void salvarEmArquivo() {
  };
}