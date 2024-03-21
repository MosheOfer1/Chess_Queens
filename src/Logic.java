public class Logic {

    public static int solutionsCounter = 0;
    public static boolean[][] bord;


    // A recursive function for putting 8 queens on the chess board, in a way that no one can eat anyone else
    public static void placeQueensRecursive(int column, boolean[][] bord) {
        // Exit condition, if it has reached to the last column on the board
        if (column == Main.bord_size){
            solutionsCounter++;
            printBoard(bord);
            return;
        }

        // If you have reached to not an empty column, check for contradictions,
        // and if all good send to the next column, else return.
        for (int i = 0; i < Main.bord_size; i++) {
            if (bord[i][column]) {
                if (checkBoard(i, column, bord)) {
                    placeQueensRecursive(column + 1, bord);
                } else {
                    return;
                }
            }
        }

        for (int i = 0; i < Main.bord_size; i++) {
            if (checkBoard(i, column, bord) && !bord[i][column]) {
                bord[i][column] = true;
                placeQueensRecursive(column + 1, bord);
                bord[i][column] = false;
            }
        }
    }

    // Return true if no one can eat anyone else
    public static boolean checkBoard(int raw, int column, boolean[][] bord) {
        //column
        for (int i = 0; i < Main.bord_size; i++) {
            if (bord[raw][i] && i!=column){
                return false;
            }
        }

        //raw
        for (int i = 0; i < Main.bord_size; i++) {
            if (bord[i][column] && i!=raw){
                return false;
            }
        }

        return checkDiagonals(raw, column, bord);
    }

    public static boolean checkDiagonals(int raw, int column, boolean[][] bord){
        int d = Main.bord_size - 1 - Math.max(raw, column);
        int x = column + d;
        int y = raw + d;
        while (x >= 0 && y >= 0){
            if (bord[y][x] && x!=column){
                return false;
            }
            x--;
            y--;
        }

        x = column - Math.min(column, Main.bord_size - 1 - raw);
        y = raw + Math.min(column, Main.bord_size - 1 - raw);
        while (x <= Main.bord_size - 1 && y >= 0){
            if (bord[y][x] && x!=column){
                return false;
            }
            x++;
            y--;
        }

        return true;
    }

    public static void printBoard(boolean[][] bord) {
        System.out.println("########");
        for (int i = 0; i < Main.bord_size; i++) {
            for (int j = 0; j < Main.bord_size; j++) {
                if (bord[i][j])
                    System.out.print("1");
                else
                    System.out.print("0");
            }
            System.out.println();
        }
    }

    public static int countSolutions(Queen[] queens) {
        solutionsCounter = 0;
        bord = new boolean[Main.bord_size][Main.bord_size];
        for (int i = 0; i < Main.bord_size; i++) {
            int s = queens[i].getInSquare();
            if (s != -1){
                bord[s / Main.bord_size][s % Main.bord_size] = true;
            }
        }
        placeQueensRecursive(0, bord);
        return solutionsCounter;
    }
}
