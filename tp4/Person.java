public class Person {
    private String username;
    private String nama;
    private int thn;
    private String rank;

    // Constructor
    public Person() {
    }

    public Person(String username, String nama, int thn, String rank) {
        this.username = username;
        this.nama = nama;
        this.thn = thn;
        this.rank = rank;
    }

    // Getter dan Setter untuk username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter dan Setter untuk nama
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter dan Setter untuk tahun lahir
    public int getThn() {
        return thn;
    }

    public void setUmur(int thn) {
        this.thn = thn;
    }

    // Getter dan Setter untuk rank
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
