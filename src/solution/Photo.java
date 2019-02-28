package solution;

import java.util.Set;

public class Photo {

  private int id;
  private boolean isVertical;
  private Set<String> tags;

  public Photo(int id, boolean isVertical, Set<String> tags) {
    this.id         = id;
    this.isVertical = isVertical;
    this.tags       = tags;
  }

  public Set<String> getTags() {
    return tags;
  }

}
