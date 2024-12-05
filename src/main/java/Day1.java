import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day1 {

  public static void main(String[] args) throws IOException {
    var input = Paths.get("C:\\Users\\webst\\Documents\\GitHub\\advent-of-code-2024\\src\\main\\resources\\inputs\\Day1Input.txt");

    System.out.println("day 1 part 1 answer: " + part1(input));
    System.out.println("day 1 part 2 answer: " + part2(input));
  }

  private static Integer part1(Path input) throws IOException {
    List<Integer> leftList = new ArrayList<>();
    List<Integer> rightList = new ArrayList<>();

    readFile(input, leftList, rightList);

    leftList.sort(Comparator.comparingInt(x -> x));
    rightList.sort(Comparator.comparingInt(x -> x));

    var total = 0;
    for (int i = 0; i < leftList.size(); i++) {
      var diff = Math.abs(leftList.get(i) - rightList.get(i));
      total += diff;
    }

    return total;
  }

  private static Integer part2(Path input) throws IOException {
    List<Integer> leftList = new ArrayList<>();
    List<Integer> rightList = new ArrayList<>();

    readFile(input, leftList, rightList);

    var map = new HashMap<Integer, Integer>();

    rightList.forEach(number -> {
      if (map.containsKey(number)) {
        map.put(number, map.get(number) + 1);
      } else {
        map.put(number, 1);
      }
    });

    return leftList.stream()
      .map(value -> map.containsKey(value)
        ? value * map.get(value)
        : 0)
      .reduce(0, Integer::sum);
  }

  private static void readFile(Path input, List<Integer> leftList, List<Integer> rightList) throws IOException {
    try (var inputStream = Files.lines(input)) {
      inputStream
        .map(line -> line.split("   "))
        .map(strings -> Arrays.stream(strings).map(Integer::valueOf).toList())
        .forEach(array -> {
          leftList.add(array.get(0));
          rightList.add(array.get(1));
        });
    }
  }
}
