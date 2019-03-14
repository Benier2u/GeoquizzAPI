package org.lpro.geoquizzback.boundary;

import org.lpro.geoquizzback.entity.Partie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartieResource extends CrudRepository<Partie, String> {
    List<Partie> findBySerieId(String id);
}
