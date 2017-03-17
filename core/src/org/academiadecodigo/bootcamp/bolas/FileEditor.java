package org.academiadecodigo.bootcamp.bolas;

import java.io.*;

/**
 * Created by codecadet on 3/17/17.
 */
public class FileEditor {
    public String Loader() throws IOException {
        BufferedReader inputStream = new BufferedReader(new FileReader("core/assets/highscores.txt")); //nao consigo passar file
        String  totalLines = "";
        String thisLine = null;
        while ((thisLine = inputStream.readLine())!=null){
            totalLines = totalLines.concat(thisLine+"\n");
        }
        inputStream.close();
        return totalLines;
    }

    public void Writer(String playerName, String value)throws IOException{

        String currentScores = Loader();

        BufferedWriter outputStream = new BufferedWriter(new FileWriter("core/assets/highscores.txt"));


        outputStream.write(currentScores + playerName+" "+value);
        outputStream.flush();
        outputStream.close();
    }

}
