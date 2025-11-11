// 2. import library yg diperlukan
import java.text.NumberFormat; // untuk format mata uang
import java.util.Locale; // untuk menentuka lokasi bahasa
import java.util.Scanner; // untuk membaca input dari pengguna

public class Tugas2 { // 1. Buat nama file bebas (Tugas2)

    // 3. Menentukan gajipokok berdasarkan golongan
    static long gajiPokok(char golongan) { // bilangan bulat panjang
        switch (Character.toUpperCase(golongan)) { // untuk memilih karakter berdasarkan gol (diubah ke huruf besar) 
            case 'A':
                return 5_000_000L; // Jika gol. A, GP 5 juta 
            case 'B':
                return 6_500_000L; // Jika gol. B, GP 6,5 Juta
            case 'C':
                return 9_500_000L; // Jika gol. C, GP 9,5 Juta
            default:
                return -1L; // Jika gol. tidak valid, kembalikan ke -1 sbg tanda eror
        }
    }


    // 4. Menentukan presentase lembur berdasarkan jam lembur
    static double persenLembur(int jam) { // Fungsi yg mengembalikan presentase lembur sbg bilangan desimal
        if (jam < 0) 
            return -1; //Jika jam negatif, maka tidak valid, kembalikan -1
        switch (jam) { //menggunakan switch untuk memilih berdasarkan jumlah jam
            case 0:
                return 0.00; // Jika 0 jam, maka presentase 0%
            case 1:
                return 0.30; //Jika 1 jam, maka presentase 30%
            case 2:
                return 0.32;
            case 3:
                return 0.34;
            case 4:
                return 0.36;
            default:
                return 0.38; // jika 5 jam atau lebih, maka presentase 38% 
        }
    }


    // 5. Definisikan metode untuk menghitung total penghasilan
    static long hitungTotal(char golongan, int jamLembur) { //Fungsi yg menghitung total gaji berdasarkan golongan dan jam lembur
        long pokok = gajiPokok(golongan); // Mendapatkan gaji pokok, dari metode gajipokok
        if (pokok < 0) { // Jika gaji pokok negatif, berarti golongan tidak valid
            throw new IllegalArgumentException("Golongan tidak valid. Gunakan A, B, atau C."); //Lempar kesini
        }
        double p = persenLembur(jamLembur); // Mendapatkan presentase lembur dari metode persenLembur
        if (p < 0) { // Jika persentase negatif, berarti jam lembur tidak valid
            throw new IllegalArgumentException("Jam lembur tidak valid. Harus >= 0."); // Lempar kesini
        }
        long gajiLembur = Math.round(p * pokok); // Hitung gaji lembur dengan membulatkan ke rupiah terdekat
        return pokok + gajiLembur; // Kembalikan total gaji (pokok + lembur)
    }


    // Step 6: Definisikan metode main untuk menjalankan program (mulai eksekusi)
    public static void main(String[] args) { // Metode utama yang dijalankan saat program dimulai
        Scanner sc = new Scanner(System.in); // Membuat objek Scanner untuk membaca input dari keyboard
        Locale id = Locale.forLanguageTag("id-ID"); // Mengatur lokasi bahasa Indonesia untuk format mata uang
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(id); // Membuat formatter mata uang dalam rupiah

        try { // Blok try untuk menangani exception
            // Step 7: Input golongan dari pengguna
            System.out.println("Masukkan golongan (A/B/C):"); // Meminta pengguna memasukkan golongan
            String sGol = sc.nextLine().trim().toUpperCase(); // Membaca input sebagai string, hapus spasi, ubah ke huruf besar
            if (sGol.isEmpty()) { // Jika input kosong
                throw new IllegalArgumentException("Golongan wajib diisi."); // Lempar exception karena golongan harus diisi
            }
            char gol = sGol.charAt(0); // Ambil karakter pertama sebagai golongan

            // Step 8: Input jam lembur dari pengguna (sesuai langkah pseudocode)
            System.out.print("Masukkan Jam Lembur (0, 1, 2, 3, 4, >= 5): "); // Meminta pengguna memasukkan jam lembur
            int jam = Integer.parseInt(sc.nextLine().trim()); // Membaca inpus sbg string, ubah ke integer

            // Step 9: Hitung gaji pokok, persentase lembur, gaji lembur, dan total (sesuai langkah pseudocode)
            long pokok = gajiPokok(gol); // Hitung gaji pokok berdasarkan golongan
            double p = persenLembur(jam); // Hitung persentase lembur berdasarkan jam
            if (pokok < 0) { // Jika gaji pokok tidak valid
                throw new IllegalArgumentException("Golongan ini tidak valid. Gunakan A, B, atau C."); // Lempar excepiton
            }
            if (p < 0) { // Jika presentase lembur tidak valid
                throw new IllegalArgumentException("Jam lembur tidak valid. Harus >=0"); // Lempar exception
            }

            long gajiLembur = Math.round(p * pokok); // Hitung gaji lembur
            long total = pokok + gajiLembur; // Hitung total penghasilan

            // 10. Tampilkan hasil penghasilan
            System.out.println("\n=== Rincian Gaji ==="); // Header
            System.out.println("Golongan : " + gol); // Golongan
            System.out.println("Gaji Pokok    : " + rupiah.format(pokok)); // Gaji pokok dalam format rupiah
            System.out.println("Jam Lembur    : " + jam + " jam"); // Tampilkan jam lembur
            System.out.println("Persen Lembur : " + Math.round(p * 100) + "%"); // Presentase lembur
            System.out.println("Gaji Lembur   : " + rupiah.format(gajiLembur)); // Gaji lembur dalam format rupiah
            System.out.println("___________________________________"); // Garis pemisah
            System.out.println("Total Penghasilan : " + rupiah.format(total)); // Total penghasilan dalam format rupiah
        } catch (NumberFormatException e) { // Tangkap exception jika input jam bukan angka
            System.out.println("Input jam lembur berupa angka bulat. "); // Tampilkan pesan eror
        } catch (IllegalArgumentException e) { // Tangkap exception untuk input tidak valid
            System.out.println(e.getMessage()); // Tampilkan pesan eror dari exception
        } finally { // Blok finally yg selalu di jalankan
            sc.close(); // Tutup scanner untuk membersihkan sumber daya
        }
    }
}

// 11. Finish