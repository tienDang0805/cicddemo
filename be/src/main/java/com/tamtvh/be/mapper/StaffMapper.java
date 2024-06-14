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
public interface StaffMapper extends AbstractMapper<StaffDTO, Staff>{
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    @Mappings({
            @Mapping(source = "role2", target = "role"),
            @Mapping(target = "role.customers", ignore = true),
            @Mapping(target = "role.staffs", ignore = true),
            @Mapping(source = "role2.MANQ", target = "MANQ"),
            @Mapping(source = "phieudats", target = "phieudats", qualifiedByName = "toLazyListPhieuDatDTO"),
            @Mapping(source = "changeprices", target = "changeprices", qualifiedByName = "toLazyListChangepriceDTO"),
            @Mapping(source = "promotions", target = "promotions", qualifiedByName = "toLazyListPromotionDTO"),
            @Mapping(source = "bills", target = "bills", qualifiedByName = "toLazyListBillDTO"),
            @Mapping(source = "phieutras", target = "phieutras", qualifiedByName = "toLazyListPhieutraDTO"),
            @Mapping(source = "phieunhaps", target = "phieunhaps", qualifiedByName = "toLazyListPhieunhapDTO"),
            @Mapping(source = "orders", target = "orders", qualifiedByName = "toLazyListOrderDTO"),

    })
    StaffDTO toDtoWithoutLists(Staff entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "role2", target = "role", ignore = true),
            @Mapping(source = "role2.MANQ", target = "MANQ"),
            @Mapping(source = "phieudats", target = "phieudats", ignore = true),
            @Mapping(source = "changeprices", target = "changeprices", ignore = true),
            @Mapping(source = "promotions", target = "promotions", ignore = true),
            @Mapping(source = "bills", target = "bills", ignore = true),
            @Mapping(source = "phieutras", target = "phieutras", ignore = true),
            @Mapping(source = "phieunhaps", target = "phieunhaps", ignore = true),
            @Mapping(source = "orders", target = "orders", ignore = true),
    })
    StaffDTO toDtoWithoutLists1(Staff entity, @Context CycleAvoidingMappingContext context);

    @Override
    default StaffDTO toDto(Staff entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyListPhieuDatDTO")
    default List<PhieudatDTO> toLazyListPhieuDatDTO(Collection<Phieudat> collection) {
        if (collection == null) {
            return null;
        }
        List<PhieudatDTO> target = new ArrayList<PhieudatDTO>(collection.size());
        for (Phieudat phieudat : collection) {
            target.add(PhieudatMapper.INSTANCE.toDtoWithoutLists1(phieudat, new CycleAvoidingMappingContext()));
        }
        return target;
    }

    @Named("toLazyListChangepriceDTO")
    default List<ChangePriceDTO> toLazyListChangepriceDTO(Collection<ChangePrice> collection) {
        if (collection == null) {
            return null;
        }
        List<ChangePriceDTO> target = new ArrayList<ChangePriceDTO>(collection.size());
        for (ChangePrice changeprice : collection) {
            target.add(ChangePriceMapper.INSTANCE.toDtoWithoutLists1(changeprice, new CycleAvoidingMappingContext()));
        }
        return target;
    }

    @Named("toLazyListPromotionDTO")
    default List<PromotionDTO> toLazyListPromotionDTO(Collection<Promotion> collection) {
        if (collection == null) {
            return null;
        }
        List<PromotionDTO> target = new ArrayList<PromotionDTO>(collection.size());
        for (Promotion promotion : collection) {
            target.add(PromotionMapper.INSTANCE.toDtoWithoutLists1(promotion, new CycleAvoidingMappingContext()));
        }
        return target;
    }

    @Named("toLazyListBillDTO")
    default List<BillDTO> toLazyListBillDTO(Collection<Bill> collection) {
        if (collection == null) {
            return null;
        }
        List<BillDTO> target = new ArrayList<BillDTO>(collection.size());
        for (Bill bill : collection) {
            target.add(BillMapper.INSTANCE.toDtoWithoutLists1(bill, new CycleAvoidingMappingContext()));
        }
        return target;
    }

    @Named("toLazyListPhieutraDTO")
    default List<PhieutraDTO> toLazyListPhieutraDTO(Collection<Phieutra> collection) {
        if (collection == null) {
            return null;
        }
        List<PhieutraDTO> target = new ArrayList<PhieutraDTO>(collection.size());
        for (Phieutra phieutra : collection) {
            target.add(PhieutraMapper.INSTANCE.toDtoWithoutLists1(phieutra, new CycleAvoidingMappingContext()));
        }
        return target;
    }

    @Named("toLazyListPhieunhapDTO")
    default List<PhieunhapDTO> toLazyListPhieunhapDTO(Collection<Phieunhap> collection) {
        if (collection == null) {
            return null;
        }
        List<PhieunhapDTO> target = new ArrayList<PhieunhapDTO>(collection.size());
        for (Phieunhap phieunhap : collection) {
            target.add(PhieunhapMapper.INSTANCE.toDtoWithoutLists1(phieunhap, new CycleAvoidingMappingContext()));
        }
        return target;
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
}
