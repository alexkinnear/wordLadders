public class TestLadder {
    public static void main(String[] args) {

        int RANDOMCT = 7;
        LadderGame g = new LadderGame("dictionary.txt");

        //These are the test cases from assignment 1
//        g.listWords(10, 6);  //Lists the first ten words in the dictionary of length 6 as a test.
//        g.play2("oops", "tots");
//        g.play("ride", "ands");
//        g.play2("happily", "angrily");
//        g.play("slow", "fast");
//        g.play("stone", "money");
//        g.play("biff", "axal");
//        g.play("word_not_in_dict", "play");
//        for (int i = 3; i < RANDOMCT; i++)
//            g.play2(i);


        // These are the test cases for assignment 3 showing both the brute force and A* solutions
        System.out.println("Brute force solution of kiss--> woof");
        g.play("kiss", "woof");
        System.out.println();
        System.out.println("A* solution of kiss--> woof");
        g.play2("kiss", "woof");
        System.out.println();
        System.out.println("Brute force solution of cock--> numb");
        g.play("cock", "numb");
        System.out.println();
        System.out.println("A* solution of cock--> numb");
        g.play2("cock", "numb");
        System.out.println();
        System.out.println("Brute force solution of jura--> such");
        g.play("jura", "such");
        System.out.println();
        System.out.println("A* solution of jura--> such");
        g.play2("jura", "such");
        System.out.println();
        System.out.println("Brute force solution of stet--> whey");
        g.play("stet", "whey");
        System.out.println();
        System.out.println("A* solution of stet--> whey");
        g.play2("stet", "whey");
        System.out.println();
        System.out.println("Brute force solution of rums--> numb");
        g.play("rums", "numb");
        System.out.println();
        System.out.println("A* solution of rums--> numb");
        g.play2("rums", "numb");
        System.out.println();
        System.out.println("Brute force solution of slow--> fast");
        g.play("slow", "fast");
        System.out.println();
        System.out.println("A* solution of slow--> fast");
        g.play2("slow", "fast");

    }

}
