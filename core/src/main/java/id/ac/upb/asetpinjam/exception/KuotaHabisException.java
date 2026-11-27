package id.ac.upb.asetpinjam.exception;

/** Minggu 9: kuota peminjaman peminjam sudah habis. Pesan menyebut peminjam dan kuotanya. */
public class KuotaHabisException extends AsetPinjamException {

    public KuotaHabisException(String idPeminjam, int kuota) {
        super("Kuota habis: peminjam " + idPeminjam + " sudah memegang " + kuota
                + " laptop aktif (kuota " + kuota + ")");
    }
}