package org.lpro.geoquizz.boundary;

import org.lpro.geoquizz.entity.Photo;
import org.lpro.geoquizz.entity.Serie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SerieResource extends CrudRepository<Serie, String> {
}
