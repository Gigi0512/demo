package highscoreTabelle;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HSTabelle {

    public static Path meineHST = Paths.get("src/main/resources/tabelle.csv");

    public static void addWin(String spieler1) {
        try {
            Path tempFile = Files.createTempFile(null, null);
            BufferedReader myReader = Files.newBufferedReader(meineHST);
            BufferedWriter myWriter = Files.newBufferedWriter(tempFile);
            boolean playerfound = false;

            String line;
            while ((line = myReader.readLine()) != null) {
                String[] fields = line.split(";");

                if (Objects.equals(fields[0], spieler1)) {
                    fields[1] = String.valueOf(Integer.parseInt(fields[1]) + 1);
                    fields[4] = String.valueOf(Integer.parseInt(fields[4]) + 1);
                    float result = Float.parseFloat(fields[2]) != 0 ? Float.parseFloat(fields[1]) / Float.parseFloat(fields[2]) : 1;
                    fields[3] = String.format("%.2f", result);

                    String modifiedLine = String.join(";", fields);
                    myWriter.write(modifiedLine);
                    playerfound=true;
                }
                else if (fields[0] != spieler1){
                    myWriter.write(line);
                }myWriter.newLine();
            }
            myWriter.close();
            myReader.close();
            Files.move(tempFile, meineHST, StandardCopyOption.REPLACE_EXISTING);

            if (!playerfound){
                addPlayer(spieler1);
                addWin(spieler1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void addLose(String spieler1) {
        try {
            Path tempFile = Files.createTempFile(null, null);
            BufferedReader myReader = Files.newBufferedReader(meineHST);
            BufferedWriter myWriter = Files.newBufferedWriter(tempFile);
            boolean playerfound = false;

            String line;
            while ((line = myReader.readLine()) != null) {
                String[] fields = line.split(";");

                if (Objects.equals(fields[0], spieler1)) {
                    fields[2] = String.valueOf(Integer.parseInt(fields[2]) + 1);
                    fields[4] = String.valueOf(0);
                    float result = Float.parseFloat(fields[2]) != 0 ? Float.parseFloat(fields[1]) / Float.parseFloat(fields[2]) : 1;
                    fields[3] = String.format("%.2f", result);


                    String modifiedLine = String.join(";", fields);
                    myWriter.write(modifiedLine);
                    playerfound=true;
                }
                else {
                    myWriter.write(line);
                }
                myWriter.newLine();
            }
            myWriter.close();
            myReader.close();
            Files.move(tempFile, meineHST, StandardCopyOption.REPLACE_EXISTING);

            if (!playerfound){
                addPlayer(spieler1);
                addLose(spieler1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addPlayer(String spieler1) {
        try {
            BufferedWriter meinWriter = Files.newBufferedWriter(meineHST, StandardOpenOption.APPEND);
            meinWriter.write(spieler1.toLowerCase() + ";0;0;0;0");
            meinWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String searchPlayer(String spieler1) {
        try {
            BufferedReader myReader = Files.newBufferedReader(meineHST);

            String line;
            while ((line = myReader.readLine()) != null) {
                String[] fields = line.split(";");

                if (Objects.equals(fields[0], spieler1)) {
                    myReader.close();
                    return fields[0] + " | Siege: " + fields[1] + " | Niederlagen: " + fields[2] + " | Sieg/Niederlage-Rate: " + fields[3] + "| Winstreak: " + fields[4];
                }

            }
            myReader.close();
            return spieler1+ " | Siege: 0 | Niederlagen: 0 | Sieg/Niederlage-Rate: 0 | Winstreak: 0";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getTopThreePlayers() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(meineHST)) {
            return reader.lines()
                    .map(line -> line.split(";"))
                    .filter(tokens -> tokens.length > 1 && isNumeric(tokens[1]))
                    .sorted((a, b) -> Double.compare(Double.parseDouble(b[1]), Double.parseDouble(a[1])))
                    .limit(3)
                    .map(tokens -> tokens[0])
                    .collect(Collectors.toList());
        }
    }

    public static String playerOne() {
        try {
            BufferedReader myReader = Files.newBufferedReader(meineHST);

            String line;
            while ((line = myReader.readLine()) != null) {
                String[] fields = line.split(";");

                if (Objects.equals(fields[0], getTopThreePlayers().get(0))) {
                    myReader.close();
                    return fields[0] + " | Siege: " + fields[1] + " | Niederlagen: " + fields[2] + " | Sieg/Niederlage-Rate: " + fields[3] + "| Winstreak: " + fields[4];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String playerTwo() {
        try {
            BufferedReader myReader = Files.newBufferedReader(meineHST);

            String line;
            while ((line = myReader.readLine()) != null) {
                String[] fields = line.split(";");

                if (Objects.equals(fields[0], getTopThreePlayers().get(1))) {
                    myReader.close();
                    return fields[0] + " | Siege: " + fields[1] + " | Niederlagen: " + fields[2] + " | Sieg/Niederlage-Rate: " + fields[3] + "| Winstreak: " + fields[4];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String playerThree() {
        try {
            BufferedReader myReader = Files.newBufferedReader(meineHST);

            String line;
            while ((line = myReader.readLine()) != null) {
                String[] fields = line.split(";");

                if (Objects.equals(fields[0], getTopThreePlayers().get(2))) {
                    myReader.close();
                    return fields[0] + " | Siege: " + fields[1] + " | Niederlagen: " + fields[2] + " | Sieg/Niederlage-Rate: " + fields[3] + "| Winstreak: " + fields[4];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



}












