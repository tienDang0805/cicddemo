package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.Ct_orderDTO;
import com.tamtvh.be.dto.OrderDTO;
import com.tamtvh.be.dto.WinelineDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Ct_order;
import com.tamtvh.be.model.Order;
import com.tamtvh.be.model.Wineline;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Ct_orderMapper extends AbstractMapper<Ct_orderDTO, Ct_order>{
    Ct_orderMapper INSTANCE = Mappers.getMapper(Ct_orderMapper.class);

    @Mappings({
            @Mapping(source = "wineline", target = "wineline", qualifiedByName = "toLazyWinelineDTO"),
            @Mapping(source = "order", target = "order", qualifiedByName = "toLazyOrderDTO"),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),
            @Mapping(source = "order.MADDH", target = "MADDH"),
    })
    Ct_orderDTO toDtoWithoutLists(Ct_order entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "wineline", target = "wineline", ignore = true),
            @Mapping(source = "order", target = "order", ignore = true),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),
            @Mapping(source = "order.MADDH", target = "MADDH"),
    })
    Ct_orderDTO toDtoWithoutLists1(Ct_order entity, @Context CycleAvoidingMappingContext context);

    @Override
    default Ct_orderDTO toDto(Ct_order entity, @Context CycleAvoidingMappingContext context) {
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

    @Named("toLazyOrderDTO")
    default OrderDTO toLazyOrderDTO(Order collection) {
        if (collection == null) {
            return null;
        }
        OrderDTO target = OrderMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }
}
