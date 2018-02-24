package start.hack.SPP_hack.Model;
import javax.persistence.*;

@Entity
@Table(name="`sensor`")
public class Sensor
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="`indexSensor`", length= 20)
    private int indexSensor;

    @Column(name="`sensorName`")
    private String sensorName;

    @Column(name="`sensorType`")
    private String sensorType;


    // Clé étrangère
    @OneToMany
    @JoinColumn(name="`indexValue`")
    private Value indexValue;

    public Value getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(Value indexValue) {
        this.indexValue = indexValue;
    }
    ///

    public int getIndexSensor() {
        return indexSensor;
    }

    public void setIndexSensor(int indexSensor) {
        this.indexSensor = indexSensor;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorName='" + sensorName + '\'' +
                ", sensorType='" + sensorType + '\'' +
                '}';
    }
}
