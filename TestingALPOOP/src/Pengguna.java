public abstract class Pengguna {
    protected String nama;
    protected String nik;

    public Pengguna(String nama, String nik) {
        this.nama = nama;
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    public abstract void tampilkanInfo();
}
