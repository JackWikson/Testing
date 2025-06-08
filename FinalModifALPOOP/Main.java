import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static ArrayList<Pasien> daftarPasien = new ArrayList<>();
    static GudangObat gudang = new GudangObat();
    static ArrayList<Dokter> dokterUmum = new ArrayList<>();
    static ArrayList<Dokter> dokterAnak = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        Dokter dokterUmum1 = new Dokter("Dr. Aditya Pratama", "D001", "Apel123");
        Dokter dokterUmum2 = new Dokter("Dr. Eka Putri", "D002", "dokterekap");

        Dokter dokterAnak1 = new Dokter("Dr. Bella Santoso", "D003", "belladoc");
        Dokter dokterAnak2 = new Dokter("Dr. Farhan Nugroho", "D004", "farhan456");

        dokterUmum.add(dokterUmum1);
        dokterUmum.add(dokterUmum2);
        dokterAnak.add(dokterAnak1);
        dokterAnak.add(dokterAnak2);

        dokterUmum1.tambahJadwal(new Jadwal("07:00", "Ruang 1", "Dr. Aditya Pratama", "081234567890"));
        dokterUmum1.tambahJadwal(new Jadwal("09:00", "Ruang 1", "Dr. Aditya Pratama", "081234567890"));
        dokterUmum1.tambahJadwal(new Jadwal("12:00", "Ruang 1", "Dr. Aditya Pratama", "081234567890"));
        dokterUmum1.tambahJadwal(new Jadwal("14:00", "Ruang 1", "Dr. Aditya Pratama", "081234567890"));

        dokterUmum2.tambahJadwal(new Jadwal("16:00", "Ruang 2", "Dr. Eka Putri", "081678901234"));
        dokterUmum2.tambahJadwal(new Jadwal("18:00", "Ruang 2", "Dr. Eka Putri", "081678901234"));
        dokterUmum2.tambahJadwal(new Jadwal("21:00", "Ruang 2", "Dr. Eka Putri", "081678901234"));
        dokterUmum2.tambahJadwal(new Jadwal("23:00", "Ruang 2", "Dr. Eka Putri", "081678901234"));

        dokterAnak1.tambahJadwal(new Jadwal("07:00", "Ruang 3", "Dr. Bella Santoso", "081345678901"));
        dokterAnak1.tambahJadwal(new Jadwal("09:00", "Ruang 3", "Dr. Bella Santoso", "081345678901"));
        dokterAnak1.tambahJadwal(new Jadwal("12:00", "Ruang 3", "Dr. Bella Santoso", "081345678901"));
        dokterAnak1.tambahJadwal(new Jadwal("14:00", "Ruang 3", "Dr. Bella Santoso", "081345678901"));

        dokterAnak2.tambahJadwal(new Jadwal("16:00", "Ruang 4", "Dr. Farhan Nugroho", "0801789012345"));
        dokterAnak2.tambahJadwal(new Jadwal("18:00", "Ruang 4", "Dr. Farhan Nugroho", "0801789012345"));
        dokterAnak2.tambahJadwal(new Jadwal("21:00", "Ruang 4", "Dr. Farhan Nugroho", "0801789012345"));
        dokterAnak2.tambahJadwal(new Jadwal("23:00", "Ruang 4", "Dr. Farhan Nugroho", "0801789012345"));

        gudang.tambahObat(new Obat("Paracetamol", 10));
        gudang.tambahObat(new Obat("Amoxicillin", 15));
        gudang.tambahObat(new Obat("Panadol", 12));
        gudang.tambahObat(new Obat("Ibuprofen", 20));
        gudang.tambahObat(new Obat("Vitamin", 16));

        System.out.println("=== Sistem Registrasi Pengecekan Kesehatan ===");

        while (true) {
            System.out.println("\nLogin sebagai:");
            System.out.println("1. Pasien");
            System.out.println("2. Dokter");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            if (pilihan == 1) {
                menuPasien();
            } else if (pilihan == 2) {
                Dokter d = loginDokter();
                if (d != null) {
                    menuDokter(d);
                } else {
                    System.out.println("Login gagal.");
                }
            } else if (pilihan == 0) {
                System.out.println("Terima kasih telah menggunakan program ini. :)");
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static Dokter loginDokter() {
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (Dokter d : getSemuaDokter()) {
            if (d.getNama().equals(nama) && d.getId().equals(id) && d.getPassword().equals(password)) {
                return d;
            }
        }
        return null;
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
                    System.out.print("Prioritas (Hijau/Kuning): ");
                    String prioritas = scanner.nextLine();

                    Pasien pasien = new Pasien(nama, nik, prioritas);

                    System.out.println("Pilih jenis dokter:");
                    System.out.println("1. Dokter Umum");
                    System.out.println("2. Dokter Anak");
                    System.out.print("Pilihan: ");
                    int jenis = scanner.nextInt();
                    scanner.nextLine();

                    List<Dokter> daftarDipilih = (jenis == 1) ? dokterUmum : dokterAnak;
                    List<Jadwal> jadwalTersedia = new ArrayList<>();

                    System.out.println("\n--- Jadwal Tersedia ---");
                    int index = 1;
                    for (Dokter d : daftarDipilih) {
                        for (Jadwal j : d.getDaftarJadwal()) {
                            if (!j.isTerpesan()) {
                                System.out.println(index + ". " + j);
                                jadwalTersedia.add(j);
                                index++;
                            }
                        }
                    }

                    if (jadwalTersedia.isEmpty()) {
                        System.out.println("Maaf, semua jadwal telah dipesan.");
                        break;
                    }

                    System.out.print("Pilih nomor jadwal: ");
                    int pilih = scanner.nextInt();
                    scanner.nextLine();

                    if (pilih < 1 || pilih > jadwalTersedia.size()) {
                        System.out.println("Pilihan tidak valid.");
                        break;
                    }

                    Jadwal jadwalDipilih = jadwalTersedia.get(pilih - 1);
                    jadwalDipilih.setTerpesan(true);
                    pasien.setJadwal(jadwalDipilih);
                    daftarPasien.add(pasien);

                    Obat obat = gudang.kurangiStokAcak(jadwalDipilih.getDokter());
                    System.out.println("\nReceipt: " + nama + " - " + jadwalDipilih);
                    if (obat != null) {
                        System.out.println(
                                "Obat " + obat.getNama() + " telah digunakan oleh " + jadwalDipilih.getDokter());
                    }
                    break;

                case 2:
                    System.out.print("NIK: ");
                    scanner.nextLine();
                    System.out.println("Pasien dalam keadaan darurat! Harap segera menuju Ruang Darurat.");
                    break;

                case 0:
                    return;
            }
        }
    }

    public static void menuDokter(Dokter dokter) {
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
                    dokter.tampilkanJadwalPasien(daftarPasien);
                    break;
                case 2:
                    gudang.tampilkanStok();
                    break;
                case 0:
                    return;
            }
        }
    }

    public static ArrayList<Dokter> getSemuaDokter() {
        ArrayList<Dokter> semua = new ArrayList<>(dokterUmum);
        semua.addAll(dokterAnak);
        return semua;
    }
}
