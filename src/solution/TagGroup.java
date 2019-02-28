package solution;

import java.util.TreeSet;

public class TagGroup {
  
  private String tag;
  TreeSet<Slide> slides;

  public TagGroup(String tag) {
    this.tag = tag;
  }
  
  public void add(Slide slide) {
    slides.add(slide);
  }
  
  public int getSize() {
    return slides.size();
  }
}
