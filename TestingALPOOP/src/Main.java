import java.util.*;

public class Main {
    static ArrayList<Pasien> daftarPasien = new ArrayList<>();
    static GudangObat gudang = new GudangObat();
    static Dokter dokter = new Dokter("Dr. Sari", "D123");
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        gudang.tambahObat(new Obat("Paracetamol", 10, 5000));
        gudang.tambahObat(new Obat("Amoxicillin", 5, 10000));
        dokter.tambahJadwal(new Jadwal("12:00", "Ruang 1"));
        dokter.tambahJadwal(new Jadwal("15:00", "Ruang 2"));
        dokter.tambahJadwal(new Jadwal("20:00", "Ruang 3"));


        System.out.println("=== Sistem Antrian Kesehatan ===");

        while (true) {
            System.out.println("\nLogin sebagai:");
            System.out.println("1. Pasien");
            System.out.println("2. Dokter");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            if (pilihan == 1) {
                menuPasien();
            } else if (pilihan == 2) {
                if (loginDokter()) {
                    menuDokter();
                } else {
                    System.out.println("Login gagal. Coba lagi.");
                }
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static boolean loginDokter() {
        System.out.print("Masukkan nama dokter: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        return nama.equals(dokter.getNama()) && password.equals("12345");
    }

    public static void menuPasien() {
        while (true) {
            System.out.println("\n--- Menu Pasien ---");
            System.out.println("1. Daftar Jadwal");
            System.out.println("2. Keadaan Darurat");
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("NIK: ");
                    String nik = scanner.nextLine();
                    System.out.println("Prioritas (Hijau/Kuning): ");
                    System.out.println("Hijau: Tidak darurat, tidak diprioritaskan untuk pengecekan");
                    System.out.println("Kuning: sedang, lebih diprioritaskan untuk pengecekan");
                    String prioritas = scanner.nextLine();

                    Pasien pasien = new Pasien(nama, nik, prioritas);

                    System.out.println("Pilih Jadwal:");
                    for (int i = 0; i < dokter.getDaftarJadwal().size(); i++) {
                        System.out.println((i + 1) + ". " + dokter.getDaftarJadwal().get(i));
                    }

                    int pilih = scanner.nextInt();
                    scanner.nextLine();

                    if (pilih >= 1 && pilih <= dokter.getDaftarJadwal().size()) {
                        Jadwal jadwal = dokter.getDaftarJadwal().get(pilih - 1);
                        pasien.setJadwal(jadwal);
                        daftarPasien.add(pasien);
                        System.out.println("Receipt: " + nama + " - " + jadwal);
                    } else {
                        System.out.println("Pilihan jadwal tidak valid.");
                    }
                    break;

                case 2:
                    System.out.print("Masukkan NIK pasien darurat: ");
                    String nikDarurat = scanner.nextLine();

                    boolean ditemukan = false;
                    for (Pasien p : daftarPasien) {
                        if (p.getNik().equals(nikDarurat)) {
                            System.out.println("Pasien darurat ditemukan: " + p.getNama());
                            System.out.println("Segera arahkan ke ruang khusus darurat: RUANG D1");
                            ditemukan = true;
                            break;
                        }
                    }

                    if (!ditemukan) {
                        System.out.println("Pasien belum terdaftar. Silakan ke meja administrasi.");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void menuDokter() {
        while (true) {
            System.out.println("\n--- Menu Dokter ---");
            System.out.println("1. Lihat Jadwal Pasien");
            System.out.println("2. Cek Stok Obat");
            System.out.println("0. Logout");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    dokter.tampilkanJadwal();
                    break;
                case 2:
                    gudang.tampilkanStok();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
