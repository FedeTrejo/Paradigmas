//package linea;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class Game {
//    public static void main(String[] args) throws IOException {
//        System.out.println("Dimensiones?");
//        int base = promptAsInt("Base? ");
//        int altura = promptAsInt("Altura? ");
//        char estrategia = promptAsChar("Estrategia de Juego: A, B o C? ");
//
//        Conecta4 game = new Conecta4(base, altura, estrategia);
//
//        System.out.println(game.showBoard());
//
//        while (!game.finished()) {
//            game.playRedAt(promptAsInt("Rojas? "));
//            System.out.println(game.showBoard());
//
//            if (!game.finished()) {
//                game.playBlueAt(promptAsInt("Azul? "));
//                System.out.println(game.showBoard());
//            }
//        }
//    }
//
//    private static int promptAsInt(String message) throws IOException {
//        System.out.print(message);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        return Integer.parseInt(reader.readLine());
//    }
//
//    private static char promptAsChar(String message) throws IOException {
//        System.out.print(message);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        return reader.readLine().charAt(0);
//    }
//}
