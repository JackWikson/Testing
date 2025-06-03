public class Pasien extends Pengguna {
    private String nik;
    private String prioritas;
    private Jadwal jadwal;

    public Pasien(String nama, String nik, String prioritas) {
        super(nama, nik);
        this.nik = nik;
        this.prioritas = prioritas;
    }

    public String getNik() {
        return nik;
    }

    public String getPrioritas() {
        return prioritas;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public void setJadwal(Jadwal jadwal) {
        this.jadwal = jadwal;
    }
}