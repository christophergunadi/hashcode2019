package solution;

import java.util.Set;

public class Slide {

  private Photo photo1;
  private Photo photo2;
  private Set<String> tags;
  private boolean containsHorizontal;

  public Slide(Photo photo) {
    this.photo1 = photo;
    containsHorizontal = true;

    tags.addAll(photo.getTags());
  }

  public Slide(Photo photo1, Photo photo2) {
    this.photo1 = photo1;
    this.photo2 = photo2;

    tags.addAll(photo1.getTags());
    tags.addAll(photo2.getTags());
  }

  public Set<String> getTags() {
    return tags;
  }

  public int calculateInterest(Slide slide) {
    int commonTags = 0;
    int uniqueTagsInOtherSlide = 0;
    for (String tag : slide.getTags()) {
      if (tags.contains(tag)) {
        commonTags++;
      } else {
        uniqueTagsInOtherSlide++;
      }
    }

    int uniqueTagsInThisSlide = tags.size() - commonTags;

    return Math.min(Math.min(commonTags, uniqueTagsInOtherSlide), uniqueTagsInThisSlide);
  }

  @Override
  public String toString() {
    if (containsHorizontal) {
      return "" + photo1.getId();
    } else {
      return photo1.getId() + " " + photo2.getId();
    }
  }

}
