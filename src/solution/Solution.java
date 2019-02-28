package solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Solution {

  private List<Slide> slideshow;
  private TreeSet<Slide> workingSlides;

  public Solution(List<Photo> verticalPhotos, List<Photo> horizontalPhotos) {
    slideshow = new ArrayList<>();
    workingSlides = new ArrayList<>();

    for (Photo photo: horizontalPhotos) {
      workingSlides.add(new Slide(photo));
    }

    groupVerticalPhotos(verticalPhotos);
  }

  private void groupVerticalPhotos(List<Photo> photos) {
    int numSlides = photos.size() / 2;
    for (int i = 0; i < numSlides; i++) {
      workingSlides.add(new Slide(photos.get(2 * i), photos.get(2 * i + 1)));
    }
  }

  public void generateSlideshow() {

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
