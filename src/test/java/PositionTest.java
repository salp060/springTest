import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.test.Application;
import org.test.entity.Employee;
import org.test.entity.Person;
import org.test.entity.Position;
import org.test.repository.EmployeeRepository;
import org.test.repository.PositionRepository;
import org.test.service.EmployeeService;
import org.test.service.PositionService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Application.class, EmployeeService.class, PositionService.class })
@WebMvcTest
public class PositionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeRepository employeeRepository;

	@MockBean
	private PositionRepository positionRepository;

	@Test
	public void getAllPosition() throws Exception {
		List<Position> positions = new ArrayList<>();
		Position position = new Position("Manager");
		List<Employee> employees = new ArrayList<>();
		Employee entity = new Employee();
		entity.setPerson(new Person("Test", "Test"));
		entity.setPosition(new Position("Manager"));
		employees.add(entity);
		position.setEmployees(employees);
		positions.add(position);
		given(positionRepository.findAll()).willReturn(positions);
		this.mockMvc.perform(get("/position")).andExpect(status().isOk())
				.andExpect(jsonPath("[0].employees[0].name", is("Test")));
	}
}
