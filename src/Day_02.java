import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_02 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("input/02.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String gameRegex = "Game (\\d+):";
        Pattern gamePattern = Pattern.compile(gameRegex);

        String ballRegex = "(\\d+)\\s*(blue|red|green)";
        Pattern ballPattern = Pattern.compile(ballRegex);

        int sumOfValidGames = 0;
        int sumOfPowers = 0;

        while (scan.hasNext()) {
            String game = scan.nextLine();
            String[] sets = game.split(";");

            Matcher gameMatcher = gamePattern.matcher(game);
            int gameNo = 0;
            if(gameMatcher.find()){
                gameNo = Integer.parseInt(gameMatcher.group(1));
            }

            //System.out.println("Game: " + gameNo);
            boolean isValid = true;
            int maxRed = 0, maxGreen = 0, maxBlue = 0;

            for (String set : sets) {
                Matcher ballMatcher = ballPattern.matcher(set);
                int red = 0, green = 0, blue = 0;

                while (ballMatcher.find()) {
                    int count = Integer.parseInt(ballMatcher.group(1));
                    String color = ballMatcher.group(2);

                    switch (color) {
                        case "blue":
                            blue = count;
                            if (blue > maxBlue) maxBlue = blue;
                            break;
                        case "red":
                            red = count;
                            if (red > maxRed) maxRed = red;
                            break;
                        case "green":
                            green = count;
                            if (green > maxGreen) maxGreen = green;
                            break;
                    }
                }

                //System.out.println("red: " + red + " - green: " + green + " - blue: " + blue);

                if(!(red <= 12 && green <= 13 && blue <= 14)) {
                    isValid = false;
                }
            }

            sumOfPowers += maxBlue * maxGreen * maxRed;

            if(isValid){
                sumOfValidGames += gameNo;
            }

        }
        scan.close();
        System.out.println(sumOfValidGames);
        System.out.println(sumOfPowers);

    }
}
