package id.ac.upb.asetpinjam.exception;

/** Minggu 9: jadwal peminjaman bentrok. Pesan menyebut kode aset yang bentrok. */
public class PeminjamanBentrokException extends AsetPinjamException {

    public PeminjamanBentrokException(String kodeAset) {
        super("Peminjaman bentrok: aset " + kodeAset + " sudah dipinjam pada rentang waktu tersebut");
    }
}