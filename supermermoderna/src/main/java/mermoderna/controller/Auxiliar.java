package mermoderna.controller;

import mermoderna.view.MenuPrincipal;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Auxiliar {

	static MenuPrincipal menu = new MenuPrincipal();
	static Scanner scn = new Scanner(System.in);
	
	private static final int BAR_LENGTH = 50;

	//Metodo responsável pela progressão da barra de carregamento do sistema
	public static void printProgressBar(int progress) {
		int completedChars = (int) ((double) progress / 100 * BAR_LENGTH);
		System.out.print("[");
		for (int i = 0; i < completedChars; i++) {
			System.out.print("=");
		}
		for (int i = completedChars; i < BAR_LENGTH; i++) {
			System.out.print(" ");
		}
		System.out.print("] " + progress + "%");
	}
	
	//"Gambiarra" para poder limpar o console do Java
	public final static void clearConsole()
	{
		 for (int i = 0; i < 100; ++i)  
		       System.out.println();  
	}
	
	//Metodo do Logo do sistema
	public final static void logo() {
		
	System.out.println(" #####                                                ##   ##              ###");
	System.out.println("##   ##                                               ### ###               ##");
    System.out.println("#        ##  ##   ######    ####    ######            #######   ####        ##    ####    ######   #####     ####");
	System.out.println(" #####   ##  ##    ##  ##  ##  ##    ##  ##           #######  ##  ##    #####   ##  ##    ##  ##  ##  ##       ##");
	System.out.println("     ##  ##  ##    ##  ##  ######    ##               ## # ##  ##  ##   ##  ##   ######    ##      ##  ##    #####");
	System.out.println("##   ##  ##  ##    #####   ##        ##               ##   ##  ##  ##   ##  ##   ##        ##      ##  ##   ##  ##");
	System.out.println(" #####    ######   ##       #####   ####              ##   ##   ####     ######   #####   ####     ##  ##    #####");
    	
	}
	
	//Metodo para validar e chamar ou não o menu inicial após uma 
	//Leitura, inserção, exclusão ou atualização de dados dos produtos e seção
	public static void voltaMenu() throws Throwable {
        System.out.println("Deseja voltar ao menu inicial? (S-sim ou qq tecla para continuar)");
        String voltar = scn.nextLine();

        //Verifica se o usuário deseja retornar a tela inicial
        //Caso contrário é chamado novamente o metodo que o usuário estava trabalhando
        if (voltar.equalsIgnoreCase("S")) {
            Auxiliar.clearConsole();
            MenuPrincipal.main(null);
        } else {
            Auxiliar.clearConsole();
            // obtém a pilha de chamadas
            StackTraceElement[] stack = Thread.currentThread().getStackTrace();
            // obtém o nome da classe que chamou o método voltaMenu()
            String classeAnterior = stack[2].getClassName();
            // obtém o nome do método anterior
            String metodoAnterior = stack[2].getMethodName();
            // chama o método anterior novamente
            Class<?> classe = Class.forName(classeAnterior);
            Method metodo = classe.getMethod(metodoAnterior);
            metodo.invoke(null);
        }
    
    }
}   

