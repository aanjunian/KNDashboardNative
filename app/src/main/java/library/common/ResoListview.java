package library.common;

/**
 * Created by ASUS ZE on 15/07/2016.
 */
public class ResoListview {
    String id;
    String name;
    int value;
    int price;

    public ResoListview(String id, String name, int value,int price){
        this.id = id;
        this.name = name;
        this.value = value;
        this.price = price;
    }




    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }
    public int getPrice(){
        return this.price;
    }


    public void setValue(int value)
    {
        this.value = value;
    }
    public void setPrice(int price)
    {
        this.value = price;
    }
}
