import java.util.Date;

public abstract class Animal {
    protected String name;
    protected Date dateOfBirth;

    protected Animal(String name, Date dateOfBirth){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
