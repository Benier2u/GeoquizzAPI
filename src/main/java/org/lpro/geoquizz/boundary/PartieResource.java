package org.lpro.geoquizz.boundary;

import org.lpro.geoquizz.entity.Partie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartieResource extends CrudRepository<Partie, String> {
    List<Partie> findBySerieId(String id);
}
