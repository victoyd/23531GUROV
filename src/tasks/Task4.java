package tasks;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Task4 {
  private static final String DEFAULT_MSG = "No value for key ";

  public static void main(final String[] args) {
    // опрашиваем ввод
    final Scanner scanner = new Scanner(System.in);
    System.out.print("filename = ");
    final String filename = scanner.next();

    //грузим файл
    final Map<String, String> props = loadProperties(filename);

    System.out.println("File loaded, enter keys ");

    String key;
    while ((key = scanner.next()) != null) {
      if (key.equals("ESC")) break;
      System.out.println(props.getOrDefault(key, DEFAULT_MSG + key));
    }
    scanner.close();
  }

  // загружаем файл
  private static Map loadProperties(final String filename) {
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
