# Catatan Minggu 01: Paradigma Pemrograman dan Setup

## Yang saya kerjakan
- Melengkapi HelloProsedural.sapa, HelloOOP, dan HelloFungsional.pembuatSapaan.
- Menjalankan kode dan menelusuri alur eksekusi tiap versi.
- Perbandingan untuk sistem peminjaman aset:
  - Prosedural:
    - Kelebihan: cocok untuk logika kecil yang berdiri sendiri, misalnya
      menghitung selisih hari keterlambatan.
    - Keterbatasan: makin banyak data aset, makin panjang daftar parameter
      dan makin mudah tertukar urutannya.
  - OOP:
    - Kelebihan: data dan perilaku yang berkaitan dibungkus bersama, misalnya
      aset dengan status dan method pinjam/kembalikan.
    - Keterbatasan: kode lebih banyak untuk hal sederhana.
  - Fungsional:
    - Kelebihan: aturan seperti denda bisa dibuat sebagai fungsi yang tidak
      mengubah data di luarnya, sehingga mudah diuji dan hasilnya selalu sama
      untuk input yang sama.
    - Keterbatasan: kurang natural untuk menyimpan status yang terus berubah,
      dan lebih sulit dipahami.

## Keputusan desain
Kalimat sapaan yang saya pakai di ketiga gaya:
`"Halo, <nama>, Selamat datang di Aset-Pinjam cuy. NIM Anda: <nim>"`.
Saya memilih kalimat ini karena terasa lebih personal dan sesuai gaya bicara
saya. Kalimat ini tetap memuat "Aset-Pinjam", nama, dan NIM sesuai dengan
aturan di docs/minggu-01.md.

## Kendala
Yang sulit minggu ini adalah memahami perbedaan kode dari ketiga versi hello.
Saya mengatasinya dengan memahami cara kerja code dari ai dan bagaimana paradigma bekerja

## Penggunaan AI
- Alat yang dipakai: Claude.
- Bagian yang dibantu AI: HelloProsedural.sapa, HelloOOP (field, konstruktor,
  sapa), HelloFungsional.pembuatSapaan, penjelasan konsepnya, serta bahan
  perbandingan kelebihan dan keterbatasan tiap gaya.
- Prompt utama:
  1. "bagaimana cara saya mengerjakan file nya"
  2. "gimana cara run nya, ajari saya sampai paham"
  3. "coba full jadinya"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: Saya tidak menolak
  saran AI, tetapi saya mengubah kalimat sapaan bawaan dari AI menjadi kalimat
  saya sendiri agar lebih personal.
- Satu hal yang saya pahami lebih baik sekarang: paradigma adalah cara berpikir
  dalam menyusun program, dan masalah yang terlihat sama bisa diselesaikan
  dengan tiga cara yang berbeda.