package helpz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {



    public static BufferedImage getSpriteAtlas(){
        BufferedImage img;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }


    //Save level to text file
    public static void createLevel(String name, int[] idArr){
        File newLevel = new File("res/" + name + ".txt");

        if(newLevel.exists()){
            System.out.println("File: " + name + " already exists!");
        } else {
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writeToFile(newLevel, idArr);
        }
    }

    private static void writeToFile(File f, int[] idArr){

        try {
            PrintWriter pw = new PrintWriter(f);
            for(Integer i : idArr){
                pw.println(i);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveLevel(String name, int[][] idArr){
        File levelFile = new File("res/" + name + ".txt");

        if(levelFile.exists()){
            writeToFile(levelFile,Utilz.twoDtoOneDintArr(idArr));
        } else {
            System.out.println("File: " + name + " does not exist!");
        }
    }

    private static ArrayList<Integer> readFromFile(File file){
        ArrayList<Integer> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                list.add((Integer.parseInt(sc.nextLine())));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public static int[][] getLevelData(String name){
        File lvlFile = new File("res/" + name + ".txt");

        if(lvlFile.exists()){
            ArrayList<Integer> list = readFromFile(lvlFile);
            return Utilz.arrayListTo2Dint(list, 20, 20);
        } else {
            System.out.println("File: " + name + " does not exist!");
            return null;
        }
    }
}
