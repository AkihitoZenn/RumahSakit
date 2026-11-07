public class PasienIGD extends Pasien implements Billable {

    private String kodeTriase;

    public PasienIGD(String idPasien, String nama, int usia, int tingkatKeparahan) {
        super(idPasien, nama, usia, tingkatKeparahan);
        this.kodeTriase = "BELUM DITENTUKAN";
    }

    public PasienIGD(String idPasien, String nama) {
        this(idPasien, nama, 0, 3);
    }

    @Override
    public String prosesPerawatan() {
        if (getTingkatKeparahan() == 5) {
            this.kodeTriase = "MERAH";
            setStatus("DIRAWAT");
            return getNama() + " masuk triase MERAH, ditangani segera.";
        } else if (getTingkatKeparahan() >= 3) {
            this.kodeTriase = "KUNING";
            setStatus("DIRAWAT");
            return getNama() + " masuk triase KUNING, ditangani prioritas menengah.";
        } else {
            this.kodeTriase = "HIJAU";
            setStatus("SELESAI");
            return getNama() + " masuk triase HIJAU, ditangani dan boleh pulang.";
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis      : IGD");
        System.out.println("Triase     : " + kodeTriase);
    }

    @Override
    public double hitungBiaya() {
        double tindakan;
        if (kodeTriase.equals("MERAH")) {
            tindakan = 1500000;
        } else if (kodeTriase.equals("KUNING")) {
            tindakan = 750000;
        } else {
            tindakan = 250000;
        }
        return BIAYA_ADMIN + tindakan;
    }

    public String getKodeTriase() { return kodeTriase; }
    public void setKodeTriase(String kodeTriase) { this.kodeTriase = kodeTriase; }
}
