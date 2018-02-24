package start.hack.SPP_hack.Controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import start.hack.SPP_hack.Model.Data;

@RestController
public class RequestController {
    @RequestMapping(method = RequestMethod.POST,path="/")
    public void insert(@RequestBody Data obj){
        System.out.println(obj.toString());
    }

    @RequestMapping(method = RequestMethod.POST,path="/request")
    public JSONObject request(@RequestBody String sObj){
        JSONObject obj = new JSONObject(sObj);
        JSONObject response=null;
        /* TODO : query DB */
        return response;
    }
}
