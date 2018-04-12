package tasks;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Task5 {

  private static final AtomicInteger maxTreesCount = new AtomicInteger(0);

  // безопасные счетчики
  private static final AtomicInteger diggedHoles = new AtomicInteger(0);
  private static final AtomicInteger plantedTrees = new AtomicInteger(0);
  private static final AtomicInteger readyTrees = new AtomicInteger(0);

  private static final BlockingQueue<Integer> hole = new LinkedBlockingQueue<>();
  private static final BlockingQueue<Integer> plant = new LinkedBlockingQueue<>();

  // копает яму
  private static final Runnable digger = () -> {
    while (diggedHoles.get() != maxTreesCount.get()) {
      final int id = diggedHoles.incrementAndGet();
      System.err.println("копаю яму №" + id);
      hole.add(id);
    }
  };

  // сажает саженец
  private static final Runnable planter = () -> {
    while (plantedTrees.get() != maxTreesCount.get()) {
      try {
        final Integer id = hole.take();
        System.err.println("сажаю саженец №" + id);
        plantedTrees.set(id);
        plant.add(id);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  };

  // подвязывает саженец
  private static final Runnable binder = () -> {
    while (readyTrees.get() != maxTreesCount.get()) {
      try {
        final Integer id = plant.take();
        System.err.println("подвязываю саженец №" + id);
        readyTrees.getAndIncrement();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  };

  public static void main(final String[] args) {
    // опрашиваем ввод
    final Scanner scanner = new Scanner(System.in);
    System.out.print("maxTreesCount = ");
    final int trees = scanner.nextInt();
    scanner.close();

    maxTreesCount.set(trees);

    // запускаем потоки
    new Thread(digger, "digger").start();
    new Thread(planter, "planter").start();
    new Thread(binder, "binder").start();
  }
}
