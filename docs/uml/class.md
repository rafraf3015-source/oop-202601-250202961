# Class Diagram Aset-Pinjam (minggu 6)


- Kelas hasil kerja minggu 1-5 digambar lengkap: `Aset`, `Ruang`, `Laptop`, `AlatLab`, `KatalogAset`,
  `DendaFlat`, `DendaProgresif`, dan interface `DapatDipinjam` serta `AturanDenda`.
- `Peminjam`, `Peminjaman`, dan `JadwalPeminjaman` adalah kerangka dari dosen. Hanya anggota yang
  dipakai Sequence Diagram pengajuan yang digambar. `bentrok()` dan `ajukan()` masih `TODO` (minggu 7 dan 9).
- Getter sederhana tidak digambar, dan enum dituliskan sebagai tipe atribut.

Notasi: `+` public, `-` private, `#` protected, `*` abstract.

```mermaid
classDiagram
    namespace kontrak {
        class DapatDipinjam {
            <<interface>>
            +bisaDipinjam() boolean
        }
        class AturanDenda {
            <<interface>>
            +hitung(aset: Aset, hariTerlambat: int) long
        }
    }
    namespace model {
        class Aset {
            <<abstract>>
            -kode: String
            -nama: String
            -status: StatusAset
            -kondisi: Kondisi
            +Aset(kode: String, nama: String)
            +bisaDipinjam() boolean
            +getJenis() JenisAset
            +hitungDenda(hariTerlambat: int)* long
        }
        class Ruang {
            -kapasitas: int
            +getJenis() JenisAset
            +hitungDenda(hariTerlambat: int) long
        }
        class Laptop {
            -spesifikasi: String
            -nomorInventaris: String
            +getJenis() JenisAset
            +hitungDenda(hariTerlambat: int) long
        }
        class AlatLab {
            -laboratorium: String
            -tingkatRisiko: TingkatRisiko
            +getJenis() JenisAset
            +hitungDenda(hariTerlambat: int) long
        }
        class Peminjam {
            <<record>>
            +id() String
            +nama() String
        }
        class Peminjaman {
            -id: String
            -peminjam: Peminjam
            -aset: Aset
            -mulai: LocalDateTime
            -selesai: LocalDateTime
            -status: StatusPeminjaman
        }
    }
    namespace katalog {
        class KatalogAset {
            -daftar: List~Aset~
            +tambah(aset: Aset) void
            +semua() List~Aset~
            +cari(kata: String) List~Aset~
            +cari(kata: String, jenis: JenisAset) List~Aset~
        }
    }
    namespace denda {
        class DendaFlat {
            +hitung(aset: Aset, hariTerlambat: int) long
        }
        class DendaProgresif {
            +hitung(aset: Aset, hariTerlambat: int) long
        }
    }
    namespace jadwal {
        class JadwalPeminjaman {
            -daftar: List~Peminjaman~
            -kuotaLaptop: int
            +tambah(p: Peminjaman) void
            +bentrok(baru: Peminjaman) boolean
            +ajukan(baru: Peminjaman) void
        }
    }

    DapatDipinjam <|.. Aset
    Aset <|-- Ruang
    Aset <|-- Laptop
    Aset <|-- AlatLab
    AturanDenda <|.. DendaFlat
    AturanDenda <|.. DendaProgresif
    KatalogAset o-- "0..*" Aset
    DendaFlat ..> Aset
    DendaProgresif ..> Aset
    Peminjaman "*" --> "1" Peminjam
    Peminjaman "*" --> "1" Aset
    JadwalPeminjaman o-- "0..*" Peminjaman
```

## Catatan

- **Ada di kode tetapi tidak digambar** (kerangka minggu 7-11 atau pendukung): enum (`StatusAset`, `Kondisi`,
  `JenisAset`, `TingkatRisiko`, `StatusPeminjaman`), `Varian`, `LayananDenda`, paket `dao`, paket `exception`,
  dan `AsetFactory`.
- **Aggregation:** `KatalogAset o-- Aset` dan `JadwalPeminjaman o-- Peminjaman`, karena objek dibuat di luar
  lalu dimasukkan lewat `tambah(...)` dan tetap hidup tanpa wadahnya.
- **Composition:** belum ada di kode saat ini, jadi tidak digambar.
- **Dependency** ke `Varian`: `Ruang`, `Laptop`, dan `AlatLab` memanggil `Varian.aktif()` di `hitungDenda`.
