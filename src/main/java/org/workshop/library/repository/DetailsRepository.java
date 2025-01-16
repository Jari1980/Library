package org.workshop.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.workshop.library.entity.Details;

public interface DetailsRepository extends CrudRepository<Details, Integer> {


    Details findByEmailEquals(String email);
    Details findByNameContains(String name);
    Details findByNameIgnoreCase(String name);

}
