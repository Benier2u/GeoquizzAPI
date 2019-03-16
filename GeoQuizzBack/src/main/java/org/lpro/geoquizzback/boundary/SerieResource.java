package org.lpro.geoquizzback.boundary;

import org.lpro.geoquizzback.entity.Serie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SerieResource extends CrudRepository<Serie, String> {
}
