import java.util.Scanner;

// Değerlendirme formu 1: proje MineSweeper sınıfı içerisinde.
public class MineSweeper {

    int fieldRow;
    int fieldCol;
    String[][] dizi;
    String[][] revealedCells;
    int row;
    int col;
    int totalMines;

    // Değerlendirme formu 2: metotlar
    // Değerlendirme formu 3: kullanıcıdan tarlanın büyüklüğünün bilgisini alıyoruz
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

            revealedCells = new String[fieldRow][fieldCol];
            for (int i = 0; i < fieldRow; i++) {
                for (int j = 0; j < fieldCol; j++) {
                    revealedCells[i][j] = "-";
                }
            }
        }
    }

    // Değerlendirme formu 2: metotlar
    // Değerlendirme formu 4: mayın yerleştirilmesi
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

    // Değerlendirme formu 2: metotlar
    // Değerlendirme formu 5: işaretlenen satır ve sütun
    public void selection() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Satır sayısı: ");
            int row = input.nextInt();

            System.out.print("Sutun sayısı: ");
            int column = input.nextInt();
            // Değerlendirme formu 6: seçilen nokta dizinin içerisinde mi dışarısında mı kontrolü
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

    // Değerlendirme formu 2: metotlar
    public int ustKontrol(int row, int col) {
        if (row - 1 >= 0 && col >= 0 && row - 1 < fieldRow && col < fieldCol) {
            if ("*".equals(dizi[row - 1][col])) {
                return 1;
            }
        }
        return 0;
    }

    // Değerlendirme formu 2: metotlar
    public int ustSagKontrol(int row, int col) {
        if (row - 1 >= 0 && col + 1 >= 0 && row - 1 < fieldRow && col + 1 < fieldCol) {
            if ("*".equals(dizi[row - 1][col + 1])) {
                return 1;
            }
        }
        return 0;
    }

    // Değerlendirme formu 2: metotlar
    public int ustSolKontrol(int row, int col) {
        if (row - 1 >= 0 && col - 1 >= 0 && row - 1 < fieldRow && col - 1 < fieldCol) {
            if ("*".equals(dizi[row - 1][col - 1])) {
                return 1;
            }
        }
        return 0;
    }

    // Değerlendirme formu 2: metotlar
    public int solKontrol(int row, int col) {
        if (col - 1 >= 0 && row >= 0 && col - 1 < fieldCol && row < fieldRow) {
            if ("*".equals(dizi[row][col - 1])) {
                return 1;
            }
        }
        return 0;
    }

    // Değerlendirme formu 2: metotlar
    public int sagKontrol(int row, int col) {
        if (col + 1 >= 0 && row >= 0 && col + 1 < fieldCol && row < fieldRow) {
            if ("*".equals(dizi[row][col + 1])) {
                return 1;
            }
        }
        return 0;
    }

    // Değerlendirme formu 2: metotlar
    public int altKontrol(int row, int col) {
        if (row + 1 >= 0 && col >= 0 && row + 1 < fieldRow && col < fieldCol) {
            if ("*".equals(dizi[row + 1][col])) {
                return 1;
            }
        }
        return 0;
    }

    // Değerlendirme formu 2: metotlar
    public int altSagKontrol(int row, int col) {
        if (row + 1 >= 0 && col + 1 >= 0 && row + 1 < fieldRow && col + 1 < fieldCol) {
            if ("*".equals(dizi[row + 1][col + 1])) {
                return 1;
            }
        }
        return 0;
    }

    // Değerlendirme formu 2: metotlar
    public int altSolKontrol(int row, int col) {
        if (row + 1 >= 0 && col - 1 >= 0 && row + 1 < fieldRow && col - 1 < fieldCol) {
            if ("*".equals(dizi[row + 1][col - 1])) {
                return 1;
            }
        }
        return 0;
    }

    // Değerlendirme formu 2: metotlar
    // Değerlendirme formu 7: girilen hamlelerden sonra oyun alanı güncelleniyor
    public void game() {
        boolean win = false;
        int emptyCells = fieldCol * fieldRow - totalMines;

        while (!win) {
            // Değerlendirme formu 9: mayına bastığında oyun sonlandırılıyor
            if (dizi[row][col] == "*") {
                System.out.println("Mayına bastınız, oyun bitti");
                break;
            } else if ((dizi[row][col] != "*")) {
                for (int i = 0; i < fieldRow; i++) {
                    for (int j = 0; j < fieldCol; j++) {
                        int mineTracker = ustKontrol(row, col) + ustSagKontrol(row, col) + ustSolKontrol(row, col) + solKontrol(row, col) + sagKontrol(row, col) + altKontrol(row, col) + altSolKontrol(row, col) + altSagKontrol(row, col);
                        if ("S".equals(revealedCells[i][j])) {
                            // Değerlendirme formu 8: etraftaki mayın sayısı yazdırılıyor
                            System.out.print(mineTracker + " ");
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
                // Değerlendirme formu 10: noktalar başarıyla seçildiğinde oyun kazandırıyor
                if (emptyCells == 0) {
                    // Değerlendirme formu 11: kazandığında 'kazandınız', kaybettiğinde 'mayına bastınız' şeklinde mesaj gönderiliyor
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
