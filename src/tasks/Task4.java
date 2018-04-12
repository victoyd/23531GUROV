package tasks;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Task4 {
  private static final String DEFAULT_MSG = "No value for key: ";

  public static void main(final String[] args) {
    // опрашиваем ввод
    final Scanner scanner = new Scanner(System.in);
    //грузим файл
    final Map<String, String> props = tryOpenProps(scanner);

    System.out.println("File loaded, enter keys ");

    String key;
    while ((key = scanner.next()) != null) {
      if (key.equals("ESC")) break;
      System.out.println(props.getOrDefault(key, DEFAULT_MSG + key));
    }
    scanner.close();
  }

  private static Map tryOpenProps(final Scanner scanner) {
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
