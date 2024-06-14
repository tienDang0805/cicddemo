package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.BillDTO;
import com.tamtvh.be.dto.Ct_phieudatDTO;
import com.tamtvh.be.dto.Ct_phieutraDTO;
import com.tamtvh.be.dto.PhieutraDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Bill;
import com.tamtvh.be.model.Ct_phieudat;
import com.tamtvh.be.model.Ct_phieutra;
import com.tamtvh.be.model.Phieutra;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Ct_phieutraMapper extends AbstractMapper<Ct_phieutraDTO, Ct_phieutra>{
    Ct_phieutraMapper INSTANCE = Mappers.getMapper(Ct_phieutraMapper.class);

    @Mappings({
            @Mapping(source = "phieutra", target = "phieutra", qualifiedByName = "toLazyPhieutraDTO"),
            @Mapping(source = "ct_phieudat", target = "ct_phieudat", qualifiedByName = "toLazyCtphieudatDTO"),
    })
    Ct_phieutraDTO toDtoWithoutLists(Ct_phieutra entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "phieutra", target = "phieutra", ignore = true),
            @Mapping(source = "ct_phieudat", target = "ct_phieudat", ignore = true),
    })
    Ct_phieutraDTO toDtoWithoutLists1(Ct_phieutra entity, @Context CycleAvoidingMappingContext context);

    @Override
    default Ct_phieutraDTO toDto(Ct_phieutra entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyPhieutraDTO")
    default PhieutraDTO toLazyPhieutraDTO(Phieutra collection) {
        if (collection == null) {
            return null;
        }
        PhieutraDTO target = PhieutraMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }

    @Named("toLazyCtphieudatDTO")
    default Ct_phieudatDTO toLazyCtphieudatDTO(Ct_phieudat collection) {
        if (collection == null) {
            return null;
        }
        Ct_phieudatDTO target = Ct_phieudatMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }
}
