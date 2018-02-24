package start.hack.SPP_hack.Model;

import javax.persistence.*;

@Entity
@Table(name="`device`")
public class Device
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="`index_device`", length= 20)
    private int indexDevice;

    // Clé étrangère
    @OneToOne
    @JoinColumn(name="`index_object`")
    private Object indexObject;

    public Object getIndexObject() {
        return indexObject;
    }

    public void setIndexObject(Object indexObject) {
        this.indexObject = indexObject;
    }
    ///

    public int getIndexDevice() {
        return indexDevice;
    }

    public void setIndexDevice(int indexDevice) {
        this.indexDevice = indexDevice;
    }


}
