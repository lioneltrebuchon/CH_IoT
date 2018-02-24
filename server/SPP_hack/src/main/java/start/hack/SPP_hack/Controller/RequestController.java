package start.hack.SPP_hack.Controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import start.hack.SPP_hack.Model.Data;

@RestController
@RequestMapping("/rest")
public class RequestController {
    @RequestMapping(method = RequestMethod.GET,path="/{obj}")
    public void insert(@PathVariable("obj") String sObj){
		JSONObject obj = new JSONObject(sObj);
		System.out.println("Dans fct"); 
        System.out.println(obj.toString());
    }
	@RequestMapping(path="/get")
	public void test(@RequestBody String sObj){
		JSONObject obj = new JSONObject(sObj);
		System.out.println("Test get");
	}
    @RequestMapping(method = RequestMethod.POST,path="/request")
    public JSONObject request(@RequestBody String sObj){
        JSONObject obj = new JSONObject(sObj);
        JSONObject response=null;
        /* TODO : query DB */
        return response;
    }
}
