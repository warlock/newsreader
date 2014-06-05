package cat.ejoy.newstream.app;

/**
 * Created by josep on 03/06/14.
 */
public class Product {
    private int _id;
    private String _name, _country, _url;

    Product(int id, String name, String country, String url) {
        _id = id;
        _name = name;
        _country = country;
        _url = url;
    }
/*
    public void addNews(String name, String country, String url) {

    }

  */
    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getCountry() {
        return _country;
    }

    public String getUrl() {
        return _url;
    }
    public boolean getNewsWitdhCountry() {
        return true;
    }
    public boolean getAllCountry() {
        return true;
    }
}
