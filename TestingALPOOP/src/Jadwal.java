public class Jadwal {
    private String waktu;
    private String ruangan;

    public Jadwal(String waktu, String ruangan) {
        this.waktu = waktu;
        this.ruangan = ruangan;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getRuangan() {
        return ruangan;
    }

    @Override
    public String toString() {
        return waktu + " - " + ruangan;
    }
}
