// Step 1: Mulai program - Definisikan kelas utama bernama Tugas2
import java.text.NumberFormat; // Mengimpor kelas untuk memformat angka menjadi mata uang
import java.util.Locale; // Mengimpor kelas untuk menentukan lokasi bahasa
import java.util.Scanner; // Mengimpor kelas untuk membaca input dari pengguna

public class Tugas1 { // Mendefinisikan kelas utama bernama Tugas2

    // Step 2: Import library yang diperlukan untuk input, format mata uang, dan lokasi bahasa (sudah dilakukan di atas)

    // Step 3: Definisikan metode untuk menentukan gaji pokok berdasarkan golongan (sesuai langkah pseudocode)
    static long gajiPokok(char golongan) { // Fungsi yang mengembalikan gaji pokok sebagai bilangan bulat panjang
        switch (Character.toUpperCase(golongan)) { // Menggunakan switch untuk memilih berdasarkan golongan (diubah ke huruf besar)
            case 'A': return 5_000_000L; // Jika golongan A, gaji pokok 5 juta
            case 'B': return 6_500_000L; // Jika golongan B, gaji pokok 6.5 juta
            case 'C': return 9_500_000L; // Jika golongan C, gaji pokok 9.5 juta
            default:  return -1L; // Jika golongan tidak valid, kembalikan -1 sebagai tanda error
        }
    }

    // Step 4: Definisikan metode untuk menentukan persentase lembur berdasarkan jam lembur (sesuai langkah pseudocode)
    static double persenLembur(int jam) { // Fungsi yang mengembalikan persentase lembur sebagai bilangan desimal
        if (jam < 0) return -1; // Jika jam negatif, tidak valid, kembalikan -1
        switch (jam) { // Menggunakan switch untuk memilih berdasarkan jumlah jam
            case 0: return 0.00; // Jika 0 jam, persentase 0%
            case 1: return 0.30; // Jika 1 jam, persentase 30%
            case 2: return 0.32; // Jika 2 jam, persentase 32%
            case 3: return 0.34; // Jika 3 jam, persentase 34%
            case 4: return 0.36; // Jika 4 jam, persentase 36%
            default: return 0.38; // Jika 5 jam atau lebih, persentase 38%
        }
    }

    // Step 5: Definisikan metode untuk menghitung total penghasilan (sesuai langkah pseudocode untuk menghitung gaji lembur dan total)
    static long hitungTotal(char golongan, int jamLembur) { // Fungsi yang menghitung total gaji berdasarkan golongan dan jam lembur
        long pokok = gajiPokok(golongan); // Mendapatkan gaji pokok dari metode gajiPokok
        if (pokok < 0) { // Jika gaji pokok negatif, berarti golongan tidak valid
            throw new IllegalArgumentException("Golongan tidak valid. Gunakan A, B, atau C."); // Lempar exception dengan pesan error
        }
        double p = persenLembur(jamLembur); // Mendapatkan persentase lembur dari metode persenLembur
        if (p < 0) { // Jika persentase negatif, berarti jam lembur tidak valid
            throw new IllegalArgumentException("Jam lembur tidak valid. Harus >= 0."); // Lempar exception dengan pesan error
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
            // Step 7: Input golongan dari pengguna (sesuai langkah pseudocode)
            System.out.print("Masukkan Golongan (A/B/C): "); // Meminta pengguna memasukkan golongan
            String sGol = sc.nextLine().trim().toUpperCase(); // Membaca input sebagai string, hapus spasi, ubah ke huruf besar
            if (sGol.isEmpty()) { // Jika input kosong
                throw new IllegalArgumentException("Golongan wajib diisi."); // Lempar exception karena golongan harus diisi
            }
            char gol = sGol.charAt(0); // Ambil karakter pertama sebagai golongan

            // Step 8: Input jam lembur dari pengguna (sesuai langkah pseudocode)
            System.out.print("Masukkan Jam Lembur (0,1,2,3,4,>=5): "); // Meminta pengguna memasukkan jam lembur
            int jam = Integer.parseInt(sc.nextLine().trim()); // Membaca input sebagai string, ubah ke integer

            // Step 9: Hitung gaji pokok, persentase lembur, gaji lembur, dan total (sesuai langkah pseudocode)
            long pokok = gajiPokok(gol); // Hitung gaji pokok berdasarkan golongan
            double p = persenLembur(jam); // Hitung persentase lembur berdasarkan jam
            if (pokok < 0) { // Jika gaji pokok tidak valid
                throw new IllegalArgumentException("Golongan tidak valid. Gunakan A, B, atau C."); // Lempar exception
            }
            if (p < 0) { // Jika persentase lembur tidak valid
                throw new IllegalArgumentException("Jam lembur tidak valid. Harus >= 0."); // Lempar exception
            }

            long gajiLembur = Math.round(p * pokok); // Hitung gaji lembur
            long total = pokok + gajiLembur; // Hitung total penghasilan

            // Step 10: Tampilkan hasil penghasilan (sesuai langkah pseudocode)
            System.out.println("\n=== Rincian Gaji ==="); // Tampilkan header rincian gaji
            System.out.println("Golongan       : " + gol); // Tampilkan golongan
            System.out.println("Gaji Pokok     : " + rupiah.format(pokok)); // Tampilkan gaji pokok dalam format rupiah
            System.out.println("Jam Lembur     : " + jam + " jam"); // Tampilkan jam lembur
            System.out.println("Persen Lembur  : " + Math.round(p * 100) + " %"); // Tampilkan persentase lembur dalam persen
            System.out.println("Gaji Lembur    : " + rupiah.format(gajiLembur)); // Tampilkan gaji lembur dalam format rupiah
            System.out.println("-----------------------------"); // Garis pemisah
            System.out.println("Total Penghasilan: " + rupiah.format(total)); // Tampilkan total penghasilan dalam format rupiah
        } catch (NumberFormatException e) { // Tangkap exception jika input jam bukan angka
            System.out.println("Input jam lembur harus berupa angka bulat."); // Tampilkan pesan error
        } catch (IllegalArgumentException e) { // Tangkap exception untuk input tidak valid
            System.out.println(e.getMessage()); // Tampilkan pesan error dari exception
        } finally { // Blok finally yang selalu dijalankan
            sc.close(); // Tutup Scanner untuk membersihkan sumber daya
        }
    }
    // Step 11: Selesai program
}


// By: dymasyogaf