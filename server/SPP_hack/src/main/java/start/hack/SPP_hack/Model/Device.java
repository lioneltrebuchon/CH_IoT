package start.hack.SPP_hack.Model;

import javax.persistence.*;

@Entity
@Table(name="`device`")
public class Device
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="`indexDevice`", length= 20)
    private int indexDevice;

    // Clé étrangère
    @OneToMany
    @JoinColumn(name="`indexSensor`")
    private Sensor indexSensor;

    public Sensor getIndexSensor() {
        return indexSensor;
    }

    public void setIndexSensor(Sensor indexSensor) {
        this.indexSensor = indexSensor;
    }
    ///

    public int getIndexDevice() {
        return indexDevice;
    }

    public void setIndexDevice(int indexDevice) {
        this.indexDevice = indexDevice;
    }


}
