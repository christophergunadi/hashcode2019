import solution.Photo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import solution.Solution;

public class Main {
  
  private static List<Photo> verticalPhotos = new ArrayList<>();
  private static List<Photo> horizontalPhotos = new ArrayList<>();
  
  public static void main(String[] args) throws FileNotFoundException {
    /* Initialise program */
    String filename = args[0];
    Scanner scan = new Scanner(new File(filename));
    int numPhotos = scan.nextInt();
    
    for (int i = 0; i < numPhotos; i++) {
      
      char orientation = scan.next().charAt(0);
      boolean isVertical = orientation == 'V';
      int numTags = scan.nextInt();
      Set<String> tags = new HashSet<>();
      
      for (int j = 0; j < numTags; j++) {
        tags.add(scan.next());
      }
      
      if (isVertical) {
        verticalPhotos.add(new Photo(i, isVertical, tags));
      } else {
        horizontalPhotos.add(new Photo(i, isVertical, tags));
      }
    }

    /* Next */
    Solution solution = new Solution(verticalPhotos, horizontalPhotos);
    solution.generateSlideshow();
    System.out.println("Score is: " + solution.calculateScore());
    solution.writeSolutionToFile();
  }
}
