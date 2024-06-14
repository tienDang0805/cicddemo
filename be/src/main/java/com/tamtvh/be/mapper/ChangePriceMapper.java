package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.BillDTO;
import com.tamtvh.be.dto.ChangePriceDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.dto.WinelineDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Bill;
import com.tamtvh.be.model.ChangePrice;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.model.Wineline;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChangePriceMapper extends AbstractMapper<ChangePriceDTO, ChangePrice>{
    ChangePriceMapper INSTANCE = Mappers.getMapper(ChangePriceMapper.class);

    @Mappings({
            @Mapping(source = "wineline", target = "wineline", qualifiedByName = "toLazyWinelineDTO"),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),
            @Mapping(source = "staff", target = "staff", qualifiedByName = "toLazyStaffDTO"),
            @Mapping(source = "staff.MANV", target = "MANV"),
    })
    ChangePriceDTO toDtoWithoutLists(ChangePrice entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "wineline", target = "wineline", ignore = true),
            @Mapping(source = "staff", target = "staff", ignore = true),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),
            @Mapping(source = "staff.MANV", target = "MANV"),
    })
    ChangePriceDTO toDtoWithoutLists1(ChangePrice entity, @Context CycleAvoidingMappingContext context);

    @Override
    default ChangePriceDTO toDto(ChangePrice entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyWinelineDTO")
    default WinelineDTO toLazyWinelineDTO(Wineline collection) {
        if (collection == null) {
            return null;
        }
        WinelineDTO target = WinelineMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

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
}
