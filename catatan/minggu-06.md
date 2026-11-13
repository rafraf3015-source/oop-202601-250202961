# Catatan Minggu 06: UML dan SOLID

## Yang saya kerjakan
Saya membuat Class Diagram dari kode minggu 1-5 (Aset dan subclass, KatalogAset, aturan denda) ditambah
tiga kelas kerangka (Peminjam, Peminjaman, JadwalPeminjaman). Saya juga membuat Sequence Diagram
pengajuan peminjaman dengan skenario sukses dan bentrok memakai fragmen alt. Terakhir, saya menulis
penjelasan prinsip OCP dan LSP di solid.md lengkap dengan kelas dan alasannya.

## Keputusan desain
- Class Diagram sengaja diringkas ke inti domain. DAO, exception, AsetFactory, dan LayananDenda tidak
  digambar karena masih kerangka untuk minggu 7-11, dan semuanya disebut di catatan bawah diagram.
- Saya memilih OCP dan LSP karena buktinya ada di kode yang sudah saya kerjakan (AturanDenda dengan
  DendaFlat/DendaProgresif, dan Aset dengan Ruang/Laptop/AlatLab).
- Sequence Diagram mengikuti kontrak Javadoc JadwalPeminjaman.ajukan karena bentrok() dan ajukan()
  masih TODO (minggu 7 dan 9). Diagram akan dicocokkan ulang setelah method itu saya isi.

## Kendala
 sulit membedakan aggregation dan composition, dan
 memasukkan diagram Mermaid ke draw.io

## Penggunaan AI
- Alat yang dipakai: Claude (Anthropic)
- Bagian yang dibantu AI: draf Class Diagram, Sequence Diagram, dan solid.md (Mermaid), serta penjelasan
  konsep UML dan SOLID.
- Prompt utama:
  1. Saya menempelkan spesifikasi tugas minggu 6 dan meminta dibantu mengerjakan serta diajari materinya.
  2. Saya menempelkan kode Aset, Ruang, Laptop, AlatLab, KatalogAset, aturan denda, JadwalPeminjaman,
     Peminjaman, dan DAO, lalu meminta diagram yang sesuai dengan kode.
  3. Saya meminta diagram diringkas dan hanya memuat kelas yang sudah saya kerjakan.
- Yang saya ubah atau tolak dari saran AI, dan alasannya: saya meminta versi ringkas karena versi awal
  terlalu banyak kelas kerangka yang belum saya kerjakan. Bukti DIP dari kelas DAO saya ganti dengan
  LSP karena DAO adalah kerangka dosen, bukan kode saya. Saya juga mencocokkan nama method dan tipe
  dengan kode asli.
- Satu hal yang saya pahami lebih baik sekarang: perbedaan aggregation dan
  composition