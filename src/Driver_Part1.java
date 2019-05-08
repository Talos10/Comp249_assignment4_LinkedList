import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class which creates a sub-dictionary of a file given by the user.
 *
 * @author Razvan Ivan
 * @since 2019/04/10
 */
public class Driver_Part1 {

    /**
     * Main method which reads every word of the file, processes it, and adds it to the sub-dictionary if need be.
     *
     * @author Razvan Ivan
     * @since 2019/04/10
     */
    public static void main(String[] args) {

        PrintWriter pw = null;//For writing to the sub-dictionary file.
        Scanner sc = new Scanner(System.in);//Used first to ask user for the name of the input file and then used to read from that input file.
        String fileName;//Name of the file that the program will process.
        ArrayList<String> dictionary = new ArrayList<>();//An array list that will contain all the unique and accepted entries for the dictionary.
        String word;//A string which will contain the current word that is to be processed.
        char [] letters = {'0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//An array of chars used in order for the program to know when to move from one section of the dictionary (letter A) to the next section (letter B).
        int index = 0;//Index variable that is used to keep track of at what letter the program currently is when creating the dictionary.

        //Prints out a welcome message to the users.
        System.out.println("\n************************************************************************************************");
        System.out.println("\nWelcome to the best sub-dictionary creating software out there: the SUB-DICTIONARY-CREATER 9000!");
        System.out.println("\n************************************************************************************************");

        //Asks the user to enter the name of the file that will be processed.
        System.out.println("\nPlease enter the name of the file you wish to create a sub-dictionary of: ");
        fileName = sc.nextLine();

        //Initializing the Scanner and PrintWriter objects to the file input and the file output (the created dictionary) respectively.
        try{
            sc = new Scanner(new FileInputStream(fileName));
            pw = new PrintWriter(new FileOutputStream("SubDictionary.txt"));
        }

        catch (FileNotFoundException e){
            System.out.println("Error! File could not be opened/created. Exiting program");
            System.exit(-1);
        }

        //A while loop which does all the word processing and then adds the processed word to the ArrayList if it matches the criteria.
        //The while loop goes from one word to the next until there are no more words.
        while(sc.hasNext()){
            word = sc.next();//Storing the current word that is about to be processed.

            //An if statement which checks to see if the current word that the program has reached is already in the sub-dictionary (the ArrayList) and checks for it in all caps since
            //all the entries of the ArrayList are stored in all caps. If the word is already there then the program skips it since no word is added twice to the dictionary. If it is
            //not there then the program can begin to process the current word.
            if(!dictionary.contains(word.toUpperCase())){

                //Checking to see if the current word contains an interrogation sign. If it does then remove it from the word.
                //Regardless if the character is there or not, the word moves to the next if statement.
                if(word.indexOf('?') >= 0)
                    word = word.substring(0, word.indexOf('?'));

                //Checking to see if the current word contains a colon. If it does then remove it from the word.
                //Regardless if the character is there or not, the word moves to the next if statement.
                if(word.indexOf(':') >= 0)
                    word = word.substring(0, word.indexOf(':'));

                //Checking to see if the current word contains an apostrophe. If it does then remove it from the word.
                //Regardless if the character is there or not, the word moves to the next if statement.
                if(word.indexOf('’') >= 0)
                    word = word.substring(0, word.indexOf('’'));

                //Checking to see if the current word contains a comma. If it does then remove it from the word.
                //Regardless if the character is there or not, the word moves to the next if statement.
                if(word.indexOf(',') >= 0)
                    word = word.substring(0, word.indexOf(','));

                //Checking to see if the current word contains an equal sign. If it does then remove it from the word.
                //Regardless if the character is there or not, the word moves to the next if statement.
                if(word.indexOf('=') >= 0)
                    word = word.substring(0, word.indexOf('='));

                //Checking to see if the current word contains a semi-colon. If it does then remove it from the word.
                //Regardless if the character is there or not, the word moves to the next if statement.
                if(word.indexOf(';') >= 0)
                    word = word.substring(0, word.indexOf(';'));

                //Checking to see if the current word contains an exclamation sign. If it does then remove it from the word.
                //Regardless if the character is there or not, the word moves to the next if statement.
                if(word.indexOf('!') >= 0)
                    word = word.substring(0, word.indexOf('!'));

                //Checking to see if the current word contains a period. If it does then remove it from the word.
                //Regardless if the character is there or not, the word moves to the next if statement.
                if(word.indexOf('.') >= 0)
                    word = word.substring(0, word.indexOf('.'));

                //Checking to see if the current word contains any numbers. If it does then the whole word is ignored.
                if(word.matches(".*\\d.*"))
                    word = "";

                //Checking to see if the current word is of length one (since we do not want the word to be a single
                //character except for the letters A and I. If the current word is of length one and is not the letter A or I then
                //the word is ignored.
                if(word.length() == 1 && !word.toUpperCase().matches("A") && !word.toUpperCase().matches("I"))
                    word = "";

                //Checking to see if the current word is an ignored word (i.e. is an empty string) or if the current word
                //is already in the dictionary (the ArrayList). If either of these are true then we do not add the current word
                //to the dictionary. Otherwise, we add it in all caps and we move on to the next word if there is any.
                if(!word.equals("") && !dictionary.contains(word.toUpperCase()))
                    dictionary.add(word.toUpperCase());
            }
        }

        //Naturally sorting the ArrayList in alphabetical order.
        dictionary.sort(null);

        //Writing to the sub-dictionary file the size of the dictionary.
        pw.print("The document produced this sub-dictionary, which includes " + dictionary.size() + " entries.");

        //A for loop which writes to the output file every entry of the dictionary. However, sections are created
        //so that each letter of the alphabet has it's own section.
        for (int i = 0; i < dictionary.size(); i++){

            //An if statement which checks each word of the dictionary (ArrayList) against the current section
            //(the letter) at which the program is. If the current word starts by the same letter as the current
            //section letter than we skip this if statement and print the current word to the output file since
            //the word is going to be printed in the right section. However, if the current word does not start by the same letter
            //as the current section letter than it means that we need to move on to the next section (to the next letter)
            //which will make the program go into the next for loop. This for loop checks against all the remaining sections
            //of the dictionary that remain to be done and stops when the first letter of the current word matches a certain
            //section. It then prints the header for the new section and then breaks out of the for loop in order to print
            //the current word to the output file. If you have noticed, the letters array (which contains all the letters
            //of the alphabet which are used to correctly separate the dictionary) starts with a 0. The 0 is not important since
            //we could've put anything other than one of the letters of the alphabet in caps. The reason for this additional
            //random character in the beginning of the array is for the special case that happens for the first word of the
            //dictionary (ArrayList) that needs to be printed. When the program gets to the first word that needs to be printed,
            //it shouldn't print it right away since we need to created a header with the current section before printing the current
            //word. And so, the 0 is there to make sure that the first letter of the first word is not equal to the first character
            //of the letters array which will allow us to print a header for the section before we print the first word.
            if(dictionary.get(i).charAt(0) != letters[index]){
                for(; index < letters.length; index++){
                    if(dictionary.get(i).charAt(0) == letters[index]){
                        pw.print("\n\n" + letters[index] + "\n==");
                        break;
                    }
                }
            }

            //Prints the current word of the dictionary (ArrayList) to the output file.
            pw.print("\n" + dictionary.get(i));
        }

        //Prints the size of the dictionary to the output file.
        System.out.println("\nNumber of entries: " + dictionary.size());

        //Closing the input and output files.
        sc.close();
        pw.close();

        //Informing the user that the sub-dictionary has been created.
        System.out.println("\nSub-dictionary has been created.");

        //Prints out a closing message.
        System.out.println("\n\n\n*******************************************************************************************");

        System.out.println("\nThank you for using the SUB-DICTIONARY-CREATER 9000!");

        System.out.println("Tell your friends about us!");

        System.out.println("\n*******************************************************************************************");

        System.out.println("\n\n\n******************************************************************");

        System.out.println("\nThis program was written by Razvan Ivan on the 10th of April 2019.");

        System.out.println("\n*************************END OF THE PROGRAM.**********************");
    }
}