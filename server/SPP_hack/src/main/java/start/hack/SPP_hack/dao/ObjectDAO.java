/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start.hack.SPP_hack.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import start.hack.SPP_hack.Model.Object;

public interface ObjectDAO extends CrudRepository<Object, Integer>{
    List<Object> findByObject_city(String city);
}
