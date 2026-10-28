# Catatan Minggu 03: Inheritance

## Yang saya kerjakan
Saya melengkapi kelas Ruang, Laptop, dan AlatLab yang mewarisi Aset. Tiap kelas
saya beri field khusus, konstruktor yang memanggil super(kode, nama) lalu
memeriksa aturannya sendiri, getter, dan getJenis().

## Keputusan desain
Field kode dan nama ada di Aset karena dimiliki semua jenis aset. Field seperti
kapasitas, spesifikasi, dan laboratorium ada di subclass karena hanya relevan
untuk satu jenis.

Contoh inheritance yang kurang tepat: kalau laptop berubah status jadi "rusak"
atau "dalam perawatan", kita tidak bisa membuat objek Laptop berganti menjadi
kelas lain saat program berjalan, karena kelas objek tetap sejak dibuat. Lebih
cocok memakai komposisi, yaitu Laptop punya field status yang bisa diganti
kapan saja.

## Kendala
memahami cara mengerjakan, menyusun codingan dan belajar apa itu Inheritance

## Penggunaan AI
- Alat: Claude
- Bagian yang dibantu: Ruang, Laptop, AlatLab
- Prompt utama:menempelkan kerangka Ruang, Laptop, AlatLab, bagaimana codingan bekerja, apa itu artinya dan bagaimana cara mengerjakanya
- Yang saya ubah/tolak: tidak ada
- Satu hal yang saya pahami lebih baik sekarang: apa itu Inheritance dan contoh codingan nya
