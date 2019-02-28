package solution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Solution {

  private List<Slide> slideshow;
  private PriorityQueue<Slide> workingSlides;
  private String filename;

  public Solution(List<Photo> verticalPhotos, List<Photo> horizontalPhotos, String filename) {
    int start = filename.lastIndexOf("/");
    int end   = filename.lastIndexOf(".");
    this.filename = filename.substring(start + 1, end);

    slideshow = new ArrayList<>();
    workingSlides = new PriorityQueue<>();

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
//    Slide slide1 = null;
//    Slide slide2 = null;
//    if (workingSlides.size() > 1) {
//      slide1 = workingSlides.poll();
//      slide2 = workingSlides.poll();
//    }
//    
//    while (!workingSlides.isEmpty()) {
//      Slide nextSlide = workingSlides.poll();
//      
//      int interest1 = slide1.calculateInterest(nextSlide);
//      int interest2 = slide2.calculateInterest(nextSlide);
//      
//      if (interest1 > interest2) {
//        slideshow.add(slide2);
//        slideshow.add(slide1);
//      } else {
//        slideshow.add
//      }
//      
//    }
    
    slideshow = new ArrayList<>(workingSlides);
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
      BufferedWriter writer = new BufferedWriter(new FileWriter("test_output/" + filename + ".sol"));
      writer.write("" + slideshow.size());
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
