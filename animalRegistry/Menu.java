package animalRegistry;
import java.util.Scanner;

public interface Menu {
    void runMenu(Scanner scanner, Registry registry, Counter counter) throws Exception;
}
