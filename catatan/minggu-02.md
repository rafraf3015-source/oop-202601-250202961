# Catatan Minggu 02: Struktur OOP: Class, Object, Encapsulation
Salin berkas ini menjadi `catatan/minggu-NN.md` (NN dua digit, misalnya `minggu-07.md`).

## Yang saya kerjakan
Melengkapi class `Aset` dengan field private, konstruktor, getter, dan setter yang memvalidasi input. Sebagian besar kode dan perapiannya dibantu AI, lalu saya jalankan `uji.sh` untuk memastikan tes minggu 2 lolos.

## Keputusan desain
Kode dibuat final tanpa setter karena identitas aset tidak boleh berubah. Validasi memakai `isBlank()` supaya input yang hanya spasi ditolak. Kode tidak dinormalisasi karena tidak diminta di aturan minggu ini.

## Kendala
Awalnya saya belum paham cara memvalidasi di konstruktor dan setter sekaligus tanpa menulis kode yang sama dua kali.

## Penggunaan AI
- Alat yang dipakai: Claude (Anthropic)
- Bagian yang dibantu AI: review class Aset dan perapian kodenya (validasi, duplikasi, format)
Prompt utama:
1."apa yang kukerjakan benar apakah semua nya sudah bagus, apakah ada yang perlu diperbaiki"
2."coba yang bener gimana"
3."ajari saya memahami codingan ini"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: saya menerima semua saran dan memahami saran dari ai
- Satu hal yang saya pahami lebih baik sekarang: field dibuat private dan diakses lewat getter/setter supaya validasi tidak bisa dilewati dari luar class.