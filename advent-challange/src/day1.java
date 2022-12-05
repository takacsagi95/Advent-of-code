import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day1 {

  public static void main(String[] args) {
    //System.out.println(hashMapFromFile("/src/inputs/day1_input.txt"));
    //System.out.println(caloryCounting("src/input1.txt"));
    System.out.println(topThreeCaloriesSum("src/input1.txt"));
  }

  public static int calorieCounting(String filePath) {
    HashMap<Integer, Integer> elvesAndCalories = hashMapFromFile(filePath);
    int maxCalories = 0;
    for (Map.Entry<Integer, Integer> entry : elvesAndCalories.entrySet()) {
      if (entry.getValue() > maxCalories) {
        maxCalories = entry.getValue();
      }
    }
    return maxCalories;
  }

  public static HashMap<Integer, Integer> hashMapFromFile(String filepath) {
    HashMap<Integer, Integer> map = new HashMap<>();
    try {
      Path path = Paths.get(filepath);
      List<String> lines = Files.readAllLines(path);
      List<Integer> numLines = new ArrayList<>();

      for (int i = 0; i < lines.size(); i++) {

        if (lines.get(i).isEmpty()) {
          numLines.add(0);
        } else {
          Integer temp = Integer.valueOf(lines.get(i));
          numLines.add(temp);
        }

      }
      int allLinesSum = 0;
      Integer elf = 1;
      for (int i = 0; i < numLines.size(); i++) {
        if (0 == numLines.get(i)) {
          map.put(elf, allLinesSum);
          elf++;
          allLinesSum = 0;
        }
        if (0 != numLines.get(i)) {
          allLinesSum = allLinesSum + numLines.get(i);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return map;
  }

  public static int topThreeCaloriesSum(String filePath) {
    // Find how many calories delivered by the top three elves who carried most calories:
    HashMap<Integer, Integer> elvesCalories = hashMapFromFile(filePath);
    ArrayList<Integer> sortedCalorieSums = sortTheMapByValues(elvesCalories);
    Integer[] topThree = new Integer[]{0, 0, 0};
    int sumOfTopThree = 0;

    for (int i = 0; i < topThree.length; i++) {
      for (int j = sortedCalorieSums.size() - 1 - i; 0 < j; j--) {
        if (sortedCalorieSums.get(j) > topThree[i]) {
          topThree[i] = sortedCalorieSums.get(j);
        }
      }
    }

    for (int i = 0; i < topThree.length; i++) {
      sumOfTopThree = sumOfTopThree + topThree[i];
    }

    return sumOfTopThree;
  }

  public static ArrayList<Integer> sortTheMapByValues(HashMap<Integer, Integer> map) {
    ArrayList<Integer> list = new ArrayList<>();
    HashMap<Integer, Integer> sortedMap = new HashMap<>();

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      list.add(entry.getValue());
    }

    Collections.sort(list);

    return list;
  }
}
