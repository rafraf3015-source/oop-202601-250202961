# Catatan Minggu 09: Exception Handling

## Yang saya kerjakan
Saya mengubah tiga exception domain (PeminjamanBentrokException, KuotaHabisException,
StatusTidakSahException) agar pesannya jelas, mengisi Peminjaman.ubahStatus dengan tabel perpindahan
status yang sah, dan mengisi JadwalPeminjaman.ajukan. Saya juga menulis UbahStatusTest dan AjukanTest.

## Keputusan desain
- DIAJUKAN tidak dihitung dalam kuota, karena aturan pasti hanya menghitung DISETUJUI dan DIPINJAM.
- DITOLAK dan DIBATALKAN tidak menempati jadwal, karena aset tidak akan dipakai. Status lain menempati.
  Karena itu bentrok() minggu 7 saya tambah penyaring status.
- Pemeriksaan dilakukan sebelum objek diubah: ajukan() memeriksa bentrok dan kuota sebelum add, dan
  ubahStatus() memeriksa tabel sebelum mengganti status. Dengan begitu keadaan objek tetap konsisten
  saat terjadi kegagalan.
- Perpindahan status disimpan sebagai Map dari status asal ke himpunan status tujuan, bukan deretan if,
  supaya aturannya terbaca seperti tabel di docs/aturan-bisnis.md.
- Keputusan lengkap ada di docs/asumsi.md.

## Kendala
memahami mengapa pemeriksaan harus sebelum perubahan.

## Penggunaan AI
- Alat yang dipakai: Claude (Anthropic)
- Bagian yang dibantu AI: tiga exception, Peminjaman.ubahStatus, JadwalPeminjaman.ajukan dan penyaring status di bentrok, UbahStatusTest, AjukanTest, serta penjelasan materi exception handling.
Prompt utama:
1. meminta dibantu mengerjakan.
2. menanyakan bagian mana yang diubah.
3. Saya meminta diajari materinya dan penjelasan tiap perubahan kode.
- Yang saya ubah atau tolak dari saran AI, dan alasannya: tidak ada
- Satu hal yang saya pahami lebih baik sekarang: exception harus dilempar sebelum objek diubah supaya keadaan tetap konsisten, atau mengapa exception domain membawa data di konstruktornya.