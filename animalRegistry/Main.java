package animalRegistry;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Counter counter = new Counter()) {
            Registry registry = new Registry(counter);
            Menu menu = new MenuImpl();
            menu.runMenu(scanner, registry, counter);
        } catch (Exception e) {
            System.err.println("Произошла ошибка при работе со счетчиком: " + e.getMessage());
        }
    }
}
