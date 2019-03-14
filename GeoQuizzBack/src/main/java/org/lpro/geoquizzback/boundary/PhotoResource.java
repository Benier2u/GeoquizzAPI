package org.lpro.geoquizzback.boundary;

import org.lpro.geoquizzback.entity.Photo;
import org.lpro.geoquizzback.entity.Serie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoResource extends CrudRepository<Photo, String> {
    List<Photo> findBySerieId(String id);
    List<Photo> findByPartiesId(String id);

    @Query(nativeQuery=true, value="SELECT * FROM photo p WHERE :serie = p.serie_id ORDER BY random() LIMIT 10")
    List<Photo> findRandomPhoto(@Param("serie") Serie serie);
}
