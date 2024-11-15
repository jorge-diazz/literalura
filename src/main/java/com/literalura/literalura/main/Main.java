package com.literalura.literalura.main;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        int option;
        do {
            menu();
            option = scanner.nextInt();
            scanner.nextLine();
            options(option);
        } while(option != 0);
    }

    public void menu() {
        System.out.println("""
                1.- Buscar libro.
                2.- Libros registrados.
                3.- Autores registrados.
                4.- Autores vivos en determinado año.
                5.- Libros por idioma.
                0.- Salir.
                """);
        System.out.print("Ingresa una opcion: ");
    }

    public void options(int option) {
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 0:
                System.out.println("Saliedo del programa.");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }
}
