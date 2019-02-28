package solution;

import java.util.List;
import java.util.Set;

public class Slide {

  private List<Photo> photos;
  private Set<String> tags;

  public Slide(List<Photo> photos) {
    this.photos = photos;

    for (Photo photo : photos) {
      tags.addAll(photo.getTags());
    }

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
    if (photos.size() == 1) {
      return "" + photos.get(0).getId();
    } else {
      return photos.get(0).getId() + " " + photos.get(1).getId();
    }
  }

}
