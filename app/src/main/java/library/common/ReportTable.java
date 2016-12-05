package library.common;


import java.io.Serializable;

/**
 * Data object representing a car.
 *
 * @author ISchwarz
 */
public class ReportTable implements Serializable {

    private String _report_type;
    private String _customer_name;
    private String _customer_number;
    private String _customer_sex;
    private String _total_product;
    private String _no_so;
    private String _total_item;
    private String _total_price;
    private String _status;
    private String _no_cb;

    public String get_total_member() {
        return _total_member;
    }

    public void set_total_member(String _total_member) {
        this._total_member = _total_member;
    }

    private  String _total_member;

    public String get_pic() {
        return _pic;
    }

    public void set_pic(String _pic) {
        this._pic = _pic;
    }

    private  String  _pic;

    public String get_no_tlp() {
        return _no_tlp;
    }

    public void set_no_tlp(String _no_tlp) {
        this._no_tlp = _no_tlp;
    }

    private  String _no_tlp;

    public String get_no_cb() {
        return _no_cb;
    }

    public void set_no_cb(String _no_cb) {
        this._no_cb = _no_cb;
    }


    public String get_customer_name() {
        return _customer_name;
    }

    public void set_customer_name(String _customer_name) {
        this._customer_name = _customer_name;
    }

    public String get_customer_number() {
        return _customer_number;
    }

    public void set_customer_number(String _customer_number) {
        this._customer_number = _customer_number;
    }

    public String get_customer_sex() {
        return _customer_sex;
    }

    public void set_customer_sex(String _customer_sex) {
        this._customer_sex = _customer_sex;
    }

    public String get_total_product() {
        return _total_product;
    }

    public void set_total_product(String _total_product) {
        this._total_product = _total_product;
    }

    public String get_report_type() {
        return _report_type;
    }

    public void set_report_type(String _report_type) {
        this._report_type = _report_type;
    }

    public String get_no_so() {
        return _no_so;
    }

    public void set_no_so(String _no_so) {
        this._no_so = _no_so;
    }

    public String get_total_item() {
        return _total_item;
    }

    public void set_total_item(String _total_item) {
        this._total_item = _total_item;
    }

    public String get_total_price() {
        return _total_price;
    }

    public void set_total_price(String _total_price) {
        this._total_price = _total_price;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }
}
