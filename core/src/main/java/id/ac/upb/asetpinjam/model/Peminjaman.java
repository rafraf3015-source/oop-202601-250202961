package id.ac.upb.asetpinjam.model;

import static id.ac.upb.asetpinjam.model.StatusPeminjaman.DIAJUKAN;
import static id.ac.upb.asetpinjam.model.StatusPeminjaman.DIBATALKAN;
import static id.ac.upb.asetpinjam.model.StatusPeminjaman.DIKEMBALIKAN;
import static id.ac.upb.asetpinjam.model.StatusPeminjaman.DIPINJAM;
import static id.ac.upb.asetpinjam.model.StatusPeminjaman.DISETUJUI;
import static id.ac.upb.asetpinjam.model.StatusPeminjaman.DITOLAK;
import static id.ac.upb.asetpinjam.model.StatusPeminjaman.TERLAMBAT;

import id.ac.upb.asetpinjam.exception.StatusTidakSahException;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * Satu pengajuan peminjaman aset oleh seorang peminjam pada rentang waktu tertentu.
 * Konstruktor dan getter DISEDIAKAN dosen. Anda mengerjakan ubahStatus (minggu 9).
 */
public class Peminjaman {

    /** Perpindahan status yang sah (docs/aturan-bisnis.md). Status akhir punya himpunan kosong. */
    private static final Map<StatusPeminjaman, Set<StatusPeminjaman>> PERPINDAHAN_SAH =
            new EnumMap<>(StatusPeminjaman.class);

    static {
        PERPINDAHAN_SAH.put(DIAJUKAN, EnumSet.of(DISETUJUI, DITOLAK));
        PERPINDAHAN_SAH.put(DISETUJUI, EnumSet.of(DIPINJAM, DIBATALKAN));
        PERPINDAHAN_SAH.put(DIPINJAM, EnumSet.of(DIKEMBALIKAN, TERLAMBAT));
        PERPINDAHAN_SAH.put(TERLAMBAT, EnumSet.of(DIKEMBALIKAN));
        PERPINDAHAN_SAH.put(DITOLAK, EnumSet.noneOf(StatusPeminjaman.class));
        PERPINDAHAN_SAH.put(DIBATALKAN, EnumSet.noneOf(StatusPeminjaman.class));
        PERPINDAHAN_SAH.put(DIKEMBALIKAN, EnumSet.noneOf(StatusPeminjaman.class));
    }

    private final String id;
    private final Peminjam peminjam;
    private final Aset aset;
    private final LocalDateTime mulai;
    private final LocalDateTime selesai;
    private StatusPeminjaman status = StatusPeminjaman.DIAJUKAN;

    public Peminjaman(String id, Peminjam peminjam, Aset aset, LocalDateTime mulai, LocalDateTime selesai) {
        if (id == null || id.isBlank() || peminjam == null || aset == null || mulai == null || selesai == null) {
            throw new IllegalArgumentException("semua data peminjaman wajib diisi");
        }
        if (!selesai.isAfter(mulai)) {
            throw new IllegalArgumentException("waktu selesai harus setelah waktu mulai");
        }
        this.id = id;
        this.peminjam = peminjam;
        this.aset = aset;
        this.mulai = mulai;
        this.selesai = selesai;
    }

    public String getId() {
        return id;
    }

    public Peminjam getPeminjam() {
        return peminjam;
    }

    public Aset getAset() {
        return aset;
    }

    public LocalDateTime getMulai() {
        return mulai;
    }

    public LocalDateTime getSelesai() {
        return selesai;
    }

    public StatusPeminjaman getStatus() {
        return status;
    }

    /** Dipakai DAO saat memuat data dari database. Tidak memvalidasi perpindahan. */
    public void setStatusDariPenyimpanan(StatusPeminjaman status) {
        this.status = status;
    }

    /**
     * Pindah ke status baru hanya jika perpindahan sah menurut docs/aturan-bisnis.md.
     * Jika tidak sah, melempar StatusTidakSahException dan status TIDAK berubah.
     */
    public void ubahStatus(StatusPeminjaman baru) {
        if (baru == null) {
            throw new IllegalArgumentException("status baru tidak boleh null");
        }
        if (!PERPINDAHAN_SAH.get(status).contains(baru)) {
            throw new StatusTidakSahException(status, baru);
        }
        this.status = baru;
    }
}