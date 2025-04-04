package animalRegistry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum PetTupe{
    DOG("dog"),
    CAT("cat"),
    HAMSTER("hamster");

    private String displayName;

    PetTupe(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

public class Pet extends Animal{
    private PetTupe petTupe;
    private String customPetTupe;
    
    private List<String> skills;

    public Pet(String name, Date dateOfBirth, PetTupe petTupe){
        super(name, dateOfBirth);

        this.petTupe = petTupe;
        this.customPetTupe = null;

        this.skills = new ArrayList<>();
    }

    public Pet(String name, Date dateOfBirth, String customPetTupe){
        super(name, dateOfBirth);

        this.petTupe = null;
        this.customPetTupe = customPetTupe;

        this.skills = new ArrayList<>();
    }

    public String getType(){
        if (petTupe == null){
            return this.customPetTupe;
        } else {
            return petTupe.toString();
        }
        
    }

    public List<String> getSkills(){
        return skills;    
    }

    public void addSkill(String skill){
        this.skills.add(skill);
    }

    @Override
    public String toString() {
        if (petTupe != null){
           return  "Вид: " + petTupe + " ," + super.toString() + ", Команды: " + skills; 
        } else {
            return  "Вид: " + customPetTupe + " ," + super.toString() + ", Команды: " + skills;
        }
    }
}
