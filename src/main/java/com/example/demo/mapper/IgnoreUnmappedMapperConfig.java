/**
 * 
 */
package com.example.demo.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * How unmapped properties of the target type of a mapping should be reported.
 * 
 * @author Daniel Manzano Borja
 * @since 21-MAY-2025
 * 
 */
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IgnoreUnmappedMapperConfig {

}
