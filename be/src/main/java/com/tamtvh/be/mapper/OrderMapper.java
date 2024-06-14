package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.Ct_orderDTO;
import com.tamtvh.be.dto.OrderDTO;
import com.tamtvh.be.dto.ProviderDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Ct_order;
import com.tamtvh.be.model.Order;
import com.tamtvh.be.model.Provider;
import com.tamtvh.be.model.Staff;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends AbstractMapper<OrderDTO, Order>{
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "ct_orders", target = "ct_orders", qualifiedByName = "toLazyListCtOrderDTO"),
            @Mapping(source = "staff", target = "staff", qualifiedByName = "toLazyStaffDTO"),
            @Mapping(source = "staff.MANV", target = "MANV"),
            @Mapping(source = "provider", target = "provider", qualifiedByName = "toLazyProviderDTO"),
            @Mapping(source = "provider.MANCC", target = "MANCC"),
    })
    OrderDTO toDtoWithoutLists(Order entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "ct_orders", target = "ct_orders", ignore = true),
            @Mapping(source = "staff", target = "staff", ignore = true),
            @Mapping(source = "staff.MANV", target = "MANV"),
            @Mapping(source = "provider", target = "provider", ignore = true),
            @Mapping(source = "provider.MANCC", target = "MANCC"),
    })
    OrderDTO toDtoWithoutLists1(Order entity, @Context CycleAvoidingMappingContext context);

    @Override
    default OrderDTO toDto(Order entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyListCtOrderDTO")
    default List<Ct_orderDTO> toLazyListCtOrderDTO(Collection<Ct_order> collection) {
        if (collection == null) {
            return null;
        }
        List<Ct_orderDTO> target = new ArrayList<Ct_orderDTO>(collection.size());
        for (Ct_order ct_order : collection) {
            target.add(Ct_orderMapper.INSTANCE.toDtoWithoutLists1(ct_order, new CycleAvoidingMappingContext()));
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

    @Named("toLazyProviderDTO")
    default ProviderDTO toLazyProviderDTO(Provider collection) {
        if (collection == null) {
            return null;
        }
        ProviderDTO target = ProviderMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }
}
