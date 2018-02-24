package start.hack.SPP_hack.Controller;

import com.google.gson.Gson;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import start.hack.SPP_hack.Model.Data;
import start.hack.SPP_hack.dao.ValueDAO;
import start.hack.SPP_hack.Model.Value;
import start.hack.SPP_hack.Model.Device;
import start.hack.SPP_hack.Model.Object;
import start.hack.SPP_hack.dao.DeviceDAO;
import start.hack.SPP_hack.dao.ObjectDAO;

@RestController
@RequestMapping("/rest")
public class RequestController {
    
    @Autowired
    private ValueDAO valueDAO;
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    private ObjectDAO objectDAO;
    
    @RequestMapping(method = RequestMethod.POST,path="/")
    public void insert(@RequestBody Data obj){
        if(obj!=null){
            //
            Device dev=deviceDAO.findOne(obj.getId());
            //
            Value tmp=new Value();
            tmp.setIndexDevice(dev);
            tmp.setValue(obj.getHumidity());
            tmp.setSensorName("Humidity");
            tmp.setTimeStamp(obj.getTimestamp());
            valueDAO.save(tmp);
            tmp.setValue(obj.getLight());
            tmp.setSensorName("Light");
            valueDAO.save(tmp);
            tmp.setValue(obj.getNoise());
            tmp.setSensorName("Noise");
            valueDAO.save(tmp);
            tmp.setValue(obj.getTemperature());
            tmp.setSensorName("Temperature");
            valueDAO.save(tmp);
            tmp.setValue(obj.getVibration());
            tmp.setSensorName("Vibration");
            valueDAO.save(tmp);
        }
    }
	@RequestMapping(path="/get")
	public void test(@RequestBody String sObj){
		JSONObject obj = new JSONObject(sObj);
		System.out.println("Test get");
	}
    @RequestMapping(method = RequestMethod.POST,path="/request")
    public JSONObject request(@RequestBody String sObj){
        JSONObject obj = new JSONObject(sObj);
        System.out.println(obj.toString());
        JSONObject response=new JSONObject();
            switch(obj.getInt("usecase")){
                case 0:
                    List<String> tmp= objectDAO.getStreetInCity(obj.getString("city"));
                    List<String> tmp2= objectDAO.getUrlInCity(obj.getString("city"));
                    System.out.println("Here");
                    response.append("size", tmp.size());
                    int i=0;
                    Iterator<String> it1=tmp.iterator();
                    Iterator<String> it2=tmp2.iterator();
                    while(it1.hasNext() && it2.hasNext()){
                        JSONObject oIn=new JSONObject();
                        oIn.put("street", it1.next());
                        oIn.put("url",it2.next());
                        response.put(String.valueOf(i),oIn);
                        i++;
                    }
                    break;
                default:
                    response=new JSONObject("{results:'none'}");
                    break;
            }
        return response;
    }
}
