import java.util.Scanner;

public class MineSweeper {

    int fieldRow;
    int fieldCol;
    String[][] dizi;
    int row;
    int col;

    // Kullanıcıdan tarlanın büyüklüğünün bilgisini alıyoruz
    public void beginning() {
        Scanner input = new Scanner(System.in);
        // boolean eklenerek sorun olması durumunda (2'den küçük sayı eklenmesi) başa dönülmesi sağlandı
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
        }
    }

    public void field() {
        dizi = new String[fieldRow][fieldCol]; // String[][] olarak oluşturuyoruz

        int totalMines = (fieldRow * fieldCol) / 4;

        for (int i = 0; i < totalMines; i++) {
            int randomRow = (int) (Math.random() * fieldRow);
            int randomCol = (int) (Math.random() * fieldCol);

            if (dizi[randomRow][randomCol] == "*") { // Mayın yerine "*" kullanıyoruz
                i--;
            } else {
                dizi[randomRow][randomCol] = "*"; // Mayın yerine "*" kullanıyoruz
            }
        }

        for (int i = 0; i < fieldRow; i++) {
            for (int j = 0; j < fieldCol; j++) {
                if (dizi[i][j] == null) { // null yerine "-" kullanıyoruz
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

            System.out.print("Sutun sayısı:  ");
            int column = input.nextInt();

            if ((row < fieldRow && row >= 0) && (column < fieldCol && column >= 0)) {
                this.row = row;
                this.col = column;
                break;
            } else {
                System.out.println("=========================================================================================================");
                System.out.println("Girdiğiniz sayı tarlanın boyutunda, boyutundan daha büyük ya da 0'dan küçük olamaz, lütfen tekrar giriniz");
                System.out.println("=========================================================================================================");
            }
        }
    }

    public int ustKontrol(int row, int col) {
        if (row - 1 >= 0 && col >= 0 && row - 1 < fieldRow && col < fieldCol) {
            if ("*".equals(dizi[row - 1][col])) { // Mayın yerine "*" kullanıyoruz
                return 1;
            }
        }
        return 0;
    }

    public int ustSagKontrol(int row, int col) {
        if (row - 1 >= 0 && col + 1 >= 0 && row - 1 < fieldRow && col + 1 < fieldCol) {
            if ("*".equals(dizi[row - 1][col + 1])) { // Mayın yerine "*" kullanıyoruz
                return 1;
            }
        }
        return 0;
    }

    public int ustSolKontrol(int row, int col) {
        if (row - 1 >= 0 && col - 1 >= 0 && row - 1 < fieldRow && col - 1 < fieldCol) {
            if ("*".equals(dizi[row - 1][col - 1])) { // Mayın yerine "*" kullanıyoruz
                return 1;
            }
        }
        return 0;
    }

    public int solKontrol(int row, int col) {
        if (col - 1 >= 0 && row >= 0 && col - 1 < fieldCol && row < fieldRow) {
            if ("*".equals(dizi[row][col - 1])) { // Mayın yerine "*" kullanıyoruz
                return 1;
            }
        }
        return 0;
    }

    public int sagKontrol(int row, int col) {
        if (col + 1 >= 0 && row >= 0 && col + 1 < fieldCol && row < fieldRow) {
            if ("*".equals(dizi[row][col + 1])) { // Mayın yerine "*" kullanıyoruz
                return 1;
            }
        }
        return 0;
    }

    public int altKontrol(int row, int col) {
        if (row + 1 >= 0 && col >= 0 && row + 1 < fieldRow && col < fieldCol) {
            if ("*".equals(dizi[row + 1][col])) { // Mayın yerine "*" kullanıyoruz
                return 1;
            }
        }
        return 0;
    }

    public int altSagKontrol(int row, int col) {
        if (row + 1 >= 0 && col + 1 >= 0 && row + 1 < fieldRow && col + 1 < fieldCol) {
            if ("*".equals(dizi[row + 1][col + 1])) { // Mayın yerine "*" kullanıyoruz
                return 1;
            }
        }
        return 0;
    }

    public int altSolKontrol(int row, int col) {
        if (row + 1 >= 0 && col - 1 >= 0 && row + 1 < fieldRow && col - 1 < fieldCol) {
            if ("*".equals(dizi[row + 1][col - 1])) { // Mayın yerine "*" kullanıyoruz
                return 1;
            }
        }
        return 0;
    }


    public void game() {
        boolean win = false;
        while (!win) {
            if ((dizi[row][col] == "*")) {
                System.out.println("Mayına bastınız, oyun bitti");
                win = true;
            } else if ((dizi[row][col] != "*")) {

                int mineTracker = ustKontrol(row, col) + ustSagKontrol(row, col) + ustSolKontrol(row, col) + solKontrol(row, col) + sagKontrol(row, col) + altKontrol(row, col) + altSolKontrol(row, col) + altSagKontrol(row, col);

                for (int i = 0; i < fieldRow; i++) {
                    for (int j = 0; j < fieldCol; j++) {
                        if (i == row && j == col) {
                            System.out.print(mineTracker + " ");
                        } else if (dizi[i][j] == "-") {
                            System.out.print(dizi[i][j]);
                        } else {
                            System.out.print("- ");
                        }
                    }
                    System.out.println();
                }

                System.out.println("Mayına basmadınız, devam edin");
                selection();
            }
        }
    }
}