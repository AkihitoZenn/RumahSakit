public class PasienRawatJalan extends Pasien implements Billable {

    private final double biayaKonsultasi = 75000;
    private int jumlahKunjungan;
    private String poli;

    public PasienRawatJalan(String idPasien, String nama, int usia, int tingkatKeparahan, String poli) {
        super(idPasien, nama, usia, tingkatKeparahan);
        this.poli = poli;
        this.jumlahKunjungan = 0;
    }

    public PasienRawatJalan(String idPasien, String nama) {
        this(idPasien, nama, 0, 1, "Poli Umum");
    }

    @Override
    public String prosesPerawatan() {
        if (getStatus().equals("SELESAI")) {
            return getNama() + " sudah selesai berobat.";
        } else if (getTingkatKeparahan() >= 4) {
            setStatus("DIRUJUK");
            return getNama() + " dirujuk ke rawat inap.";
        } else {
            this.jumlahKunjungan++;
            setStatus("SELESAI");
            return getNama() + " selesai konsultasi di " + poli
                    + " (kunjungan ke-" + jumlahKunjungan + ").";
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis      : Rawat Jalan");
        System.out.println("Poli       : " + poli);
        System.out.println("Kunjungan  : " + jumlahKunjungan + " kali");
    }

    @Override
    public double hitungBiaya() {
        return BIAYA_ADMIN + (biayaKonsultasi * jumlahKunjungan);
    }

    public double getBiayaKonsultasi() { return biayaKonsultasi; }

    public int getJumlahKunjungan() { return jumlahKunjungan; }
    public void setJumlahKunjungan(int jumlahKunjungan) { this.jumlahKunjungan = jumlahKunjungan; }

    public String getPoli() { return poli; }
    public void setPoli(String poli) { this.poli = poli; }
}
