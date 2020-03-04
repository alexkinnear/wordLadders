import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;

public class LadderGame {
    static int MaxWordSize = 15;
    ArrayList<String>[] allList;  // Array of ArrayLists of words of each length.
    Random random;

    public LadderGame(String file) {
        random = new Random();
        allList = new ArrayList[MaxWordSize];
        for (int i = 0; i < MaxWordSize; i++)
            allList[i] = new ArrayList<String>();

        try {
            Scanner reader = new Scanner(new File("src/" + file));
            while (reader.hasNext()) {
                String word = reader.next();
                if (word.length()< MaxWordSize) allList[word.length()].add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int Priority(String word, String targetWord, int moves) {
        int correctLetters = 0;
        int incorrectLetters = 0;
        char[] currentWord = word.toCharArray();
        char[] finalWord = targetWord.toCharArray();
        for (int i = 0; i < currentWord.length; i++) {
            if (currentWord[i] == finalWord[i]) {
                correctLetters++;
            }
        }
        incorrectLetters = word.length() - correctLetters;

        return moves + incorrectLetters + 1;
    }



    public void play2(String a, String b) {
        ArrayList<String> originalList = new ArrayList<>();
        ArrayList<String> wordsUsedList = new ArrayList<>();
        int totalInserts = 0;
        if (!allList[a.length()].contains(a) || !allList[b.length()].contains(b)) {
            System.out.println("Words not found in dictionary");
            return;
        }
        if (a.length() != b.length()) {
            System.out.println("Words are not the same length");
            return;
        }
        originalList.add(a);
        if (a.length() > MaxWordSize) return;

        // initialize my priority queue
        AVLTree<WordInfo> priorityQ = new AVLTree<WordInfo>();
        WordInfo originalWord = new WordInfo(a, 0, a.length(), a);
        priorityQ.insert(originalWord);
        totalInserts++;

        ArrayList list = new ArrayList();
        ArrayList<String> l = allList[a.length()];
        list = (ArrayList) l.clone();
        System.out.println("Seeking a solution from " + a + " ->" + b + " Size of List " + list.size());

        boolean solutionFound = false;
        while (!priorityQ.isEmpty()) {
            WordInfo startWord = priorityQ.findMin();
            ArrayList<String> oneAwayList = new ArrayList<>();
            oneAwayList = oneAway(startWord.word, startWord.word.length(), wordsUsedList);

            for (int i = 0; i < oneAwayList.size(); i++) {
                String history = startWord.history + " ";
                history += oneAwayList.get(i);
                wordsUsedList.add(oneAwayList.get(i));
                WordInfo newLink = new WordInfo(oneAwayList.get(i), startWord.moves + 1, Priority(oneAwayList.get(i), b, startWord.moves), history);
                if (oneAwayList.get(i).equals(b)) {
                    solutionFound = true;
                    System.out.println(newLink.toString2() + " Total Enqueues: " + totalInserts);
                    break;
                }
                priorityQ.insert(newLink);
                totalInserts++;
            }

            if (priorityQ.findMin().word.equals(b)) {
                solutionFound = true;
                System.out.println(priorityQ.findMin() + " Total Enqueues: " + totalInserts);
                break;
            }

            originalList = (ArrayList<String>)oneAwayList.clone();
            oneAwayList.clear();
            if (priorityQ.isEmpty() && !solutionFound) {
                System.out.println("No ladder was found");
            }
            oneAwayList = oneAway(priorityQ.findMin().word, priorityQ.findMin().word.length(), oneAwayList);
            priorityQ.deleteMin();
        }
        if (!solutionFound) {
            System.out.println("No ladder was found");
        }

    }



    public void play(String a, String b) {
        // List to keep track of the words already added to queue
        ArrayList<String> original = new ArrayList<>();
        ArrayList<String> wordsUsed = new ArrayList<>();
        int totalEnqueues = 0;
        if (!allList[a.length()].contains(a) || !allList[b.length()].contains(b)) {
            System.out.println("Words not found in dictionary");
            return;
        }
        original.add(a);
        if (a.equals(b)) {
            return;
        }
        // Initialize my queue
        MyQueue<WordInfo> q = new MyQueue<>();
        WordInfo originalWord = new WordInfo(a, 0, a);
        q.enqueue(originalWord);
        totalEnqueues++;
        if (a.length() != b.length()) {
            System.out.println("Words are not the same length");
            return;
        }
        if (a.length() >= MaxWordSize) return;
        ArrayList list = new ArrayList();
        ArrayList<String> l = allList[a.length()];
        list = (ArrayList) l.clone();
        System.out.println("Seeking a solution from " + a + " ->" + b + " Size of List " + list.size());

        // Solve the word ladder problem
        boolean solutionFound = false;
        while (!q.isEmpty()) {
            WordInfo startWord = q.dequeue();
            ArrayList<String> oneAwayList = new ArrayList<>();
            oneAwayList = oneAway(startWord.word, startWord.word.length(), wordsUsed);
            for (int i = 0; i < oneAwayList.size(); i++) {
                String history = startWord.history + " ";
                history += oneAwayList.get(i);
                wordsUsed.add(oneAwayList.get(i));
                WordInfo newLink = new WordInfo(oneAwayList.get(i), startWord.moves + 1, history);
                if (oneAwayList.get(i).equals(b)) {
                    solutionFound = true;
                    System.out.println(newLink + " Total Enqueues: " + totalEnqueues);
                    break;
                }
                q.enqueue(newLink);
                totalEnqueues++;
            }
            //Uncomment the next line to see partial ladders
//            System.out.println(q.dequeue());

            original = (ArrayList<String>)oneAwayList.clone();
            oneAwayList.clear();
            if (q.isEmpty() && !solutionFound) {
                System.out.println("No ladder was found");
            }
        }

    }



    // Method to find all Strings that are one away from the input String word of length wordLength
    public ArrayList<String> oneAway(String word, int wordLength, ArrayList wordsUsed) {
        ArrayList<String> oneAwayList = new ArrayList<>();
        char[] wordArr = word.toCharArray();
        for (String item : allList[wordLength]) {
            char[] itemArr = item.toCharArray();
            int sameLetter = 0;
            for (int i = 0; i < itemArr.length; i++) {
                if (itemArr[i] == wordArr[i]) {
                    sameLetter++;
                }
            }
            if (sameLetter == wordLength - 1 && !wordsUsed.contains(item)) {
                oneAwayList.add(item);
            }
        }
        return oneAwayList;
    }



    public void play2(int len) {
        if (len >= MaxWordSize) return;
        ArrayList<String> list = allList[len];
        String a = list.get(random.nextInt(list.size()));
        String b = list.get(random.nextInt(list.size()));
        play2(a, b);

    }

    // lists given number of words of given length
    public void listWords(int howMany, int numLetters) {
        for (int i = 0; i < howMany; i++) {
            System.out.println(allList[numLetters].get(i));
        }
    }

}
