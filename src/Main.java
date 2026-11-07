public class Main {
    public static void main(String[] args) {

        // Dokter (association dengan Pasien)
        Dokter dokterUmum = new Dokter("DR-001", "Arya Azizan", "Penyakit Dalam");

        PasienRawatInap inap = new PasienRawatInap("RI-001", "Otniel", 19, 3, 4, 300000);
        PasienRawatJalan jalan = new PasienRawatJalan("RJ-001", "Farel Hasan", 18, 2, "Poli Umum");
        PasienIGD igd = new PasienIGD("IGD-001", "Sultan", 20, 5);

        inap.setDokter(dokterUmum);
        jalan.setDokter(dokterUmum);

        System.out.println("===== [1] STATE DAN BEHAVIOR =====");
        inap.tampilkanInfo();                       // method void
        double biaya = inap.hitungBiaya();          // method return value
        System.out.println("Biaya awal : Rp" + String.format("%,.0f", biaya));

        System.out.println("\n===== [2] IMMUTABILITY =====");
        System.out.println("Konstanta NAMA_RS    : " + Pasien.NAMA_RS);
        System.out.println("Konstanta BIAYA_ADMIN: Rp" + String.format("%,.0f", Pasien.BIAYA_ADMIN));
        System.out.println("ID pasien (final)    : " + inap.getIdPasien());
        // inap.idPasien = "X";   -> ERROR: private & final, tidak bisa diubah

        System.out.println("\n===== [3] ENKAPSULASI DAN OVERLOADING =====");
        inap.setNama("Otniel");
        System.out.println("Nama setelah setNama(): " + inap.getNama());
        PasienRawatJalan jalanSederhana = new PasienRawatJalan("RJ-002", "Cepe");        // konstruktor 2 parameter
        PasienRawatInap inapSederhana = new PasienRawatInap("RI-002", "Albet", 2);        // konstruktor 3 parameter
        System.out.println("Konstruktor ringkas: " + jalanSederhana.getNama() + ", poli "
                + jalanSederhana.getPoli() + " | " + inapSederhana.getNama()
                + ", inap " + inapSederhana.getLamaInap() + " hari");

        System.out.println("\n===== [4] INHERITANCE =====");
        Pasien[] daftarPasien = {inap, jalan, igd};
        for (Pasien p : daftarPasien) {
            System.out.println("- " + p.getNama() + " adalah " + p.getClass().getSimpleName()
                    + " (turunan Pasien)");
        }

        System.out.println("\n===== [5] SUPER CHAINING & [6] OVERRIDING =====");
        System.out.println("tampilkanInfo() pada subclass memanggil super.tampilkanInfo():");
        jalan.tampilkanInfo();

        System.out.println("\n===== [7] ABSTRACT CLASS =====");
        // Pasien p = new Pasien("X", "Y");   -> ERROR: abstract class tidak bisa diinstansiasi
        for (Pasien p : daftarPasien) {
            System.out.println(p.getClass().getSimpleName() + " -> abstract method prosesPerawatan() dijalankan");
        }

        System.out.println("\n===== [8] LOGIKA STATE (if/else) =====");
        System.out.println("Status awal inap : " + inap.getStatus());
        System.out.println(inap.prosesPerawatan());
        System.out.println("Status sekarang  : " + inap.getStatus());
        System.out.println(inap.prosesPerawatan());
        inap.setTingkatKeparahan(5);
        System.out.println(inap.prosesPerawatan());
        System.out.println("Kelas sekarang   : " + inap.getKelas());
        System.out.println(jalan.prosesPerawatan());
        System.out.println(jalan.prosesPerawatan());
        System.out.println(igd.prosesPerawatan());

        System.out.println("\n===== [9] POLIMORFISME INTERFACE =====");
        Billable tagihan1 = new PasienRawatInap("RI-003", "Ucup", 31, 2, 3, 300000);
        Billable tagihan2 = jalan;
        Billable tagihan3 = igd;
        Billable[] semuaTagihan = {tagihan1, tagihan2, tagihan3};
        for (Billable b : semuaTagihan) {
            System.out.println(b.getClass().getSimpleName() + " -> Rp"
                    + String.format("%,.0f", b.hitungBiaya()));
        }

        System.out.println("\n===== [10] MULTIPLE INTERFACE =====");
        // PasienRawatInap implements Billable, Terjadwal
        Terjadwal jadwal = inap;
        jadwal.jadwalkan("Senin, 10 Nov 2026 pukul 08.00");
        System.out.println("Lewat interface Terjadwal: " + jadwal.getJadwal());
        Billable bill = inap;
        System.out.println("Lewat interface Billable : Rp" + String.format("%,.0f", bill.hitungBiaya()));

        System.out.println("\n===== TAMBAHAN: ASSOCIATION PASIEN - DOKTER =====");
        System.out.println(inap.getNama() + " ditangani dr. " + inap.getDokter().getNama()
                + " (" + inap.getDokter().getSpesialis() + ")");
        System.out.println(igd.getNama() + " belum punya dokter: "
                + (igd.getDokter() == null ? "ya" : "tidak"));
    }
}
