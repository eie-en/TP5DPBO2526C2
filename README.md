# ⡞⠳⣄⣀⣠⠞⢷ ֹ۪
<p align="center">
  <✦•┈๑⋅⋯ ⋯⋅๑┈•✦>
</p>
    
Saya Aghni Lutvia Sari dengan NIM 2508921 mengerjakan TP2
dalam mata kuliah DPBO untuk keberkahanNya maka saya
tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

## .✦ ݁˖ Penjelasan Design 
Jadi untuk designnya aku pake tema player valorant, ID di sini aku ganti username karena sama sama primary, lalu kategorinya adalah rank (ada Iron, Gold, Silver, Platinum, Diamond, Ascendant, Immortal, dan Radiant). Udah sih gitu aja.

𑣲⋆ Desain Program

1. **Model (Representasi Data - `Person.java`)**
Berfungsi sebagai *blueprint* atau kelas entitas yang menampung atribut data seseorang, meliputi `username`, `nama`, `thn` (umur), dan `rank`, lengkap dengan konstruktor, *getter*, serta *setter* untuk membungkus data tersebut.


2. **Database Handler (Koneksi & Query - `Database.java`)**
Bertindak sebagai jembatan komunikasi ke database MySQL (`db_peopledata`) menggunakan JDBC. Kelas ini memisahkan method eksekusi menjadi dua fungsi utama: `selectQuery(sql)` untuk mengambil data (`SELECT`) dan `insertUpdateDeleteQuery(sql)` untuk memanipulasi data (`INSERT`, `UPDATE`, `DELETE`).


3. **View & Controller (Antarmuka Pengguna & Logika - `PeopleMenu.java` & `PeopleMenu.form`)**
Mengatur tampilan grafis (GUI) menggunakan Java Swing dan form IntelliJ IDEA yang mencakup komponen input teks, *combobox* rank Valorant, tombol aksi, serta tabel (`JTable`). Kelas ini juga mengatur seluruh *event listener* (aksi klik tombol, pemilihan baris tabel, dan validasi form) untuk mengontrol jalannya aplikasi secara langsung.

𑣲⋆ Alur Program

1. **Inisialisasi Window & Koneksi Database**
Program menjalankan method `main` pada kelas `PeopleMenu` untuk membuat objek jendela GUI, mengatur ukuran, posisi di tengah layar, dan langsung menginisialisasi objek `Database` yang bertugas menghubungkan aplikasi ke database MySQL (`db_peopledata`).


2. **Memuat Data Awal ke Tabel (Load Table)**
Saat *constructor* `PeopleMenu()` dipanggil, program langsung menjalankan method `setTable()` untuk melakukan query `SELECT` ke database, mengambil seluruh data tabel `person`, lalu memasukkannya ke dalam komponen `JTable` agar data langsung tampil saat aplikasi dibuka.


3. **Pengaturan Tampilan & Komponen (UI Setup)**
Program mengatur gaya huruf judul menjadi tebal, mengisi pilihan *ComboBox* dengan daftar rank Valorant (*Iron* sampai *Radiant*), serta menyembunyikan tombol *Delete* di awal karena belum ada data yang dipilih.


4. **Interaksi Pengguna: Menambah Data Baru (Insert)**
Pengguna mengisi kolom *Username*, *Nama*, *Umur*, dan memilih *Rank*, lalu menekan tombol **Submit**:
* **Validasi Kosong:** Program mengecek apakah ada kolom yang kosong. Jika ada, muncul dialog error.


* **Validasi Duplikasi:** Program mengecek apakah *username* sudah terdaftar di database. Jika sudah ada, muncul dialog error bahwa username tersebut sudah terdaftar.


* **Validasi Angka:** Program memastikan input umur berupa angka.


* Jika semua validasi lolos, query `INSERT` dijalankan ke database, tabel otomatis diperbarui, dan form dibersihkan.


5. **Interaksi Pengguna: Memilih & Mengubah Data (Update)**
Saat pengguna mengklik salah satu baris data pada tabel:
* Program mengambil data dari baris tersebut dan menampilkannya kembali ke dalam *text field* dan *combobox*.


* Kolom *username* dikunci (*read-only*) agar *primary key* tidak bisa diubah, teks tombol berubah dari *"Submit"* menjadi *"Update"*, dan tombol *Delete* dimunculkan.


* Setelah pengguna mengubah data dan menekan tombol **Update**, query `UPDATE` dijalankan, tabel direfresh, dan form kembali dikosongkan.


6. **Interaksi Pengguna: Menghapus Data (Delete)**
Saat pengguna mengklik tombol **Delete** (yang muncul setelah baris dipilih), program menampilkan dialog konfirmasi penghapusan. Jika pengguna memilih *Yes*, query `DELETE` dijalankan berdasarkan *username*, data terhapus dari database, tabel diperbarui, dan form dibersihkan.

## .✦ ݁˖ Dokumentasi

<img width="1920" height="1080" alt="Screenshot (643)" src="https://github.com/user-attachments/assets/affb18bd-996a-490a-bd88-db84f2201415" />

<img width="1920" height="1080" alt="Screenshot (644)" src="https://github.com/user-attachments/assets/663b21b7-b1c3-47be-8a36-df480cfa13fc" />

<img width="1920" height="1080" alt="Screenshot (634)" src="https://github.com/user-attachments/assets/c0fd3f9d-7231-4461-a71f-399b44db1d33" />

<img width="1920" height="1080" alt="Screenshot (635)" src="https://github.com/user-attachments/assets/9d5198fa-90d5-4b70-84f3-1608f7e8a8fb" />

<img width="1920" height="1080" alt="Screenshot (636)" src="https://github.com/user-attachments/assets/28fd6c62-d4fd-49a5-a969-747e170abfa7" />

<img width="1920" height="1080" alt="Screenshot (637)" src="https://github.com/user-attachments/assets/df5b0adc-13a5-43e7-9929-87408d283d15" />

<img width="1920" height="1080" alt="Screenshot (638)" src="https://github.com/user-attachments/assets/b4c8fe8f-e5cf-477c-880b-8c55b1e8a68d" />

<img width="1920" height="1080" alt="Screenshot (639)" src="https://github.com/user-attachments/assets/4f300b4d-3c5c-4490-9496-3abd1756bc28" />

<img width="1920" height="1080" alt="Screenshot (640)" src="https://github.com/user-attachments/assets/f9b08e54-f340-45fb-956d-2ade5754d256" />

<img width="1920" height="1080" alt="Screenshot (641)" src="https://github.com/user-attachments/assets/314250a5-bb8c-4073-acfe-f2e49047c8c1" />

<img width="1920" height="1080" alt="Screenshot (642)" src="https://github.com/user-attachments/assets/48a190b3-6f28-4758-91ac-90b774fc38d4" />
