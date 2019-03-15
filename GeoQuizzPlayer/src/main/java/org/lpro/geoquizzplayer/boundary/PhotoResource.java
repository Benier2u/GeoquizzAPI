package org.lpro.geoquizzplayer.boundary;

import org.lpro.geoquizzplayer.entity.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoResource extends CrudRepository<Photo, String> {
    List<Photo> findByPartiesId(String id);
}
