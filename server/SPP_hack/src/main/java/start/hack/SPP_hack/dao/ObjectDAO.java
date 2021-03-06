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
    @Query(value="SELECT url FROM object WHERE object.object_city=:city ORDER BY object.index_object",nativeQuery = true)
    List<String> getUrlInCity(@Param("city") String city);
    @Query(value="SELECT object_street FROM object WHERE object.object_city=:city ORDER BY object.index_object",nativeQuery = true)
    List<String> getStreetInCity(@Param("city") String city);
     @Query(value="SELECT object_street FROM object WHERE object.index_object=:index",nativeQuery = true)
    String findOneS(@Param("index") int index);
    @Query(value="SELECT url FROM object WHERE object.index_object=:index",nativeQuery = true)
    String findOneU(@Param("index") int index);
}
