import java.util.ArrayList;

public class Dokter extends Pengguna {
    ArrayList<Jadwal> daftarJadwal;

    public Dokter(String nama, String nik) {
        super(nama, nik);
        daftarJadwal = new ArrayList<>();
    }

    public void tambahJadwal(Jadwal j) {
        daftarJadwal.add(j);
    }

    public void tampilkanJadwal() {
        if (daftarJadwal.isEmpty()) {
            System.out.println("Belum ada jadwal.");
        } else {
            for (int i = 0; i < daftarJadwal.size(); i++) {
                System.out.println((i + 1) + ". " + daftarJadwal.get(i));
            }
        }
    }

    public ArrayList<Jadwal> getDaftarJadwal() {
        return daftarJadwal;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Dokter: " + nama + " | NIK: " + nik);
    }
}
