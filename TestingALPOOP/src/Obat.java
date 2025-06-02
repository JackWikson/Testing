public class Obat {
    private String nama;
    private int stok;
    private int harga;

    public Obat(String nama, int stok, int harga) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getStok() {
        return stok;
    }

    public int getHarga() {
        return harga;
    }

    public void kurangiStok(int jumlah) {
        this.stok -= jumlah;
    }

    @Override
    public String toString() {
        return nama + " - Stok: " + stok + " - Harga: Rp" + harga;
    }
}
