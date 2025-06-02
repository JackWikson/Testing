public class Pasien extends Pengguna {
    private String prioritas;
    private Jadwal jadwal;

    public Pasien(String nama, String nik, String prioritas) {
        super(nama, nik);
        this.prioritas = prioritas;
    }

    public void setJadwal(Jadwal j) {
        this.jadwal = j;
    }

    public String getPrioritas() {
        return prioritas;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Pasien: " + nama + " | NIK: " + nik + " | Prioritas: " + prioritas);
    }
}
