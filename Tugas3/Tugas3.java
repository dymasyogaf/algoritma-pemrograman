import java.util.Locale;
import java.text.NumberFormat;
import java.util.Scanner;

public class Tugas3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] gaji = { 5_000_000, 6_500_000, 9_500_000 };

        double[] persenLembur = { 0.30, 0.32, 0.34, 0.36, 0.38 };

        try {
            System.out.print("Masukkan Golongan (A/B/C): ");
            String gol = sc.next().trim().toUpperCase();
            
            System.out.print("Masukkan Jam Lembur: ");
            int jam = sc.nextInt();

            int idxGol = -1;
            
            if (gol.equals("A")) {
                idxGol = 0;
            } else if (gol.equals("B")) {
                idxGol = 1;
            } else if (gol.equals("C")) {
                idxGol = 2;
            } else {
                System.out.println("Golongan tidak valid!");
                return;
            }

            int gajiPokok = gaji[idxGol];

            double persen = 0.0;
            
            if (jam <= 0) {
                persen = 0.0;
            } else if (jam >= 5) {
                persen = persenLembur[4];
            } else {
                persen = persenLembur[jam - 1];
            }

            long gajiLembur = Math.round(gajiPokok * persen);
            long total = gajiPokok + gajiLembur;

            Locale id = Locale.forLanguageTag("id-ID");
            NumberFormat rupiah = NumberFormat.getCurrencyInstance(id);

            System.out.println("=== Rincian Penghasilan ===");
            System.out.println("Golongan        : " + gol);
            System.out.println("Gaji Pokok      : " + rupiah.format(gajiPokok));
            System.out.println("Jam Lembur      : " + jam);
            System.out.println("Persen Lembur   : " + (int)Math.round(persen * 100) + "%");
            System.out.println("Gaji Lembur     : " + rupiah.format(gajiLembur));
            System.out.println("----------------------------");
            System.out.println("Jumlah Penghasilan: " + rupiah.format(total));
        } catch (Exception e) {
            System.out.println("Input tidak valid! Pastikan memasukkan golongan (A/B/C) dan jam lembur (angka).");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
