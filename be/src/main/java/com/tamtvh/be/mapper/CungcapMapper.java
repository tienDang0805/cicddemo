package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.BillDTO;
import com.tamtvh.be.dto.CungcapDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.dto.WinelineDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Bill;
import com.tamtvh.be.model.Cungcap;
import com.tamtvh.be.model.Provider;
import com.tamtvh.be.model.Wineline;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CungcapMapper extends AbstractMapper<CungcapDTO, Cungcap>{
    CungcapMapper INSTANCE = Mappers.getMapper(CungcapMapper.class);

    @Mappings({
            @Mapping(source = "provider", target = "provider", qualifiedByName = "toLazyProviderDTO"),
            @Mapping(source = "wineline", target = "wineline", qualifiedByName = "toLazyWinelineDTO"),
            @Mapping(source = "provider.MANCC", target = "MANCC"),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),
    })
    CungcapDTO toDtoWithoutLists(Cungcap entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "provider", target = "provider", ignore = true),
            @Mapping(source = "wineline", target = "wineline", ignore = true),
            @Mapping(source = "provider.MANCC", target = "MANCC"),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),
    })
    CungcapDTO toDtoWithoutLists1(Cungcap entity, @Context CycleAvoidingMappingContext context);

    @Override
    default CungcapDTO toDto(Cungcap entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyProviderDTO")
    default ProviderDTO toLazyBillDTO(Provider collection) {
        if (collection == null) {
            return null;
        }
        ProviderDTO target = ProviderMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

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
