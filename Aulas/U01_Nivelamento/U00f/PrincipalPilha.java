/**
 * Pilha dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
public class PrincipalPilha {
	public static void main(String[] args) {
		try {
			System.out.println(" ==== PILHA FLEXIVEL ====");
			Pilha pilha = new Pilha();
         	int x1;

			 // Leitura da lista
			 Arq.openRead("pilha.dat");
			 int[] arq = new int[1000];
			 int count = 0;
			 while(Arq.hasNext() == true){
				arq[count] = Arq.readInt();
				count++;
			 }
			 
			 Arq.close();

			boolean escolha = true;
			while(escolha){
				System.out.println("Escolha uma das opções abaixo: ");
				System.out.println("(1) Inserir");
				System.out.println("(2) Remover");
				System.out.println("(3) Listar");
				System.out.println("(4) Sair");
				int option = MyIO.readInt();


				switch (option){
					case 1:
						for(int i = 0; i < count; i++){
							pilha.inserir(arq[i]);
						}
						
						System.out.print("Apos insercoes: ");
						pilha.mostrar();
						break;
					case 2:
						x1 = pilha.remover();

						System.out.print("Apos as remocoes " + x1 + ": ");
						pilha.mostrar();
						break;
					case 3:
						pilha.mostrar();
						break;
					case 4:
						Arq.openWrite("pilha.dat");
						escolha = false;
						break;
					default:
						break;
				}
			}

		}
		catch(Exception erro) {
			System.out.println(erro.getMessage());
		}
	}
}
