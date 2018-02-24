/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start.hack.SPP_hack.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import start.hack.SPP_hack.Model.Value;
/**
 *
 * @author Hamza
 */
public interface ValueDAO extends CrudRepository<Value, Integer> {
    @Query(value="SELECT value.value FROM value WHERE value.sensor_name=:name ORDER BY time_stamp",nativeQuery = true)
    List<Float> getValueOf(@Param("name") String name);

}
