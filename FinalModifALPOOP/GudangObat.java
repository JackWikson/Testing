import java.util.ArrayList;
import java.util.Random;

public class GudangObat {
    private ArrayList<Obat> stok;
    private ArrayList<String> riwayatPemakaian;

    public GudangObat() {
        stok = new ArrayList<>();
        riwayatPemakaian = new ArrayList<>();
    }

    public void tambahObat(Obat obat) {
        stok.add(obat);
    }

    public void tampilkanStok() {
        System.out.println("=== Stok Obat ===");
        for (Obat obat : stok) {
            System.out.println(obat.getNama() + " - " + obat.getJumlah() + " buah");
        }

        System.out.println("\n=== Obat yang Dipakai ===");
        if (riwayatPemakaian.isEmpty()) {
            System.out.println("Belum ada obat yang digunakan.");
        } else {
            for (String log : riwayatPemakaian) {
                System.out.println(log);
            }
        }
    }

    public Obat kurangiStokAcak(String namaDokter) {
        if (stok.isEmpty()) return null;

        Random rand = new Random();
        int index = rand.nextInt(stok.size());
        Obat obat = stok.get(index);

        if (obat.getJumlah() > 0) {
            obat.setJumlah(obat.getJumlah() - 1);
            riwayatPemakaian.add("Obat " + obat.getNama() + " dipakai oleh " + namaDokter);
            return obat;
        }

        return null;
    }
}
