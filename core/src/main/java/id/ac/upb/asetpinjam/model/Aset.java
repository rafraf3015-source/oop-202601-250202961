package id.ac.upb.asetpinjam.model;

import id.ac.upb.asetpinjam.kontrak.DapatDipinjam;

/**
 * Aset yang dapat dipinjam.
 *
 * <p>Kelas abstrak: tidak bisa diinstansiasi langsung, harus lewat subclass
 * (Ruang, dll.) yang masing-masing mengisi hitungDenda().
 */
public abstract class Aset implements DapatDipinjam {

    private final String kode;
    private String nama;
    private StatusAset status = StatusAset.TERSEDIA;
    private Kondisi kondisi = Kondisi.BAIK;

    /**
     * Membuat aset baru. Kode dan nama tidak boleh null, kosong, atau hanya spasi.
     *
     * @throws IllegalArgumentException jika kode atau nama tidak valid
     */
    public Aset(String kode, String nama) {
        validasiTeks(kode, "Kode");
        validasiTeks(nama, "Nama");
        this.kode = kode;
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public StatusAset getStatus() {
        return status;
    }

    public Kondisi getKondisi() {
        return kondisi;
    }

    /** Nama baru tidak boleh null, kosong, atau hanya spasi. */
    public void setNama(String nama) {
        validasiTeks(nama, "Nama");
        this.nama = nama;
    }

    /** Status tidak boleh null. */
    public void setStatus(StatusAset status) {
        if (status == null) {
            throw new IllegalArgumentException("Status tidak boleh null");
        }
        this.status = status;
    }

    /** Kondisi tidak boleh null. */
    public void setKondisi(Kondisi kondisi) {
        if (kondisi == null) {
            throw new IllegalArgumentException("Kondisi tidak boleh null");
        }
        this.kondisi = kondisi;
    }

    private static void validasiTeks(String nilai, String namaField) {
        if (nilai == null || nilai.isBlank()) {
            throw new IllegalArgumentException(namaField + " tidak boleh null atau kosong");
        }
    }

    /** True hanya jika status TERSEDIA dan kondisi bukan RUSAK_BERAT. */
    @Override
    public boolean bisaDipinjam() {
        return status == StatusAset.TERSEDIA
            && kondisi != Kondisi.RUSAK_BERAT;
    }

    /** TODO minggu 3: dioverride tiap subclass agar mengembalikan jenis yang benar. */
    public JenisAset getJenis() {
        throw new UnsupportedOperationException("TODO minggu 3");
    }

    /** Denda keterlambatan. Wajib diimplementasikan tiap subclass (aturan di docs/aturan-bisnis.md). */
    public abstract long hitungDenda(int hariTerlambat);
}