package library.common;

import java.io.Serializable;
import java.util.Comparator;

public class ModelListview implements Serializable{
    private String _id;
    private String _name;
    private int _value;
    private boolean _selected;
    private String _price;
    private String _NIK;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String amount;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_value() {
        return _value;
    }

    public void set_value(int _value) {
        this._value = _value;
    }

    public boolean is_selected() {
        return _selected;
    }

    public void set_selected(boolean _selected) {
        this._selected = _selected;
    }

    public boolean isSelected()
    {
        return _selected;
    }

    public String get_price(){return  _price;}
    public long set_price(String _price){this._price = _price;
        return 0;
    }

    public String get_NIK(){return  _NIK;}
    public String set_NIK(String _NIK){this._NIK = _NIK;
        return _NIK;
    }

    /*Comparator for sorting the list by roll no*/
    public static Comparator<ModelListview> StuRollno = new Comparator<ModelListview>() {
        @Override
        public int compare(ModelListview lhs, ModelListview rhs) {
            int rollno1 = lhs.get_value();
            int rollno2 = rhs.get_value();

            //	   /*For ascending order*/
            return rollno2-rollno1;
//
//	   /*For descending order*/
//            //rollno2-rollno1;
//        }
        }

//        public int compare(Student s1, Student s2) {
//
//            int rollno1 = s1.getRollno();
//            int rollno2 = s2.getRollno();
//
//	   /*For ascending order*/
//            return rollno1-rollno2;
//
//	   /*For descending order*/
//            //rollno2-rollno1;
//        }
    };


//    public ModelListview(String id, String name, int value, boolean selected){
//        this.id = id;
//        this.name = name;
//        this.value = value;
//        this.selected = selected;
//    }
//    public String getId(){
//        return this.id;
//    }
//    public String getName(){
//        return this.name;
//    }
//    public int getValue(){
//        return this.value;
//    }
//
//    public void setSelected(boolean selected)
//    {
//        this.selected = selected;
//    }
//
//    public void setValue(int value)
//    {
//        this.value = value;
//    }
//
//    public boolean isSelected()
//    {
//        return selected;
//    }
}
