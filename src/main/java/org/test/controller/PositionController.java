package org.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.dto.PositionDTO;
import org.test.service.PositionService;

@RestController
@RequestMapping("/position")
public class PositionController {
	@Autowired
	private PositionService positionService;

	@RequestMapping(method = RequestMethod.GET)
	List<PositionDTO> get() {
		return positionService.findAll();
	}
}
