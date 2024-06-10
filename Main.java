import java.io.*;
import java.util.Scanner;

class MathOperator implements Serializable {
    double findResult(int x) {
        return x - Math.sin(x);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String command;
        boolean print = true;

        do {
            if (print)
                System.out.println("Введите комманду (stop/save/upload): ");
            else
                print = true;
            command = input.nextLine();
            if (command.equals("save")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("result.txt"))){
                    System.out.println("Введите x: ");
                    int x = input.nextInt();
                    MathOperator operator = new MathOperator();
                    double result = operator.findResult(x);
                    oos.writeInt(x);
                    oos.writeDouble(result);
                    oos.writeObject(operator);
                    print = false;
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if (command.equals("upload")) {
                try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream("result.txt"))){
                    int x = oos.readInt();
                    double result = oos.readDouble();
                    MathOperator operator = (MathOperator)oos.readObject();
                    System.out.println(x);
                    System.out.println(result);
                    System.out.println(operator);
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if (!command.equals("stop") && !command.isEmpty()) {
                System.out.println("Вы ввели некорректную команду!");
            }
        }
        while (!command.equals("stop"));
    }
}