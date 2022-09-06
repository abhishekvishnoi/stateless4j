package com.github.stateless4j.sample.features;

/**
 *
 * Defines the Mehdos / Features which will be performed on each State's Transition .
 */
public class Features {

    public static void showSongsList() {
        System.out.println("Showing song list:\n\t1. La la la\n\t2. Guli guli guli\n\t3. A Ram Sam Sam");
    }

    public static void hideSongsList() {
        System.out.println("Hiding song list");
    }

    public static void consumeCoin() {
        System.out.println("Coin consumed");
    }

    public static void returnCoin() {
        System.out.println("Coin returned");
    }

    public static void showTimer() {
        System.out.println("Showing song timer");
    }

    public static void hideTimer() {
        System.out.println("Hiding song timer");
    }

    public static void playSong() {
        System.out.println("Playing selected song...");
    }

    public static void pauseSong() {
        System.out.println("Pausing...");
    }

    public static void startBlinking() {
        System.out.println("Start blinking: blink, blonk, blink, blonk...");
    }

    public static void stopBlinking() {
        System.out.println("Stop blinking");
    }


}
