package com.sundar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sundar.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{

	
//	@Query(
//	        nativeQuery = true,
//	        value
//	        = "SELECT ea.id, ea.city, ea.state FROM gfgmicroservicesdemo.address ea join gfgmicroservicesdemo.employee e on e.id = ea.employee_id where ea.employee_id=:employeeId")
//	       Optional<Address> findAddressByEmployeeId(@Param("employeeId") Long employeeId);
}
