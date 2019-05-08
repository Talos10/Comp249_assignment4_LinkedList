import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class creates a LinkedList of cellphones after reading from a file and tests out multiple special cases to ensure that all the created LinkedList methods work as intended.
 *
 * @author Razvan Ivan
 * @see CellList
 * @since 2019/04/10
 */
public class CellListUtilization {

    /**
     * Main method which stores the cellphones read from a file in a LinkedList and tests edge cases to ensure that all the created LinkedList methods work as intended.
     *
     * @author Razvan Ivan
     * @since 2019/04/10
     */
    public static void main(String[] args) {

        //Creating a few things that will help in testing. Their roles will be clear in the descriptions of the tests.
        CellList list1 = new CellList();
        CellList list2 = new CellList();
        CellList list3 = new CellList();
        CellList list4 = null;
        CellList abcList = new CellList();
        CellList list5 = null;
        Scanner sc = null;
        Scanner read = new Scanner(System.in);
        long searchSerial;
        long serial;
        String brand;
        double price;
        int year;
        CellPhone cp;
        CellPhone trial = new CellPhone(123, "generic", 123, 123);
        CellPhone test = new CellPhone(456, "test", 456, 456);
        CellPhone test2 = new CellPhone(999, "test2", 999, 999);
        CellList.CellNode node;
        CellList.CellNode cellnode = list1.new CellNode();

        //Prints out a welcome message to the users.
        System.out.println("\n************************************************************************************************");
        System.out.println("\nWelcome to the training grounds of the CellList LinkedList!");
        System.out.println("\n************************************************************************************************");

        //Printing list1 which is an empty list.
        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        //Creating the input file that contains the info of the cellphones.
        try{
            sc = new Scanner(new FileInputStream("Cell_Info.txt"));
        }

        catch (FileNotFoundException e){
            System.out.println("File could not be created/found. Exiting program.");
            System.exit(-1);
        }

        //Storing all the information of each cellphone one at a time and then creating a cellphone object with all that information.
        //Afterwards, the newly creating cellphone object gets added to the LinkedList but only if its serial number does not match
        //a cellphone that is already in the LinkedList.
        while(sc.hasNextLine()){
            serial = sc.nextLong();
            brand = sc.next();
            price = sc.nextDouble();
            year = sc.nextInt();
            cp = new CellPhone(serial, brand, year, price);

            if(!list1.contains(serial)){
                list1.addToStart(cp);
            }

            else
                System.out.println("###Phone duplicate detected with serial number " + serial + ".");
        }

        System.out.println("\n\n*****List1 has been created!*****");

        //Displaying the contents of the list1 to show that all the cellphones have been copied and that there are
        //no two cellphones with the same serial number.
        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        //Prompting the user to search for three cellphones in the LinkedList by their serial number.
        for(int i = 0; i < 3; i++){
            System.out.println("\nPlease enter the serial number of the cellphone that you wish to search for: ");
            searchSerial = read.nextLong();
            list1.find(searchSerial);
        }

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Showing list1.
        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        //Making a copy of the LinkedList that contains the cellphones so that tests can be run on both.
        System.out.println();
        System.out.println("*****Creating list2 which is a copy of list1*****");
        list2 = new CellList(list1);

        //Showing the contents of list2 which is the copy of the original LinkedList list1 with all the info
        //in order to show that the list has been copied successfully.
        System.out.println();
        list2.showContents();

        //Replacing certain nodes of list2.
        System.out.println();
        System.out.println("*****Replacing nodes in list2:*****");

        list2.replaceAtIndex(trial, 0);//Testing if replacing the head node works. Will work.

        list2.replaceAtIndex(trial, 5);//Testing if replacing the a random node (not head or end) works. Will work.

        list2.replaceAtIndex(trial, 22);//Testing if replacing the end node works. Will work.

        list2.replaceAtIndex(trial, 23);//Index out of bounds. Won't work (as intended).

        //Showing that the two lists are no longer identical and that list1 is indeed a different object than
        //list2 since the changes to list2 have not affected list1.
        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        System.out.println();
        System.out.println("List2:");
        list2.showContents();

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Checking to see if the contains method works properly.
        System.out.println();
        System.out.println("Checking if list1 contains certain serial numbers:");
        list1.contains(1119000);//First serial of list. Will work.

        list1.contains(2999900);//Random middle serial of list. Will work.

        list1.contains(3890909);//Last serial of list. Will work.

        list1.contains(123);//Non-existant serial of list. Won't work (as intended).

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Showing list1.
        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        //Checking to see if the insertAtIndex method works at index 0.
        System.out.println();
        System.out.println("List1:");
        list1.insertAtIndex(trial, 0);

        //Showing the contents to see that the node was in fact inserted at index 0.
        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        //Deleting the newly added node from the start.
        System.out.println();
        System.out.println("List1:");
        list1.deleteFromStart();

        //Showing the contents of list1 to show that deleteFromStart method has worked since the previously added node to the head has been deleted.
        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Testing insertAtIndex and deleteFrom index for the same index (index 3) in order to show that they both work as intended.
        System.out.println();
        System.out.println("List1:");
        list1.insertAtIndex(trial, 3);

        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        System.out.println();
        System.out.println("List1:");
        list1.deleteFromIndex(3);

        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Testing insertAtIndex and deleteFrom index for the last index of the list1 in order to show that they both work as intended.
        System.out.println();
        System.out.println("List1:");
        list1.insertAtIndex(trial, 22);

        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        System.out.println();
        System.out.println("List1:");
        list1.deleteFromIndex(22);

        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Testing insertAtIndex and deleteFrom index for the same index (index 23) which is out of bounds in order to show that they both won't work (as intended).
        System.out.println();
        System.out.println("List1:");
        list1.insertAtIndex(trial, 23);

        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        System.out.println();
        System.out.println("List1:");
        list1.deleteFromIndex(23);

        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Checking to see if the find method works.
        System.out.println();
        System.out.println("Finding things in list1:");

        System.out.println(list1.find(1119000).getPhone());//First serial of list. Will work.

        System.out.println(list1.find(2999900).getPhone());//Random middle serial of list. Will work.

        System.out.println(list1.find(3890909).getPhone());//Last serial of list. Won't work (as intended).

        System.out.println(list1.find(56242));//Non-existant serial of list (we don't print it because the method returns null if the phone is not found). Won't work (as intended).

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Deleting elements from list2.
        System.out.println();
        System.out.println("Deleting elements from list2:");

        System.out.println();
        System.out.println("List2 before deleting:");
        list2.showContents();

        System.out.println();
        System.out.println("Deleting elements from list2:");

        list2.deleteFromStart();

        list2.deleteFromStart();

        System.out.println();
        System.out.println("List2 after deleting:");
        list2.showContents();

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Showing list1.
        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        //Showing list2.
        System.out.println();
        System.out.println("List2:");
        list2.showContents();

        //Checking for equality between list1 and list2. It will give false.
        System.out.println();
        System.out.println("Are list1 and list2 equal? Answer:" + list1.equals(list2));

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Remaking list2 to be a copy of list1.
        System.out.println();
        System.out.println("Making list2 a copy of list1:");
        list2 = new CellList(list1);

        System.out.println();
        System.out.println("List1:");
        list1.showContents();

        System.out.println();
        System.out.println("List2:");
        list2.showContents();

        //Checking for equality between list1 and list2. It will give true.
        System.out.println();
        System.out.println("Are list1 and list2 equal now? Answer:" + list1.equals(list2));

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Showing the contents of list3. Empty list.
        System.out.println();
        System.out.println("List3:");
        list3.showContents();

        //Can't print list4 directly since it's null.
        System.out.println();
        System.out.println("List4:");
        System.out.println("List4 is a null pointer.");

        //Checking for equality between list3 (empty list) and list4 (which is null). It will give false.
        System.out.println();
        System.out.println("Are list3 and list4 equal? Answer:" + list3.equals(list4));

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Initializing list4 to an empty list.
        System.out.println();
        System.out.println("List4 is now an empty list.");

        list4 = new CellList();

        //Checking for equality between list3 (empty list) and list4 (empty list). It will give true.
        System.out.println();
        System.out.println("Are list3 and list4 equal now? Answer:" + list3.equals(list4));

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        //Adding elements to an empty list.
        System.out.println();
        System.out.println("List3 adding element:");

        list3.insertAtIndex(trial, 2);
        list3.insertAtIndex(trial, 0);

        //Checking for equality between list3 and list4 (empty list). It will give false.
        System.out.println();
        System.out.println("Are list3 and list4 equal now? Answer:" + list3.equals(list4));

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();

        System.out.println("\n\nShowcasing dangerous privacy leak coming from the find method which returns a node pointer to the real object from the LinkedList.");

        //Showcasing dangerous privacy leak coming from the find method which returns a node pointer to the real object from the LinkedList.
        list1.showContents();
        list2.showContents();

        //Storing the node reference.
        node = list2.find(2389076);

        //Changing the cellphone of that node which will modify the LinkedList (we show its contents to prove it).
        node.setPhone(trial);
        list2.showContents();

        //We set the next node of the received node to be null which effectively deletes the part of the LinkedList that is after the received node.
        node.setNode(null);
        list2.showContents();

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();


        System.out.println("\n\n\n&*&*&*&*&*&");

        //Filling up abcList with different cellphones.

        abcList.insertAtIndex(trial, 0);//Inserting at index 0 in an empty list. The way it is coded, it will simply call addToStart method.
        abcList.showContents();
        abcList.insertAtIndex(test, 0);
        abcList.showContents();
        abcList.addToStart(trial);
        abcList.showContents();
        abcList.insertAtIndex(test2, 1);
        abcList.showContents();
        abcList.replaceAtIndex(trial, 3);
        abcList.showContents();

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();


        System.out.println("\n\n\n&*&*&*&*&*&");

        //Checking if equals and deleteFromStart method work.

        abcList.insertAtIndex(trial, 0);
        abcList.showContents();
        abcList.insertAtIndex(trial, 4);
        abcList.showContents();
        abcList.equals(list5);
        list5 = new CellList();
        abcList.equals(list5);
        abcList.deleteFromStart();
        abcList.showContents();

        System.out.println("\nEnter any number to continue.");
        searchSerial = read.nextLong();


        System.out.println("\n\n\n&*&*&*&*&*&");

        //Checking if replaceAtIndex, contains, find, deleteFromIndex work.

        abcList.replaceAtIndex(test2, 0);
        abcList.showContents();
        abcList.deleteFromIndex(3);
        abcList.showContents();
        abcList.contains(222);
        abcList.find(224);
        abcList.addToStart(trial);
        abcList.showContents();
        abcList.find(123);
        abcList.contains(999);
        abcList.deleteFromIndex(1);
        abcList.showContents();
        abcList.insertAtIndex(trial,3);
        abcList.replaceAtIndex(null, 0);
        abcList.showContents();

        //More privacy leaks
        test.setBrand("haha");
        test2.setBrand("hihi");
        trial.setBrand("hoho");

        System.out.println("\n\nList1:");
        list1.showContents();

        System.out.println("\n\nList2:");
        list2.showContents();

        System.out.println("\n\nList3:");
        list3.showContents();

        System.out.println("\n\nList4:");
        list4.showContents();

        System.out.println("\n\nListabc:");
        abcList.showContents();


        sc.close();
        read.close();

        //Prints out a closing message.
        System.out.println("\n\n\n************************************************************************");

        System.out.println("\nThank you for visiting the training grounds for the CellList LinkedList!");

        System.out.println("\n************************************************************************");

        System.out.println("\n\n\n******************************************************************");

        System.out.println("\nThis program was written by Razvan Ivan on the 10th of April 2019.");

        System.out.println("\n*************************END OF THE PROGRAM.**********************");
    }
}
