package start.hack.SPP_hack.Model;
import javax.persistence.*;

@Entity
@Table(name="`value`")
public class Value
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="`indexValue`", length= 20)
    private int indexValue;

    @Column(name="`timeStamp`")
    private long timeStamp;

    @Column(name="`value`")
    private float value;

    public int getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(int indexValue) {
        this.indexValue = indexValue;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "value=" + value +
                '}';
    }
}
