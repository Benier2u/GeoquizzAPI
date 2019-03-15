package org.lpro.geoquizzplayer.boundary;

import org.lpro.geoquizzplayer.entity.Partie;
import org.springframework.data.repository.CrudRepository;

public interface PartieResource extends CrudRepository<Partie, String> {
}
