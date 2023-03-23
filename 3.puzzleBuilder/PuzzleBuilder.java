package original;

import java.util.DuplicateFormatFlagsException;
import java.util.NoSuchElementException;

public class PuzzleBuilder {
    private String[] puzzleElements; //puzzle value
    private int[] wordStartRows; //x coordinate
    private int[] wordStartCol;  //y
    private int[] wordOrientation;  // 방향
    private int numberOfWords; // size
    private String name;

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;


    public PuzzleBuilder(String name, int size) { // constructor
        this.name = name;
        puzzleElements = new String[size];
        wordStartRows = new int[size];
        wordStartCol = new int[size];
        wordOrientation = new int[size];
        this.numberOfWords = 0;
    }

    public int getSize() { //getter
        return numberOfWords;
    }

    public boolean isAlreadyExist(String value, int orientation){
        for(int i = 0; i < numberOfWords; i++)
            if(puzzleElements[i].compareTo(value) == 0 && wordOrientation[i] == orientation) throw new IllegalStateException("Duplicate keyword!");

        return false;
    }
    boolean overlapWithExistingWord(String word, int row , int col,int direction){
        for(int i = 0; i < numberOfWords; i++){
            int startRow =wordStartRows[i];
            int startCol = wordStartCol[i];
            int endRow = startRow + (wordOrientation[i] == HORIZONTAL ? puzzleElements[i].length() : 0);
            int endCol = startCol + (wordOrientation[i] == VERTICAL ? puzzleElements[i].length() : 0);

            if(direction == HORIZONTAL){
                if(startRow <= row && row <= endRow && col <= endCol && col + word.length() >= startCol)
                       throw new IllegalStateException();
                else if( startCol <= col && col <= endCol && row <= endRow && row + word.length() >= startRow)
                    throw new IllegalStateException();
            }
        }
        return false;
    }

    private void uploadedWord(String word, int row, int col, int orientation) {
        puzzleElements[numberOfWords] = word;
        wordStartRows[numberOfWords] = row;
        wordStartCol[numberOfWords] = col;
        this.wordOrientation[numberOfWords++] = orientation;
    }

    public void addWord(String word, int row, int col, int orientation) {  // x,y, 방향
        if(!isAlreadyExist(word, orientation) && !overlapWithExistingWord(word,row, col, orientation)) {
            uploadedWord(word, row, col, orientation);
        }
    }


    public int getWordPosition(int coordinate, String findWord, int orientation) {
        for (int i = 0; i < numberOfWords; i++) {
            if (isContains(findWord, orientation, i)) {
                return (coordinate == 0) ? wordStartRows[i] : wordStartCol[i];
            }
        }
        throw new NoSuchElementException("The word '" + findWord + "' is not found in the puzzle.");
    }

    private boolean isContains(String value, int dir, int i) {
        return puzzleElements[i].compareTo(value) == 0 && wordOrientation[i] == dir;
    }

    public int getPoint() {
        int totalPoint = 0;
        for (int i = 0; i < numberOfWords; i++) {
            int point;
            if (wordOrientation[i] == 0)
                point = puzzleElements[i].length();
            else
                point = puzzleElements[i].length() * 2;

            totalPoint += point;
        }
        return totalPoint;
    }

    private String getReportHeader(boolean isHTML) {
        if (isHTML) {
            return "<H1> Report on Puzzle <EM> " + name + "</EM></H1>\n";
        } else {
            return "Report on Puzzle " + name + "\n";
        }
    }

    private String getReportRow(boolean isHTML, String word, int startRow, int startCol, int orientation, int result) {
        if (isHTML) {
            return "\tWord: <B>" + word + "\tPosition: [" + startRow + ", " + startCol + "]" + "\tDir: " + orientation + " </B> Point: <B> " + result + "</B>\n";
        } else {
            return "\tWord: " + word + "\tPosition: [" + startRow + ", " + startCol + "]" + "\tDir: " + orientation + " Point: " + result + "\n";
        }
    }
    public String getShortReport(boolean isHTML) {
        String value = getReportHeader(isHTML);
        int point = 0;
        for (int i = 0; i < numberOfWords; i++) {
            int result;
            if (wordOrientation[i] == 0) {
                result = puzzleElements[i].length();
            } else {
                result = puzzleElements[i].length() * 2;
            }

            value += getReportRow(isHTML, puzzleElements[i], 0, 0, wordOrientation[i], result);
            point += result;
        }

        if (isHTML) {
            value += "<HR> Total Point: <B>" + point + "</B><P>\n";
        } else {
            value += "Total Point: " + point + "\n";
        }

        return value;
    }

    public String getFullReport(boolean isHTML) {
        String value = getReportHeader(isHTML);
        int point = 0;
        for (int i = 0; i < numberOfWords; i++) {
            int result;
            if (wordOrientation[i] == 0) {
                result = puzzleElements[i].length();
            } else {
                result = puzzleElements[i].length() * 2;
            }

            value += getReportRow(isHTML, puzzleElements[i], wordStartRows[i], wordStartCol[i], wordOrientation[i], result);
            point += result;
        }

        if (isHTML) {
            value += "<HR> Total Point: <B>" + point + "</B><P>\n";
        } else {
            value += "Total Point: " + point + "\n";
        }

        return value;
    }


}
