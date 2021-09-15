package controller;

import java.util.Random; //Importando biblioteca random
import java.util.concurrent.Semaphore; //Importando semáforos

public class IngressosController extends Thread {

	Random gera_ingressos = new Random(); //Gerando ingressos aleatórios
	private int comprador; //Variável para simular pessoa comprando ingressos
	private Semaphore espera; //Semaforo para esperar o tempo
	private static int quantIngressos = 100; //Limitando a quantidade de ingressos em 100
	int compra_ingressos = gera_ingressos.nextInt(4) + 1; //Colocando de forma randomica para cada Thread comprar de 1 a 4 ingressos de forma aleatória
	
	public IngressosController() {
		super();
	}
	
	//O This serve para diferenciar uma variável global de uma variável privada
	public IngressosController(int comprador, Semaphore espera) {
		this.comprador = comprador;
		this.espera = espera;
		
	}
	
	//Tudo que estiver dentro do método Run será executado
	@Override
	public void run() {
		login();
		try {
			espera.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			espera.release();
		}
	}
	
	//Fazendo o login no sistema, contando o tempo e tentando comprar o ingresso em uma estrutura try catch, dentro do try será executado o método 'Comprando Ingresso'
	private void login() {
		int tempo = (int) ((gera_ingressos.nextInt(2000) + 50));
		if(tempo < 1000) {
			try {
				sleep(tempo);
				comprandoIngresso();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Compra não efetuada!");
		}
	}
	
	
	//Método para comprar o ingresso
	private void comprandoIngresso() {
		System.out.println("#" + comprador + " Fazendo a compra do ingresso");
		int tempo = (int) ((gera_ingressos.nextInt(3000) + 1000)); //Gerando ingressos
		if(tempo < 2500) {
			try {
				sleep(tempo);
				validacaoCompra(); //Depois de contar o tempo irá fazer uma validação da compra, dentro do bloco try será executado o método 'validação da compra'
			} catch (InterruptedException e) {
				e.printStackTrace(); //Caso a compra não seja validada, irá aparecer o erro
			}	
		}else {
			System.out.println("Compra não efetuada!");
		}
	}
	
	//Método para validar a compra
	private void validacaoCompra() { //Se a quantidade de ingressos for > que 0 e a quantidade de ingressos for menor ou igual a compra dos ingressos, irá executar o seguinte bloco:
		if(quantIngressos > 0 && compra_ingressos <= quantIngressos) {
			quantIngressos -= compra_ingressos; //A quantidade de ingressos irá diminuindo conforme a compra
			System.out.println("Compra executada com sucesso! de " + compra_ingressos + " ingressos"); //Mensagem para mostrar a compra dos ingressos
			System.out.println("Quantidade de ingressos disponíveis: " + quantIngressos); //Mensagem para mostrar a quantidade de ingressos disponíveis
		}
	}
}
