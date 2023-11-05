import java.util.Random;

public class BubbleSort {
	
	public static int[] insertInVetor(int qtd, Random random){
		
        int[] vetor = new int[qtd];
		
		for(int i = 0; i< qtd; i++){
			vetor[i] = random.nextInt(qtd);
		}
		
		return vetor;	
	};
	
	public static class informacoes{
		int[] vetor;
		int mudancas; 
		int iteracoes;
		long tempo;
		
		public informacoes(int[] vetor, int mudancas, int iteracoes, long tempo){
			this.vetor = vetor;
			this.mudancas = mudancas;
		    this.iteracoes = iteracoes;
		    this.tempo = tempo;
		}
		
	}
	
	
	public static informacoes organizerBubble(int[] vetor,int qtd){
		long inicio = System.nanoTime();
		int mudancas = 0;
		int iteracoes = 0;
		boolean troca = true;
		int verdade = 0;
		int i;
		while(troca){
	        troca = false;  // O loop finaliza
	        for(i = 0; i < qtd - 1; i++){  // Deve ser i < qtd - 1, não i < qtd
	            if (vetor[i] > vetor[i+1]){
	                verdade =+ 1;
	                mudancas = mudancas + 1;
	                int temporaria = vetor[i];
	                vetor[i] = vetor[i+1];
	                vetor[i+1] = temporaria;
	                troca = true;  // Uma troca ocorreu, então definimos troca como true
	            }
	            iteracoes = iteracoes + 1;
	        }
	    }
		long fim = System.nanoTime();
		
		long tempoDeExecucao = fim - inicio;
		return new informacoes(vetor, mudancas, iteracoes, tempoDeExecucao);
		};
		
//		return vetor;
		
		public static informacoes organizarShellSort(int vetor[], int qtd){
			long inicio = System.nanoTime();
		    int mudancas = 0;
		    int iteracoes = 0;
		    // o intervalo nesse caso é de 1 quarto do valor da qtd
		    for (int intervalo = qtd/4; intervalo > 0; intervalo /= 4) {
		        for (int i = intervalo; i < qtd; i += 1) {
		            int temp = vetor[i];
		            int j;
		            for (j = i; j >= intervalo && vetor[j - intervalo] > temp; j -= intervalo) {
		                vetor[j] = vetor[j - intervalo];
		                mudancas++;
		                iteracoes++;
		            }
		            vetor[j] = temp;
		            mudancas++;
		            iteracoes++;
		        }
		    }

		    long fim = System.nanoTime();
		    long tempoDeExecucao = fim - inicio;
		    return new informacoes(vetor, mudancas, iteracoes, tempoDeExecucao);
		}
		
		public static informacoes organizarHeapSort(int vetor[], int qtd){
			int tamanho = qtd;
	        int i = tamanho / 2;
	        int mudancas = 0;
	        int iteracoes = 0;
	        long inicio = System.nanoTime();
	        while (true) {
	            int pai, esquerdo, direito, temp;

	            if (i > 0) {
	                i--;
	                temp = vetor[i];
	            } else {
	                tamanho--;
	                if (tamanho <= 0) {
	                	long fim = System.nanoTime();
	        		    long tempoDeExecucao = fim - inicio;
	                	return new informacoes(vetor, mudancas, iteracoes, tempoDeExecucao);
	                	}
	                temp = vetor[tamanho];
	                vetor[tamanho] = vetor[0];
	                mudancas++;
	            }
	            pai = i;
	            esquerdo = (2 * i) + 1;

	            while (esquerdo < tamanho) {
	                direito = esquerdo + 1;

	                if ((direito < tamanho) && (vetor[esquerdo] < vetor[direito]))
	                    esquerdo = direito;

	                if (vetor[esquerdo] > temp) {
	                    vetor[pai] = vetor[esquerdo];
	                    pai = esquerdo;
	                    esquerdo = (pai * 2) + 1;
	                    mudancas++;
	                } else
	                    break;

	                iteracoes++;
	            }
	            vetor[pai] = temp;
	            mudancas++;
	        }
	    }
		
		public static void executarMetodos() {
		    int[] qtds = {50, 500, 1000, 5000, 10000};
		    Random random = new Random();

		    for (int qtd : qtds) {
		        System.out.println("Qtd: " + qtd);

		        int[] vetor = insertInVetor(qtd, random);
		        informacoes info = calcularMedia(vetor, qtd, 5, "Bubble");
		        System.out.println("Bubble Sort - Mudanças: " + info.mudancas + ", Iterações: " + info.iteracoes + ", Tempo: " + info.tempo+ " milissegundos");

		        vetor = insertInVetor(qtd, random);
		        info = calcularMedia(vetor, qtd, 5, "Shell");
		        System.out.println("Shell Sort - Mudanças: " + info.mudancas + ", Iterações: " + info.iteracoes + ", Tempo: " + info.tempo+ " milissegundos");

		        vetor = insertInVetor(qtd, random);
		        info = calcularMedia(vetor, qtd, 5, "Heap");
		        System.out.println("Heap Sort - Mudanças: " + info.mudancas + ", Iterações: " + info.iteracoes + ", Tempo: " + info.tempo+ " milissegundos");
		    }
		}

		public static informacoes calcularMedia(int[] vetor, int qtd, int rodadas, String metodo) {
		    int totalMudancas = 0;
		    int totalIteracoes = 0;
		    long totalTempo = 0;

		    for (int i = 0; i < rodadas; i++) {
		        informacoes info;
		        switch (metodo) {
		            case "Bubble":
		                info = organizerBubble(vetor.clone(), qtd);
		                break;
		            case "Shell":
		                info = organizarShellSort(vetor.clone(), qtd);
		                break;
		            case "Heap":
		                info = organizarHeapSort(vetor.clone(), qtd);
		                break;
		            default:
		                throw new IllegalArgumentException("Método desconhecido: " + metodo);
		        }
		        totalMudancas += info.mudancas;
		        totalIteracoes += info.iteracoes;
		        totalTempo += info.tempo;
		    }

		    return new informacoes(null, totalMudancas / rodadas, totalIteracoes / rodadas, totalTempo / rodadas);
		}


	
	public static void main(String[] args) {
		
		long seed = 12345;  // Semente
		Random random = new Random(seed);
		
		executarMetodos();
	}
}
