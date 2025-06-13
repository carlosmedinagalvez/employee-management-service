/**
 * 
 */
package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.config.RequestLoggingFilterConfig;
import com.example.demo.entity.Employee;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.mapper.IEmployeeMapper;
import com.example.demo.models.dto.EmployeeDTO;
import com.example.demo.repository.IEmployeeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class where the business methods of employee operations are
 * implemented.
 *
 * @author Daniel Manzano Borja
 * @since 21-MAY-2025
 */
@Service
@Slf4j
public class EmployeeService implements IEmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	private IEmployeeRepository iEmployeeRepository;
	private IAuditLogService iAuditLogService;
	private IEmployeeMapper iEmployeeMapper;

	/**
	 * Constructor that injects the iEmployeeRepository, iEventLogRepository and
	 * IEmployeeMapper interfaces.
	 * 
	 * @param iEmployeeRepository | JPA-interface that contains the full API of
	 *                            employee CRUD Repository.
	 * @param iAuditLogService    | Service that saves the event to the audit log.
	 * @param iEmployeeMapper     | Activates the generation of a implementation of
	 *                            that type via MapStruct.
	 */
	public EmployeeService(IEmployeeRepository iEmployeeRepository, IAuditLogService iAuditLogService,
			IEmployeeMapper iEmployeeMapper) {
		this.iEmployeeRepository = iEmployeeRepository;
		this.iAuditLogService = iAuditLogService;
		this.iEmployeeMapper = iEmployeeMapper;
	}

	/**
	 * Method getting a list of all existing employees. Record the event
	 * successfully or unsuccessfully in the audit log.
	 * 
	 * @return List<{@link EmployeeDTO}> | employee list.
	 */
	@Override
	public List<EmployeeDTO> getAllEmployees() {
		log.info("Fetching all employees...");
		List<Employee> entityList = iEmployeeRepository.findAll();
		if (entityList.isEmpty()) {
			iAuditLogService.auditLog("LISTING_FAILED", "No employees found");
			throw new NoSuchElementException("No employees found");
		}
		iAuditLogService.auditLog("LISTING", "Employees retrieved successfully");
		return entityList.stream().map(iEmployeeMapper::updateDtoFromEmployeeEntity).toList();
	}

	/**
	 * Method that obtains an employee by ID. Record the event successfully or
	 * unsuccessfully in the audit log.
	 * 
	 * @param id | employee ID.
	 * @return {@link EmployeeDTO} | result object response.
	 */
	@Override
	public EmployeeDTO getEmployeeById(Long id) {
		log.info("Searching for employee with ID: {}...", id);
		Employee entity = iEmployeeRepository.findById(id).orElseThrow(() -> {
			log.warn("Employee ID {} not found", id);
			iAuditLogService.auditLog("QUERY_FAILED", "Attempt to query employee not found " + id);
			return new EmployeeNotFoundException(id);
		});
		EmployeeDTO result = iEmployeeMapper.updateDtoFromEmployeeEntity(entity);
		iAuditLogService.auditLog("QUERY", "Employee query by ID: " + id);
		return result;
	}

	/**
	 * Method that inserts one or more employees in a single request. Record the
	 * event successfully or unsuccessfully in the audit log.
	 * 
	 * @param {@link List<EmployeeDTO>} | employeeDTO list.
	 * @return {@link List<EmployeeDTO>} | employeeDTO list.
	 */
	@Override
	public List<EmployeeDTO> saveEmployees(List<EmployeeDTO> employeeDTOList) {
		try {
			List<Employee> employees = employeeDTOList.stream().map(iEmployeeMapper::updateEntityFromEmployeeDto)
					.collect(Collectors.toList());
			List<Employee> savedEntityList = iEmployeeRepository.saveAll(employees);
			if (!savedEntityList.isEmpty()) {
				for (Employee employee : savedEntityList) {
					iAuditLogService.auditLog("CREATION", "Employee created by ID: " + employee.getId());
				}
			}
			return savedEntityList.stream().map(iEmployeeMapper::updateDtoFromEmployeeEntity).toList();
		} catch (Exception ex) {
			iAuditLogService.auditLog("CREATION_FAILED", "Failed to save employees: " + ex.getMessage());
			throw new RuntimeException("Unable to save employees", ex);
		}
	}

	/**
	 * Method to partially update an employee. Record the event successfully or
	 * unsuccessfully in the audit log.
	 * 
	 * @param {id}   | Employee ID
	 * @param {@link EmployeeDTO} | employeeDTO object request.
	 * @return {@link EmployeeDTO} | employeeDTO object response.
	 */
	@Override
	public EmployeeDTO partialEmployeeUpdate(Long id, EmployeeDTO employeeDTO) {
		try {
			Employee existing = iEmployeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
			iEmployeeMapper.updateEntityFromEmployeeDto(employeeDTO, existing);
			Employee updated = iEmployeeRepository.save(existing);
			iAuditLogService.auditLog("UPDATE", "Updated employee with ID: " + id);
			return iEmployeeMapper.updateDtoFromEmployeeEntity(updated);
		} catch (NoSuchElementException e) {
			iAuditLogService.auditLog("UPDATE_FAILED",
					"Failed to update employee with ID: " + id + " - " + e.getMessage());
			throw e;
		} catch (Exception ex) {
			iAuditLogService.auditLog("UPDATE_FAILED", "Unexpected error: " + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Method that delete an employee by id. Record the event successfully or
	 * unsuccessfully in the audit log.
	 * 
	 * @param {id} | Employee ID
	 * @return boolean
	 */
	public boolean deleteEmployee(Long id) {
		log.info("Deleting employee with ID {}", id);
		return iEmployeeRepository.findById(id).map(user -> {
			iEmployeeRepository.delete(user);
			iAuditLogService.auditLog("DELETION", "Deleted employee by ID: " + id);
			return true;
		}).orElse(false);
	}

}
