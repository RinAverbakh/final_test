package animalRegistry;
import java.util.Date;

enum PackAnimalTupe{
    HORSE("horse"),
    DONCEY("doncey"),
    CAMEL("camel");

    private String displayName;

    PackAnimalTupe(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

public class PackAnimal extends Animal{
    private boolean isRidingAnimal;
    private boolean islivestock;
    private PackAnimalTupe packAnimalTupe;
    private String customPackAnimalTupe;

    public PackAnimal(String name, Date dateOfBirth, PackAnimalTupe packAnimalTupe, boolean isRidingAnimal, boolean islivestock){
        super(name, dateOfBirth);
        this.packAnimalTupe = packAnimalTupe;
        this.customPackAnimalTupe = null;
        this.isRidingAnimal = isRidingAnimal;
        this.islivestock = islivestock;
    }

    public PackAnimal(String name, Date dateOfBirth, PackAnimalTupe packAnimalTupe, boolean isRidingAnimal){
        super(name, dateOfBirth);
        this.packAnimalTupe = packAnimalTupe;
        this.customPackAnimalTupe = null;
        this.isRidingAnimal = isRidingAnimal;
        this.islivestock = false;
    }

    public PackAnimal(String name, Date dateOfBirth, PackAnimalTupe packAnimalTupe){
        super(name, dateOfBirth);
        this.packAnimalTupe = packAnimalTupe;
        this.customPackAnimalTupe = null;
        this.isRidingAnimal = false;
        this.islivestock = false;
    }

    public PackAnimal(String name, Date dateOfBirth, String customPetTupe, boolean isRidingAnimal, boolean islivestock){
        super(name, dateOfBirth);
        this.packAnimalTupe = null;
        this.customPackAnimalTupe = customPetTupe;
        this.isRidingAnimal = isRidingAnimal;
        this.islivestock = islivestock;
    }

    public PackAnimal(String name, Date dateOfBirth, String customPetTupe, boolean isRidingAnimal){
        super(name, dateOfBirth);
        this.packAnimalTupe = null;
        this.customPackAnimalTupe = customPetTupe;
        this.isRidingAnimal = isRidingAnimal;
        this.islivestock = false;
    }

    public PackAnimal(String name, Date dateOfBirth, String customPetTupe){
        super(name, dateOfBirth);
        this.packAnimalTupe = null;
        this.customPackAnimalTupe = customPetTupe;
        this.isRidingAnimal = false;
        this.islivestock = false;
    }

    public void teachToRide(){
        this.isRidingAnimal = true;
    }

    public void collectBenefits(){
        this.islivestock = true;
    }

    public boolean isRidingAnimal(){
        return isRidingAnimal;
    }

    public boolean islivestock(){
        return islivestock;
    }

    public String getType(){
        if (packAnimalTupe == null){
            return this.customPackAnimalTupe;
        } else {
            return packAnimalTupe.toString();
        }
        
    }

    public String getBenefit(){
        String result = "";
        if (isRidingAnimal){
            result += "ездовое животное";
        } else {
            result += "неездовое животное";
        }

        if (islivestock){
            result += ", даёт продовольствие.";
        } else {
            result += ", не даёт продовольствие.";
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";

        if (packAnimalTupe != null){
            result += "Вид: " + packAnimalTupe + " ," + super.toString();
        } else {
            result += "Вид: " + customPackAnimalTupe + " ," + super.toString();
        }

        if (isRidingAnimal){
            result += ", ездовое животное";
        } else {
            result += ", неездовое животное";
        }

        if (islivestock){
            result += ", даёт продовольствие.";
        } else {
            result += ", не даёт продовольствие.";
        }

        return result;
    }
}
