import droid.*;
import battle.*;
import weapon.*;
import arena.Arena;
import util.ColorConsole;

import java.util.*;
import java.io.*;

public class Main {
    private static List<Droid> droids = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static List<Arena> arenas = Arrays.asList(
            new Arena("Пустеля", "Зменшує точність на 10%"),
            new Arena("Місто", "Збільшує шкоду на 15%"),
            new Arena("Ліс", "Збільшує регенерацію енергії")
    );

    public static void main(String[] args) {
        System.out.println(ColorConsole.CYAN + "🎮 ЛАСКАВО ПРОСИМО ДО БИТВИ ДРОЇДІВ! 🎮" + ColorConsole.RESET);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // спожити новий рядок

            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startOneVsOneBattle();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    saveBattleToFile();
                    break;
                case 6:
                    replayBattleFromFile();
                    break;
                case 7:
                    System.out.println("Вихід з програми...");
                    return;
                default:
                    System.out.println("Невірний вибір!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n" + ColorConsole.PURPLE + "=== ГОЛОВНЕ МЕНЮ ===" + ColorConsole.RESET);
        System.out.println("1. Створити дроїда");
        System.out.println("2. Показати список дроїдів");
        System.out.println("3. Бій 1 на 1");
        System.out.println("4. Командний бій");
        System.out.println("5. Зберегти бій у файл");
        System.out.println("6. Відтворити бій із збереженого файлу");
        System.out.println("7. Вихід");
        System.out.print("Виберіть опцію: ");
    }

    private static void createDroid() {
        System.out.println("\n" + ColorConsole.BLUE + "=== СТВОРЕННЯ ДРОЇДА ===" + ColorConsole.RESET);
        System.out.println("Виберіть тип дроїда:");
        System.out.println("1. Штурмовик (збалансований)");
        System.out.println("2. Танк (багато здоров'я, мало шкоди)");
        System.out.println("3. Підтримка (лікує союзників)");
        System.out.println("4. Снайпер (висока шкода, мало здоров'я)");

        int type = scanner.nextInt();
        scanner.nextLine(); // спожити новий рядок

        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.nextLine();

        Droid droid = null;
        switch (type) {
            case 1:
                droid = new AssaultDroid(name);
                droid.setWeapon(new Blaster());
                break;
            case 2:
                droid = new TankDroid(name);
                droid.setWeapon(new RocketLauncher());
                break;
            case 3:
                droid = new SupportDroid(name);
                droid.setWeapon(new HealingGun());
                break;
            case 4:
                droid = new SniperDroid(name);
                droid.setWeapon(new Blaster());
                break;
            default:
                System.out.println("Невірний тип дроїда!");
                return;
        }

        droids.add(droid);
        System.out.println(ColorConsole.GREEN + "✅ Дроїд " + name + " створений!" + ColorConsole.RESET);
    }

    private static void showDroids() {
        System.out.println("\n" + ColorConsole.BLUE + "=== СПИСОК ДРОЇДІВ ===" + ColorConsole.RESET);
        if (droids.isEmpty()) {
            System.out.println("Дроїдів немає!");
            return;
        }

        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i));
        }
    }

    private static void startOneVsOneBattle() {
        if (droids.size() < 2) {
            System.out.println("Потрібно як мінімум 2 дроїди для бою!");
            return;
        }

        showDroids();
        System.out.print("Виберіть першого дроїда: ");
        int droid1Index = scanner.nextInt() - 1;
        System.out.print("Виберіть другого дроїда: ");
        int droid2Index = scanner.nextInt() - 1;

        if (droid1Index < 0 || droid1Index >= droids.size() ||
                droid2Index < 0 || droid2Index >= droids.size()) {
            System.out.println("Невірний вибір дроїдів!");
            return;
        }

        // Вибір арени
        Arena arena = arenas.get(new Random().nextInt(arenas.size()));
        arena.applyArenaEffect();

        Battle battle = new OneVsOneBattle(droids.get(droid1Index), droids.get(droid2Index));
        battle.startBattle();
    }

    private static void startTeamBattle() {
        if (droids.size() < 2) {
            System.out.println("Потрібно як мінімум 2 дроїди для командного бою!");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println("\n" + ColorConsole.BLUE + "=== ФОРМУВАННЯ КОМАНД ===" + ColorConsole.RESET);
        showDroids();

        // Формування команди 1
        System.out.println("Виберіть дроїдів для команди 1 (введіть номери через пробіл, 0 для завершення):");
        while (true) {
            int choice = scanner.nextInt();
            if (choice == 0) break;
            if (choice > 0 && choice <= droids.size()) {
                team1.add(droids.get(choice - 1));
            }
        }

        // Формування команди 2
        System.out.println("Виберіть дроїдів для команди 2 (введіть номери через пробіл, 0 для завершення):");
        while (true) {
            int choice = scanner.nextInt();
            if (choice == 0) break;
            if (choice > 0 && choice <= droids.size()) {
                team2.add(droids.get(choice - 1));
            }
        }

        if (team1.isEmpty() || team2.isEmpty()) {
            System.out.println("Обидві команди повинні мати хоча б одного дроїда!");
            return;
        }

        // Вибір арени
        Arena arena = arenas.get(new Random().nextInt(arenas.size()));
        arena.applyArenaEffect();

        Battle battle = new TeamBattle(team1, team2);
        battle.startBattle();
    }

    private static void saveBattleToFile() {
        System.out.print("Введіть ім'я файлу для збереження: ");
        String filename = scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename + ".txt"))) {
            // Тут має бути логіка збереження останнього бою
            writer.println("Битва дроїдів - збережений запис");
            writer.println("Функція збереження буде реалізована в наступній версії");
            System.out.println(ColorConsole.GREEN + "✅ Бій збережено у файл: " + filename + ".txt" + ColorConsole.RESET);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні файлу: " + e.getMessage());
        }
    }

    private static void replayBattleFromFile() {
        System.out.print("Введіть ім'я файлу для відтворення: ");
        String filename = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename + ".txt"))) {
            String line;
            System.out.println("\n" + ColorConsole.CYAN + "=== ВІДТВОРЕННЯ БОЮ ===" + ColorConsole.RESET);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Thread.sleep(500); // Затримка для кращого сприйняття
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }
}