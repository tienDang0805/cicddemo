package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.CustomerDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.RoleDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Customer;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Role;
import com.tamtvh.be.model.Staff;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends AbstractMapper<RoleDTO, Role>{
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mappings({
            @Mapping(source = "customers", target = "customers", qualifiedByName = "toLazyListCustomerDTO"),
            @Mapping(source = "staffs", target = "staffs", qualifiedByName = "toLazyListStaffDTO"),
    })
    RoleDTO toDtoWithoutLists(Role entity, @Context CycleAvoidingMappingContext context);

    @Override
    default RoleDTO toDto(Role entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyListStaffDTO")
    default List<StaffDTO> toLazyListStaffDTO(Collection<Staff> collection) {
        if (collection == null) {
            return null;
        }
        List<StaffDTO> target = new ArrayList<StaffDTO>(collection.size());
        for (Staff staff : collection) {
            target.add(StaffMapper.INSTANCE.toDtoWithoutLists1(staff, new CycleAvoidingMappingContext()));
        }
        return target;
    }

    @Named("toLazyListCustomerDTO")
    default List<CustomerDTO> toLazyListCustomerDTO(Collection<Customer> collection) {
        if (collection == null) {
            return null;
        }
        List<CustomerDTO> target = new ArrayList<CustomerDTO>(collection.size());
        for (Customer customer : collection) {
            target.add(CustomerMapper.INSTANCE.toDtoWithoutLists1(customer, new CycleAvoidingMappingContext()));
        }
        return target;
    }
}
