import java.util.Scanner;

public class Main {

    public Scanner sc;
    public static int option;

    public Box myBox = new Box(new Player("j1"), new Player("j2"));

    public Board myBoard;

    public Main() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {

        System.out.println(
                "*------------------------*\n" +
                "| INICIADO JUEGO DE MESA |\n" +
                "*------------------------*"
        );

        Main myMain = new Main();
        myMain.createBoard();

        do {
            option = myMain.showMenu();
            myMain.executeOperation(option);
        } while (option != 0);

        System.out.println("Bye xD");

    }

    public int showMenu() {

        int chooseOption = 0;

        System.out.print(
                "\n" +
                "*---------------------*\n" +
                "| (1) Tirar Dado      |\n" +
                "| (2) Mostrar Tablero |\n" +
                "| (3) Salir           |\n" +
                "*---------------------*\n" +
                "Elegir opcion: "
        );

        chooseOption = sc.nextInt();
        return chooseOption;
    }

    public void executeOperation(int i) {
        switch (i) {
            case 1:
                tirarDado();
                break;
            case 2:
                printBoard();
                break;
            case 3:
                option = 0;
                break;
            default:
                System.out.println("Error, opcion no valida");
        }
    }

    public void createBoard() {
        int cols = 0;
        int rows = 0;

        System.out.println(
                "S E T T I N G S:"
        );

        System.out.print("Ingrese las columnas de tu tablero: ");
        cols = sc.nextInt();
        System.out.print("Ingrese las filas de tu tablero: ");
        rows = sc.nextInt();

        myBoard = new Board(cols, rows);
        myBoard.createBoard(cols*rows, myBox);
    }

    public void printBoard() {
        System.out.println("");
        myBoard.printBoard();
        System.out.println("");
    }

    public void tirarDado() {
        boolean winner = myBoard.move();
        if(winner) {
            myBoard.printBoard();
            executeOperation(3);
        }
    }

}
