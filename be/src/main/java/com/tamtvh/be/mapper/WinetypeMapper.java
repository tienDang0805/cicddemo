package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.dto.WinelineDTO;
import com.tamtvh.be.dto.WinetypeDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.model.Wineline;
import com.tamtvh.be.model.Winetype;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WinetypeMapper extends AbstractMapper<WinetypeDTO, Winetype>{
    WinetypeMapper INSTANCE = Mappers.getMapper(WinetypeMapper.class);

    @Mappings({
            @Mapping(source = "winelines", target = "winelines", qualifiedByName = "toLazyListWinelineDTO"),
    })
    WinetypeDTO toDtoWithoutLists(Winetype entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "winelines", target = "winelines", ignore = true),
    })
    WinetypeDTO toDtoWithoutLists1(Winetype entity, @Context CycleAvoidingMappingContext context);

    @Override
    default WinetypeDTO toDto(Winetype entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyListWinelineDTO")
    default List<WinelineDTO> toLazyListWinelineDTO(Collection<Wineline> collection) {
        if (collection == null) {
            return null;
        }
        List<WinelineDTO> target = new ArrayList<WinelineDTO>(collection.size());
        for (Wineline wineline : collection) {
            target.add(WinelineMapper.INSTANCE.toDtoWithoutLists1(wineline, new CycleAvoidingMappingContext()));
        }
        return target;
    }
}
