import java.util.ArrayList;

public class GudangObat {
    private ArrayList<Obat> daftarObat;

    public GudangObat() {
        daftarObat = new ArrayList<>();
    }

    public void tambahObat(Obat obat) {
        daftarObat.add(obat);
    }

    public void tampilkanStok() {
        if (daftarObat.isEmpty()) {
            System.out.println("Tidak ada stok obat.");
        } else {
            for (Obat o : daftarObat) {
                System.out.println(o);
            }
        }
    }
}
