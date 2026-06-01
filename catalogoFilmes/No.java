package catalogoFilmes;

public class No {
	private Filme filme;
	private No esq, dir;
	
	public No(Filme filme) {
		this.filme=filme;
		this.esq=null;
		this.dir=null;
	}
	
	public boolean eFolha() {
		return (this.esq==null && this.dir==null);
	}
	
	
	public Filme getFilme() { return filme; }
	public void setFilme(Filme filme) {this.filme=filme;}
    public No getEsq() { return esq; }
    public void setEsq(No esq) { this.esq = esq; }
    public No getDir() { return dir; }
    public void setDir(No dir) { this.dir = dir; } 
}

