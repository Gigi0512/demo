package highscoreTabelle;

import spielerPackage.Spieler;

import java.io.*;
import java.nio.file.*;
import java.util.Objects;

public class HSTabelle {

    public Path meineHST = Paths.get("Spielentwurf/highscoreTable/tabelle.csv");

    public void addWin(Spieler spieler1) {
        try {
            Path tempFile = Files.createTempFile(null, null);
            BufferedReader myReader = Files.newBufferedReader(meineHST);
            BufferedWriter myWriter = Files.newBufferedWriter(tempFile);
            boolean playerfound = false;

            String line;
            while ((line = myReader.readLine()) != null) {
                String[] fields = line.split(";");

                if (Objects.equals(fields[0], spieler1.getName())) {
                    fields[1] = String.valueOf(Integer.parseInt(fields[1]) + 1);
                    fields[4] = String.valueOf(Integer.parseInt(fields[4]) + 1);
                    fields[3] = String.valueOf(Float.parseFloat(fields[2]) != 0 ? Float.parseFloat(fields[1]) / Float.parseFloat(fields[2]) : 1);


                    String modifiedLine = String.join(";", fields);
                    myWriter.write(modifiedLine);
                    playerfound=true;
                }
                else if (fields[0] != spieler1.getName()){
                    myWriter.write(line);
                }
                myWriter.newLine();
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


    public void addLose(Spieler spieler1) {
        try {
            Path tempFile = Files.createTempFile(null, null);
            BufferedReader myReader = Files.newBufferedReader(meineHST);
            BufferedWriter myWriter = Files.newBufferedWriter(tempFile);
            boolean playerfound = false;

            String line;
            while ((line = myReader.readLine()) != null) {
                String[] fields = line.split(";");

                if (Objects.equals(fields[0], spieler1.getName())) {
                    fields[2] = String.valueOf(Integer.parseInt(fields[2]) + 1);
                    fields[4] = String.valueOf(0);
                    fields[3] = String.valueOf(Float.parseFloat(fields[2]) != 0 ? Float.parseFloat(fields[1]) / Float.parseFloat(fields[2]) : 1);


                    String modifiedLine = String.join(";", fields);
                    myWriter.write(modifiedLine);
                    playerfound=true;
                }
                else if (fields[0] != spieler1.getName()){
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

    public void addPlayer(Spieler spieler1) {
        try {
            BufferedWriter meinWriter = Files.newBufferedWriter(meineHST, StandardOpenOption.APPEND);

            meinWriter.newLine();
            meinWriter.write("\n" + spieler1.getName() + ";0;0;0;0");
            meinWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}












