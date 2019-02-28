package solution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
    Collections.sort(photos);
    while (photos.size() > 1) {
      Photo currPhoto = photos.remove(0);
//      List<Photo> possPhotos = new ArrayList<>();
      int i = 10;
      int j = 0;
      TreeMap<Integer, Photo> mapping = new TreeMap<>();
      while (i > 0 && !photos.isEmpty()) {
        j++;
        i--;
        Photo otherPhoto = photos.remove(photos.size() - 1);
        int calculatedNumTags = currPhoto.calculateTotalTagsWithPhoto(otherPhoto);
        mapping.put(calculatedNumTags, otherPhoto);
//        possPhotos.add(photos.remove(photos.size() - 1));
      }
      
      Map.Entry<Integer, Photo> entry = null;
      for (int k = 0; k <= j / 2; k++) {
        entry = mapping.pollFirstEntry();
        if (k <= j/2 - 1 && entry != null) {
          photos.add(entry.getValue());
        }
      }
      
      if (j > 0 && entry != null) {
        workingSlides.add(new Slide(currPhoto, entry.getValue()));
      }
      
      photos.addAll(mapping.values());
      
    }
//
//    while (photos.size() > 1) {
//      Photo photo1 = photos.remove(0);
//      Photo photo2 = photos.remove(photos.size() - 1);
//
//      workingSlides.add(new Slide(photo1, photo2));
//    }

//    int numSlides = photos.size() / 2;
//    for (int i = 0; i < numSlides; i++) {
//      workingSlides.add(new Slide(photos.get(2 * i), photos.get(2 * i + 1)));
//      }
  }

  public void generateSlideshow() {
    Slide prevSlide = workingSlides.poll();
    slideshow.add(prevSlide);
    
    while (workingSlides.size() > 0) {
      List<Slide> nextFiveSlides = new ArrayList<>();
      int i = 100;
      while (i > 0 && !workingSlides.isEmpty()) {
        nextFiveSlides.add(workingSlides.poll());
        i--;
      }
      
      Slide maxSlide = nextFiveSlides.get(0);
      int maxScore = prevSlide.calculateInterest(maxSlide); 
      for (int j = 1; j < nextFiveSlides.size(); j++) {
        int score = prevSlide.calculateInterest(nextFiveSlides.get(j));
        if (score > maxScore) {
          maxSlide = nextFiveSlides.get(j);
          maxScore = score;
        }
      }

      slideshow.add(maxSlide);
      prevSlide = maxSlide;
      nextFiveSlides.remove(maxSlide);
      workingSlides.addAll(nextFiveSlides);
    }
    
//    while (workingSlides.size() > 2) {
//      Slide slide1 = workingSlides.poll();
//      Slide slide2 = workingSlides.poll();
//      Slide slide3 = workingSlides.poll();
//      
//      int interest1 = slide1.calculateInterest(slide3);
//      int interest2 = slide2.calculateInterest(slide3);
//      
//      if (interest1 > interest2) {
//        slideshow.add(slide2);
//        slideshow.add(slide1);
//      } else {
//        slideshow.add(slide1);
//        slideshow.add(slide2);
//      }
//      slideshow.add(slide3);
//    }
//    
//    while (!workingSlides.isEmpty()) {
//      slideshow.add(workingSlides.poll());
//    }

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
    System.out.println(slideshow.size());
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
