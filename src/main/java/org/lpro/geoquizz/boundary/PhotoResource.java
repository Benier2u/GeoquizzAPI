package org.lpro.geoquizz.boundary;

import org.lpro.geoquizz.entity.Photo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoResource extends CrudRepository<Photo, String> {
    List<Photo> findBySerieId(String id);
    List<Photo> findByPartiesId(String id);

    @Query(nativeQuery=true, value="SELECT *  FROM photo ORDER BY random() LIMIT 10")
    List<Photo> findRandomPhoto();
}
