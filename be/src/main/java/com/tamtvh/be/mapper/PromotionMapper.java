package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.Ct_promotionDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.PromotionDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Ct_promotion;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Promotion;
import com.tamtvh.be.model.Staff;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PromotionMapper extends AbstractMapper<PromotionDTO, Promotion>{
    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    @Mappings({
            @Mapping(source = "staff", target = "staff", qualifiedByName = "toLazyStaffDTO"),
            @Mapping(source = "staff.MANV", target = "MANV"),
            @Mapping(source = "ct_promotions", target = "ct_khuyenmais", qualifiedByName = "toLazyListCtPromotionDTO")
    })
    PromotionDTO toDtoWithoutLists(Promotion entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "staff", target = "staff", ignore = true),
            @Mapping(source = "staff.MANV", target = "MANV"),
            @Mapping(source = "ct_promotions", target = "ct_khuyenmais", ignore = true),
    })
    PromotionDTO toDtoWithoutLists1(Promotion entity, @Context CycleAvoidingMappingContext context);

    @Override
    default PromotionDTO toDto(Promotion entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyStaffDTO")
    default StaffDTO toLazyStaffDTO(Staff collection) {
        if (collection == null) {
            return null;
        }
        StaffDTO target = StaffMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }

    @Named("toLazyListCtPromotionDTO")
    default List<Ct_promotionDTO> toLazyListCtPromotionDTO(Collection<Ct_promotion> collection) {
        if (collection == null) {
            return null;
        }
        List<Ct_promotionDTO> target = new ArrayList<Ct_promotionDTO>(collection.size());
        for (Ct_promotion ct_promotion : collection) {
            target.add(Ct_promotionMapper.INSTANCE.toDto(ct_promotion, new CycleAvoidingMappingContext()));
        }
        return target;
    }
}
