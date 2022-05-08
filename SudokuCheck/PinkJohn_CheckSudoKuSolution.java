package cs501;

/**
 * 12th edition
 * Check Sudoku solution: checks whether a solution is valid by checking whether every number is valid in the board.
 * Write the program to check whether every row, every column, and every small box has the number 1 to 9
 * The input should from a txt files with multiple sets of puzzle solutions. Each with 9 rows of numbers separated by spaces
 * with a blank line except for the line after the last puzzle solution which need an end-of data signal.
 * Not for solving puzzles but for checking for valid solutions.
 *
 * @Truong
 * @Roland
 * @Date: 11/29/2021
 */

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class PinkJohn_CheckSudoKuSolution {
    public static void main(String[] args) throws IOException {
        String intro = "This program will check the validation of the Sudoku solutions." + "\nPlease choose the file:";
        int option = -JOptionPane.YES_OPTION;

        while (option == -JOptionPane.YES_OPTION) {
            Scanner fileScanner = null;
            File chosenFile = null;
            int sudokuNumber = 1;

            JOptionPane.showConfirmDialog(null, intro, "Program Introduction", JOptionPane.INFORMATION_MESSAGE);

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            String fileName = chooser.getSelectedFile().getName();

            StringBuilder outputText = new StringBuilder();
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                outputText.append("You chose to open this file: " + fileName + "\n");

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    chosenFile = chooser.getSelectedFile();
                    fileScanner = new Scanner(chosenFile);
                }

                while (fileScanner.hasNext()) {

                    int[][] inputData = new int[9][9];

                    //read data lines
                    for (int i = 0; i < 9; i++) {
                        String nextLine = fileScanner.nextLine();
                        String[] splits = nextLine.split(" ");
                        for (int j = 0; j < 9; j++) {
                            inputData[i][j] = Integer.valueOf(splits[j]);
                        }

                    }
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            outputText.append(inputData[i][j] + " ");
                        }
                        outputText.append("\n");
                    }
                    Optional<String> result = isValidSudoku(inputData);
                    if (result.isPresent()) {
                        outputText.append("Sudoku number " + sudokuNumber + " is NOT valid.\n");
                        outputText.append("Reason: " + result.get() + "\n");
                    } else {
                        outputText.append("Sudoku number " + sudokuNumber + " is valid.\n");
                    }

                    //Read the blank line if it is not the end of line
                    if (fileScanner.hasNextLine()) {
                        fileScanner.nextLine();
                    }
                    sudokuNumber++;
                }
            }

            String output = outputText.toString();

            System.out.println(output);
            JOptionPane.showMessageDialog(null, output);

            option = -JOptionPane.showConfirmDialog(null, "Do you want to continue?");
        }
    }

    public static Optional<String> isValidSudoku(int[][] board) {

        // Use an array to record the status.
        // Each position (pos) in the array represents the status of the number pos + 1. We need an array for each row, column, and box
        int[][] rows = new int[9][9]; //
        int[][] cols = new int[9][9];
        int[][] boxes = new int[9][9];

        //Check if the number n has been previously seen by checking the n−1th index in the array.
        // If the value at this index equals to 1, it means that we have already seen this number, so the sudoku is not valid.
        // We return false in this case.

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) {
                    System.out.println("Number at row " + row + " pos " + col + " is 0.");
                    return Optional.of("Number at row " + row + " col " + col + " is 0.");
                }

                int pos = board[row][col] - 1;

                // Check the row
                //Check if the number n has been previously seen by checking the n-1th index in the array.
                //If the value at this index equals to 1, it means that we have already seen this number,
                //so the sudoku is not valid. We return false in this case.

                if (rows[row][pos] == 1) {
                    System.out.println("Duplication detected at row " + row + " col " + col);
                    return Optional.of("Duplication detected at row " + row + " col " + col);
                }



                //Otherwise, if the value at this position equals 0, then it is the first time encountering this number,
                //so we update the value at this position to 1 to mark that we have seen this number.
                rows[row][pos] = 1;

                // Check the column
                if (cols[col][pos] == 1) {
                    System.out.println("Duplication detected at row " + row + " col " + col);
                    return Optional.of("Duplication detected at row " + row + " col " + col);
                }
                cols[col][pos] = 1;

                // Check the box
                int index = (row / 3) * 3 + col / 3;
                if (boxes[index][pos] == 1) {
                    System.out.println("Duplication detected at row " + row + " col " + col);
                    return Optional.of("Duplication detected at row " + row + " col " + col);
                }
                boxes[index][pos] = 1;
            }
        }
        return Optional.empty();
    }
}
