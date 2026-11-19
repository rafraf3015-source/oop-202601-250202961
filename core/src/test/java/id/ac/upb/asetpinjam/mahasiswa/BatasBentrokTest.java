package id.ac.upb.asetpinjam.mahasiswa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import id.ac.upb.asetpinjam.jadwal.JadwalPeminjaman;
import id.ac.upb.asetpinjam.model.Aset;
import id.ac.upb.asetpinjam.model.Peminjam;
import id.ac.upb.asetpinjam.model.Peminjaman;
import id.ac.upb.asetpinjam.model.Ruang;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * FOLDER INI MILIK ANDA. Tulis tes Anda sendiri di sini (minggu 7 dan seterusnya).
 *
 * <p>Tes dosen sengaja tidak menentukan: apakah peminjaman 09:00-11:00 dan 11:00-13:00 pada aset yang sama
 * bentrok? Putuskan, catat alasannya di docs/asumsi.md, lalu tuliskan tesnya di sini.
 * Hapus @Disabled setelah Anda mengisinya.
 *
 * <p>Syarat minggu 10: folder ini memuat minimal 8 tes (tidak termasuk contoh bawaan ini).
 */
class BatasBentrokTest {
        private static final Peminjam PEMINJAM = new Peminjam("P1", "Budi");
    private static final Aset RUANG_A = new Ruang("R1", "Ruang A", 30);
    private static final Aset RUANG_B = new Ruang("R2", "Ruang B", 30);

    private static LocalDateTime jam(int jam, int menit) {
        return LocalDateTime.of(2026, 11, 20, jam, menit);
    }

    private static Peminjaman pinjam(String id, Aset aset, LocalDateTime mulai, LocalDateTime selesai) {
        return new Peminjaman(id, PEMINJAM, aset, mulai, selesai);
    }

    private static JadwalPeminjaman jadwalDengan09sampai11() {
        JadwalPeminjaman jadwal = new JadwalPeminjaman(1);
        jadwal.tambah(pinjam("A", RUANG_A, jam(9, 0), jam(11, 0)));
        return jadwal;
    }

    @Test
    void selesaiSamaDenganMulaiBerikutnyaTidakBentrok() {
        assertFalse(jadwalDengan09sampai11().bentrok(pinjam("B", RUANG_A, jam(11, 0), jam(13, 0))));
    }

    @Test
    void mulaiSamaDenganSelesaiSebelumnyaTidakBentrok() {
        assertFalse(jadwalDengan09sampai11().bentrok(pinjam("B", RUANG_A, jam(7, 0), jam(9, 0))));
    }

    @Test
    void tumpangTindihSatuMenitBentrok() {
        assertTrue(jadwalDengan09sampai11().bentrok(pinjam("B", RUANG_A, jam(10, 59), jam(12, 0))));
    }

    @Test
    void rentangTerkandungBentrok() {
        assertTrue(jadwalDengan09sampai11().bentrok(pinjam("B", RUANG_A, jam(9, 30), jam(10, 0))));
    }

    @Test
    void rentangMenelanRentangLamaBentrok() {
        assertTrue(jadwalDengan09sampai11().bentrok(pinjam("B", RUANG_A, jam(8, 0), jam(12, 0))));
    }

    @Test
    void asetBerbedaTidakPernahBentrok() {
        assertFalse(jadwalDengan09sampai11().bentrok(pinjam("B", RUANG_B, jam(9, 0), jam(11, 0))));
    }
}


