# Prinsip SOLID di Aset-Pinjam

Dua prinsip yang ditunjukkan: **OCP** dan **LSP**. Keduanya terlihat di kode hasil kerja minggu 2-5.

## 1. Open/Closed Principle (OCP)

- **Kelas:** `AturanDenda` (interface), `DendaFlat`, `DendaProgresif`.
- **Bukti di kode:** `DendaFlat` dan `DendaProgresif` mengimplementasikan `AturanDenda`. Keduanya hanya
  memanggil `aset.hitungDenda(...)` lewat tipe `Aset`, tidak mengenal `Ruang`, `Laptop`, atau `AlatLab`.
- **Alasan:** aturan denda baru (misalnya denda khusus dosen) cukup dibuat sebagai kelas baru yang
  mengimplementasikan `AturanDenda`, tanpa mengubah `DendaFlat`, `DendaProgresif`, atau kelas lain.
  Jenis aset baru juga cukup berupa subclass `Aset` baru dengan `hitungDenda` sendiri. Kode lama yang
  sudah teruji tidak dibongkar, sehingga pengembangan lebih aman. Sistem terbuka untuk ditambah,
  tertutup untuk diubah.

## 2. Liskov Substitution Principle (LSP)

- **Kelas:** `Aset` (abstrak) dengan `Ruang`, `Laptop`, dan `AlatLab`.
- **Bukti di kode:** setiap subclass memanggil `super(kode, nama)`, sehingga validasi `Aset` tetap berlaku.
  `KatalogAset.cari(kata, jenis)`, `DendaFlat`, dan `DendaProgresif` bekerja untuk semua subclass lewat
  tipe `Aset` tanpa pemeriksaan `instanceof`.
- **Alasan:** subclass dapat menggantikan `Aset` tanpa mengubah perilaku yang diharapkan pemanggil.
  `bisaDipinjam()` dan validasi nama tetap sama, dan `hitungDenda` hanya berbeda pada multiplier
  (1x, 2x, 4x), bukan pada kontraknya (hari negatif tetap ditolak).

## Keterbatasan yang saya temukan (refleksi)

- `Aset.getJenis()` bukan abstrak dan melempar `UnsupportedOperationException`. Subclass baru yang lupa
  meng-override-nya baru gagal saat program dijalankan, bukan saat kompilasi. Ini bisa melemahkan LSP.
- `Ruang`, `Laptop`, dan `AlatLab` memanggil `Varian.aktif()` (singleton statis) langsung di
  `hitungDenda`. Mereka bergantung pada kelas konkret, sehingga sulit dites dengan tarif berbeda.
  Ini kebalikan dari Dependency Inversion Principle.
- Rumus `hari x multiplier x tarif` ditulis berulang di tiga subclass (duplikasi).
