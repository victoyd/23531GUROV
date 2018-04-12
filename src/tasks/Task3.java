package tasks;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class Task3 {
  public static void main(final String[] args) {
    // опрашиваем ввод
    final Scanner scanner = new Scanner(System.in);
    final Properties props = tryOpenProps(scanner);
    final String value = tryReadValueByKey(props, scanner);
    scanner.close();

    System.out.println(value);
  }

  private static Properties tryOpenProps(final Scanner scanner) {
    InputStream stream = null;
    Properties properties = null;
    do {
      System.out.print("filename = ");
      final String filename = scanner.next();
      stream = tryOpenInputStream(stream, filename);
      if (stream == null) continue;
      properties = tryOpenProperties(stream, properties, filename);
    } while (stream == null || properties == null);
    return properties;
  }

  // выводит значение из файла
  private static String tryReadValueByKey(final Properties props, final Scanner scanner) {
    String value;
    do {
      System.out.print("key = ");
      final String key = scanner.next();
      value = props.getProperty(key);
      if (value == null) {
        System.out.println("Unable to find value for key " + key);
      }
    } while (value == null);
    return value;
  }

  private static Properties tryOpenProperties(InputStream stream, Properties properties, String filename) {
    try {
      properties = loadProperties(stream);
    } catch (IOException e) {
      System.out.println("Unable to read file " + filename);
    }
    return properties;
  }

  private static InputStream tryOpenInputStream(InputStream stream, String filename) {
    try {
      stream = Files.newInputStream(Paths.get(filename));
    } catch (IOException e) {
      System.out.println("Unable to open file " + filename);
    }
    return stream;
  }

  // загружаем файл
  private static Properties loadProperties(final InputStream fileStream) throws IOException {
    final Properties properties = new Properties();
    properties.load(fileStream);
    fileStream.close();
    return properties;
  }
}
