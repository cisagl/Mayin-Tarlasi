import java.util.Scanner;

public class MineSweeper {
    int fieldRow;
    int fieldCol;
    String[][] dizi;
    String[][] revealedCells;
    int row;
    int col;
    int totalMines;
    public void beginning() {
        Scanner input = new Scanner(System.in);
        boolean isConditions = false;
        while (!isConditions) {
            System.out.print("Tarla satır sayısını belirtiniz (Minimum 2 yazmalısınız): ");
            int n = input.nextInt();
            System.out.print("Tarla sutun sayısını belirtiniz (Minimum 2 yazmalısınız): ");
            int k = input.nextInt();

            if (n >= 2 && k >= 2) {
                this.fieldRow = n;
                this.fieldCol = k;
                this.dizi = new String[fieldRow][fieldCol];
                isConditions = true;
            } else {
                System.out.println("Hatalı giriş yaptınız, lütfen tekrar deneyiniz.");
            }

            revealedCells = new String[fieldRow][fieldCol];
            for (int i = 0; i < fieldRow; i++) {
                for (int j = 0; j < fieldCol; j++) {
                    revealedCells[i][j] = "-";
                }
            }
        }
    }
    public void field() {
        dizi = new String[fieldRow][fieldCol];
        this.totalMines = (fieldRow * fieldCol) / 4;
        for (int i = 0; i < totalMines; i++) {
            int randomRow = (int) (Math.random() * fieldRow);
            int randomCol = (int) (Math.random() * fieldCol);

            if (dizi[randomRow][randomCol] == "*") {
                i--;
            } else {
                dizi[randomRow][randomCol] = "*";
            }
        }

        for (int i = 0; i < fieldRow; i++) {
            for (int j = 0; j < fieldCol; j++) {
                if (dizi[i][j] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(dizi[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    public void selection() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Satır sayısı: ");
            int row = input.nextInt();

            System.out.print("Sutun sayısı: ");
            int column = input.nextInt();
            if ((row < fieldRow && row >= 0) && (column < fieldCol && column >= 0) && "-".equals(revealedCells[row][column])) {
                this.row = row;
                this.col = column;
                revealedCells[row][column] = "S";
                break;
            } else {
                System.out.println("=========================================");
                System.out.println(" Girdiğiniz sayı tarlanın boyutunda,\n Tarlanın boyutundan daha büyük,\n 0'dan daha küçük,\n Ya da daha önce seçilmiş bir hücre olamaz\n Lütfen tekrar giriniz");
                System.out.println("==========================================");
            }
        }
    }
    public int topControl(int row, int col) {
        if (row - 1 >= 0 && col >= 0 && row - 1 < fieldRow && col < fieldCol) {
            if ("*".equals(dizi[row - 1][col])) {
                return 1;
            }
        }
        return 0;
    }
    public int topRightControl(int row, int col) {
        if (row - 1 >= 0 && col + 1 >= 0 && row - 1 < fieldRow && col + 1 < fieldCol) {
            if ("*".equals(dizi[row - 1][col + 1])) {
                return 1;
            }
        }
        return 0;
    }
    public int topLeftControl(int row, int col) {
        if (row - 1 >= 0 && col - 1 >= 0 && row - 1 < fieldRow && col - 1 < fieldCol) {
            if ("*".equals(dizi[row - 1][col - 1])) {
                return 1;
            }
        }
        return 0;
    }
    public int leftControl(int row, int col) {
        if (col - 1 >= 0 && row >= 0 && col - 1 < fieldCol && row < fieldRow) {
            if ("*".equals(dizi[row][col - 1])) {
                return 1;
            }
        }
        return 0;
    }
    public int rightControl(int row, int col) {
        if (col + 1 >= 0 && row >= 0 && col + 1 < fieldCol && row < fieldRow) {
            if ("*".equals(dizi[row][col + 1])) {
                return 1;
            }
        }
        return 0;
    }
    public int bottomControl(int row, int col) {
        if (row + 1 >= 0 && col >= 0 && row + 1 < fieldRow && col < fieldCol) {
            if ("*".equals(dizi[row + 1][col])) {
                return 1;
            }
        }
        return 0;
    }
    public int bottomRightControl(int row, int col) {
        if (row + 1 >= 0 && col + 1 >= 0 && row + 1 < fieldRow && col + 1 < fieldCol) {
            if ("*".equals(dizi[row + 1][col + 1])) {
                return 1;
            }
        }
        return 0;
    }
    public int bottomLeftControl(int row, int col) {
        if (row + 1 >= 0 && col - 1 >= 0 && row + 1 < fieldRow && col - 1 < fieldCol) {
            if ("*".equals(dizi[row + 1][col - 1])) {
                return 1;
            }
        }
        return 0;
    }
    public int mineTracker(int row, int col) {
        int mineTracker = topControl(row, col) + topRightControl(row, col) + topLeftControl(row, col) + leftControl(row, col) + rightControl(row, col) + bottomControl(row, col) + bottomLeftControl(row, col) + bottomRightControl(row, col);
        return mineTracker;
    }
    public void game() {
        boolean win = false;
        int emptyCells = fieldCol * fieldRow - totalMines;

        while (!win) {
            if (dizi[row][col] == "*") {
                System.out.println("Mayına bastınız, oyun bitti");
                break;
            } else if ((dizi[row][col] != "*")) {
                for (int i = 0; i < fieldRow; i++) {
                    for (int j = 0; j < fieldCol; j++) {
                        if ("S".equals(revealedCells[i][j])) {
                            System.out.print(mineTracker(i, j) + " ");
                        } else if (dizi[i][j] == "-") {
                            System.out.print(dizi[i][j]);
                        } else {
                            System.out.print("- ");
                        }
                    }
                    System.out.println();
                }
                emptyCells--;
                revealedCells[row][col] = "S";
                if (emptyCells == 0) {
                    System.out.println("Tüm boş hücreleri seçtiniz, oyunu kazandınız!");
                    win = true;
                } else {
                    System.out.println("Mayına basmadınız, devam edin");
                    selection();
                }
            }
        }
    }
}