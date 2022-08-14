public class Board {

    private Box head;
    private Box tail;
    private int cols;
    private int rows;
    private int numToRow;
    private boolean turno;
    private boolean winner = false;

    public Board(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        turno = false;
    }

    public void printBoard() {
        printBoard(head, 1);
    }

    private void printBoard(Box current, int boxNum) {
        if(current == null) {
            return;
        }
        if(numToRow == cols) {
            System.out.println("");
            numToRow = 0;
        }
        if(!current.toString().equals("null,null")) {
            if(current.getPlayerj1().equals("j1") && current.getPlayerj2().equals("null")) {
                System.out.print("[" + current.getPlayerj1() + "] ");
            } else if(current.getPlayerj1().equals("null") && current.getPlayerj2().equals("j2")) {
                System.out.print("[" + current.getPlayerj2() + "] ");
            } else {
                System.out.print("[" + current.getPlayerj1() + "," + current.getPlayerj2() + "] ");
            }
        } else {
            System.out.print("[" + boxNum + "] ");
        }
        numToRow++;
        printBoard(current.getNext(), boxNum+1);
    }

    public void createBoard(int size, Box box) {
        if(size != 0) {
            if(head == null) {
                head = box;
            } else {
                tail.setNext(box);
            }
            tail = box;
            createBoard(size-1, new Box(new Player("null"), new Player("null")));
        } else {
            return;
        }
    }

    public boolean move() {
        Dado dado = new Dado();
        int num = dado.rol();
        String playerTurno = (turno == false) ? "j1" : "j2";
        System.out.print(
                "\n*-------*-------------*\n" +
                "|   " + playerTurno + "  |  DADO = " + num + "   |\n" +
                "*-------*-------------*"
        );
        return move(num, head, num);
    }

    public boolean move(int numMove, Box current, int staticNum) {
        if(turno == false) {
            if(numMove == staticNum) {
                current = searchj1("j1", head);
            }
            // Casos base
            if(numMove == 0) {
                current.setPlayerj1("j1");
                turno = true;
                Box deletej1 = searchj1("j1", head);
                deletej1.setPlayerj1("null");
                if(current == tail) {
                    System.out.println(
                            "\n\n" +
                                    "()...()              ()...()\n" +
                                    "( ='.')  j1 GANADOR  ('.'= )\n" +
                                    "('')('')             ('')('')\n"
                    );
                    winner = true;
                    return winner;
                }
                return winner;
            }
            // Llamado recursivo
            if(current.getNext() != null) {
                move(numMove - 1, current.getNext(), staticNum);
            } else {
                System.out.println("\n W A R N I N G: !! No es posible moverse el valor de tu dado sobrepaso las casillas !!");
                turno = true;
            }
        } else {
            if(numMove == staticNum) {
                current = searchj2("j2", head);
            }
            // Caso base
            if(numMove == 0) {
                current.setPlayerj2("j2");
                turno = false;
                Box deletej2 = searchj2("j2", head);
                deletej2.setPlayerj2("null");
                if(current == tail) {
                    System.out.println(
                            "\n\n" +
                            "()...()              ()...()\n" +
                            "( ='.')  j2 GANADOR  ('.'= )\n" +
                            "('')('')             ('')('')\n"
                    );
                    winner = true;
                    return winner;
                }
                return winner;
            }
            // Llamado recursivo
            if(current.getNext() != null) {
                move(numMove - 1, current.getNext(), staticNum);
            } else {
                System.out.println("\n W A R N I N G: !! No es posible moverse el valor de tu dado sobrepaso las casillas !!");
                turno = false;
            }
        }
        return winner;
    }

    public Box searchj1(String goal, Box current) {
        // Casos base
        if(current == null) {
            return null;
        }
        if(current.getPlayerj1().equals(goal)) {
            return current;
        }
        // Llamado recursivo
        return searchj1(goal, current.getNext());
    }

    public Box searchj2(String goal, Box current) {
        // Casos base
        if(current == null) {
            return null;
        }
        if(current.getPlayerj2().equals(goal)) {
            return current;
        }
        // Llamado recursivo
        return searchj2(goal, current.getNext());
    }

}
