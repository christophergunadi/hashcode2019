package solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {

  private List<Slide> solution;

  public Solution() {
    solution = new ArrayList<>();
  }

  public void addSlideToSolution(Slide slide) {
    solution.add(slide);
  }

  public int calculateScore() {
    int numSlides = solution.size();
    if (numSlides <= 1) return 0;

    int score = 0;
    Slide prevSlide = solution.get(0);

    for (int i = 1; i < numSlides; i++) {
      score += prevSlide.calculateInterest(solution.get(i));
      prevSlide = solution.get(i);
    }

    return score;
  }

  public void writeSolutionToFile() {
    try {
    BufferedWriter writer = new BufferedWriter(new FileWriter("solution.txt"));
      writer.write(solution.size());
      writer.newLine();

      for (Slide slide : solution) {
        writer.write(slide.toString());
        writer.newLine();
      }

      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
