import java.util.NoSuchElementException;

/**
 * A LinkedList class which is composed of cell nodes where each stores a cellphone object inside.
 *
 * @author Razvan Ivan
 * @see CellPhone
 * @since 2019/04/10
 */
public class CellList {

    /**
     * An inner class which acts as the node for the CellList and will store the given cellphone objects (one in each node).
     *
     * @author Razvan Ivan
     * @since 2019/04/10
     */
    public class CellNode{//We should make this inner class private in order to avoid privacy leak that can allow a user to freely modify a LinkedList.
        private CellPhone phone;//Cellphone object which will be what is stored inside each CellNode.
        private CellNode nextNode;//A pointer to the next CellNode.

        /**
         * A default constructor which creates a CellNode with the phone set to null and the pointer to the next CellNode set to null.
         */
        public CellNode(){
            phone = null;
            nextNode = null;
        }


        /**
         * A parametrized constructor which creates a CellNode with the phone and pointer set to the given values.
         *
         * @param phone a CellPhone object
         * @param node a pointer to the next CellNode
         */
        public CellNode(CellPhone phone, CellNode node){//This constructor does not make a copy of the given cellphone before assigning it to the node. This causes a privacy leak since the CellPhone may be modified directly by the user which also affects the list without wanting to.
            this.phone = phone;
            nextNode = node;
        }

        /**
         * A copy constructor which creates a CellNode by making a deep copy of an existing CellNode.
         *
         * @param node a CellNode that will be used to create the copy
         */
        public CellNode(CellNode node){
            CellNode cn = new CellNode(node.phone, node.nextNode);//Creating a new CellNode with the attributes of the given CellNode to ensure that there is no privacy leak.

            this.phone = cn.phone;
            this.nextNode = cn.nextNode;
        }

        /**
         * A clone method which returns a copy of a CellNode by calling the copy constructor of the CellNode with the calling object as an argument.
         *
         * @return an object of the class Object that is a copy of the CellNode calling object
         */
        @Override
        protected Object clone(){
            return new CellNode(this);
        }

        /**
         * An accessor method which returns the CellPhone object of the CellNode calling object.
         *
         * @return the CellPhone object of the calling object
         */
        public CellPhone getPhone() {
            return phone;
        }

        /**
         * A mutator method which sets the CellPhone attribute of the CellNode calling object to the given Cellphone object.
         *
         * @param phone the new CellPhone object
         */
        public void setPhone(CellPhone phone) {//Privacy leak here since the given CellPhone object is directly used instead of a copy of it which can create unwanted changes to the CellList when changes are made to the given CellPhone.
            this.phone = phone;
        }

        /**
         * An accessor method which returns the CellNode pointer of the CellNode calling object that points to the next CellNode in the CellList.
         *
         * @return the CellNode pointer of the CellNode calling object that points to the next CellNode in the CellList
         */
        public CellNode getNode() {
            return nextNode;
        }

        /**
         * A mutator method which sets the CellNode pointer attribute of the CellNode calling object to the given CellNode object.
         *
         * @param node the new CellNode pointer
         */
        public void setNode(CellNode node) {//Privacy leak here since the given CellNode object is directly used instead of a copy of it which can create unwanted changes to the CellList when changes are made to the given CellNode.
            nextNode = node;
        }

        /**
         * A toString method which displays the information of the CellNode calling object by printing its CellPhone object and its CellNode pointer.
         *
         * @return a string which displays the information of the CellNode calling object by printing its CellPhone object and its CellNode pointer.
         */
        @Override
        public String toString() {
            return "CellNode{" +
                    "phone=" + phone +
                    ", node=" + nextNode +
                    '}';
        }
    }

    private CellNode head;//A CellNode pointer which points to the head or the first CellNode in the CellList.

    private int size;//An int which holds the size of the CellList.

    /**
     * A default constructor which creates a CellList with the head set to null and the size set to 0.
     */
    public CellList(){
        head = null;
        size = 0;
    }

    /**
     * A copy constructor which creates a CellList by making a deep copy of an existing CellList.
     *
     * @param oldList a CellList that will be used to create the copy
     */
    public CellList(CellList oldList){

        //An if statement which checks to see if the given list is null. If it is then make the head point to null and set the size to 0.
        if(oldList == null){
            System.out.println("Given list is null. The list created will be an empty list.");
            head = null;
            size = 0;
        }

        //An if statement which checks to see if the given list is null. If it is then make the head point to null and set the size to 0.
        else if(oldList.head == null){
            head = null;
            size = 0;
        }

        //Getting to this else means the given list exists and is not empty.
        else{
            head = new CellNode(oldList.head);//The head of our new list now points to a cell node that is identical to the head of the old list.
            size = 1;//Size is increased to 1 since the head of our new list now actually points to the only cell node present in the new list.
            CellNode oldListPointer, newListPointerFollower, newListPointerEnd;//Creating three temporary pointers that will help to copy the old list.
            oldListPointer = oldList.head.nextNode;//One of the pointers (oldListPointer) is in charge of pointing to the cell node of the old list that is to be copied. And so, since the head of the old list has already been copied, we want that pointer to point to the cell node that follows.
            newListPointerFollower = head;//One of the pointers (newListPointerFollower) follows each new node from the new list that gets created. It now points to the head of the new list which is the cell node that just got created.
            newListPointerEnd = null;//One of the pointers (newListPointerEnd) always points to the last node created (excluding the head). And so, for now, it points at nothing.

            while(oldListPointer != null){//Condition which means stop whenever the pointer that points to the node cell to be copied of the old list points to null i.e. there are no more cell nodes to copy.
                newListPointerEnd = new CellNode(oldListPointer.phone, oldListPointer.nextNode);//Creating a copy of the cell node from the old list that our old list pointer is pointing to. Then we make our end pointer of the new list point to that node.
                newListPointerFollower.nextNode = newListPointerEnd;//We link the previous node in the new list to the newly created node (line above) so that it is added to the new list.
                newListPointerEnd.nextNode = null;//We make the newly created cell node point to null since we assume it is the last node in the new list for now.
                newListPointerFollower = newListPointerEnd;//We make the follower pointer of the new list point to the newly created node since the job of that pointer is to point to the newly created node. However, we only change the follower pointer once the previous cell node it was pointing to was connected to the newly create cell node.
                oldListPointer = oldListPointer.nextNode;//We move the pointer to the next cell node of the old list that we need to copy.
                size++;//We increase the size of the new list by once since a new cell node was just created.
            }

            oldListPointer = newListPointerFollower = newListPointerEnd = null;//Once the copy has been created, we make all the temporary nodes point to null.
        }
    }

    /**
     * A method which adds a CellNode object to the start of the calling CellList.
     *
     * @param cp a CellPhone object which will be stored inside the CellNode that will be added
     */
    public void addToStart(CellPhone cp){//There is a privacy leak since the CellNode is created directly with the given CellPhone object and not a copy of it.
        head = new CellNode(cp, head);//Making the head point to the newly created CellNode
        System.out.println("\nNew cell node was added successfully at the start with cellphone " + cp + ". Old size of the linked list was " + size + ".");
        size++;//Increasing the size of the CellList by one.
        System.out.println("New size is " + size + ".");
    }

    /**
     * A method which inserts (to the left) a CellNode inside the calling CellList.
     *
     * @param cp a CellPhone object which will be stored inside the CellNode that will be added
     * @param index an int which indicates the position at which the adding must be done
     */
    public void insertAtIndex(CellPhone cp, int index){//Multiple privacy leaks which happen when calling the addToStart method and when creating a CellNode directly with the given CellPhone object and not a copy of it.

        //An if statement which checks if the given CellPhone object is null. If it is then it no adding will be done and the method will return.
        if(cp == null){
            System.out.println("\nGiven cellphone is null and will not be added to the list.");
            return;
        }

        //A try-catch block which to see if the given index is valid or not. If it is not then it will throw and exception which will be caught.
        try{

            //If the index is smaller than 0 or bigger than the size - 1 then the given index might not be good.
            if(index < 0 || index > (size - 1)){

                //There is one exception. If the index fits in conditional above but it is equal to zero, then it means that the
                //size of the array is 0 which means that the CellList calling object is an empty list. And so, instead of refusing
                //to add because technically index 0 does not exist in an empty array, we call the addToStart method and add
                //a CellNode with the given CellPhone at the beginning of the CellList which is what the user probably wanted to do.
                if(index == 0){
                    addToStart(cp);//Privacy leak from addToStart method.
                    return;
                }

                //Throws an exception if the index is not equal to 0 and if it is smaller than 0 or bigger than the size - 1.
                else
                    throw new NoSuchElementException();
            }
        }

        //Catching the exception from above and informing the user that the adding could not be done since the index is out of bounds.
        catch(NoSuchElementException e){
            System.out.println("\nNo element can be inserted at that index because the index " + index + " is out of bounds!");
            return;
        }

        //If we got here, it means that the given CellPhone object is not null, that the list is not empty, and that the index is not out of bounds.

        //If statement which checks to see if the given index is equal to 0. If it is, then we call the addToStart method which will
        //add a CellNode with the given CellPhone to the start of the CellList.
        if(index == 0){
            addToStart(cp);//Privacy leak from addToStart method.
        }

        //If we got here than the index is not 0.
        else{
            CellNode cn = head;//CellNode pointer which starts from the head but will always point to the CellNode that will come before the CellNode that may be inserted.
            CellNode ahead = cn.nextNode;//CellNode pointer which starts from the CellNode after the head but will always point to the CellNode that will come after the CellNode that may be inserted.
            CellNode newNode;//CellNode pointer which will point to the CellNode that may potentially be inserted.

            //For loop which makes the two pointers advance until the desired index. The reason as to why i starts at index - 1 and not at index
            //is because, at this point, we have already verified that the index 0 is not the desired target and so we subtract 1 from the index
            //since the first potential location has already been searched.
            for (int i = (index - 1); i > 0; i--) {
                cn = cn.nextNode;
                ahead = cn.nextNode;
            }

            //Reaching this point means that the program has reached the desired index. And so, a new CellNode will be created with the given CellPhone
            //that will point to the CellNode ahead which is the CellNode that should come after the newly inserted CellNode. Then, we take
            //CellNode cn, which is the CellNode that should come before the newly inserted CellNode, and make it point to the newly inserted node.
            newNode = new CellNode(cp, ahead);//Privacy leak since the CellNode is created using directly the given CellPhone object instead of a copy of it. This makes it so that unwanted changes can happen to the CellList when changing the given CellPhone object.
            cn.nextNode = newNode;

            System.out.println("\nNew cell node was added successfully at index " + index + " with cellphone " + cp + ". Old size of the linked list was " + size + ".");
            size++;//Increasing the size of the CellList.
            System.out.println("New size is " + size + ".");
        }
    }

    /**
     * A method which deletes the first CellNode from the calling CellList object.
     */
    public void deleteFromStart(){

        //If statement which checks to see if the head of the calling CellList object is null. If it is then the method simply returns
        //and informs the user that the deletion cannot take place.
        if(head == null){
            System.out.println("\nList is empty. Cannot delete from start.");
            return;
        }

        //Getting here means the list is not empty.
        CellNode oldHead = head;//Creating a CellNode pointer that points to the current head of the CellList calling object,
        head = head.nextNode;//Making the head pointer point to the CellNode that comes after the current head.
        oldHead.nextNode = null;//Making it so that the old head points to nothing.
        oldHead = null;//Making it so that nothing points to the old head anymore.

        System.out.println("\nCell node at the start was deleted successfully. Old size of the linked list was " + size + ".");
        size--;//Decreasing the size of the calling CellList object.
        System.out.println("New size is " + size + ".");
    }


    /**
     * A method which deletes the CellNode present at the given index of the calling CellList object.
     *
     * @param index an int which corresponds to the position at which the CellNode that needs to be deleted is
     */
    public void deleteFromIndex(int index){

        //Try-catch block which makes sure that the given index is not out of bounds. If it is, then an exception
        //is thrown and caught and the method simply returns without deleting anything.
        try{
            if(index < 0 || index > (size - 1))
                throw new NoSuchElementException();
        }

        catch(NoSuchElementException e){
            System.out.println("\nNo element can be deleted from that index because the index " + index + " is out of bounds!");
            return;
        }

        //If statement which checks to see if the given index is 0. If it is, then we simply call the deleteFromStart method.
        if(index == 0){
            deleteFromStart();
        }

        //Getting here means the calling CellList object is not empty and the index is not 0 and not out of bounds.
        else{
            CellNode cn = head;//Creating a CellNode which initially points to the head but will generally point to the CellNode that is before the one we want to delete.
            CellNode ahead = cn.nextNode;//Creating a CellNode which initially points to the CellNode after the head but will generally point to the CellNode that needs to be deleted.

            //For loop which makes the two pointers advance until the desired index. The reason as to why i starts at index - 1 and not at index
            //is because, at this point, we have already verified that the index 0 is not the desired target and so we subtract 1 from the index
            //since the first potential location has already been searched.
            for(int i = (index - 1); i > 0; i--){
                cn = cn.nextNode;
                ahead = cn.nextNode;
            }

            //Reaching this point means that the program has reached the desired index. And so, the CellNode ahead will point to the CellNode that needs to be
            //deleted. In order to delete that CellNode, we first change the pointer that is pointing to it and change it to point to the CellNode that is after
            //the CellNode we want to delete. Once that is done, we make sure that the CellNode we want to delete does not point to another node and then we make
            //we delete the pointer that is pointing to the CellNode we want to delete.
            cn.nextNode = ahead.nextNode;
            ahead.nextNode = null;
            ahead = null;

            System.out.println("\nCell node at index " + index + " was deleted successfully. Old size of the linked list was " + size + ".");
            size--;//Decreasing the size of the calling CellList object.
            System.out.println("New size is " + size + ".");
        }
    }

    /**
     * A method which replaces the CellPhone of the CellNode at the given index with the given CellPhone.
     *
     * @param cp the new CellPhone object that will serve as replacement
     * @param index an int which gives us the position of the CellNode that contains the CellPhone that is to be replaced
     */
    public void replaceAtIndex(CellPhone cp, int index){//Privacy leak since we change the old CellPhone directly with the given CellPhone and not with a copy of it. This makes it so that unwanted changes can happen to the CellList when changing the given CellPhone object.

        //An if statement which checks to see if the given CellPhone object is null. If it is, the method simply returns and no replacing is done.
        if(cp == null)
            System.out.println("\nGiven cellphone is null and will not be added to the list.");

        //An else if statement which checks to see if the given index is not out of bounds once the given CellPhone object has been verified to not be null.
        else if(index < 0 || index > (size - 1))
            System.out.println("\nNo element can be replaced at that index because the index " + index + " is out of bounds!");

        //Getting here means the given CellPhone is not null and the given index is not out of bounds.
        else{
            CellNode cn = head;//Creating a CellNode pointer that initially points to the head and that will eventually point to the target CellNode as indicated by the index.

            //For loop which makes the pointer advance in the CellList until reaching the wanted index.
            for(int i = index; i > 0; i--)
                cn = cn.nextNode;

            cn.phone = cp;//Privacy leak since we change the old CellPhone directly with the given CellPhone and not with a copy of it. This makes it so that unwanted changes can happen to the CellList when changing the given CellPhone object.

            System.out.println("\nOld cellphone of the cell node at index " + index + " was replaced successfully with the new cellphone " + cp + ". Old size of the linked list was " + size + ".");
            System.out.println("New size is " + size + " (should remain unchanged).");
        }
    }

    /**
     * A method which searches the CellList calling object for a CellNode containing a CellPhone object with the given serial number
     *
     * @param serial a long which represents the serial number that the user wants to search with
     * @return a pointer to the CellNode which contains the CellPhone that corresponds to the given serial number (it may be null if no CellPhone was a match)
     */
    public CellNode find(long serial){
        int iterations;//An int variable to keep track of the number of iterations done by the method.

        //An if statement which checks if the head of the CellList calling object is null. If it is, the method simply returns null and no search is done.
        if(head == null){
            System.out.println("\nList has not been initialized yet (empty list) and therefore nothing was found. Iterations done: 0");
            return null;
        }

        //Getting here means the list is not empty.
        else{
            CellNode cn = head;//Creating a CellNode pointer that initially points to the head and that gets updated to point to the next CellNode until a CellPhone is a match or until the whole CellList has been searched.

            //A for loop which checks the serial number of the CellPhone of the current CellNode with the given serial number. If it is a match then
            //information is displayed to the user and a pointer to the CellNode that contains the matched CellPhone is returned. Else, the pointer
            //is updated to point to the next CellNode. This process repeats itself until there is a match or until all the list has been searched.
            for(iterations = 0; iterations < size; iterations++){
                if(cn.phone.getSerialNum() == serial){
                    System.out.println("\nA phone with the serial number " + serial + " was found at index " + iterations + ". A pointer to it has been returned.");
                    System.out.println("Iterations done: " + (iterations + 1));
                    return cn;
                }

                cn = cn.nextNode;//Updating the CellNode pointer to point to the next CellNode.
            }

            //Reaching here means no match was found in the CellList and thus we inform the user of this fact and return null.
            System.out.println("\nThe whole list was searched and no phone was found with the serial number " + serial + ". Iterations done: " + (iterations + 1));
            return null;
        }
    }

    /**
     * A method which searches the CellList calling object for a CellNode that contains a CellPhone with a serial number that matches the one given by the user and returns true or false depending on it there was a match or not
     *
     * @param serial a long which represents the serial number given by the user that is used for the search
     * @return a boolean which represents whether a match was found or not
     */
    public boolean contains(long serial){
        int iterations;//An int variable to keep track of the number of iterations done by the method.

        //An if statement which checks if the head of the CellList calling object is null. If it is, the method simply returns false and no search is done.
        if(head == null){
            System.out.println("\nList has not been initialized yet (empty list) and therefore nothing was found. Iterations done: 0");
            return false;
        }

        //Getting here means the list is not empty.
        else{
            CellNode cn = head;//Creating a CellNode pointer that initially points to the head and that gets updated to point to the next CellNode until a CellPhone is a match or until the whole CellList has been searched.

            //A for loop which checks the serial number of the CellPhone of the current CellNode with the given serial number. If it is a match then
            //information is displayed to the user and the method returns true. Else, the pointer
            //is updated to point to the next CellNode. This process repeats itself until there is a match or until all the list has been searched.
            //If there is no match after having searched the whole CellList then the method returns false;
            for(iterations = 0; iterations < size; iterations++){
                if(cn.phone.getSerialNum() == serial){
                    System.out.println("\nThe linked list contains a phone with the serial number " + serial + ". Iterations done: " + (iterations + 1));
                    return true;
                }

                cn = cn.nextNode;//Updating the CellNode pointer to point to the next CellNode.
            }

            //Reaching here means no match was found in the CellList and thus we inform the user of this fact and return false.
            System.out.println("\nThe whole list was searched and no phone was found with the serial number " + serial + ". Iterations done: " + (iterations + 1));
            return false;
        }
    }

    /**
     * A method which prints every node of the calling CellList object.
     */
    public void showContents(){

        //An if statement which checks the size of the calling CellList object. If its size is 0,
        //then we know that the list is empty and that there is nothing to display and so the
        //method returns.
        if(size == 0)
            System.out.println("\nList is empty. Nothing to display.");

        else{
            CellNode cn = head;//Creating a CellNode pointer that initially points to the head and that gets updated to point to the next CellNode until the last one.

            System.out.println("\nThe current size of the list is " + size + ". Here are the contents of that list:");
            System.out.print("=======================================================================");

            //A for loop which is used to iterate through every CellNode and to display the contents of each.
            for(int i = 0; i < size; i++){

                //An if statement which breaks the line once every three CellNodes have been displayed on the current line.
                if(i%3 == 0)
                    System.out.println();

                //Prints the CellPhone object of the CellNode followed by an arrow which represents the CellNode pointer
                //that points to the next CellNode.
                System.out.print(cn.phone + " ----> ");

                //An if statement which checks to see if the CellNode that follows the current CellNode is null. If it is
                //then an X will be printed to signify null and the method will return. Else, the for loop will update
                //the CellNode pointer that is used to traverse the CellList.
                if(cn.nextNode == null){
                    System.out.print(" X\n");
                    return;
                }


                else{
                    cn = cn.nextNode;//Updating the CellNode pointer to point to the next CellNode if that next CellNode is not null.
                }
            }

            System.out.println();//Breaking the line once every CellNode in the CellList has been printed.
        }
    }

    /**
     * A method which checks for equality between two CellList objects by comparing the CellPhone objects that their CellNodes contain
     *
     * @param list a CellList object which is the object that will be compared to the calling CellList object
     * @return a boolean which represents the results of the equality
     */
    public boolean equals(CellList list){

        //An if statement which checks to see if the given list is null. If it is, then the method returns false.
        if(list == null){
            System.out.println("\nThe given list is null. The two lists are not equal.");
            return false;
        }

        //An else if statement which checks to see if the two lists are of the same size. If they aren't, then the method returns false.
        else if(size != list.size){
            System.out.println("\nThe two linked lists are not equal (not same size).");
            return false;
        }

        //An else if statement which checks to see if the two lists point to the same head. If they are, then the method returns true.
        //This can only happen if both lists are empty and thus both lists will have their head point to null (which is considered to be the same null).
        else if(head == list.head){
            System.out.println("\nThe two linked lists are equal (both heads are null).");
            return true;
        }

        //Getting here means the given list is not null, and that both lists have the same size which is not 0.
        else{
            CellNode firstList = head;//Creating a CellNode pointer that initially points to the head of the CellList calling object and that gets updated to point to the next CellNode until the last one.
            CellNode secondList = list.head;//Creating a CellNode pointer that initially points to the head of the given CellList and that gets updated to point to the next CellNode until the last one.

            //A for loop which iterates through both CellList objects and uses if statements to check for equality.
            for(int i = 0; i < size; i++){

                //An if statement which checks if the CellPhone of the current CellNode of the CellList calling object is equal to the CellPhone of the current CellNode of the given CellList.
                //If they aren't, then the method returns false.
                if(!(firstList.phone.equals(secondList.phone))){
                    System.out.println("\nThe two linked lists are not equal (at least one cell node differs).");
                    return false;
                }

                //An if statement which checks if the current CellNode of the CellList calling object points to the same CellNode as the the current CellNode of the given CellList.
                //If they do, then the method returns true. This can only happen when both CellNode objects point to null which means that both CellList objects have been traversed
                //completely and that all CellNodes are equal between the two CellLists.
                if(firstList.nextNode == secondList.nextNode){
                    System.out.println("\nThe two linked lists are equal (all cell nodes match).");
                    return true;
                }

                //If both CellNodes do not point to the same thing then it means that we are not at the end of both CellLists yet. This means that we must update both CellNode pointers
                //to point to their next respective CellNode.
                else{
                    firstList = firstList.nextNode;
                    secondList = secondList.nextNode;
                }
            }

            return false;//The method should never get here and so this line should not exist but the compiler complains if there is no return here
        }
    }
}