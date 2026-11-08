package id.ac.upb.asetpinjam.denda;

import id.ac.upb.asetpinjam.kontrak.AturanDenda;
import id.ac.upb.asetpinjam.model.Aset;

/**
 * Minggu 5: denda yang makin berat bila makin lama terlambat.
 *
 * <p>Rumus: sampai hari ke-7 sama dengan flat. Setelah itu, setiap kelipatan
 * 7 hari di atas toleransi menambah satu kali denda flat.
 * progresif = flat + flat * (hari - 7) / 7, untuk hari > 7.
 */
public class DendaProgresif implements AturanDenda {

    private static final int HARI_TOLERANSI = 7;

    @Override
    public long hitung(Aset aset, int hariTerlambat) {
        if (hariTerlambat <= 0) {
            return 0;
        }
        long flat = aset.hitungDenda(hariTerlambat);
        if (hariTerlambat <= HARI_TOLERANSI) {
            return flat;
        }
        long tambahan = flat * (hariTerlambat - HARI_TOLERANSI) / HARI_TOLERANSI;
        return flat + tambahan;
    }
}