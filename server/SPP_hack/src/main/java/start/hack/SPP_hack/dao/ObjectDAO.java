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
import start.hack.SPP_hack.Model.Object;

public interface ObjectDAO extends CrudRepository<Object, Integer>{
    @Query(value="SELECT e.url FROM Object e WHERE e.city=:city ORDER BY e.Object_street",nativeQuery = true)
    List<String> getUrlInCity(@Param("city") String city);
    @Query(value="SELECT e.Object_street FROM Object e WHERE e.city=:city ORDER BY e.Object_street",nativeQuery = true)
    List<String> getStreetInCity(@Param("city") String city);
}
