package org.lpro.geoquizzback.boundary;

import org.lpro.geoquizzback.entity.Serie;
import org.springframework.data.repository.CrudRepository;

public interface SerieResource extends CrudRepository<Serie, String> {
}
