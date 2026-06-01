package catalogoFilmes;

public class Filme {
	private int codigo;
	private String nome;
	private String genero;
	private int anoLancamento;
	private int nota;
	
	public Filme(int codigo, String nome, String genero, int anoLancamento, int nota) {
		this.codigo = codigo;
		this.nome = nome;
		this.genero = genero;
		this.anoLancamento = anoLancamento;
		this.nota = nota;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public int getNota() {
		return nota;
	}


	public void setNota(int nota) {
		this.nota = nota;
	}



	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public int getAnoLancamento() {
		return anoLancamento;
	}


	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	
	@Override
	public String toString() {
	    return "Código: " + this.codigo + " | Nome: " + this.nome + " (" + this.anoLancamento + ") | Gênero: " + this.genero + " | Nota: " + this.nota;
	}

	
	
}
