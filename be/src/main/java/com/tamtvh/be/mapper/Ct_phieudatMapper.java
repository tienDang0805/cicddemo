package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.Ct_phieudatDTO;
import com.tamtvh.be.dto.Ct_phieutraDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.WinelineDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Ct_phieudat;
import com.tamtvh.be.model.Ct_phieutra;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Wineline;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Ct_phieudatMapper extends AbstractMapper<Ct_phieudatDTO, Ct_phieudat>{
    Ct_phieudatMapper INSTANCE = Mappers.getMapper(Ct_phieudatMapper.class);

    @Mappings({
            @Mapping(source = "phieudat", target = "phieudat", qualifiedByName = "toLazyPhieuDatDTO"),
            @Mapping(source = "phieudat.MAPD", target = "MAPD"),
            @Mapping(source = "wineline", target = "wineline", qualifiedByName = "toLazyWinelineDTO"),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),
            @Mapping(source = "ct_phieutras", target = "ct_phieutras", qualifiedByName = "toLazyListCtPhieutraDTO"),
    })
    Ct_phieudatDTO toDtoWithoutLists(Ct_phieudat entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "phieudat", target = "phieudat", ignore = true),
            @Mapping(source = "phieudat.MAPD", target = "MAPD"),
            @Mapping(source = "wineline", target = "wineline", ignore = true),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),
            @Mapping(source = "ct_phieutras", target = "ct_phieutras", ignore = true),
    })
    Ct_phieudatDTO toDtoWithoutLists1(Ct_phieudat entity, @Context CycleAvoidingMappingContext context);

    @Override
    default Ct_phieudatDTO toDto(Ct_phieudat entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyPhieuDatDTO")
    default PhieudatDTO toLazyPhieuDatDTO(Phieudat collection) {
        if (collection == null) {
            return null;
        }
        PhieudatDTO target = PhieudatMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

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
