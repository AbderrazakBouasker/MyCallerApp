package jose.mokeni.mycallerapp;

public class Profil {
    String firstname,lastname,number;
    int id;

    public Profil( String name, String lastname, String number) {
        this.firstname = name;
        this.lastname = lastname;
        this.number = number;
    }

    public Profil(int id, String name, String lastname, String number) {
        this.id = id;
        this.firstname = name;
        this.lastname = lastname;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Profil{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", number='" + number + '\'' +
                ", id=" + id +
                '}';
    }
}
