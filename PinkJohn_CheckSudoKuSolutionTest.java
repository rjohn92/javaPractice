package cs501;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PinkJohn_CheckSudoKuSolutionTest {

    @Test
    void isValidSudokuTest() {

        //Test setup
        int[][] test = {
                {4, 3, 5, 2, 6, 9, 7, 8, 1},
                {6, 8, 2, 5, 7, 1, 4, 9, 3},
                {1, 9, 7, 8, 3, 4, 5, 6, 2},
                {8, 2, 6, 1, 9, 5, 3, 4, 7},
                {3, 7, 4, 6, 8, 2, 9, 1, 5},
                {9, 5, 1, 7, 4, 3, 6, 2, 8},
                {5, 1, 9, 3, 2, 6, 8, 7, 4},
                {2, 4, 8, 9, 5, 7, 1, 3, 6},
                {7, 6, 3, 4, 1, 8, 2, 5, 9}
        };

        //Test Execution
        Optional<String> result = PinkJohn_CheckSudoKuSolution.isValidSudoku(test);

        //Test assertion
        assertTrue(result.isEmpty());

    }

    @Test
    void isNotValidSudokuTest2() {

        //Test setup
        int[][] test2 = {
                {4, 3, 5, 2, 6, 9, 7, 8, 1},
                {6, 8, 2, 5, 7, 1, 4, 9, 3},
                {1, 9, 7, 8, 3, 4, 5, 6, 2},
                {8, 2, 6, 1, 9, 5, 3, 4, 7},
                {3, 7, 4, 6, 8, 2, 9, 1, 5},
                {9, 5, 1, 7, 4, 3, 6, 2, 8},
                {5, 1, 9, 3, 2, 6, 8, 7, 4},
                {2, 4, 8, 9, 5, 7, 1, 3, 6},
                {0, 6, 3, 4, 1, 8, 2, 5, 9} //having '0'
        };

        //Test Execution
        Optional<String> result = PinkJohn_CheckSudoKuSolution.isValidSudoku(test2);

        //Test assertion
        assertEquals(result, Optional.of("Sudoku number 2 is NOT valid.\n" + "Reason: Number at row 0 col 1 is 0.\n"));


    }


    @Test
    void isNotValidSudokuTest3() {

        //Test setup
        int[][] test3 = {
                {4, 3, 5, 2, 6, 9, 7, 8, 1},
                {6, 8, 2, 5, 7, 1, 4, 9, 3},
                {1, 9, 7, 8, 3, 4, 5, 6, 2},
                {8, 2, 6, 1, 9, 5, 3, 4, 7},
                {3, 7, 4, 6, 8, 2, 9, 1, 5},
                {9, 5, 1, 7, 4, 3, 6, 2, 8},
                {5, 1, 9, 3, 2, 6, 8, 7, 4},
                {2, 4, 8, 9, 5, 7, 1, 3, 6},
                {9, 6, 3, 4, 1, 8, 2, 5, 9} //having duplicated '9'
        };

        //Test Execution
        Optional<String> result = PinkJohn_CheckSudoKuSolution.isValidSudoku(test3);

        //Test assertion
        assertEquals(result, Optional.of("Sudoku number 3 is NOT valid.\n" + "Reason: Duplication detected at row 8 col 0.\n"));

    }


}