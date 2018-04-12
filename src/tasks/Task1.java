package tasks;

import java.util.Scanner;

public class Task1 {
  public static void main(final String[] args) {
    // опрашиваем ввод
    final Scanner scanner = new Scanner(System.in);
    final double a = tryReadDouble("a", scanner);
    final double b = tryReadDouble("b", scanner);
    final double h = tryReadDouble("h", scanner);
    scanner.close();
    // выводим результат функции
    for (double x = a; x <= b; x += h) {
      System.out.printf("F(%g)=%g\n", x, calc(x));
    }
  }

  // здесь производится валидация ввода
  private static double tryReadDouble(final String value, final Scanner scanner) {
    double dVal = Double.NaN;
    do {
      System.out.print(value + " = ");
      if (scanner.hasNextDouble()) {
        dVal = scanner.nextDouble();
      } else {
        System.out.println("Only numbers are allowed!");
        scanner.next();
      }
    } while (Double.isNaN(dVal));
    return dVal;
  }

  private static double calc(final double x) {
    return Math.tan(x) - 3;
  }
}
