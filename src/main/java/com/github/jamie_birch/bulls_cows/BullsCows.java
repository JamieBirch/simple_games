package com.github.jamie_birch.bulls_cows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BullsCows {
    public static void main(String[] args) {
        System.out.println("Game start!");

        byte difficulty = 4;

        ArrayList<Byte> goal = generate(difficulty);

        System.out.println("Try number");
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        while(run){
            int input = scanner.nextInt();
            run = !compare(input, goal);
        }
        System.out.println("You won!");
        scanner.close();
    }

    private static ArrayList<Byte> generate(byte difficulty){

        ArrayList<Byte> expected = (getBytes());
        Collections.shuffle(expected);
        ArrayList<Byte> goal = new ArrayList<>(expected.subList(0, difficulty));

//        System.out.println(goal);

        return goal;
    }

    private static ArrayList<Byte> getBytes() {
        int size = 10;
        Byte[] numbers = new Byte[size];
        for(int i = 0; i < size; i++){
            numbers[i] = (byte)i;
        }
        return new ArrayList<>(Arrays.asList(numbers));
    }

    private static boolean compare(int input, ArrayList<Byte> goal){

        int bulls = 0;
        int cows = 0;

        for(int i = 0, d = (int)(Math.pow(10, goal.size()-1)); i < goal.size(); i++, d/=10){

            int n = input/d;
            int r = input%d;
            if(checkBulls(n, i, goal)){
                bulls++;
            } else if (checkCows(n, goal)){
                cows++;
            }
            input = r;

        }
        System.out.printf("%d bulls, %d cows%n", bulls, cows);
        return bulls == 4;
    }

    private static boolean checkBulls(int n, int i, ArrayList<Byte> goal) {
        return goal.get(i) == n;
    }

    private static boolean checkCows(int n, ArrayList<Byte> goal) {
        for(int i = 0; i < goal.size(); i++){
            if(goal.get(i) == n) return true;
        }
        return false;
    }


}
