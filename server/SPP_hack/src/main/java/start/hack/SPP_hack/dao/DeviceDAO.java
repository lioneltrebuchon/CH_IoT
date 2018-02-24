/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start.hack.SPP_hack.dao;
import start.hack.SPP_hack.Model.Device;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Hamza
 */
public interface DeviceDAO extends CrudRepository<Device, Integer>{
    @Query("SELECT e.indexDevice FROM Device e WHERE e.indexObject = :indexO")
    List<Integer> getDevicesOf(@Param("indexO") int index);
}
