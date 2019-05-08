import java.util.Scanner;

/**
 * A class which describes a cellphone.
 *
 * @author Razvan Ivan
 * @since 2019/04/10
 */
public class CellPhone {
    private long serialNum;//A long variable used to store the serial number of the cellphone.
    private String brand;//A string used to store the brand of the cellphone.
    private int year;//An int variable used to store the year of manufacturing of the cellphone.
    private double price;//A double variable used to store the price of the cellphone.

    /**
     * A parametrized constructor which creates a CellPhone and sets the serial number, brand, year, and price to the given values.
     *
     * @param serialNum
     * @param brand
     * @param year
     * @param price
     */
    public CellPhone(long serialNum, String brand, int year, double price) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    /**
     * A copy constructor which creates a CellPhone by making a deep copy of an existing CellPhone and by setting the serial number of the new CellPhone object with the newly given serial number (and not the same one from the given CellPhone).
     *
     * @param obj the CellPhone object that will be used to make a copy
     * @param val the new serial number of the CellPhone object
     */
    public CellPhone(CellPhone obj, long val){
        serialNum = val;
        brand = obj.brand;
        year = obj.year;
        price = obj.price;
    }

    /**
     * A clone method which returns a copy of a CellPhone object by calling the copy constructor of the CellPhone with the calling object as an argument.
     *
     * @return an object of the class Object that is a copy of the CellPhone calling object
     */
    @Override
    protected Object clone(){
        Scanner sc = new Scanner(System.in);
        long serialNumber;

        //Prompting the user to enter a new serial number for the new CellPhone.
        System.out.println("Please enter the serial number of the new clone cellphone: ");
        serialNumber = sc.nextLong();

        return new CellPhone(this, serialNumber);
    }

    /**
     * A toString method which displays the serial number, brand, price, and year of the calling CellPhone object.
     *
     * @return a string with the serial number, brand, price, and year of the calling CellPhone object
     */
    @Override
    public String toString() {
        return "[" + serialNum + ": " + brand + " " + price + "$ " + year + "]";
    }

    /**
     * A method which checks for equality between the given object of class Object and the calling CellPhone object.
     *
     * @param obj an object of the class Object that is potentially a CellPhone object
     * @return a boolean which represents the result of the equality
     */
    @Override
    public boolean equals(Object obj){

        //An if statement which checks to see if the given object is null or if the classes of both the given object and the calling object do not match.
        //If either of those apply, then the method returns false.
        if(obj == null || this.getClass() != obj.getClass())
            return false;

        //Both objects are of the same class if we get here.
        else{
            CellPhone cell = (CellPhone) obj;//Casting the given object to a CellPhone.

            return (brand.equals(cell.brand) && year == cell.year && price == cell.price);//Comparing the brand, year, and price of both objects to check for equality. The serial number is not compared since it can and should be different.
        }
    }

    /**
     * An accessor method which returns the price of the CellPhone calling object.
     *
     * @return a double which is the price of the CellPhone calling object
     */
    public double getPrice() {
        return price;
    }

    /**
     * A mutator method which sets the price attribute of the CellPhone calling object with the given value.
     *
     * @param price the new price that is to be set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * An accessor method which returns the year of manufacturing of the CellPhone calling object.
     *
     * @return an int which is the year of manufacturing of the CellPhone calling object
     */
    public int getYear() {

        return year;
    }

    /**
     * A mutator method which sets the year of manufacturing attribute of the CellPhone calling object with the given value.
     *
     * @param year the new year of manufacturing that is to be set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * An accessor method which returns the brand of the CellPhone calling object.
     *
     * @return a string which is the brand of the CellPhone calling object
     */
    public String getBrand() {

        return brand;
    }

    /**
     * A mutator method which sets the brand attribute of the CellPhone calling object with the given value.
     *
     * @param brand the new brand that is to be set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * An accessor method which returns the serial number of the CellPhone calling object.
     *
     * @return a long which is the serial number of the CellPhone calling object
     */
    public long getSerialNum() {

        return serialNum;
    }

    /**
     * A mutator method which sets the serial number attribute of the CellPhone calling object with the given value.
     *
     * @param serialNum the new serial number that is to be set
     */
    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }
}