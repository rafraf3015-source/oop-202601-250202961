# Catatan Minggu 05: Abstraction

## Yang saya kerjakan
Saya mengubah `Aset` menjadi abstract class dan mengisi `bisaDipinjam()` supaya
hanya bernilai true jika status TERSEDIA dan kondisi bukan RUSAK_BERAT. Method
`hitungDenda` saya jadikan abstract agar tiap subclass wajib mengisinya sendiri.
Lalu saya mengimplementasikan interface `AturanDenda` di dua kelas: `DendaFlat`
yang memakai `aset.hitungDenda(hari)`, dan `DendaProgresif` dengan rumus sendiri.

## Keputusan desain
- `Aset` dibuat abstract class karena semua aset berbagi data (kode, nama, status,
  kondisi) dan perilaku (`bisaDipinjam`), tetapi "aset umum" tidak masuk akal
  dibuat langsung. `DapatDipinjam` dan `AturanDenda` berupa interface karena hanya
  kontrak perilaku, dan `AturanDenda` bisa ditukar-tukar (DendaFlat atau DendaProgresif).
- Rumus `DendaProgresif`: sampai hari ke-7 sama dengan flat. Setelah itu,
  denda = flat + flat * (hari - 7) / 7. Alasannya sederhana dan memenuhi syarat
  tes (0 hari = 0, tidak lebih murah dari flat, tidak turun, hari ke-30 lebih
  mahal dari flat). Detail ada di docs/asumsi.md.

## Kendala
Saya sempat menempelkan potongan kode ke dalam `Aset.java` tanpa menghapus
bagian lama, sehingga ada dua deklarasi class dan kompilasi gagal. Saya
mengatasinya dengan mengganti seluruh isi file, lalu memeriksa bahwa hanya ada
satu deklarasi class. (Tambahkan kendala lain yang Anda alami sendiri, misalnya
subclass yang belum meng-override hitungDenda.)

## Penggunaan AI
- Alat yang dipakai: Claude
- Bagian yang dibantu AI: `Aset.bisaDipinjam()`, `Aset.hitungDenda` (abstract),
  `DendaFlat.hitung`, `DendaProgresif.hitung`
  1. "bantu saya mengerjakan week 5" (disertai isi docs/minggu-05.md)
  2. "gimana isi nya jelaskan materi nya apa yang di ajari dan tolong kerjakan"
  
- Yang saya ubah atau tolak dari saran AI, dan alasannya: (ISI SENDIRI. Contoh:
  "Saya memakai rumus progresif versi kedua karena hanya memanggil hitungDenda
  sekali, jadi lebih mudah saya pahami." Jangan tulis sesuatu yang tidak terjadi.)
- Satu hal yang saya pahami lebih baik sekarang: "Abstract
  class dipakai untuk keluarga kelas yang berbagi data dan perilaku, sedangkan
  interface untuk kontrak kemampuan yang bisa ditukar.