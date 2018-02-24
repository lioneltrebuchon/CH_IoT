package start.hack.SPP_hack.Model;
import javax.persistence.*;

@Entity
@Table(name="`Object`")
public class Object
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="`indexObject`", length= 20)
    private int indexObject;

    @Column(name="`object_category`")
    private String object_category;

    @Column(name="`object_street`")
    private String object_street;

    @Column(name="`object_zip`")
    private String object_zip;

    @Column(name="`object_city`")
    private String object_city;

    @Column(name="`url`")
    private String url;

    // Clé étrangère
    @OneToOne
    @JoinColumn(name="`indexDevice'")
    private Device indexDevice;

    public void setIndexDevice(Device indexDevice) {
        this.indexDevice = indexDevice;
    }

    public int getIndexObject() {
        return indexObject;
    }
    ////

    public void setIndexObject(int indexObject) {
        this.indexObject = indexObject;
    }

    public Device getIndexDevice() {
        return indexDevice;
    }


    public String getObject_category() {
        return object_category;
    }

    public void setObject_category(String object_category) {
        this.object_category = object_category;
    }

    public String getObject_street() {
        return object_street;
    }

    public void setObject_street(String object_street) {
        this.object_street = object_street;
    }

    public String getObject_zip() {
        return object_zip;
    }

    public void setObject_zip(String object_zip) {
        this.object_zip = object_zip;
    }

    public String getObject_city() {
        return object_city;
    }

    public void setObject_city(String object_city) {
        this.object_city = object_city;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Object{" +
                "object_category='" + object_category + '\'' +
                ", object_street='" + object_street + '\'' +
                ", object_zip='" + object_zip + '\'' +
                ", object_city='" + object_city + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
