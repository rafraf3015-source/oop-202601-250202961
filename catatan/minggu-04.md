# Catatan Minggu 04: (Polymorphism)

Salin berkas ini menjadi `catatan/minggu-NN.md` (NN dua digit, misalnya `minggu-07.md`).

## Yang saya kerjakan
Minggu ini saya mengisi method `hitungDenda` di tiga class, yaitu Ruang, Laptop, dan AlatLab. Bedanya cuma angka pengalinya: Ruang 1, Laptop 2, AlatLab 4. Saya juga mengisi dua method `cari` di KatalogAset, satu untuk cari berdasarkan nama, satu lagi cari berdasarkan nama dan jenis. Setelah dijalankan, semua tes minggu 1 sampai 4 lulus.


## Keputusan desain
Tidak ada keputusan sendiri karena aturan minggu ini sudah pasti. Untuk `cari(kata, jenis)` saya pakai `cari(kata)` di dalamnya, supaya tidak menulis kode pencarian dua kali.

## Kendala
Awalnya saya bingung harus mengisi apa dan apa bedanya overriding dengan overloading. Saya juga sempat menjalankan `uji.ps1` tanpa angka, jadi yang jalan cuma tes minggu 1. Setelah pakai `.\scripts\uji.ps1 4`, tes minggu 4 baru ikut jalan. Saya masih belajar, jadi beberapa bagian saya perlu baca ulang supaya paham.

## Penggunaan AI
- Alat yang dipakai: Claude
- Bagian yang dibantu AI: Penjelasan konsep overriding, overloading, dan dynamic binding. AI juga memberi contoh kode untuk `hitungDenda` (Ruang, Laptop, AlatLab) dan `cari` (KatalogAset), lalu saya sesuaikan dengan kode proyek saya.
- Prompt utama:
  1. "apa yang perlu di kerjakan berikan saya materi dan pengejaran nya cara memahami code"
  2. "gimana gimana gak paham aku"
  3. "coba isikan"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: Jawaban awal AI salah tebak (tipe denda dikira `int`, `Aset` dikira abstract) karena belum melihat kode saya. Setelah saya tempel kode asli, jawabannya diperbaiki.
- Satu hal yang saya pahami lebih baik sekarang: mengerti perngertian Overriding, Dynamic binding dan perbedaan Overriding,Overloading