public class Dokter {

    // Atribut instans kekal: ID dokter tidak boleh berubah
    private final String idDokter;
    private String nama;
    private String spesialis;

    public Dokter(String idDokter, String nama, String spesialis) {
        this.idDokter = idDokter;
        this.nama = nama;
        this.spesialis = spesialis;
    }

    public String getIdDokter() { return idDokter; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getSpesialis() { return spesialis; }
    public void setSpesialis(String spesialis) { this.spesialis = spesialis; }
}
