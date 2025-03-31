package animalRegistry;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class MenuImpl implements Menu {

    @Override
    public void runMenu(Scanner scanner, Registry registry, Counter counter) throws Exception {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Завести новое животное");
            System.out.println("2. Увидеть список команд животного");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Увидеть список всех животных");
            System.out.println("5. Удалить животное из списка");
            System.out.println("0. Выход");

            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        addNewAnimal(scanner, registry, counter);
                        break;
                    case "2":
                        viewAnimalCommands(scanner, registry);
                        break;
                    case "3":
                        trainAnimal(scanner, registry);
                        break;
                    case "4":
                        viewAllAnimals(registry);
                        break;
                    case "5":
                        removeAnimal(scanner, registry);
                        break;
                    case "0":
                        System.out.println("Выход из программы.");
                        return; 
                    default:
                        System.out.println("Неверный выбор. Попробуйте еще раз.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Ошибка ввода: Неверный формат данных.");
                scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.err.println("Ошибка: Ввод не найден.");
            } catch (IllegalStateException e) {
                System.err.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Непредвиденная ошибка: " + e.getMessage());
            }
        }
    }

    private void addNewAnimal(Scanner scanner, Registry registry, Counter counter) throws Exception {
        try {
            System.out.print("Введите имя животного: ");
            String name = scanner.nextLine();

            System.out.print("Введите дату рождения животного (гггг-мм-дд): ");
            String dateStr = scanner.nextLine();
            Date dateOfBirth = java.sql.Date.valueOf(dateStr);

            System.out.println("Выберите, из какой группы хотите добавить животное:");
            System.out.println("Домашнее животное(Pet) - введите 1");
            System.out.println("Вьючное дивотное(PackAnimal) - введите 2");

            String typeAnimal = scanner.nextLine();
            
            
            switch (typeAnimal) {
                case "1":
                    System.out.println("Выберите вид животного:");
                    for (PetTupe type : PetTupe.values()) {
                        System.out.println(type.ordinal() + 1 + ". " + type);
                    }
                    System.out.print("Введите номер вида: ");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine();

                    PetTupe petType;
                    if (typeChoice > 0 && typeChoice <= PetTupe.values().length) {
                        petType = PetTupe.values()[typeChoice - 1];

                        Pet newPet = new Pet(name, dateOfBirth, petType);
                        registry.addAnimal(newPet);
                    } else {
                        System.out.println("Неверный номер вида. Введите новый вид животного: ");
                        String customPetType = scanner.nextLine();
                        
                        Pet newPet = new Pet(name, dateOfBirth, customPetType);
                        registry.addAnimal(newPet);
                        return;
                    }


                break;

                case "2":
                   System.out.println("Выберите вид животного:");
                    for (PackAnimalTupe type : PackAnimalTupe.values()) {
                        System.out.println(type.ordinal() + 1 + ". " + type);
                    }
                    System.out.print("Введите номер вида: ");
                    int tupeChoice = scanner.nextInt();
                    scanner.nextLine();

                    PackAnimalTupe packAnimalTupe;
                    if (tupeChoice > 0 && tupeChoice <= PackAnimalTupe.values().length) {
                        packAnimalTupe = PackAnimalTupe.values()[tupeChoice - 1];

                        PackAnimal newPackAnimal = new PackAnimal(name, dateOfBirth, packAnimalTupe);
                        registry.addAnimal(newPackAnimal);
                    } else {
                        System.out.println("Неверный номер вида. Введите новый вид животного: ");
                        String customPackAnimalType = scanner.nextLine();
                        
                        PackAnimal newPackAnimal = new PackAnimal(name, dateOfBirth, customPackAnimalType);
                        registry.addAnimal(newPackAnimal);
                        return;
                    }

                    PackAnimal newPackAnimal = new PackAnimal(name, dateOfBirth, packAnimalTupe);
                    registry.addAnimal(newPackAnimal);
                break;

                default:
                    System.out.println("Неверный номер группы. Животное не будет добавлено.");
                break;
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: Неверный формат даты. Используйте гггг-мм-дд.");
        }
    }

    private void viewAnimalCommands(Scanner scanner, Registry registry) {
        try {
            System.out.print("Введите ID животного: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Animal animal = registry.findAnimalById(id);
            if (animal instanceof Pet) {
                Pet pet = (Pet) animal;
                System.out.println("Команды, которые знает " + pet.getName() + ": " + pet.getSkills());
            } else if(animal instanceof PackAnimal){
                PackAnimal packAnimal = (PackAnimal) animal;
                System.out.println("Пользу, которую приносит " + packAnimal.getName() + ": " + packAnimal.getBenefit());
            } else if (animal != null) {
                System.out.println("У этого животного нет команд.");
            } else {
                System.out.println("Животное с ID " + id + " не найдено.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Ошибка ввода: ID должен быть числом.");
            scanner.nextLine();
        }
    }

    private void trainAnimal(Scanner scanner, Registry registry) {
        try {
            System.out.print("Введите ID животного: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Animal animal = registry.findAnimalById(id);
            if (animal instanceof Pet) {
                Pet pet = (Pet) animal;
                System.out.print("Введите новую команду для " + pet.getName() + ": ");
                String command = scanner.nextLine();
                pet.addSkill(command);
                System.out.println("Команда \"" + command + "\" успешно добавлена для " + pet.getName());

            } else if (animal instanceof PackAnimal){
                PackAnimal packAnimal = (PackAnimal) animal;
                System.out.println("Обучить езде животное по имени " + packAnimal.getName() + "? (yes/no)");
                String answer = scanner.nextLine();
                if(answer.equals("yes")){
                    packAnimal.teachToRide();
                } else if (!(answer.equals("no"))){
                    System.out.println("Введён нераспознанный ответ, в обучении отказано.");
                }

                System.out.println("Собирать продовольствие с животного по имени " + packAnimal.getName() + "? (yes/no)");
                answer = scanner.nextLine();
                if(answer.equals("yes")){
                    packAnimal.collectBenefits();
                } else if (!(answer.equals("no"))){
                    System.out.println("Введён нераспознанный ответ, в сборе отказано");
                }


            } else if (animal != null) {
                System.out.println("Этому типу животного нельзя добавлять команды.");
            } else {
                System.out.println("Животное с ID " + id + " не найдено.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Ошибка ввода: ID должен быть числом.");
            scanner.nextLine();
        }
    }

    private void viewAllAnimals(Registry registry) {
        if (registry.isEmpty()) {
            System.out.println("В реестре пока нет животных.");
        } else {
            System.out.println("Список всех животных в реестре:");
            registry.printRegistry();
        }
    }

    private void removeAnimal(Scanner scanner, Registry registry) {
        try {
            System.out.print("Введите ID животного для удаления: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            registry.removeAnimal(id);
        } catch (InputMismatchException e) {
            System.err.println("Ошибка ввода: ID должен быть числом.");
            scanner.nextLine();
        }
    }
}
