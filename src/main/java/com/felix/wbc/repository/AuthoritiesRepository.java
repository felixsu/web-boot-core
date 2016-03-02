package com.felix.wbc.repository;

import com.felix.wbc.model.Authorities;
import org.springframework.stereotype.Repository;

/**
 * Created by fsoewito on 3/1/2016.
 */

@Repository
public interface AuthoritiesRepository extends GenericRepository <Authorities, String> {
}
