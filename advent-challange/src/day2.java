import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day2 {

  public static void main(String[] args) {
    System.out.println(totalScore("src/inputDay2.txt"));
    //System.out.println(totalScore("src/shortInputDay2.txt"));
    System.out.println(totalScoreByStrategyGuide("src/inputDay2.txt")); // 15002 is too low.
  }

  public static Integer totalScore(String filePath) {
    List<List<String>> games = readInput(filePath);
    int score = 0;
    for (List list : games) {
      if (list.get(1).equals("X")) {
        score += 1;
      } else if (list.get(1).equals("Y")) {
        score += 2;
      } else if (list.get(1).equals("Z")) {
        score += 3;
      }
      if (list.get(0).equals("A") && list.get(1).equals("X")) {
        score += 3; // because rock against rock
      } else if (list.get(0).equals("A") && list.get(1) == "Y") {
        score += 6; // because rock against paper
      } else if (list.get(0).equals("A") && list.get(1).equals("Z")) {
        score += 0; // because rock against scissors
      } else if (list.get(0).equals("B") && list.get(1).equals("X")) {
        score += 0; // because paper against rock
      } else if (list.get(0).equals("B") && list.get(1).equals("Y")) {
        score += 3; // because paper against paper
      } else if (list.get(0).equals("B") && list.get(1).equals("Z")) {
        score += 6; // because paper against scissors
      } else if (list.get(0).equals("C") && list.get(1).equals("X")) {
        score += 6; // because scissors against rock
      } else if (list.get(0).equals("C") && list.get(1).equals("Y")) {
        score += 0; // because scissors against paper
      } else if (list.get(0).equals("C") && list.get(1).equals("Z")) {
        score += 3; // because scissors against rock
      }
    }
    return score;
  }

  public static int totalScoreByStrategyGuide(String filePath) {
    List<List<String>> games = readInput(filePath);
    int score = 0;
    System.out.println(games.size());
    for (List list : games) {
      if (list.get(0).equals("A") && list.get(1).equals("X")) {
        // I should choose: "Y" - the paper to loose
        score = score + 2 + 0;
      } else if (list.get(0).equals("A") && list.get(1).equals("Y")) {
        // I should choose the same: "X" - the rock to end in a draw
        score = score + 2 + 3;
      } else if (list.get(0).equals("A") && list.get(1).equals("Z")) {
        // I should choose "Z" - the scissors to win
        score += 3 + 6;
      } else if (list.get(0).equals("B") && list.get(1).equals("X")) {
        // I should choose "X" - the rock to lose
        score = score + 1 + 0;
      } else if (list.get(0).equals("B") && list.get(1).equals("Y")) {
        // I should choose the "Y" - the paper to end in a draw
        score = score + 2 + 3;
      } else if (list.get(0).equals("B") && list.get(1).equals("Z")) {
        // I should choose "Z" - the scissors to win
        score = score + 3 + 6;
      } else if (list.get(0).equals("C") && list.get(1).equals("X")) {
        // I should choose "Y" - the paper to loose
        score = score + 2 + 0;
      } else if (list.get(0).equals("C") && list.get(1).equals("Y")) {
        // I should choose "Z" - the scissors to end in a draw
        score = score + 2 + 3;
      } else if (list.get(0).equals("C") && list.get(1).equals("Z")) {
        // I should choose "Z" - the rock to win
        score = score + 1 + 6;
      }
    }
    return score;
  }

  public static List<List<String>> readInput(String filePath) {
    try {
      Path path = Paths.get(filePath);
      List<String> lines = Files.readAllLines(path);
      List<List<String>> games = new ArrayList<>();
      for (String line : lines) {
        String[] temp = line.split(" ");
        games.add(Arrays.asList(temp));
      }
      return games;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
/*
      } else if (list.get(0).equals("B") && list.get(1) == "Z") {
        score += 6;
      } else if (list.get(0).equals("C") && list.get(1).equals("X")) {
        score += 6;
      } else if (list.get(0).equals("A") && list.get(1).equals("X")) {
        score += 3;
      } else if (list.get(0).equals("B") && list.get(1).equals("Y")) {
        score += 3;
      } else if (list.get(0).equals("C") && list.get(1).equals("Z")) {
        score += 3;
      }
 */