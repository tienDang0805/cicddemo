package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.CungcapDTO;
import com.tamtvh.be.dto.OrderDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Cungcap;
import com.tamtvh.be.model.Order;
import com.tamtvh.be.model.Provider;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProviderMapper extends AbstractMapper<ProviderDTO, Provider>{
    ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);

    @Mappings({
            @Mapping(source = "orders", target = "orders", qualifiedByName = "toLazyListOrderDTO"),
            @Mapping(source = "cungcaps", target = "cungcaps", qualifiedByName = "toLazyListCungcapDTO"),
    })
    ProviderDTO toDtoWithoutLists(Provider entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "orders", target = "orders", ignore = true),
            @Mapping(source = "cungcaps", target = "cungcaps", ignore = true),
    })
    ProviderDTO toDtoWithoutLists1(Provider entity, @Context CycleAvoidingMappingContext context);

    @Override
    default ProviderDTO toDto(Provider entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyListOrderDTO")
    default List<OrderDTO> toLazyListOrderDTO(Collection<Order> collection) {
        if (collection == null) {
            return null;
        }
        List<OrderDTO> target = new ArrayList<OrderDTO>(collection.size());
        for (Order order : collection) {
            target.add(OrderMapper.INSTANCE.toDtoWithoutLists1(order, new CycleAvoidingMappingContext()));
        }
        return target;
    }

    @Named("toLazyListCungcapDTO")
    default List<CungcapDTO> toLazyListCungcapDTO(Collection<Cungcap> collection) {
        if (collection == null) {
            return null;
        }
        List<CungcapDTO> target = new ArrayList<CungcapDTO>(collection.size());
        for (Cungcap cungcap : collection) {
            target.add(CungcapMapper.INSTANCE.toDtoWithoutLists1(cungcap, new CycleAvoidingMappingContext()));
        }
        return target;
    }
}
