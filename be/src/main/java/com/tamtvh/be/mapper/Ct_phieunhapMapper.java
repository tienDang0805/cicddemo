package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.Ct_phieunhapDTO;
import com.tamtvh.be.dto.PhieunhapDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.dto.WinelineDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Ct_phieunhap;
import com.tamtvh.be.model.Phieunhap;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.model.Wineline;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Ct_phieunhapMapper extends AbstractMapper<Ct_phieunhapDTO, Ct_phieunhap>{
    Ct_phieunhapMapper INSTANCE = Mappers.getMapper(Ct_phieunhapMapper.class);

    @Mappings({
            @Mapping(source = "phieunhap", target = "phieunhap", qualifiedByName = "toLazyPhieunhapDTO"),
            @Mapping(source = "wineline", target = "wineline", qualifiedByName = "toLazyWinelineDTO"),
    })
    Ct_phieunhapDTO toDtoWithoutLists(Ct_phieunhap entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "phieunhap", target = "phieunhap", ignore = true),
            @Mapping(source = "wineline", target = "wineline", ignore = true),
    })
    Ct_phieunhapDTO toDtoWithoutLists1(Ct_phieunhap entity, @Context CycleAvoidingMappingContext context);

    @Override
    default Ct_phieunhapDTO toDto(Ct_phieunhap entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyPhieunhapDTO")
    default PhieunhapDTO toLazyPhieunhapDTO(Phieunhap collection) {
        if (collection == null) {
            return null;
        }
        PhieunhapDTO target = PhieunhapMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }

    @Named("toLazyWinelineDTO")
    default WinelineDTO toLazyWinelineDTO(Wineline collection) {
        if (collection == null) {
            return null;
        }
        WinelineDTO target = WinelineMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }
}
