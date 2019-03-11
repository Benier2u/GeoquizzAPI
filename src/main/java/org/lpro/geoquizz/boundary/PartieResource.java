package org.lpro.geoquizz.boundary;

import org.lpro.geoquizz.entity.Partie;
import org.springframework.data.repository.CrudRepository;

public interface PartieResource extends CrudRepository<Partie, String> {
}
