package id.ac.upb.asetpinjam.exception;

import id.ac.upb.asetpinjam.model.StatusPeminjaman;

/** Minggu 9: perpindahan status peminjaman tidak sah. Pesan menyebut status asal dan tujuan. */
public class StatusTidakSahException extends AsetPinjamException {

    public StatusTidakSahException(StatusPeminjaman asal, StatusPeminjaman tujuan) {
        super("Perpindahan status tidak sah: " + asal + " -> " + tujuan);
    }
}