package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.Ct_promotionDTO;
import com.tamtvh.be.dto.PromotionDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.dto.WinelineDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Ct_promotion;
import com.tamtvh.be.model.Promotion;
import com.tamtvh.be.model.Staff;
import com.tamtvh.be.model.Wineline;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Ct_promotionMapper extends AbstractMapper<Ct_promotionDTO, Ct_promotion>{
    Ct_promotionMapper INSTANCE = Mappers.getMapper(Ct_promotionMapper.class);

    @Mappings({
            @Mapping(source = "promotion", target = "promotion", qualifiedByName = "toLazyPromotionDTO"),
            @Mapping(source = "wineline", target = "wineline", qualifiedByName = "toLazyWinelineDTO"),
            @Mapping(source = "promotion.MAKM", target = "MAKM"),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),
    })
    Ct_promotionDTO toDtoWithoutLists(Ct_promotion entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "promotion", target = "promotion", ignore = true),
            @Mapping(source = "wineline", target = "wineline", ignore = true),
            @Mapping(source = "promotion.MAKM", target = "MAKM"),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),
    })
    Ct_promotionDTO toDtoWithoutLists1(Ct_promotion entity, @Context CycleAvoidingMappingContext context);

    @Override
    default Ct_promotionDTO toDto(Ct_promotion entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyPromotionDTO")
    default PromotionDTO toLazyPromotionDTO(Promotion collection) {
        if (collection == null) {
            return null;
        }
        PromotionDTO target = PromotionMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

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
