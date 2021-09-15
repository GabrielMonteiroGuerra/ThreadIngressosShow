package view;

import java.util.concurrent.Semaphore; //Importando semáforo

import controller.IngressosController; //Importando a classe controller

public class Principal {

	public static void main(String[] args) {
		
		Semaphore espera = new Semaphore(1); //Método construtor semáforo
			
		for(int comprador = 1; comprador <= 300; comprador++) { //300 Threads fazendo a compra
			Thread ingressos = new IngressosController(comprador, espera);
			ingressos.start(); //Inicializando
			
		}

	}

}
