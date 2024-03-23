import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class TestA {

    @Test
    public void testPrintBoard1() {
        boolean[][] board = new boolean[][]{
                {true, true, true, false, false, false, true, true},
                {false, false, false, true, true, false, false, false},
                {true, true, true, false, false, true, true, true},
                {false, false, false, false, false, false, false, false},
                {true, true, true, false, false, true, true, true},
                {false, false, false, true, true, false, false, false},
                {true, true, true, false, false, true, true, true},
                {false, false, false, false, false, false, false, false}};
        String expectedOutput =
                """
########
11100011
00011000
11100111
00000000
11100111
00011000
11100111
00000000
""";

        // Arrange
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Act
        Logic.printBoard(board);

        // Assert
        assertEquals(expectedOutput, outputStreamCaptor.toString().replace("\r",""));
    }

    @Test
    public void testCheckBoardNoConflicts() {
        boolean[][] board = {
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false}
        };
        assertTrue(Logic.checkBoard(0, 0, board));
    }

    @Test
    public void testCheckBoardWithConflicts() {
        boolean[][] board = {
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, true, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false}
        };
        assertFalse(Logic.checkBoard(2, 3, board));
        assertTrue(Logic.checkDiagonals(2, 1, board));
    }

    @Test
    public void testCheckDiagonalsConflicts() {
        boolean[][] board = {
                {false, false, false, false, false, false, false, false},
                {false, false, true, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, true, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false}
        };
        assertFalse(Logic.checkDiagonals(2, 1, board));
        assertFalse(Logic.checkDiagonals(4, 3, board));
    }



    @Test
    public void testCountSolutions() {
        Queen[] queens = new Queen[Main.bord_size];
        for (int i = 0; i < Main.bord_size; i++)
            queens[i] = new Queen(i);
        assertEquals(92, Logic.countSolutions(queens));
    }
    @Test
    public void testCountSolutionsWithQueenInASquare1() {
        Queen[] queens = new Queen[Main.bord_size];
        for (int i = 0; i < Main.bord_size; i++)
            queens[i] = new Queen(i);
        queens[0].setInSquare(0);
        assertEquals(4, Logic.countSolutions(queens));
    }


    @Test
    public void testPrints2() {
        boolean[][] bord = new boolean[Main.bord_size][Main.bord_size];
        bord[4][4] = true;
        String expectedOutput =
                """
                        ########
                        00000100
                        00100000
                        10000000
                        00000001
                        00001000
                        01000000
                        00010000
                        00000010
                        ########
                        00010000
                        00000010
                        10000000
                        00000001
                        00001000
                        01000000
                        00000100
                        00100000
                        ########
                        00000100
                        00100000
                        10000000
                        00000010
                        00001000
                        00000001
                        01000000
                        00010000
                        ########
                        00100000
                        00000100
                        00000001
                        10000000
                        00001000
                        00000010
                        01000000
                        00010000
                        ########
                        00100000
                        00000010
                        01000000
                        00000001
                        00001000
                        10000000
                        00010000
                        00000100
                        ########
                        00100000
                        00000100
                        01000000
                        00000010
                        00001000
                        10000000
                        00000001
                        00010000
                        ########
                        00000010
                        00100000
                        00000001
                        01000000
                        00001000
                        10000000
                        00000100
                        00010000
                        ########
                        00000100
                        00010000
                        01000000
                        00000001
                        00001000
                        00000010
                        10000000
                        00100000
                        """;

        // Arrange
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Act
        Logic.placeQueensRecursive(0, bord);

        // Assert
        assertEquals(expectedOutput, outputStreamCaptor.toString().replace("\r",""));

    }
}