public class Jadwal {
    private String waktu;
    private String ruangan;
    private String dokter;
    private String nomorTelepon;
    private boolean terpesan = false;

    public Jadwal(String waktu, String ruangan, String dokter, String nomorTelepon) {
        this.waktu = waktu;
        this.ruangan = ruangan;
        this.dokter = dokter;
        this.nomorTelepon = nomorTelepon;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getRuangan() {
        return ruangan;
    }

    public String getDokter() {
        return dokter;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public boolean isTerpesan() {
        return terpesan;
    }

    public void setTerpesan(boolean terpesan) {
        this.terpesan = terpesan;
    }

    @Override
    public String toString() {
        return waktu + " | " + ruangan + " | " + dokter + " | Telp: " + nomorTelepon;
    }
}
