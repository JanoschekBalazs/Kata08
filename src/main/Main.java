package main;

import profiler.Stopwatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        float elapsedTime;
        stopwatch.start();

        File input = new File("wordlist.txt");
        ArrayList<String> words = read(input);

        elapsedTime = stopwatch.checkTime();
        System.out.println("Reading input took " + elapsedTime + " seconds.");
    }

    static ArrayList<String> read(File from) {
        try {
            Scanner in = new Scanner(from);
            ArrayList<String> toReturn = new ArrayList<>();
            while (in.hasNextLine())
                toReturn.add(in.nextLine());
            in.close();
            return toReturn;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return null;
        }
    }
}
