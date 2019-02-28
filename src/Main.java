import solution.Photo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import solution.Solution;

public class Main {
  
  private static List<Photo> verticalPhotos;
  private static List<Photo> horizontalPhotos;
  
  public static void main(String[] args) throws FileNotFoundException {
    for (int n = 0; n < args.length; n++) {
      verticalPhotos = new ArrayList<>();
      horizontalPhotos = new ArrayList<>();
    /* Initialise program */
      String filename = args[n];
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
      Solution solution = new Solution(verticalPhotos, horizontalPhotos, args[n]);
      solution.generateSlideshow();
      System.out.println("Score for " + args[n] + " is: " + solution.calculateScore());
      solution.writeSolutionToFile();
    }
  }
}
