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
public interface PhieunhapMapper extends AbstractMapper<PhieunhapDTO, Phieunhap>{
    PhieunhapMapper INSTANCE = Mappers.getMapper(PhieunhapMapper.class);

    @Mappings({
            @Mapping(source = "ct_phieunhaps", target = "ct_phieunhaps", qualifiedByName = "toLazyListCtphieunhapDTO"),
            @Mapping(source = "staff", target = "staff", qualifiedByName = "toLazyStaffDTO"),
            @Mapping(source = "order", target = "order", qualifiedByName = "toLazyOrderDTO"),
    })
    PhieunhapDTO toDtoWithoutLists(Phieunhap entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "ct_phieunhaps", target = "ct_phieunhaps", ignore = true),
            @Mapping(source = "staff", target = "staff", ignore = true),
            @Mapping(source = "order", target = "order", ignore = true),
    })
    PhieunhapDTO toDtoWithoutLists1(Phieunhap entity, @Context CycleAvoidingMappingContext context);

    @Override
    default PhieunhapDTO toDto(Phieunhap entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyListCtphieunhapDTO")
    default List<Ct_phieunhapDTO> toLazyListCtphieunhapDTO(Collection<Ct_phieunhap> collection) {
        if (collection == null) {
            return null;
        }
        List<Ct_phieunhapDTO> target = new ArrayList<Ct_phieunhapDTO>(collection.size());
        for (Ct_phieunhap ct_phieunhap : collection) {
            target.add(Ct_phieunhapMapper.INSTANCE.toDto(ct_phieunhap, new CycleAvoidingMappingContext()));
        }
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

    @Named("toLazyOrderDTO")
    default OrderDTO toLazyOrderDTO(Order collection) {
        if (collection == null) {
            return null;
        }
        OrderDTO target = OrderMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }
}
