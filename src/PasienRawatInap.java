// [Syarat 4] extends | [Syarat 10] implements dua interface sekaligus
public class PasienRawatInap extends Pasien implements Billable, Terjadwal {

    private int lamaInap;
    private double tarifKamarPerHari;
    private String kelas;
    private String jadwal;

    // [Syarat 5] super(...) pada konstruktor subclass
    public PasienRawatInap(String idPasien, String nama, int usia, int tingkatKeparahan,
                           int lamaInap, double tarifKamarPerHari) {
        super(idPasien, nama, usia, tingkatKeparahan);
        this.lamaInap = lamaInap;
        this.tarifKamarPerHari = tarifKamarPerHari;
        this.kelas = "Reguler";
        this.jadwal = "Belum dijadwalkan";
    }

    // [Syarat 3] Konstruktor overloaded
    public PasienRawatInap(String idPasien, String nama, int lamaInap) {
        this(idPasien, nama, 0, 1, lamaInap, 300000);
    }

    // [Syarat 6 & 8] Override + logika if/else yang membaca dan mengubah state
    @Override
    public String prosesPerawatan() {
        if (getStatus().equals("SELESAI")) {
            return getNama() + " sudah selesai dirawat, tidak ada tindakan.";
        } else if (getTingkatKeparahan() >= 4) {
            this.kelas = "ICU";
            setStatus("DIRAWAT");
            return getNama() + " dipindahkan ke ruang ICU.";
        } else if (getStatus().equals("TERDAFTAR")) {
            setStatus("DIRAWAT");
            return getNama() + " masuk kamar kelas " + kelas + ".";
        } else {
            this.lamaInap++;
            return getNama() + " masih dirawat, lama inap menjadi " + lamaInap + " hari.";
        }
    }

    // [Syarat 6] Override method superclass
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo(); // [Syarat 5] super.namaMethod()
        System.out.println("Jenis      : Rawat Inap");
        System.out.println("Kelas      : " + kelas);
        System.out.println("Lama Inap  : " + lamaInap + " hari");
        System.out.println("Jadwal     : " + jadwal);
    }

    // Implementasi interface Billable
    @Override
    public double hitungBiaya() {
        double tarif = tarifKamarPerHari;
        if (kelas.equals("ICU")) {
            tarif = tarifKamarPerHari * 3;
        }
        return (lamaInap * tarif) + BIAYA_ADMIN;
    }

    // Implementasi interface Terjadwal
    @Override
    public void jadwalkan(String jadwal) {
        this.jadwal = jadwal;
    }

    @Override
    public String getJadwal() {
        return jadwal;
    }

    public int getLamaInap() { return lamaInap; }
    public void setLamaInap(int lamaInap) { this.lamaInap = lamaInap; }

    public double getTarifKamarPerHari() { return tarifKamarPerHari; }
    public void setTarifKamarPerHari(double tarifKamarPerHari) { this.tarifKamarPerHari = tarifKamarPerHari; }

    public String getKelas() { return kelas; }
    public void setKelas(String kelas) { this.kelas = kelas; }
}
