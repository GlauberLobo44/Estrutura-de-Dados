package catalogoFilmes;

import java.util.InputMismatchException;
import java.util.Scanner;



public class Tree {
	private No raiz;
	private Scanner sc; 
	public Tree() {
		this.sc = new Scanner(System.in);
		this.buildTree();
	}
	
	public void buildTree() {
        int opcao = 0;
        do {
            System.out.println("\n=============================================");
            System.out.println("          SISTEMA DE CATÁLOGO DE FILMES      ");
            System.out.println("=============================================");
            System.out.println("1.  Inserir filme");
            System.out.println("2.  Buscar filme");
            System.out.println("3.  Remover filme");
            System.out.println("4.  Exibir filmes em ordem");
            System.out.println("5.  Exibir filmes em pré-ordem");
            System.out.println("6.  Exibir filmes em pós-ordem");
            System.out.println("7.  Mostrar filme com maior código");
            System.out.println("8.  Mostrar filme com menor código");
            System.out.println("9.  Mostrar quantidade de filmes");
            System.out.println("10. Mostrar altura da árvore");
            System.out.println("11. Exibir filmes acima de determinada nota");
            System.out.println("12. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números inteiros.");
                sc.nextLine();
                opcao = 0;
                continue;
            }

            switch (opcao) {
                case 1: insertFilme(); break;
                case 2: searchFilme(); break;
                case 3: removeFilme(); break;
                case 4: 
                    System.out.println("\n--- EXIBIÇÃO EM ORDEM ---");
                    showAll(raiz); 
                    break;
                case 5: 
                    System.out.println("\n--- EXIBIÇÃO EM PRÉ-ORDEM ---");
                    exibirPreOrdem(raiz); 
                    break;
                case 6: 
                    System.out.println("\n--- EXIBIÇÃO EM PÓS-ORDEM ---");
                    exibirPosOrdem(raiz); 
                    break;
                case 7: findHigherCode(); break;
                case 8: findLesserCode(); break;
                case 9: 
                    System.out.println("Quantidade total de filmes: " + contarFilmes(raiz)); 
                    break;
                case 10: 
                    System.out.println("Altura da árvore: " + calcularAltura(raiz)); 
                    break;
                case 11: exibirPorNota(); break;
                case 12: System.out.println("Encerrando o sistema..."); break;
                default: System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 12);
    }
	
	
	private void insertFilme() {
		try {
            System.out.print("Digite o código do filme (int): ");
            int codigo = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Digite o nome do filme: ");
            String nome = sc.nextLine();
            
            System.out.print("Digite o gênero: ");
            String genero = sc.nextLine();
            
            System.out.print("Digite o ano de lançamento: ");
            int anoLancamento = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Digite a nota do filme (0.0 a 10.0): ");
            int nota = sc.nextInt();
            sc.nextLine();

            Filme novoFilme = new Filme(codigo, nome, genero, anoLancamento, nota);
            this.raiz = runInsert(this.raiz, novoFilme);
            System.out.println("Filme cadastrado!");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada de dados inválida. Operação abortada.");
            sc.nextLine();
        }
	    
			
	}
	
	private No runInsert(No atual, Filme novoFilme) {
		if(atual==null) {
			return new No(novoFilme);
		}
		int checar=novoFilme.getNome().compareToIgnoreCase(atual.getFilme().getNome());
		
		if(checar<0) {
			atual.setEsq(runInsert(atual.getEsq(), novoFilme));
		}else if(checar>0) {
			atual.setDir(runInsert(atual.getDir(),novoFilme));
		}else {
			System.out.println("Aviso! "+novoFilme.getNome()+" já existe no calogo");
		}
		return atual;
	}
	
	public void searchFilme() {
		System.out.println("Qual filme voce procura?");
		String nomeFilme=sc.nextLine();
		
		if(nomeFilme.trim().isEmpty()) {
			System.out.println("Você nao pode buscar um nome vazio");
			return;
		}
		
		No resultado=runSearch(this.raiz, nomeFilme);
			
		if(resultado!=null) {
			System.out.println("\n[Resultado da busca]: "+resultado.getFilme().toString());
		}else {
			System.out.println("Filme nao encontrado!");
		}
	}
	
	
	private No runSearch(No atual, String nomeFilme) {
		if(atual==null) return null;
		
		int checar=nomeFilme.compareToIgnoreCase(atual.getFilme().getNome());
		if(checar==0) return atual;
		
		return (checar<0)? runSearch(atual.getEsq(), nomeFilme): runSearch(atual.getDir(), nomeFilme);
	}
	
	public void removeFilme() {
		System.out.print("Digite o nome do filme que deseja remover: ");
	    String nomeRemover = sc.nextLine();

	    
	    if (nomeRemover.trim().isEmpty()) {
	        System.out.println("Erro: O nome do filme não pode ser vazio.");
	        return; 
	    }

	    this.raiz = runRemover(this.raiz, nomeRemover);
	}
	
	private No runRemover(No atual, String nomeRemover) {
		if(atual==null) {
			System.out.println("Filme nao encontrado");
			return null;
		}
		int checar=nomeRemover.compareToIgnoreCase(atual.getFilme().getNome());
		
		if(checar<0) {
			atual.setEsq(runRemover(atual.getEsq(), nomeRemover));
		}else if(checar>0) {
			atual.setDir(runRemover(atual.getDir(), nomeRemover));
		}else {
			System.out.println(atual.getFilme().getNome() + "' removido com sucesso.");
			if(atual.getEsq()==null) {
				return atual.getDir();
			}
			if (atual.getDir() == null) {
	            return atual.getEsq();
	        }
			
		}	
	    return atual;
			
		}
	
	
	private void showAll(No atual) { 
        if (atual != null) {
            showAll(atual.getEsq());
            System.out.println(atual.getFilme().toString());
            showAll(atual.getDir());
        }
    }

    private void exibirPreOrdem(No atual) { 
        if (atual != null) {
            System.out.println(atual.getFilme().toString());
            exibirPreOrdem(atual.getEsq());
            exibirPreOrdem(atual.getDir());
        }
    }

    private void exibirPosOrdem(No atual) { 
        if (atual != null) {
            exibirPosOrdem(atual.getEsq());
            exibirPosOrdem(atual.getDir());
            System.out.println(atual.getFilme().toString());
        }
    }

    
    public void findHigherCode() {
        Filme maior = buscarMaiorCodigo(raiz);
        if (maior != null) System.out.println("Filme com maior código: " + maior.toString());
        else System.out.println("Árvore vazia.");
    }

    private Filme buscarMaiorCodigo(No atual) {
        if (atual == null) return null;
        Filme maior = atual.getFilme();
        Filme esq = buscarMaiorCodigo(atual.getEsq());
        Filme dir = buscarMaiorCodigo(atual.getDir());
        
        if (esq != null && esq.getCodigo() > maior.getCodigo()) maior = esq;
        if (dir != null && dir.getCodigo() > maior.getCodigo()) maior = dir;
        return maior;
    }

    public void findLesserCode() {
        Filme menor = buscarMenorCodigo(raiz);
        if (menor != null) System.out.println("Filme com menor código: " + menor.toString());
        else System.out.println("Árvore vazia.");
    }

    private Filme buscarMenorCodigo(No atual) {
        if (atual == null) return null;
        Filme menor = atual.getFilme();
        Filme esq = buscarMenorCodigo(atual.getEsq());
        Filme dir = buscarMenorCodigo(atual.getDir());
        
        if (esq != null && esq.getCodigo() < menor.getCodigo()) menor = esq;
        if (dir != null && dir.getCodigo() < menor.getCodigo()) menor = dir;
        return menor;
    }

     private int contarFilmes(No atual) {
        if (atual == null) return 0;
        return 1 + contarFilmes(atual.getEsq()) + contarFilmes(atual.getDir());
    }
     
     
     
     private int calcularAltura(No atual) {
    	 if (atual == null) return -1;
    	 int altEsq = calcularAltura(atual.getEsq());
    	 int altDir = calcularAltura(atual.getDir());
    	 return 1 + Math.max(altEsq, altDir);
     }
     public void exibirPorNota() {
    	 try {
    		 System.out.print("Exibir filmes com nota acima de: ");
    		 double notaCorte = sc.nextDouble();
    		 sc.nextLine();
    		 System.out.println("\n--- FILMES COM NOTA COMPATÍVEL ---");
    		 filtrarPorNota(raiz, notaCorte);
    		 } catch (InputMismatchException e) {
    			 System.out.println("Erro: Valor de nota inválido.");
    			 sc.nextLine();
    			 }
    	 }
     
     private void filtrarPorNota(No atual, double notaCorte) {
    	 if (atual != null) {
    		 filtrarPorNota(atual.getEsq(), notaCorte);
    		 if (atual.getFilme().getNota() >= notaCorte) {
    			 System.out.println(atual.getFilme().toString());
    			 }
    		 filtrarPorNota(atual.getDir(), notaCorte);
    		 }
    	 }
	
     

	
}

