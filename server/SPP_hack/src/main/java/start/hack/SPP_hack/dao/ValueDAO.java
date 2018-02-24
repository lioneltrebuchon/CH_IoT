/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start.hack.SPP_hack.dao;

import org.springframework.data.repository.CrudRepository;
import start.hack.SPP_hack.Model.Value;
/**
 *
 * @author Hamza
 */
public interface ValueDAO extends CrudRepository<Value, Integer> {
    
}
