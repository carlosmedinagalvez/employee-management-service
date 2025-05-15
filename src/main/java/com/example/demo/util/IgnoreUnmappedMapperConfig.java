/**
 * 
 */
package com.example.demo.util;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * @author dmanzano
 *
 */
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IgnoreUnmappedMapperConfig {

}
