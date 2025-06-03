import java.util.ArrayList;

public class Dokter extends Pengguna {
    private String password;
    private ArrayList<Jadwal> daftarJadwal = new ArrayList<>();

    public Dokter(String nama, String id, String password) {
        super(nama, id);
        this.password = password;
    }

    public void tambahJadwal(Jadwal jadwal) {
        daftarJadwal.add(jadwal);
    }

    public void tampilkanJadwal() {
        if (daftarJadwal.isEmpty()) {
            System.out.println("Belum ada jadwal.");
        } else {
            System.out.println("Jadwal Dokter " + getNama() + ":");
            for (Jadwal jadwal : daftarJadwal) {
                System.out.println("- " + jadwal);
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Jadwal> getDaftarJadwal() {
        return daftarJadwal;
    }
}
