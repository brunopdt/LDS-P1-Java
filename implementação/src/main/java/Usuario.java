
public abstract class Usuario implements ISalvavel {
  protected String nome;
  protected String sobrenome;
  protected String usuario;
  protected String senha;

  public Usuario(String nome, String sobrenome, String usuario, String senha, boolean isAdmin) {
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.usuario = usuario;
    this.senha = senha;
  }

  public void salvarEmArquivo() {
  };

  public String getUsuario() {
    return usuario;
  }

  public String getSenha() {
    return senha;
  }

  public String getNomeCompleto() {
    return nome + " " + sobrenome;
  }
}