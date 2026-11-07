// [Syarat 7] Superclass berstatus abstract
public abstract class Pasien {

    // [Syarat 2] Konstanta global
    public static final String NAMA_RS = "RSUD Jenderal Soedirman";
    public static final double BIAYA_ADMIN = 25000;

    // [Syarat 2] Atribut instans kekal (private final)
    private final String idPasien;

    // [Syarat 1 & 3] Atribut state, semuanya private
    private String nama;
    private int usia;
    private int tingkatKeparahan; // skala 1 - 5
    private String status;        // TERDAFTAR, DIRAWAT, DIRUJUK, SELESAI
    private Dokter dokter;        // association: pasien memiliki dokter penanggung jawab

    // [Syarat 3] Konstruktor overloaded #1
    public Pasien(String idPasien, String nama) {
        this(idPasien, nama, 0, 1);
    }

    // [Syarat 3] Konstruktor overloaded #2
    public Pasien(String idPasien, String nama, int usia, int tingkatKeparahan) {
        this.idPasien = idPasien;
        this.nama = nama;
        this.usia = usia;
        this.tingkatKeparahan = tingkatKeparahan;
        this.status = "TERDAFTAR";
    }

    // [Syarat 7] Abstract method
    public abstract String prosesPerawatan();

    // [Syarat 1] Method prosedur (void)
    public void tampilkanInfo() {
        System.out.println("[" + NAMA_RS + "]");
        System.out.println("ID Pasien  : " + idPasien);
        System.out.println("Nama       : " + nama);
        System.out.println("Usia       : " + usia + " tahun (" + getKategoriUsia() + ")");
        System.out.println("Keparahan  : " + tingkatKeparahan + " dari 5");
        System.out.println("Status     : " + status);
        if (dokter != null) {
            System.out.println("Dokter     : dr. " + dokter.getNama() + " (" + dokter.getSpesialis() + ")");
        } else {
            System.out.println("Dokter     : belum ditentukan");
        }
    }

    // [Syarat 1] Method fungsi (return value)
    public String getKategoriUsia() {
        if (usia < 17) {
            return "Anak";
        } else if (usia < 60) {
            return "Dewasa";
        } else {
            return "Lansia";
        }
    }

    // Getter & setter
    public String getIdPasien() { return idPasien; } // final: hanya getter

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public int getUsia() { return usia; }
    public void setUsia(int usia) { this.usia = usia; }

    public int getTingkatKeparahan() { return tingkatKeparahan; }
    public void setTingkatKeparahan(int tingkatKeparahan) {
        if (tingkatKeparahan >= 1 && tingkatKeparahan <= 5) {
            this.tingkatKeparahan = tingkatKeparahan;
        }
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Dokter getDokter() { return dokter; }
    public void setDokter(Dokter dokter) { this.dokter = dokter; }
}
