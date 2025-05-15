/**
 * 
 */
package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.models.dto.EmployeeDTO;
import com.example.demo.repository.IEmployeeRepository;
import com.example.demo.util.IEmployeeMapper;

/**
 * Service class where the business methods of employee operations are
 * implemented.
 *
 * @author Daniel Manzano Borja
 * @since 14-MAY-2025
 */
@Service
public class EmployeeService implements IEmployeeService {
	
	private IEmployeeRepository iEmployeeRepository;
	private IEmployeeMapper iEmployeeMapper;

	/**
	 * Constructor that injects the iEmployeeRepository, IEmployeeMapper interfaces.
	 * 
	 * @param iUserRepository    | JPA-interface that contains the full API of CRUD Repository.
	 * @param iEmployeeMapper | Interface that activates the generation of a
	 *                        implementation of that type via MapStruct.
	 */
	public EmployeeService(IEmployeeRepository iEmployeeRepository, IEmployeeMapper iEmployeeMapper) {
		this.iEmployeeRepository = iEmployeeRepository;
		this.iEmployeeMapper = iEmployeeMapper;
	}

	/**
	 * Method getting a list of all existing employees.
	 * 
	 * @return List<{@link EmployeeDTO}> | employee list.
	 */
	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<EmployeeDTO> resultList = new ArrayList<EmployeeDTO>();
		List<Employee> entityList = iEmployeeRepository.findAll();
		iEmployeeMapper.updateDtoFromUserEntityList(entityList, resultList);
		return resultList;
	}

	/**
	 * Method that get a employee by id.
	 * 
	 * @param id | employee Id.
	 * @return {@link Employee} | employee object response.
	 */
	@Override
	public EmployeeDTO getEmployeeById(Long id) {
		EmployeeDTO result = new EmployeeDTO();
		Employee entity = new Employee();
		Optional<Employee> opt = iEmployeeRepository.findById(id);
		if (opt.isPresent()) {
			entity = opt.get();
		}
		iEmployeeMapper.updateDtoFromEmployeeEntity(entity, result);
		return result;
	}

	/**
	 * Method that inserts one or more employees in a single request.
	 * 
	 * @param {@link List<EmployeeDTO>} | employeeDTO list.
	 * @return {@link List<EmployeeDTO>} | employeeDTO list.
	 */
	@Override
	public List<EmployeeDTO> saveEmployees(List<EmployeeDTO> employeeDTOList) {
		List<EmployeeDTO> resultList = new ArrayList<EmployeeDTO>();
		List<Employee> employeeEntityList = new ArrayList<Employee>();
		iEmployeeMapper.updateEntityFromEmployeeDtoList(employeeDTOList, employeeEntityList);
		List<Employee> entityList = iEmployeeRepository.saveAll(employeeEntityList);
		if (!entityList.isEmpty()) {
			iEmployeeMapper.updateDtoFromUserEntityList(entityList, resultList);
		}
		return resultList;

	}

	/**
	 * Method to partially update an employee.
	 * 
	 * @param {id} | Employee ID
	 * @param {@link EmployeeDTO} | employeeDTO object request.
	 * @return {@link EmployeeDTO} | employeeDTO object response.
	 */
	@Override
	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
		Employee entityEmployee = iEmployeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
		setEmployeePatch(employeeDTO, entityEmployee);
		EmployeeDTO result = new EmployeeDTO();
		Employee entity = new Employee();
		entity = iEmployeeRepository.save(entityEmployee);
		iEmployeeMapper.updateDtoFromEmployeeEntity(entity, result);
		return result;
	}
	
	private void setEmployeePatch(EmployeeDTO employeeDTO, Employee entityEmployee) {
		if(employeeDTO.getAge() != null) {
			entityEmployee.setAge(employeeDTO.getAge());
        }
        if(employeeDTO.getDateOfBirth() != null) {
        	entityEmployee.setDateOfBirth(employeeDTO.getDateOfBirth());
        }
        
        if(employeeDTO.getFirstName() != null) {
			entityEmployee.setFirstName(employeeDTO.getFirstName());
        }
        if(employeeDTO.getGender() != null) {
        	entityEmployee.setGender(employeeDTO.getGender());
        }
        
        if(employeeDTO.getMaternalLastName() != null) {
			entityEmployee.setMaternalLastName(employeeDTO.getMaternalLastName());
        }
        if(employeeDTO.getPaternalLastName() != null) {
        	entityEmployee.setPaternalLastName(employeeDTO.getPaternalLastName());
        }
        
        if(employeeDTO.getPosition() != null) {
			entityEmployee.setPosition(employeeDTO.getPosition());
        }
        if(employeeDTO.getSecondName() != null) {
        	entityEmployee.setSecondName(employeeDTO.getSecondName());
        }
	}
	
	
	/**
	 * Method that delete an employee by id.
	 * 
	 * @param {@link EmployeeDTO} | Object employeeDTO.
	 * @return {@link Employee} | employee object response.
	 */
	public boolean deleteEmployee(Long id) {
		return iEmployeeRepository.findById(id).map(user -> {
			iEmployeeRepository.delete(user);
			return true;
		}).orElse(false);
	}

}
