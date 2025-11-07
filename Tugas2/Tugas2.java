import java.text.NumberFormat; // Mengimpor kelas untuk format mata uang
import java.util.Locale; // Mengimpor kelas untuk pengaturan lokal
import java.util.Scanner; // Mengimpor kelas untuk input dari pengguna

public class Tugas2 { 

    // Metode untuk menentukan gaji pokok berdasarkan golongan
    static long gajiPokok(char golongan) {
        switch (Character.toUpperCase(golongan)) { // Mengubah golongan ke huruf besar untuk konsistensi
            case 'A': return 5_000_000L; // Gaji pokok untuk golongan A
            case 'B': return 6_500_000L; // Gaji pokok untuk golongan B
            case 'C': return 9_500_000L; // Gaji pokok untuk golongan C
            default:  return -1L; // Mengembalikan -1 jika golongan tidak valid
        }
    }

    // Metode untuk menentukan persentase lembur berdasarkan jam lembur
    static double persenLembur(int jam) {
        if (jam < 0) return -1; // Mengembalikan -1 jika jam lembur negatif (tidak valid)
        switch (jam) {
            case 0: return 0.00; // Persentase lembur untuk 0 jam
            case 1: return 0.30; // Persentase lembur untuk 1 jam
            case 2: return 0.32; // Persentase lembur untuk 2 jam
            case 3: return 0.34; // Persentase lembur untuk 3 jam
            case 4: return 0.36; // Persentase lembur untuk 4 jam
            default: return 0.38; // Persentase lembur untuk 5 jam atau lebih
        }
    }

    // Metode untuk menghitung total penghasilan
    static long hitungTotal(char golongan, int jamLembur) {
        long pokok = gajiPokok(golongan); // Mendapatkan gaji pokok
        if (pokok < 0) {
            throw new IllegalArgumentException("Golongan tidak valid. Gunakan A, B, atau C."); // Melempar exception jika golongan tidak valid
        }
        double p = persenLembur(jamLembur); // Mendapatkan persentase lembur
        if (p < 0) {
            throw new IllegalArgumentException("Jam lembur tidak valid. Harus >= 0."); // Melempar exception jika jam lembur tidak valid
        }
        long gajiLembur = Math.round(p * pokok); // Menghitung gaji lembur dan membulatkannya
        return pokok + gajiLembur; // Mengembalikan total penghasilan
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Membuat objek Scanner untuk input
        Locale id = Locale.forLanguageTag("id-ID"); // Mengatur lokal ke Indonesia
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(id); // Membuat formatter mata uang Rupiah

        try {
            System.out.print("Masukkan Golongan (A/B/C): "); // Meminta input golongan
            String sGol = sc.nextLine().trim().toUpperCase(); // Membaca input dan membersihkannya
            if (sGol.isEmpty()) {
                throw new IllegalArgumentException("Golongan wajib diisi."); // Melempar exception jika kosong
            }
            char gol = sGol.charAt(0); // Mengambil karakter pertama sebagai golongan

            System.out.print("Masukkan Jam Lembur (0,1,2,3,4,>=5): "); // Meminta input jam lembur
            int jam = Integer.parseInt(sc.nextLine().trim()); // Membaca dan mengubah ke integer

            long pokok = gajiPokok(gol); // Mendapatkan gaji pokok
            double p = persenLembur(jam); // Mendapatkan persentase lembur
            if (pokok < 0) {
                throw new IllegalArgumentException("Golongan tidak valid. Gunakan A, B, atau C."); // Exception untuk golongan tidak valid
            }
            if (p < 0) {
                throw new IllegalArgumentException("Jam lembur tidak valid. Harus >= 0."); // Exception untuk jam lembur tidak valid
            }

            long gajiLembur = Math.round(p * pokok); // Menghitung gaji lembur
            long total = pokok + gajiLembur; // Menghitung total penghasilan

            System.out.println("\n=== Rincian Gaji ==="); // Menampilkan header rincian
            System.out.println("Golongan       : " + gol); // Menampilkan golongan
            System.out.println("Gaji Pokok     : " + rupiah.format(pokok)); // Menampilkan gaji pokok dalam format Rupiah
            System.out.println("Jam Lembur     : " + jam + " jam"); // Menampilkan jam lembur
            System.out.println("Persen Lembur  : " + Math.round(p * 100) + " %"); // Menampilkan persentase lembur
            System.out.println("Gaji Lembur    : " + rupiah.format(gajiLembur)); // Menampilkan gaji lembur dalam format Rupiah
            System.out.println("-----------------------------"); // Garis pemisah
            System.out.println("Total Penghasilan: " + rupiah.format(total)); // Menampilkan total penghasilan
        } catch (NumberFormatException e) {
            System.out.println("Input jam lembur harus berupa angka bulat."); // Menangani error jika input bukan angka
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Menampilkan pesan exception
        } finally {
            sc.close(); // Menutup Scanner untuk mencegah kebocoran sumber daya
        }
    }
}
