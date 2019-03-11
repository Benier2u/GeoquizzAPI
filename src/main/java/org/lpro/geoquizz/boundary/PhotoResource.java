package org.lpro.geoquizz.boundary;

import org.lpro.geoquizz.entity.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoResource extends CrudRepository<Photo, String> {
    List<Photo> findBySerieId(String id);
}
