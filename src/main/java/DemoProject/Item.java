package DemoProject;

import javafx.scene.paint.Color;

public class Item {
  public Item(String value, Color color, String acronym) {
    this.value = value;
    this.color = color;
    this.acronym = acronym;
  }

  private final String value;
  private final Color color;
  private final String acronym;

  public String getValue() {
    return value;
  }

  public Color getColor() {
    return color;
  }

  public String getAcronym() {
    return acronym;
  }
}
