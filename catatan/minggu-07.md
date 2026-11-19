# Catatan Minggu 07: Koleksi


## Yang saya kerjakan
Saya mengisi empat method di JadwalPeminjaman: bentrok, semuaUrutMulai, perAset, dan hapus. Saya juga
menulis BatasBentrokTest untuk keputusan batas waktu dan JadwalKoleksiTest untuk menguji semuaUrutMulai,
perAset, dan hapus.

## Keputusan desain
- Batas waktu: rentang dianggap setengah terbuka [mulai, selesai). Peminjaman 09:00-11:00 dan 11:00-13:00
  pada aset yang sama TIDAK bentrok, karena aset sudah dikembalikan pukul 11:00 sehingga bisa langsung
  dipakai peminjam berikutnya. Keputusan ini juga dicatat di docs/asumsi.md.
- Struktur data internal: tetap satu List. perAset() membangun Map saat dipanggil, tidak disimpan.
  Alasannya tambah() dari dosen langsung memasukkan ke List dan daftarMutable() membuka List ke subclass,
  sehingga Map yang disimpan bisa tidak sinkron dengan List. Datanya kecil, jadi pencarian linear cukup.
- semuaUrutMulai() mengurutkan salinan, bukan daftar asli, supaya urutan masuk tidak berubah.
- Status peminjaman belum dipakai untuk menyaring bentrok. Itu urusan minggu 9.

## Kendala
membedakan isBefore dan isAfter dibatas waktu, memahami rumus bentrok, atau memahami computeIfAbsent.

## Penggunaan AI
- Alat yang dipakai: Claude (Anthropic)
- Bagian yang dibantu AI: JadwalPeminjaman (bentrok, semuaUrutMulai, perAset, hapus), BatasBentrokTest,
  JadwalKoleksiTest, dan penjelasan materi List, Set, Map, Comparator, serta lambda.
- Prompt utama:
  1. Saya menempelkan spesifikasi minggu 7 dan meminta dibantu mengerjakan.
  2. Saya menempelkan stub BatasBentrokTest dan menanyakan apa saja yang harus dikerjakan.
  3. Saya meminta diajari semua materinya dari nol, termasuk penjelasan tiap fungsi kode.
- Yang saya ubah atau tolak dari saran AI, dan alasannya: tidak ada
- Satu hal yang saya pahami lebih baik sekarang: mengapa batas waktu memakai
  rentang setengah terbuka, atau mengapa rumus bentrok memakai dua pembanding.