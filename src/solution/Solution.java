package solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {

  private List<Slide> slideshow;

  public Solution() {
    slideshow = new ArrayList<>();
  }

  public void addSlideToSlideShow(Slide slide) {
    slideshow.add(slide);
  }

  public int calculateScore() {
    int numSlides = slideshow.size();
    if (numSlides <= 1) return 0;

    int score = 0;
    Slide prevSlide = slideshow.get(0);

    for (int i = 1; i < numSlides; i++) {
      score += prevSlide.calculateInterest(slideshow.get(i));
      prevSlide = slideshow.get(i);
    }

    return score;
  }

  public void writeSolutionToFile() {
    try {
    BufferedWriter writer = new BufferedWriter(new FileWriter("slideshow.txt"));
      writer.write(slideshow.size());
      writer.newLine();

      for (Slide slide : slideshow) {
        writer.write(slide.toString());
        writer.newLine();
      }

      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
