# Asumsi dan Keputusan Desain

Isi bagian ini sendiri. Satu entri per keputusan. Dinilai dari kejelasan alasan dan konsistensi dengan kode dan tes Anda,
bukan dari jawaban "benar".

Format tiap entri:

```
## A-NN: judul singkat
- Pertanyaan: ...
- Keputusan: ...
- Alasan: ...
- Dampak ke kode: ... (kelas/metode)
- Tes yang membuktikan: ... (nama tes di folder mahasiswa/)
```

## Aturan ambigu dari docs/aturan-bisnis.md

Salin setiap pertanyaan ambigu (nomor 1 sampai 7) ke sini dan jawab. Contoh:

## A-01: batas waktu bentrok
- Pertanyaan: 09:00-11:00 dan 11:00-13:00 pada aset yang sama bentrok atau tidak?
- Keputusan: (isi)
- Alasan: (isi)
- Dampak ke kode: (isi)
- Tes yang membuktikan: (isi)

## Asumsi lain

Tambahkan di sini keputusan lain yang Anda ambil: rumus denda progresif, perubahan skema database, format error API, dan seterusnya.

## Minggu 5: Rumus DendaProgresif
- Hari 1 sampai 7: sama dengan denda flat.
- Setelah hari ke-7: denda = flat + flat * (hari - 7) / 7.
- Alasan: sederhana, memenuhi semua syarat tes (0 hari = 0, tidak lebih murah dari flat,
  tidak turun, hari ke-30 lebih mahal dari flat), dan hanya bergantung pada hitungDenda().

  ## Minggu 9: Exception handling

- Kuota laptop: hanya peminjaman berstatus DISETUJUI atau DIPINJAM milik peminjam yang sama
  yang dihitung. DIAJUKAN tidak dihitung (sesuai aturan pasti). Akibatnya satu peminjam bisa punya
  beberapa pengajuan laptop yang masih menunggu, tetapi tidak bisa mengajukan lagi bila sudah
  memegang laptop aktif sebanyak kuota.
- Jadwal: peminjaman berstatus DITOLAK atau DIBATALKAN tidak menempati jadwal, karena aset sudah
  tidak akan dipakai. Status lain (termasuk DIAJUKAN) menempati jadwal supaya dua pengajuan
  pada waktu yang sama tidak sama-sama lolos.
- Konsistensi: ajukan() memeriksa bentrok dan kuota SEBELUM menambah ke daftar, sehingga jika
  exception dilempar jadwal tidak berubah. ubahStatus() memeriksa perpindahan sebelum
  mengubah status, sehingga status tetap bila perpindahan tidak sah.
- ubahStatus(null) melempar IllegalArgumentException karena itu kesalahan pemrograman,
  bukan perpindahan status yang tidak sah.