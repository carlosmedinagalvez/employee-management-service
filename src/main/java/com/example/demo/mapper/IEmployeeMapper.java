/**
 * 
 */
package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.demo.entity.Employee;
import com.example.demo.models.dto.EmployeeDTO;

/**
 * Marks an interface or abstract class as a mapper and activates the generation
 * of a implementation of that type via MapStruct.
 * 
 * @author Daniel Manzano Borja
 * @since 21-MAY-2025
 *
 */
@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface IEmployeeMapper {

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	List<EmployeeDTO> updateDtoFromUserEntityList(List<Employee> userEntity, @MappingTarget List<EmployeeDTO> userDto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	List<EmployeeDTO> updateDtoFromUserEntityList(List<Employee> userEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	List<Employee> updateEntityFromEmployeeDtoList(List<EmployeeDTO> employeeDto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	List<Employee> updateEntityFromEmployeeDtoList(List<EmployeeDTO> employeeDto,
			@MappingTarget List<Employee> employeeEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromEmployeeDto(EmployeeDTO employeeDTO, @MappingTarget Employee employeeEntity);

	@Mappings({})
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateDtoFromEmployeeEntity(Employee employeeEntity, @MappingTarget EmployeeDTO employeeDTO);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	EmployeeDTO updateDtoFromEmployeeEntity(Employee entity);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Employee updateEntityFromEmployeeDto(EmployeeDTO employeeDTO);

}
