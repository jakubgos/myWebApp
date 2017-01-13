package com.myapp.DB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Bos on 2017-01-06.
 */
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

    List<Product> findByIsBorrowed(Long isBorrowed);

}
