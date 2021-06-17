package org.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.dto.EmployeeDTO;
import org.test.dto.PositionDTO;
import org.test.repository.PositionRepository;

@Component
public class PositionService {

	@Autowired
	private PositionRepository positionRepository;

	public List<PositionDTO> findAll() {
		List<PositionDTO> positions = new ArrayList<>();
		positionRepository.findAll().forEach(p -> {
			PositionDTO position = new PositionDTO();
			position.setId(p.getId());
			position.setName(p.getName());
			position.setEmployees(
					p.getEmployees().stream().map(e -> new EmployeeDTO(e)).sorted().collect(Collectors.toList()));
			positions.add(position);
		});
		return positions;
	}
}
