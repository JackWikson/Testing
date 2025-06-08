import java.util.ArrayList;

public class Dokter extends Pengguna {
    private String password;
    private ArrayList<Jadwal> daftarJadwal = new ArrayList<>();

    public Dokter(String nama, String id, String password) {
        super(nama, id);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void tambahJadwal(Jadwal jadwal) {
        daftarJadwal.add(jadwal);
    }

    public ArrayList<Jadwal> getDaftarJadwal() {
        return daftarJadwal;
    }

    public void tampilkanJadwalPasien(ArrayList<Pasien> daftarPasien) {
        System.out.println("\n--- Jadwal " + this.getNama() + " ---");
        for (Jadwal j : daftarJadwal) {
                System.out.println("- " + j);
            }

        System.out.println("\n--- Notifikasi Pasien ---");
        boolean ada = false;
        for (Pasien p : daftarPasien) {
            Jadwal j = p.getJadwal();
            if (j != null && j.getDokter().equals(this.getNama())) {
                System.out.println(p.getNama() + " dengan NIK " + p.getId() +" (Prioritas: " + p.getPrioritas() + ") telah memilih jadwal: " + j);
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Belum ada pasien yang memilih jadwal Anda.");
        }
    }
}
