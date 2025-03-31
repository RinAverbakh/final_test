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
        super();
        this.packAnimalTupe = packAnimalTupe;
        this.customPackAnimalTupe = null;
        this.isRidingAnimal = isRidingAnimal;
        this.islivestock = islivestock;
    }

    public PackAnimal(String name, Date dateOfBirth, PackAnimalTupe packAnimalTupe, boolean isRidingAnimal){
        super();
        this.packAnimalTupe = packAnimalTupe;
        this.customPackAnimalTupe = null;
        this.isRidingAnimal = isRidingAnimal;
        this.islivestock = false;
    }

    public PackAnimal(String name, Date dateOfBirth, PackAnimalTupe packAnimalTupe){
        super();
        this.packAnimalTupe = packAnimalTupe;
        this.customPackAnimalTupe = null;
        this.isRidingAnimal = false;
        this.islivestock = false;
    }

    public PackAnimal(String name, Date dateOfBirth, String customPetTupe, boolean isRidingAnimal, boolean islivestock){
        super();
        this.packAnimalTupe = null;
        this.customPackAnimalTupe = customPetTupe;
        this.isRidingAnimal = isRidingAnimal;
        this.islivestock = islivestock;
    }

    public PackAnimal(String name, Date dateOfBirth, String customPetTupe, boolean isRidingAnimal){
        super();
        this.packAnimalTupe = null;
        this.customPackAnimalTupe = customPetTupe;
        this.isRidingAnimal = isRidingAnimal;
        this.islivestock = false;
    }

    public PackAnimal(String name, Date dateOfBirth, String customPetTupe){
        super();
        this.packAnimalTupe = null;
        this.customPackAnimalTupe = customPetTupe;
        this.isRidingAnimal = false;
        this.islivestock = false;
    }

    public void teachToRide(){
        this.isRidingAnimal = true;
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
}
