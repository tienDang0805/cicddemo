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
public interface PhieutraMapper extends AbstractMapper<PhieutraDTO, Phieutra>{
    PhieutraMapper INSTANCE = Mappers.getMapper(PhieutraMapper.class);

    @Mappings({
            @Mapping(source = "bill", target = "bill", qualifiedByName = "toLazyBillDTO"),
            @Mapping(source = "bill.MAHD", target = "MAHD"),
            @Mapping(source = "staff", target = "staff", qualifiedByName = "toLazyStaffDTO"),
            @Mapping(source = "ct_phieutras", target = "ct_phieutras", qualifiedByName = "toLazyListCtPhieutraDTO"),
    })
    PhieutraDTO toDtoWithoutLists(Phieutra entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "bill", target = "bill", ignore = true),
            @Mapping(source = "bill.MAHD", target = "MAHD"),
            @Mapping(source = "staff", target = "staff", ignore = true),
            @Mapping(source = "ct_phieutras", target = "ct_phieutras", ignore = true),
    })
    PhieutraDTO toDtoWithoutLists1(Phieutra entity, @Context CycleAvoidingMappingContext context);

    @Override
    default PhieutraDTO toDto(Phieutra entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
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

    @Named("toLazyListCtPhieutraDTO")
    default List<Ct_phieutraDTO> toLazyListCtPhieutraDTO(Collection<Ct_phieutra> collection) {
        if (collection == null) {
            return null;
        }
        List<Ct_phieutraDTO> target = new ArrayList<Ct_phieutraDTO>(collection.size());
        for (Ct_phieutra ct_phieutra : collection) {
            target.add(Ct_phieutraMapper.INSTANCE.toDtoWithoutLists1(ct_phieutra, new CycleAvoidingMappingContext()));
        }
        return target;
    }
}
