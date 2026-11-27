package id.ac.upb.asetpinjam.jadwal;

import id.ac.upb.asetpinjam.exception.KuotaHabisException;
import id.ac.upb.asetpinjam.exception.PeminjamanBentrokException;
import id.ac.upb.asetpinjam.model.JenisAset;
import id.ac.upb.asetpinjam.model.Peminjaman;
import id.ac.upb.asetpinjam.model.StatusPeminjaman;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Jadwal peminjaman di memori.
 *
 * <p>Minggu 7: bentrok, semuaUrutMulai, perAset, hapus. Minggu 9: ajukan (melempar exception).
 * tambah() DISEDIAKAN dan sengaja TIDAK memeriksa apa pun.
 */
public class JadwalPeminjaman {

    private final List<Peminjaman> daftar = new ArrayList<>();
    private final int kuotaLaptop;

    public JadwalPeminjaman(int kuotaLaptop) {
        this.kuotaLaptop = kuotaLaptop;
    }

    protected int kuotaLaptop() {
        return kuotaLaptop;
    }

    protected List<Peminjaman> daftarMutable() {
        return daftar;
    }

    /** Menambah apa adanya tanpa pemeriksaan. */
    public void tambah(Peminjaman p) {
        daftar.add(p);
    }

    /**
     * True jika {@code baru} bentrok dengan peminjaman yang sudah ada untuk ASET YANG SAMA.
     *
     * <p>Batas waktu: rentang setengah terbuka [mulai, selesai), jadi 09:00-11:00 dan 11:00-13:00
     * pada aset yang sama TIDAK bentrok. Peminjaman berstatus DITOLAK atau DIBATALKAN tidak
     * menempati jadwal (lihat docs/asumsi.md).
     */
    public boolean bentrok(Peminjaman baru) {
        for (Peminjaman ada : daftar) {
            boolean asetSama = ada.getAset().getKode().equals(baru.getAset().getKode());
            boolean tumpangTindih = baru.getMulai().isBefore(ada.getSelesai())
                    && ada.getMulai().isBefore(baru.getSelesai());
            if (asetSama && menempatiJadwal(ada) && tumpangTindih) {
                return true;
            }
        }
        return false;
    }

    /** DITOLAK dan DIBATALKAN melepaskan jadwal; status lain masih menempati. */
    private static boolean menempatiJadwal(Peminjaman p) {
        StatusPeminjaman s = p.getStatus();
        return s != StatusPeminjaman.DITOLAK && s != StatusPeminjaman.DIBATALKAN;
    }

    /** Semua peminjaman, diurutkan menaik menurut waktu mulai. Mengembalikan salinan baru. */
    public List<Peminjaman> semuaUrutMulai() {
        List<Peminjaman> hasil = new ArrayList<>(daftar);
        hasil.sort(Comparator.comparing(Peminjaman::getMulai));
        return hasil;
    }

    /** Peminjaman dikelompokkan menurut kode aset. */
    public Map<String, List<Peminjaman>> perAset() {
        Map<String, List<Peminjaman>> hasil = new LinkedHashMap<>();
        for (Peminjaman p : daftar) {
            hasil.computeIfAbsent(p.getAset().getKode(), k -> new ArrayList<>()).add(p);
        }
        return hasil;
    }

    /** Hapus peminjaman berdasarkan id. True jika ada yang terhapus. */
    public boolean hapus(String id) {
        return daftar.removeIf(p -> p.getId().equals(id));
    }

    /**
     * Periksa lalu tambahkan. Semua pemeriksaan dilakukan SEBELUM daftar diubah, sehingga jika
     * exception dilempar jadwal tetap seperti semula.
     *
     * @throws PeminjamanBentrokException jika bentrok dengan peminjaman lain pada aset yang sama
     * @throws KuotaHabisException jika peminjam sudah memegang {@link #kuotaLaptop()} laptop aktif
     *     (status DISETUJUI atau DIPINJAM) dan mengajukan laptop lagi
     */
    public void ajukan(Peminjaman baru) {
        if (baru == null) {
            throw new IllegalArgumentException("peminjaman tidak boleh null");
        }
        if (bentrok(baru)) {
            throw new PeminjamanBentrokException(baru.getAset().getKode());
        }
        if (baru.getAset().getJenis() == JenisAset.LAPTOP) {
            String idPeminjam = baru.getPeminjam().id();
            long laptopAktif = daftar.stream()
                    .filter(p -> p.getPeminjam().id().equals(idPeminjam))
                    .filter(p -> p.getAset().getJenis() == JenisAset.LAPTOP)
                    .filter(p -> p.getStatus() == StatusPeminjaman.DISETUJUI
                            || p.getStatus() == StatusPeminjaman.DIPINJAM)
                    .count();
            if (laptopAktif >= kuotaLaptop) {
                throw new KuotaHabisException(idPeminjam, kuotaLaptop);
            }
        }
        daftar.add(baru);
    }
}