import java.util.*;

public class Main {
    static ArrayList<Pasien> daftarPasien = new ArrayList<>();
    static GudangObat gudang = new GudangObat();
    static ArrayList<Dokter> dokterUmum = new ArrayList<>();
    static ArrayList<Dokter> dokterAnak = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        dokterUmum.add(new Dokter("Dr. Aditya Pratama", "D001", "Apel123"));
        dokterUmum.add(new Dokter("Dr. Eka Putri", "D002", "dokterekap"));
        dokterUmum.add(new Dokter("Dr. Indra Kurnia", "D003", "Indrapass"));

        dokterAnak.add(new Dokter("Dr. Bella Santoso", "D004", "belladoc"));
        dokterAnak.add(new Dokter("Dr. Farhan Nugroho", "D005", "farhan456"));
        dokterAnak.add(new Dokter("Dr. Joko Susilo", "D006", "Halojoko"));

        dokterUmum.get(0).tambahJadwal(new Jadwal("08:00", "Ruang 1", "Dr. Aditya Pratama", "081234567890"));
        dokterUmum.get(1).tambahJadwal(new Jadwal("09:00", "Ruang 2", "Dr. Eka Putri", "081678901234"));
        dokterUmum.get(2).tambahJadwal(new Jadwal("10:00", "Ruang 3", "Dr. Indra Kurnia", "081112223334"));

        dokterAnak.get(0).tambahJadwal(new Jadwal("11:00", "Ruang 4", "Dr. Bella Santoso", "081345678901"));
        dokterAnak.get(1).tambahJadwal(new Jadwal("13:00", "Ruang 5", "Dr. Farhan Nugroho", "0801789012345"));
        dokterAnak.get(2).tambahJadwal(new Jadwal("14:00", "Ruang 6", "Dr. Joko Susilo", "081123334445"));

        gudang.tambahObat(new Obat("Paracetamol", 10));
        gudang.tambahObat(new Obat("Antibiotik", 15));

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
                Dokter d = loginDokter();
                if (d != null) {
                    menuDokter(d);
                } else {
                    System.out.println("Login gagal.");
                }
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
                    System.out.print("\nNama: ");
                    String nama = scanner.nextLine();
                    System.out.print("NIK: ");
                    String nik = scanner.nextLine();
                    System.out.println("Prioritas (Hijau/Kuning): ");
                    System.out.println("Hijau = Tidak Darurat, mengalami cedera ringan atau penyakit yang tidak mengancam jiwa.");
                    System.out.println("Kuning = Membutuhkan perhatian medis segera, tetapi tidak dalam kondisi kritis.");
                    String prioritas = scanner.nextLine();

                    Pasien pasien = new Pasien(nama, nik, prioritas);

                    System.out.println("\nPilih tipe dokter:");
                    System.out.println("1. Dokter Umum");
                    System.out.println("2. Dokter Anak");
                    System.out.print("Pilihan: ");
                    int tipe = scanner.nextInt();
                    scanner.nextLine();

                    ArrayList<Dokter> listDokter = (tipe == 1) ? dokterUmum : dokterAnak;

                    ArrayList<Jadwal> semuaJadwal = new ArrayList<>();
                    int index = 1;
                    for (Dokter d : listDokter) {
                        for (Jadwal j : d.getDaftarJadwal()) {
                            if (!j.isTerpesan()) {
                                System.out.println(index + ". " + j);
                                semuaJadwal.add(j);
                                index++;
                            }
                        }
                    }

                    if (semuaJadwal.isEmpty()) {
                        System.out.println("Tidak ada jadwal tersedia.");
                        break;
                    }

                    System.out.print("\nPilih jadwal: ");
                    int pilih = scanner.nextInt();
                    scanner.nextLine();

                    Jadwal jadwal = semuaJadwal.get(pilih - 1);
                    jadwal.setTerpesan(true);
                    pasien.setJadwal(jadwal);
                    daftarPasien.add(pasien);

                    Obat obat = gudang.kurangiStokAcak(jadwal.getDokter());
                    System.out.println("Receipt: " + pasien.getNama() + " - " + jadwal);
                    if (obat != null) {
                        System.out.println("Obat yang dipakai: " + obat.getNama());
                    }
                    break;

                case 2:
                    System.out.print("NIK: ");
                    scanner.nextLine();
                    System.out.println("Pasien darurat! Segera ke ruang darurat.");
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
                    dokter.tampilkanJadwal();
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
