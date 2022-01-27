import java.util.Scanner; //Leitura de dados.
import java.util.Random;
public class TP1 {
	static String[][] temasDoJogo = new String[50][50]; // Matriz para armazenar os temas.
	public static void main(String[] args) {
		int opcao_menu = 0;
		int opcao_tema = 0; 
		int opcao_palavras = 0;
		int i = 0 ;
		int j = 1; 
		int conta_palavra = 0;
		String deleta;
		String deleta_palavra = " ";
		String nome_tema = " "; //Receberei os temas nessa variavel.
		String nome_palavra = " "; //Receberei as palavras nessa variavel.
		String pesquisa = " ";
		String pesquisa_palavra = " ";
		String [][]temas;
		String [][]palavras;
		String resto_palavras = "0";
		String resto_temas = "0"; //Os temas não prenchidos serão como zero
		boolean repetido = false;
		temas= new String[50][50]; //Vetor onde poderei registrar até 50 temas.
		palavras = new String[50][50];
		Scanner ler = new Scanner (System.in); //Leitura de dados.
		do {
			opcao_menu = Menu(opcao_menu, ler); //Função de menu principal
			switch(opcao_menu) {
			case 1: System.out.println("Gerenciar Temas");
					System.out.println("");
			do {
					opcao_tema = Menu_Tema(opcao_tema, ler);// Função  de menu de tema
				switch(opcao_tema) {
				case 1: 
					//Cadastro dos temas:
					do {
						System.out.println("");
						System.out.println("Cadastrar temas: \n");
						System.out.println("Digite o tema que se deseja cadastrar \n");
						nome_tema = ler.next(); //Leitura do tema desejado.
						if(ja_existe(temas,nome_tema, i)) { //Comparando o conteudo digitado agora com os já armazenados
							System.out.println("Esse tema já existe \n");
						}else {
							if(nome_tema.equals(resto_temas)) {
								repetido = true; //Nome repetido
							}else {
								temas[i][0] = nome_tema; //Se não for repetido irei armazenar
								System.out.println("Na posicao " + i+ " foi armazenado " + temas[i][0]);
								System.out.println("");
								i++; //Avançando as posições do vetor
								break;
							}
						}
					}while(!repetido); //Se houver nomes repetidos ele não vai questionar
 				break;
				case 2:
					//Excluir
					System.out.println(" 2 - Excluir: \n");
					System.out.println("");
					System.out.println("Digite o tema que se deseja excluir");
					deleta = ler.next(); //Lendo o tema que se deseja excluir
					deletar_tema(temas, deleta, i);
				
				break;
				case 3:
					//Buscar
					System.out.println(" 3 - Buscar temas:");
					System.out.println("");
					System.out.println("Digite o tema que se deseja pesquisar: ");
					pesquisa = ler.next(); //Lendo o tema que será pesquisado.
					buscar_tema(temas, pesquisa, i);
				break;
				case 4:
					System.out.println("");
					break;
				default:
					System.out.println("Opcao invalida, escolha uma\n"
							+ "opção de 1 a 4\n"); //Caso o usuario digite opcoes fora desses valores.
					
				}
			}while(opcao_tema !=4); //Quando se digitar 4 irei sair
			break;
			case 2: System.out.println("Gerenciar Palavras \n");
			System.out.println("");	
			do {
				opcao_palavras = Menu_palavras(opcao_palavras, ler); //Função de menu de palavras.
				switch(opcao_palavras) {
				case 1:
					//Cadastro de palavras em temas existentes.
					System.out.println(" 1 - Cadastrar palavras : \n");
					System.out.println("");
					Gerenciar_palavras(temas,pesquisa,i, ler,j, nome_palavra, palavras, resto_palavras,conta_palavra);
					conta_palavra++;
					j++;
				break;
				case 2:
					//Deletar palavras
					System.out.println(" 2 - Excluir palavras : \n");
					System.out.println("");
					System.out.println("Digite a palavra que se deseja excluir \n");
					deleta_palavra = ler.next(); //Lendo a palavra que se deseja excluir
					deletar_palavra(palavras, deleta_palavra,  j,i);
					
				break;
				case 3:
					//Buscar palavras
					System.out.println(" 3 - Buscar palavras : \n");
					System.out.println("");
					System.out.println("Digite a palavra que se deseja pesquisar: ");
					pesquisa_palavra = ler.next(); //Lendo a palavra que será pesquisado.
					buscar_palavra(  palavras,  pesquisa_palavra,  j);
				break;
				case 4:
					System.out.println(" 4 - Listar palavras : \n");
					System.out.println("");
					listar_palavras( j, palavras,  i,  pesquisa,  ler, temas); //Função que lista todas as palavras registradas
				break;
				case 5:
					System.out.println("");
				break;
				default:
					System.out.println("Opcao invalida, escolha uma\n"
							+ "opção de 1 a 4\n"); //Caso o usuario digite opcoes fora desses valores.
				}
			}while(opcao_palavras !=5);
			break;	
			case 3: 
				//Jogar
				Boolean acabou = false;
				String palavra_chave = "";
				String letras_usadas = "";
				String palavra_adivinhada = "";
				int erros = 0;
				Random gerador = new Random();
				System.out.println("Jogar \n");
				System.out.println("\n");
				System.out.println(" 3 - Buscar temas:");
				System.out.println("");
				System.out.println("Digite o tema que deseja extrair palavras para o jogo: ");
				pesquisa = ler.next(); //Lendo o tema que será pesquisado.
				buscar_tema(temas, pesquisa, i); //Função que pesquisa os temas e verifica
		do {
				int numero_sorteado = gerador.nextInt(conta_palavra);
					if(numero_sorteado == 0) {
						 numero_sorteado = gerador.nextInt(conta_palavra);
					}
				palavra_chave = palavras[0][numero_sorteado];
				for(int k = 0; k< palavra_chave.length();k++) {
					palavra_adivinhada = "-";
					if(acabou==true) {
						break;
					}
					
					for(int tentativas=1; ; tentativas++) {
						System.out.println("Tente adivinhar a palavra abaixo, você pode errar até 5 vezes \n");
						System.out.println("tentativa " + tentativas + " ate agora adivinhado: " + palavra_adivinhada);
						char letra_tentada = new Scanner(System.in).next().charAt(0);
						if(letras_usadas.indexOf(letra_tentada)>= 0) {
							System.out.println("Voce ja tentou a letra: " + letra_tentada);
						}else {
							letras_usadas +=letra_tentada;
							if(palavra_chave.indexOf(letra_tentada) >= 0) {
								palavra_adivinhada = "";
								for(int l = 0;l<palavra_chave.length();l++) {
									palavra_adivinhada += letras_usadas.indexOf(palavra_chave.charAt(l))>=0 ? palavra_chave.charAt(l) : "-";
								}
								if(palavra_adivinhada.contains("-")) {
									System.out.println("Muito bem, existe a letra " + letra_tentada);
								}else {
									System.out.println("Parabéns voce venceu !!! \n");
									acabou = true;
									break;
								}
							}else {
								System.out.println("infelizmente,não existe a letra " + letra_tentada);
								erros++;
								if(erros==5) {
									System.out.println("Voce perdeu \n");
									acabou = true;
									break;
								}
							}
						}
					}
					
				}
			}while(!acabou);
			break;
			case 4: System.out.println("Sair \n");
				System.out.println("Muito obrigado até a próxima\n"); //Saída do programa.
			break;
			default:
				System.out.println("Opcao invalida, escolha uma\n"
						+ "opção de 1 a 4\n"); //Caso o usuario digite opcoes fora desses valores.
			}
		}while(opcao_menu !=4); //Enquanto não se digitar 4 não sairei do looping.
	}



public static int Menu(int opcao_menu, Scanner ler) {
	System.out.println("Bem vindos ao jogo da forca !!!");
	System.out.println("1 - Gerenciar Temas: ");
	System.out.println("2 - Gerenciar Palavras: ");
	System.out.println("3 - Jogar: ");
	System.out.println("4 - Sair: ");
	System.out.println("Digite a opção desejada:");
	System.out.println("");
	opcao_menu =ler.nextInt(); //Leitura da opção desejada 
	System.out.println("");
		//Opcoes do menu.
	return opcao_menu;
}
public static int Menu_Tema(int opcao_tema, Scanner ler) { //Menu de opcoes que se abre no case 1.
	//Menu de opcoes dos temas do case 1.
	System.out.println("");
	System.out.println(" 1 - Cadastrar tema: ");
	System.out.println(" 2 - Excluir tema: ");
	System.out.println(" 3 - Buscar tema:");
	System.out.println(" 4 - Sair");
	System.out.println(" Digite a opcao desejada: ");
	System.out.println("");
	opcao_tema = ler.nextInt(); //Leitura de opcao de tema.	
	System.out.println("");
	return opcao_tema; //Retorno o menu de opcao de temas
}
public static Boolean ja_existe(String temas[][], String pesquisa, int i) {
	for(int c = 0; c < i; c++) { //Vetor que passa por todos os temas cadastrados até o momento
		if(temas[c][0].equals(pesquisa)) { //Verifico se o tema já foi registrado antes
			return true;
		}
	}
	return false;
}
public static void buscar_tema( String temas[][], String pesquisa, int i) {
	Boolean encontrado = false; //Verificação se foi ou não encontrado.
	for(int c = 0; c<i; c++) { //Passarei por todos os temas registrados
		if(temas[c][0].equals(pesquisa)) { //Comparando o que foi digitado com o que estava armazenado
			System.out.println("Na posição " + c + " foi encontrado " +temas[c][0] );
			encontrado = true; //Encontramos o tema
		}
	}
	if(!encontrado) { //Se não for encontrado 
		System.out.println("Tema não encontrado!");
	}
}
public static void deletar_tema(String temas[][], String deleta, int i) {
	Boolean encontrado = false; //Verificação se foi ou não encontrado.
	for(int c = 0; c<i; c++) { //Passarei por todos os temas registrados
		if(temas[c][0].equals(deleta)) { //Comparando o que foi digitado com o que estava armazenado
			System.out.println("Na posição " + c + " foi encontrado " +temas[c][0] );
			encontrado = true; //Encontramos o tema
			System.out.println("Este tema será deletado \n");
			temas[c][0] = "";
			i--;
		}
	}
	if(!encontrado) { //Se não for encontrado 
		System.out.println("Tema não encontrado, verifique se existem palavras cadastradas nesse tema \n");
	}
}
public static int Menu_palavras(int opcao_palavras, Scanner ler) {
	System.out.println("");
	System.out.println(" 1 - Cadastrar palavras \n");
	System.out.println(" 2 - Excluir palavras \n");
	System.out.println(" 3 - Buscar palavras \n");
	System.out.println(" 4 - Listar palavras \n");
	System.out.println(" 5 - Sair \n");
	System.out.println("");
	opcao_palavras = ler.nextInt(); //Leitura de opcao do menu de palavras.
	
	
	return opcao_palavras;
}
public static void Gerenciar_palavras( String temas[][], String pesquisa, int i, Scanner ler, int j, String nome_palavra, String palavras[][], String resto_palavras, int conta_palavra) {
	Boolean encontrado = false; //Verificação se foi ou não encontrado.
	Boolean encontrado2 = false;
	System.out.println("Digite o tema em que se deseja cadastras as palavras \n");
	pesquisa = ler.next(); //Leitura do tema que se deseja cadastrar
	for(int c = 0; c<i; c++) { //Passarei por todos os temas registrados
		
			if(temas[c][0].equals(pesquisa)) { //Comparando o que foi digitado com o que estava armazenado
				System.out.println("Na posição " + c + " foi encontrado " +temas[c][0] );
				encontrado = true; //Encontramos o tema
			}
	}
			if(!encontrado) { //Se não for encontrado          
				System.out.println("Tema não encontrado!");
				System.out.println("Digite o tema que se deseja cadastrar as palavras: ");
				pesquisa = ler.next(); //Lendo o tema que será pesquisado.
			}
		do {
				System.out.println("Digite a palavra que se deseja cadastrar: \n");
				nome_palavra = ler.next();
				if(ja_existe_palavra(palavras,nome_palavra,j)) {
					System.out.println("Essa palavra ja existe \n");
				}else {
					if(nome_palavra.equals(resto_palavras)) {
						encontrado2 = true;
					}else {
						palavras[0][j] = nome_palavra; //Se não for repetido irei armazenar
						System.out.println("Na posicao " + j + " foi armazenado " + palavras[0][j]);
						conta_palavra++;
						System.out.println("");
						j++;
						break;
					}
					
				}
			
		}while(!encontrado2);
					}
public static Boolean ja_existe_palavra(String palavras[][], String nome_palavra, int j) {
	for(int d = 1; d < j; d++) { //Vetor que passa por todos os temas cadastrados até o momento
		if(palavras[0][d].equals(nome_palavra)) { //Verifico se o tema já foi registrado antes
			return true;
		}
	}
	return false;
}
public static void deletar_palavra(String palavras[][], String deleta_palavra, int j, int i) {
	Boolean encontrado = false; //Verificação se foi ou não encontrado.
for(int c = 0; c<i; c++) {
	for(int d = 1; d<j; d++) { //Passarei por todas as palavras registrados
		if(palavras[0][d].equals(deleta_palavra)) { //Comparando o que foi digitado com o que estava armazenado
			System.out.println("Na posição " + d + " foi encontrado " +palavras[0][d] );
			encontrado = true; //Encontramos o tema
			System.out.println("Este tema será deletado \n");
			palavras[0][d] = "";
		}
	}
}
	if(!encontrado) { //Se não for encontrado 
		System.out.println("Palavra não encontrada, verifique se existem palavras cadastradas nesse tema \n");
	}
}
public static void buscar_palavra( String palavras[][], String pesquisa_palavra, int j) {
	Boolean encontrado = false; //Verificação se foi ou não encontrado.
	for(int d = 1; d<j; d++) { //Passarei por todas as palavras registradas
		if(palavras[0][d].equals(pesquisa_palavra)) { //Comparando o que foi digitado com o que estava armazenado
			System.out.println("Na posição " + d + " foi encontrado " +palavras[0][d] );
			encontrado = true; //Encontramos a palavra
		}
	}

	if(!encontrado) { //Se não for encontrado 
		System.out.println("Palavra não encontrada!");
	}
}
public static void listar_palavras(int j, String palavras[][], int i, String pesquisa, Scanner ler, String temas[][]) {
	Boolean encontrado = false; //Verificação se foi ou não encontrado.
	System.out.println("Digite o tema em que se deseja verificar as palavras \n");
	pesquisa = ler.next(); //Leitura do tema que se deseja cadastrar
	for(int c = 0; c<i; c++) { //Passarei por todos os temas registrados
		
			if(temas[c][0].equals(pesquisa)) { //Comparando o que foi digitado com o que estava armazenado
				System.out.println("Na posição " + c + " foi encontrado " +temas[c][0] );
				encontrado = true; //Encontramos o tema
				for (int d = 1; d < j; d++) { //For que passa por todas as palavras cadastradas
					System.out.println("Foi registrado na posição " + d + " a palavra " + palavras[0][d]);
				}
			}
	}
			if(!encontrado) { //Se não for encontrado          
				System.out.println("Tema não encontrado!");
			}

}
}


