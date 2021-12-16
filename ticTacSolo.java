package lesson4;

import java.util.Random;
import java.util.Scanner;

public class ticTacSolo {
    private static final int SIZE = 5;

    private static final char DOT_EMPTY = '•';
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';

    private static final char[][] MAP = new char[SIZE][SIZE];
    private static final Scanner in = new Scanner(System.in);
    private static final Random random = new Random();
    public static final String HEADER_FIRST_SYMBOL = "♣";
    public static final String SPACE_MAP = " ";

    private static int turnsCount = 0;

    private static final int WinCond = 3;


    public static void TurnGame() {
        do {
            System.out.println("Игра начинается!");
            initMap();
            printMap();
            playGame();
        } while (isContinueGame());
        endGame();
    }


    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;
            }
        }
    }


    private static void printMap() {
        printHeaderMap();
        printBodyMap();
    }


    private static void printHeaderMap() {
        System.out.print(HEADER_FIRST_SYMBOL + SPACE_MAP);
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
        }
        System.out.println();
    }


    private static void printMapNumber(int i) {
        System.out.print(i + 1 + SPACE_MAP);
    }


    private static void printBodyMap() {
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + SPACE_MAP);
            }
            System.out.println();
        }
    }


    private static void playGame() {
        while (true) {
            turnHuman();
            printMap();
            if (checkEnd(DOT_HUMAN)) {
                break;
            }

            turnAI();
            printMap();
            if (checkEnd(DOT_AI)) {
                break;
            }
        }
    }


    private static void turnHuman() {
        System.out.println("ХОД ЧЕЛОВЕКА");
        int rowNumber, columnNumber;

        while (true) {
            rowNumber = getValidNumberFromUser() - 1;
            columnNumber = getValidNumberFromUser() - 1;
            if (isCellFree(rowNumber, columnNumber)) {
                break;
            }
            System.out.println("\nВы выбрали занятую ячейку");
        }

        MAP[rowNumber][columnNumber] = DOT_HUMAN;
        turnsCount++;
    }


    private static int getValidNumberFromUser() {
        while (true) {
            System.out.print("Введите координату(1-" + SIZE + "): ");
            if (in.hasNextInt()) {
                int n = in.nextInt();
                if (isNumberValid(n)) {
                    return n;
                }
                System.out.println("\nПроверьте значение координаты. Должно быть от 1 до " + SIZE);
            } else {
                in.next();
                System.out.println("\nВвод допускает лишь целые числа!");
            }
        }
    }


    private static boolean isNumberValid(int n) {
        return n >= 1 && n <= SIZE;
    }


    private static boolean isCellFree(int rowNumber, int columnNumber) {
        return MAP[rowNumber][columnNumber] == DOT_EMPTY;
    }


    private static void turnAI() {
        System.out.println("ХОД Компьютера");
        int rowNumber, columnNumber;

        do {
            rowNumber = random.nextInt(SIZE);
            columnNumber = random.nextInt(SIZE);

        } while (!isCellFree(rowNumber, columnNumber));

        MAP[rowNumber][columnNumber] = DOT_AI;
        turnsCount++;
    }


    private static boolean checkEnd(char symbol) {
        if (checkWin(symbol)) {
            if (symbol == DOT_HUMAN) {
                System.out.println("\n ура! вы победили");
            } else {
                System.out.println("\nпобедили машины");
            }
            return true;
        }

        if (checkDraw()) {
            System.out.println("\nНичья");

            return true;
        }
        return false;
    }


    private static boolean checkDraw() {
        return turnsCount >= SIZE * SIZE;
    }


    private static boolean checkWin(char symbol) {
        if (checkHorizontal(symbol)) return true;
        if (checkMainD(symbol)) return true;
        if (checkVertical(symbol)) return true;
        if (checkRowNumUpToDownRight(symbol)) return true;
        if (checkRowDownUpLeft(symbol)) return true;
        if (checkRowNumDownUpRight(symbol)) return true;
        if (columnNLefDown(symbol)) return true;
        return false;
    }


    private static boolean isContinueGame() {
        System.out.println("Continue ? y\\n");
        return switch (in.next()) {
            case "y", "yes", "+", "да", "yep" -> true;
            default -> false;
        };
    }


    private static void endGame() {
        in.close();
        System.out.println("SeeYaAgain");
    }


    private static boolean checkVertical(char symbol) {

        int counter = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MAP[j][i] == symbol) {
                    counter++;
                } else if (counter == WinCond) {
                    return true;
                } else {
                    counter = 0;
                }
            }
        }
        return false;
    }


    private static boolean checkHorizontal(char symbol) {

        int counter = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == symbol) {
                    counter++;
                } else if (counter == WinCond) {
                    return true;
                } else {
                    counter = 0;
                }
            }
        }
        return false;
    }


    private static boolean checkMainD(char symbol) {

        int counter = 0;

        for (int i = 0; i < SIZE; i++) {
            if (MAP[i][i] == symbol) {
                counter++;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }
        return false;
    }


    private static boolean columnNLefDown(char symbol) {

        int counter = 0;
        int column = MAP[0].length - 1;

        for (int i = 0; i < SIZE; i++) {
            if (MAP[i][column] == symbol) {
                counter++;
                column--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[i][column - 1] == symbol) {
                counter++;
                column--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[i][column - 2] == symbol) {
                counter++;
                column--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        return false;
    }


    private static boolean checkRowNumDownUpRight(char symbol) {

        int counter = 0;
        int rowNum = MAP[0].length - 1;

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum][i] == symbol) {
                counter++;
                rowNum--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum - 1][i] == symbol) {
                counter++;
                rowNum--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum - 2][i] == symbol) {
                counter++;
                rowNum--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        return false;
    }


    private static boolean checkRowDownUpLeft(char symbol) {

        int counter = 0;
        int column = MAP[0].length - 1;
        int rowNum = MAP[0].length - 1;

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum][column - 1] == symbol) {
                counter++;
                column--;
                rowNum--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum - 1][column - 2] == symbol) {
                counter++;
                column--;
                rowNum--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum][column - 2] == symbol) {
                counter++;
                column--;
                rowNum--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum - 1][column] == symbol) {
                counter++;
                column--;
                rowNum--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum  - 2][column - 1] == symbol) {
                counter++;
                column--;
                rowNum--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum - 2][column] == symbol) {
                counter++;
                column--;
                rowNum--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum][column] == symbol) {
                counter++;
                column--;
                rowNum--;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        return false;
    }


    private static boolean checkRowNumUpToDownRight(char symbol) {

        int counter = 0;
        int rowNum = MAP[0].length - 1;

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum - 4][i] == symbol) {
                counter++;
                rowNum++;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum - 3][i] == symbol) {
                counter++;
                rowNum++;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (MAP[rowNum - 2][i] == symbol) {
                counter++;
                rowNum++;
            } else if (counter == WinCond) {
                return true;
            } else {
                counter = 0;
            }
        }

        return false;
    }
}

