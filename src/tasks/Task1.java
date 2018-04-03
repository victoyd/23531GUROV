package tasks;

import java.util.Scanner;

public class Task1 {
  public static void main(final String[] args) {
    // опрашиваем ввод
    final Scanner scanner = new Scanner(System.in);
    System.out.print("a = ");
    final double a = scanner.nextDouble();
    System.out.print("b = ");
    final double b = scanner.nextDouble();
    System.out.print("h = ");
    final double h = scanner.nextDouble();
    scanner.close();
    // выводим результат функции
    for (double x = a; x <= b; x += h) {
      System.out.printf("F(%g)=%g\n", x, calc(x));
    }
  }

  private static double calc(final double x) {
    return Math.tan(x) - 3;
  }
}
