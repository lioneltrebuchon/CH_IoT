package start.hack.SPP_hack.Controller;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
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
            tmp.setTimeStamp(obj.getTimestamp());
            tmp.setValue(obj.getHumidity());
            tmp.setSensorName("Humidity");
            
            valueDAO.save(tmp);
            tmp=new Value();
            tmp.setIndexDevice(dev);
            tmp.setTimeStamp(obj.getTimestamp());
            tmp.setValue(obj.getLight());
            tmp.setSensorName("Light");
            valueDAO.save(tmp);
            tmp=new Value();
            tmp.setIndexDevice(dev);
            tmp.setTimeStamp(obj.getTimestamp());
            tmp.setValue(obj.getNoise());
            tmp.setSensorName("Noise");
            valueDAO.save(tmp);
            tmp=new Value();
            tmp.setIndexDevice(dev);
            tmp.setTimeStamp(obj.getTimestamp());
            tmp.setValue(obj.getTemperature());
            tmp.setSensorName("Temperature");
            valueDAO.save(tmp);
            tmp=new Value();
            tmp.setIndexDevice(dev);
            tmp.setTimeStamp(obj.getTimestamp());
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
    public String request(@RequestBody String sObj){
        JSONObject obj = new JSONObject(sObj);
        System.out.println(obj.toString());
        JSONObject response=new JSONObject();
            switch(obj.getInt("usecase")){
                case 0:
                    List<String> tmp= objectDAO.getStreetInCity(obj.getString("city"));
                    List<String> tmp2= objectDAO.getUrlInCity(obj.getString("city"));
                    
                    Iterator<String> it1=tmp.iterator();
                   // if(obj.getString("city")=="st gallen"){ //for the Demo
                        response.put("size", 6);
                        response.append("streets", objectDAO.findOneS(1));
                        response.append("urls", objectDAO.findOneU(1));
                        response.append("streets", "St. Leonhard-Str. 39");
                        response.append("urls", "https://www.mywincasa.ch/go/rent/51776f51-e0a1-4b8b-881a-8cabbcb6bd93?utm_campaign=idx&utm_source=owp&utm_medium=website");
                        response.append("streets", "Bernhardswiesweg 6");
                        response.append("urls", "https://www.mywincasa.ch/go/rent/28346116-2fb7-4d3e-a3cf-8dfe350db14a?utm_campaign=idx&utm_source=owp&utm_medium=website");
                        response.append("streets", "Zürcherstr. 464");
                        response.append("urls", "https://www.mywincasa.ch/go/rent/e8aed698-56e3-4768-952d-f061e3cc49df?utm_campaign=idx&utm_source=owp&utm_medium=website");
                    //}else {
                    //    response.put("size", 5);
                    //}
                    /*for(int i=0;i<5;i++){
                        System.out.println(tmp.get(i).toString());
                        response.append("streets",tmp.get(i));
                        response.append("urls",tmp2.get(i));
                    }*/
                    break;
                case 1:
                    List<Float> tmpH= valueDAO.getValueOf("Humidity");
                    List<Float> tmpV= valueDAO.getValueOf("Vibration");
                    List<Float> tmpN= valueDAO.getValueOf("Noise");
                    float meanH=0;
                    for(Float f: tmpH){
                        meanH+=f;
                    }
                    meanH=meanH/tmpV.size();
                    float meanV;
                    meanV=0;
                    for(Float f: tmpV){
                        meanV+=f;
                    }
                    meanV=meanV/tmpV.size();
                    float meanN=0;
                    for(Float f: tmpN){
                        meanN+=f;
                    }
                    meanN=meanN/tmpN.size();
                    response.put("humidity", meanH);
                    response.put("vibration",meanV);
                    response.put("noise",meanN);
                    break;
                case 2:
                    List<Float> tmpT= valueDAO.getValueOf("Temperature");
                    List<Float> tmpL= valueDAO.getValueOf("Light");
                    float meanT=0;
                    for(Float f: tmpT){
                        meanT+=f;
                    }
                    meanT=meanT/tmpT.size();
                    float meanL;
                    meanL=0;
                    for(Float f: tmpL){
                        meanL+=f;
                    }
                    meanL=meanL/tmpL.size();
                    response.put("temperature", meanT);
                    response.put("light",meanL);
                    break;
                default:
                    response=new JSONObject("{results:'none'}");
                    break;
            }
        return response.toString();
    }
    @RequestMapping(method = RequestMethod.POST,path="/uploadXls")
    public void fill(){
        Workbook workbook = null;
        try {

            workbook = Workbook.getWorkbook(new File("./Exemple1.xls")); // enregistrer le fi+chier en format Excel 97-2003
            Sheet sheet = workbook.getSheet(0); // lire la sheet Tabelle1 du fichier Excel
            Object tmp = new Object();
          for(int i= 4; i< sheet.getRows(); i++) {
              tmp=new Object();
                Cell cell1 = sheet.getCell(2, i);
                Cell cell2 = sheet.getCell(8, i);
                Cell cell3 = sheet.getCell(9, i);
                Cell cell4 = sheet.getCell(10, i);
                Cell cell5 = sheet.getCell(67, i);
                //System.out.println("Adress:" + "" + cell2.getContents() + "," + cell3.getContents() + " " + cell4.getContents());
                //System.out.println("Link:" + "" + cell5.getContents());

              try {
                  tmp.setObject_category(cell1.getContents());
                  tmp.setObject_street(cell2.getContents());
                  tmp.setObject_zip(cell3.getContents());
                  tmp.setObject_city(cell4.getContents());
                  tmp.setUrl(cell5.getContents());
                  objectDAO.save(tmp);
               }catch (Exception e){}
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {

            if (workbook != null) {
                workbook.close();
            }

        } 
    
    }
}
