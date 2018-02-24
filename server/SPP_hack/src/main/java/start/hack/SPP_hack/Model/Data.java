package start.hack.SPP_hack.Model;

public class Data {
    //Data from captors
    public int id;
    public long timestamp;
    public float vibration;
    public float noise;
    public float humidity;
    public float temperature;
    public float light;

    //Setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getVibration() {
        return vibration;
    }

    public void setVibration(float vibration) {
        this.vibration = vibration;
    }

    public float getNoise() {
        return noise;
    }

    public void setNoise(float noise) {
        this.noise = noise;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getLight() {
        return light;
    }

    public void setLight(float light) {
        this.light = light;
    }
	
	@Override
	public String toString(){
		String msg="Object : [" + id + "," + timestamp + ","+ vibration + "," + noise + "," + humidity + "," + temperature +","+ light +"]";
		return msg;
	}
}
