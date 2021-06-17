package org.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.test.entity.Position;

public interface PositionRepository extends CrudRepository<Position, String> {

	Position findByName(String name);
}
