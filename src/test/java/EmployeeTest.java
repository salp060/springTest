import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.test.Application;
import org.test.dto.EmployeeDTO;
import org.test.entity.Employee;
import org.test.entity.Person;
import org.test.entity.Position;
import org.test.repository.EmployeeRepository;
import org.test.repository.PositionRepository;
import org.test.service.EmployeeService;
import org.test.service.PositionService;

import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Application.class, EmployeeService.class, PositionService.class })
@WebMvcTest
public class EmployeeTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeRepository employeeRepository;

	@MockBean
	private PositionRepository positionRepository;

	@Test
	public void getAllEmployees() throws Exception {
		List<Employee> employees = new ArrayList<>();
		Employee entity = new Employee();
		entity.setPerson(new Person("Test", "Test"));
		entity.setPosition(new Position("Manager"));
		employees.add(entity);
		given(employeeRepository.findAll()).willReturn(employees);
		this.mockMvc.perform(get("/employee")).andExpect(status().isOk()).andExpect(jsonPath("[0].name", is("Test")));
	}

	@Test
	public void getEmployeesByName() throws Exception {
		List<Employee> employees = new ArrayList<>();
		Employee entity = new Employee();
		entity.setPerson(new Person("Test", "Test"));
		entity.setPosition(new Position("Manager"));
		employees.add(entity);
		given(employeeRepository.findByPersonName("Test")).willReturn(employees);
		this.mockMvc.perform(get("/employee").param("name", "Test")).andExpect(status().isOk())
				.andExpect(jsonPath("[0].position", is("Manager")));
	}

	@Test
	public void createEmployee() throws Exception {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setName("Test");
		dto.setPosition("Manager");
		given(positionRepository.findByName("Manager")).willReturn(new Position("Manager"));
		Employee entity = new Employee();
		entity.setPerson(new Person("Test", "Test"));
		entity.setPosition(new Position("Manager"));
		given(employeeRepository.save(Mockito.any(Employee.class))).willReturn(entity);
		Gson gson = new Gson();
		String json = gson.toJson(dto);
		this.mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.lastName", is("Test")));
	}
}
