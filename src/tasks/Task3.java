package tasks;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Task3 {
  public static void main(final String[] args) {
    // опрашиваем ввод
    final Scanner scanner = new Scanner(System.in);
    System.out.print("filename = ");
    final String filename = scanner.next();
    System.out.print("key = ");
    final String key = scanner.next();
    scanner.close();

    final String value = retrieveValueFromFile(filename, key);
    System.out.println(value);
  }

  // выводит значение из файла
  private static String retrieveValueFromFile(final String filename, final String key) {
    final Properties props = loadProperties(filename);
    final String value = props.getProperty(key);
    if (value == null) {
      throw new RuntimeException("Unable to find value for key " + key);
    }
    return value;
  }

  // загружаем файл
  private static Properties loadProperties(final String filename) {
    final Properties properties = new Properties();
    try {
      final InputStream inStream = Task3.class.getResourceAsStream("/" + filename);
      if (inStream == null) {
        throw new IOException("Unable to open file " + filename);
      }
      properties.load(inStream);
      inStream.close();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Unable to load file " + filename, e);
    }
    return properties;
  }
}
