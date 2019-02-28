package solution;

import java.util.Set;

public class Photo implements Comparable {

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
  
  public int calculateTotalTagsWithPhoto(Photo other) {
    Set<String> tagsFromOther = other.getTags();
    tagsFromOther.addAll(tags);
    
    return tagsFromOther.size();
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

  @Override
  public int compareTo(Object o) {
    Photo photo = (Photo) o;
    int numTags = photo.getTags().size();

    if (numTags > tags.size()) {
      return -1;
    } else if (numTags == tags.size()) {
      return 0;
    } else {
      return 1;
    }
  }
}
