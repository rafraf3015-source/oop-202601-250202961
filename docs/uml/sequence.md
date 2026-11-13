# Sequence Diagram: Pengajuan Peminjaman

Alur: klien membuat `Peminjaman`, lalu memanggil `JadwalPeminjaman.ajukan(baru)`.


## Skenario sukses dan skenario bentrok (fragmen `alt`)

```mermaid
sequenceDiagram
    actor K as Klien
    participant P as Peminjaman
    participant J as JadwalPeminjaman

    K->>P: new Peminjaman(id, peminjam, aset, mulai, selesai)
    P-->>K: baru
    K->>J: ajukan(baru)
    J->>J: bentrok(baru)
    alt bentrok = true (skenario bentrok)
        J-->>K: throw PeminjamanBentrokException
    else laptop dan kuota peminjam sudah penuh
        J-->>K: throw KuotaHabisException
    else bentrok = false dan kuota cukup (skenario sukses)
        J->>J: tambah(baru)
        J-->>K: selesai tanpa exception
    end
```

## Penjelasan

| Skenario | Kondisi | Hasil |
|---|---|---|
| Sukses | Tidak bentrok pada aset yang sama dan kuota laptop belum habis | `baru` ditambahkan ke daftar jadwal |
| Bentrok | Rentang waktu tumpang tindih pada aset yang sama | `PeminjamanBentrokException` |
| Kuota habis (tambahan) | Peminjam sudah memegang `kuotaLaptop` laptop aktif dan mengajukan laptop lagi | `KuotaHabisException` |

Definisi bentrok di batas waktu (11:00 vs 11:00) adalah keputusan di `docs/asumsi.md`.
