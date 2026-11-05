package id.ac.upb.asetpinjam.model;

import id.ac.upb.asetpinjam.config.Varian;

/** Minggu 3: aset berupa laptop. Spesifikasi dan nomor inventaris tidak boleh kosong. */
public class Laptop extends Aset {

    // TODO minggu 3: field spesifikasi dan nomorInventaris.
        private final String spesifikasi;
        private final String nomorInventaris;

    public Laptop(String kode, String nama, String spesifikasi, String nomorInventaris) {
        super(kode, nama);
        if (spesifikasi == null || spesifikasi.trim().isEmpty()) {
            throw new IllegalArgumentException("Spesifikasi tidak boleh kosong");
        }
        if (nomorInventaris == null || nomorInventaris.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor inventaris tidak boleh kosong");
        }
        this.spesifikasi = spesifikasi;
        this.nomorInventaris = nomorInventaris;
    }

    public String getSpesifikasi() {
        return spesifikasi;
    }

    public String getNomorInventaris() {
        return nomorInventaris;
    }
        @Override
    public JenisAset getJenis() {
        return JenisAset.LAPTOP;
    }
    

    // TODO minggu 3: override getJenis(). Minggu 4: override hitungDenda(int).
    @Override
    public long hitungDenda(int hariTerlambat) {
        if (hariTerlambat < 0) {
            throw new IllegalArgumentException("Hari terlambat tidak boleh negatif");
        }
        return (long) hariTerlambat * 2 * Varian.aktif().dendaPerHari;
    }

}
