package solution;

import java.util.Set;

public class Photo {

  private int id;
  boolean isVertical;
  private Set<String> tags;

  public Photo(int id, boolean isVertical, Set<String> tags) {
    this.id         = id;
    this.isVertical = isVertical;
    this.tags       = tags;
  }

  public Set<String> getTags() {
    return tags;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("photo");
    sb.append(id);
    sb.append(" ");
    sb.append(isVertical ? "V" : "H");
    sb.append(" ");
    sb.append(tags.toString());
    
    return sb.toString();
  }
}
