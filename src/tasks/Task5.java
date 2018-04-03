package tasks;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Task5 {

  // безопасные счетчики
  private static final AtomicInteger trees = new AtomicInteger(0);

  private static final AtomicInteger hole = new AtomicInteger(0);
  private static final AtomicInteger plant = new AtomicInteger(0);
  private static final AtomicInteger readyTree = new AtomicInteger(0);

  // копает яму
  private static final Runnable digger = () -> {
    while (hole.get() < trees.get()) {
      if (hole.get() < trees.get()) {
        System.out.println("копаю яму №" + hole.incrementAndGet());
      }
    }
  };

  // сажает саженец
  private static final Runnable planter = () -> {
    while (plant.get() < trees.get()) {
      if (plant.get() < hole.get()) {
        System.out.println("сажаю саженец №" + plant.incrementAndGet());
      }
    }
  };

  // подвязывает саженец
  private static final Runnable binder = () -> {
    while (readyTree.get() < trees.get()) {
      if (readyTree.get() < plant.get()) {
        System.out.println("подвязываю саженец №" + readyTree.incrementAndGet());
      }
    }
  };

  public static void main(final String[] args) {
    // опрашиваем ввод
    final Scanner scanner = new Scanner(System.in);
    System.out.print("treesCount = ");
    final int treesCount = scanner.nextInt();
    scanner.close();

    trees.set(treesCount);

    // запускаем потоки
    new Thread(digger, "digger").start();
    new Thread(planter, "planter").start();
    new Thread(binder, "binder").start();
  }
}
