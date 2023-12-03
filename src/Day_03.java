import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_03 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("input/03.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Pattern noPattern = Pattern.compile("\\d+");
        Pattern gearPattern = Pattern.compile("\\*");
        String preLine = "", line = "", postLine = "";
        int sumOfEngineParts = 0, sumOfGearRatio = 0;
        boolean wasLast = false;
        while (!wasLast) {
            //Add a border of "."'s around the engine map
            preLine = line;
            line = postLine;
            if (scan.hasNext())
                postLine = "." + scan.nextLine().trim() + ".";
            else {
                postLine = ".".repeat(line.length());
                wasLast = true;
            }
            if (line.isEmpty()) continue;
            if (preLine.isEmpty()) preLine = ".".repeat(line.length());

            //Part 1
            Matcher noMatcher = noPattern.matcher(line);
            while (noMatcher.find()) {
                if (preLine.substring(noMatcher.start() - 1, noMatcher.end() + 1).matches(".*[^.0-9]+.*") ||
                        postLine.substring(noMatcher.start() - 1, noMatcher.end() + 1).matches(".*[^.0-9]+.*") ||
                        line.substring(noMatcher.start() - 1, noMatcher.start()).matches(".*[^.0-9]+.*") ||
                        line.substring(noMatcher.end(), noMatcher.end() + 1).matches(".*[^.0-9]+.*")) {

                    sumOfEngineParts += Integer.parseInt(noMatcher.group());
                }

            }

            //Part 2
            Matcher gearMatcher = gearPattern.matcher(line);
            while (gearMatcher.find()) {
                int nNo = 0, n1 = 0, n2 = 0;

                for (String curLine: new String[]{preLine, line, postLine}) {
                    noMatcher = noPattern.matcher(curLine);
                    while (noMatcher.find()) {
                        if (noMatcher.start() <= gearMatcher.end() && noMatcher.end() >= gearMatcher.start() ) {
                            nNo++;
                            if (nNo > 2) {
                                System.err.println("More than 2 numbers next to a gear " + n1 + " " + n2 + " " + noMatcher.group() );
                                System.exit(1);
                            }
                            if (n1 == 0) n1 = Integer.parseInt(noMatcher.group());
                            else n2 = Integer.parseInt(noMatcher.group());
                        }
                    }
                }
                sumOfGearRatio += n1 * n2;
            }

        }

        scan.close();
        System.out.println(sumOfEngineParts);
        System.out.println(sumOfGearRatio);
    }
}
