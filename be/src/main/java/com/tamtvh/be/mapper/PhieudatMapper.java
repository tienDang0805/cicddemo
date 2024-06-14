package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.*;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhieudatMapper extends AbstractMapper<PhieudatDTO, Phieudat>{
    PhieudatMapper INSTANCE = Mappers.getMapper(PhieudatMapper.class);

    @Mappings({
            @Mapping(source = "customer", target = "customer", qualifiedByName = "toLazyCustomerDTO"),
            @Mapping(source = "customer.MAKH", target = "MAKH"),
            @Mapping(source = "staff", target = "staff", qualifiedByName = "toLazyStaffDTO"),
            @Mapping(source = "staff.MANV", target = "MANVD"),
            @Mapping(source = "ct_phieudats", target = "ct_phieudats", qualifiedByName = "toLazyListCtPhieudatDTO"),
            @Mapping(source = "bill", target = "bill", qualifiedByName = "toLazyBillDTO"),
    })
    PhieudatDTO toDtoWithoutLists(Phieudat entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "customer", target = "customer", ignore = true),
            @Mapping(source = "customer.MAKH", target = "MAKH"),
            @Mapping(source = "staff", target = "staff", ignore = true),
            @Mapping(source = "staff.MANV", target = "MANVD"),
            @Mapping(source = "ct_phieudats", target = "ct_phieudats", ignore = true),
            @Mapping(source = "bill", target = "bill", ignore = true),
    })
    PhieudatDTO toDtoWithoutLists1(Phieudat entity, @Context CycleAvoidingMappingContext context);

    @Override
    default PhieudatDTO toDto(Phieudat entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyListCtPhieudatDTO")
    default List<Ct_phieudatDTO> toLazyListCtPhieudatDTO(Collection<Ct_phieudat> collection) {
        if (collection == null) {
            return null;
        }
        List<Ct_phieudatDTO> target = new ArrayList<Ct_phieudatDTO>(collection.size());
        for (Ct_phieudat ct_phieudat : collection) {
            target.add(Ct_phieudatMapper.INSTANCE.toDtoWithoutLists1(ct_phieudat, new CycleAvoidingMappingContext()));
        }
        return target;
    }

    @Named("toLazyBillDTO")
    default BillDTO toLazyBillDTO(Bill collection) {
        if (collection == null) {
            return null;
        }
        BillDTO target = BillMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }

    @Named("toLazyStaffDTO")
    default StaffDTO toLazyStaffDTO(Staff collection) {
        if (collection == null) {
            return null;
        }
        StaffDTO target = StaffMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }

    @Named("toLazyCustomerDTO")
    default CustomerDTO toLazyCustomerDTO(Customer collection) {
        if (collection == null) {
            return null;
        }
        CustomerDTO target = CustomerMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }
}
