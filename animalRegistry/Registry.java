package animalRegistry;
import java.util.ArrayList;
import java.util.List;

public class Registry {
    private List<Animal> animals;
    private Counter counter;

    public Registry(Counter counter) {
        this.animals = new ArrayList<>();
        this.counter = counter;
    }

    public void addAnimal(Animal animal) throws IllegalStateException {
        try {
            if (counter.isClosed()) {
                throw new IllegalStateException("Счётчик выключен. Невозможно добавить");
            }
            animals.add(animal);
            counter.add();
            System.out.println("Животное успешно добавлено в реестр. Текущее количество животных: " + counter.getCount());
        } catch (IllegalStateException e) {
            System.err.println("Ошибка добавления животного: " + e.getMessage());
            throw e;
        }
    }


    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals);
    }

    public void removeAnimal(int id) {
        if (counter.getCount() < id) {
            System.out.println("Животного с таким индексом пока что нет.");
        } else {
            animals.remove(id-1);
            System.out.println("Животное с ID " + id + " успешно удалено из реестра.");
        }
    }


    public Animal findAnimalById(int id) {
        if (counter.getCount() < id) {
            return null;
        } else {
            return animals.get(id-1);
        }
    }

    public void printRegistry(){
        int id = 1;
        for (Animal animal : animals) {
            System.out.println(id + ". " + animal.toString());
            id ++;
        }
    }

    public boolean isEmpty(){
        return animals.isEmpty();
    }
}