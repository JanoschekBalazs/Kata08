package main;

import profiler.Stopwatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();

        float elapsedTime;
        ArrayList<String> words = new ArrayList<>();
        File input = new File("wordlist.txt");

        try {
            Scanner in = new Scanner(input);
            if (in.hasNextLine())
                words.add(in.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        elapsedTime = stopwatch.checkTime();
        System.out.println("Reading input took " + elapsedTime + " seconds.");
    }
}
