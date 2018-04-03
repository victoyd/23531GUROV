package tasks.task2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task2 {
  public static void main(final String[] args) {
    final Map<Class<? extends Product>, Integer> productStats = retrieveStats();
    final Product[] products = prepareProductArray(productStats);

    for (final Product product : products) {
      System.out.println("product name: " + product.whoAmI());
      // проверяем если объект является типом Present
      if (product instanceof Present) {
        final Present present = (Present) product;
        System.out.println("it's a present!");
        System.out.println("can be presented: " + present.itCanBePresented());
      }
    }
  }

  private static Map<Class<? extends Product>, Integer> retrieveStats() {
    // опрашиваем ввод
    final Scanner scanner = new Scanner(System.in);
    System.out.print("books = ");
    final int books = scanner.nextInt();
    System.out.print("shoes = ");
    final int shoes = scanner.nextInt();
    System.out.print("toys = ");
    final int toys = scanner.nextInt();
    System.out.print("pictures = ");
    final int pictures = scanner.nextInt();
    scanner.close();

    // создаем словарь из нужных классов и их количества
    final Map<Class<? extends Product>, Integer> productStats = new HashMap<>();
    productStats.put(Book.class, books);
    productStats.put(Shoe.class, shoes);
    productStats.put(Toy.class, toys);
    productStats.put(Picture.class, pictures);
    return productStats;
  }

  private static Product[] prepareProductArray(
      final Map<Class<? extends Product>, Integer> productStats
  ) {
    return productStats
        .entrySet().stream()
        // пробегаемся по всем продуктам, создаем n массивов по m элементов
        .map(it -> {
          try {
            final Class<? extends Product> clazz = it.getKey();
            final int count = it.getValue();
            final Product[] arr = new Product[count];
            Arrays.fill(arr, clazz.newInstance());
            return arr;
          } catch (Exception e) {
            e.printStackTrace();
          }
          return null;
        })
        // разворачиваем, чтобы был не массив массивов, а плоский массив
        .flatMap(Arrays::stream)
        // сортируем, чтобы сначала шли только объекты типа Present
        .sorted((a, b) -> a instanceof Present ? 1 : -1)
        // записываем все в массив
        .toArray(Product[]::new);
  }
}
