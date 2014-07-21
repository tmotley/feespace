package com.feestudio;

import com.feestudio.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * User: tom
 * Date: 7/20/14
 * Time: 6:18 PM
 */
public interface ClientRepo extends CrudRepository<Client, Integer> {
    public List<Client> findByName(String name);
}

