package com.biai.writingrecognition;

/**
 *
 * @author alachman
 */
public class DownsampledData implements Comparable<DownsampledData>, Cloneable {

    private boolean[][] filledPixels;
    private char letter;

    public DownsampledData(char letter, int width, int height) {
        filledPixels = new boolean[width][height];
        this.letter = letter;
    }

    public void clearImage() {
        for (int x = 0; x < filledPixels.length; x++) {
            for (int y = 0; y < filledPixels[0].length; y++) {
                filledPixels[x][y] = false;
            }
        }
    }

    @Override
    public Object clone() {
        final DownsampledData obj = new DownsampledData(this.letter, getWidth(),
                getHeight());
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                obj.setPixelData(x, y, getDataForPixel(x, y));
            }
        }
        return obj;
    }

    @Override
    public int compareTo(DownsampledData obj) {
        if (getLetter() > obj.getLetter()) {
            return 1;
        } else {
            return -1;
        }
    }

    public boolean getDataForPixel(int x, int y) {
        return filledPixels[x][y];
    }

    public int getHeight() {
        return filledPixels[0].length;
    }

    public int getWidth() {
        return filledPixels.length;
    }

    public char getLetter() {
        return letter;
    }

    public void setPixelData(int x, int y, boolean value) {
        filledPixels[x][y] = value;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "" + this.letter;
    }

}
