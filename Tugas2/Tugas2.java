import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Tugas2 {

    static long gajiPokok(char golongan) {
        switch (Character.toUpperCase(golongan)) {
            case 'A':
                return 5_000_000L;
            case 'B':
                return 6_500_000L;
            case 'C':
                return 9_500_000L;
            default:
                return -1L;
        }
    }

    static double persenLembur(int jam) {
        if (jam < 0)
            return -1;
        switch (jam) {
            case 0:
                return 0.00;
            case 1:
                return 0.30;
            case 2:
                return 0.32;
            case 3:
                return 0.34;
            case 4:
                return 0.36;
            default:
                return 0.38;
        }
    }

    static long hitungTotal(char golongan, int jamLembur) {
        long pokok = gajiPokok(golongan);
        if (pokok < 0) {
            throw new IllegalArgumentException("Golongan tidak valid. Gunakan A, B, atau C.");
        }
        double p = persenLembur(jamLembur);
        if (p < 0) {
            throw new IllegalArgumentException("Jam lembur tidak valid. Harus >= 0.");
        }
        long gajiLembur = Math.round(p * pokok);
        return pokok + gajiLembur;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locale id = Locale.forLanguageTag("id-ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(id);

        try {
            System.out.println("Masukkan golongan (A/B/C):");
            String sGol = sc.nextLine().trim().toUpperCase();
            if (sGol.isEmpty()) {
                throw new IllegalArgumentException("Golongan wajib diisi.");
            }
            char gol = sGol.charAt(0);

            System.out.print("Masukkan Jam Lembur (0, 1, 2, 3, 4, >= 5): ");
            int jam = Integer.parseInt(sc.nextLine().trim());

            long pokok = gajiPokok(gol);
            double p = persenLembur(jam);
            if (pokok < 0) {
                throw new IllegalArgumentException("Golongan ini tidak valid. Gunakan A, B, atau C.");
            }
            if (p < 0) {
                throw new IllegalArgumentException("Jam lembur tidak valid. Harus >=0");
            }

            long gajiLembur = Math.round(p * pokok);
            long total = pokok + gajiLembur;

            System.out.println("\n=== Rincian Gaji ===");
            System.out.println("Golongan : " + gol);
            System.out.println("Gaji Pokok    : " + rupiah.format(pokok));
            System.out.println("Jam Lembur    : " + jam + " jam");
            System.out.println("Persen Lembur : " + Math.round(p * 100) + "%");
            System.out.println("Gaji Lembur   : " + rupiah.format(gajiLembur));
            System.out.println("___________________________________");
            System.out.println("Total Penghasilan : " + rupiah.format(total));
        } catch (NumberFormatException e) {
            System.out.println("Input jam lembur berupa angka bulat. ");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}