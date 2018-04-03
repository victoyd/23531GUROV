package tasks.task2;

public class Toy implements Present {
  @Override
  public String itCanBePresented() {
    return "yes";
  }

  @Override
  public String whoAmI() {
    return "Toy";
  }
}
