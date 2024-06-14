package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.ReviewDTO;
import com.tamtvh.be.dto.WinelineDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Review;
import com.tamtvh.be.model.Wineline;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper extends AbstractMapper<ReviewDTO, Review>{
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mappings({
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "customer.MAKH", target = "MAKH"),
            @Mapping(source = "customer.role1.MANQ", target = "customer.MANQ"),
            @Mapping(target = "customer.phieudats", ignore = true),
            @Mapping(source = "wineline", target = "wineline", qualifiedByName = "toLazyListWinelineDTO"),
            @Mapping(source = "wineline.MADONG", target = "MADONG"),

    })
    ReviewDTO toDtoWithoutLists(Review entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "customer", target = "customer", ignore = true),
            @Mapping(source = "wineline", target = "wineline", ignore = true),
            @Mapping(source = "customer.MAKH", target = "MAKH"),
            //@Mapping(target = "customer.reviews", ignore = true),

    })
    ReviewDTO toDtoWithoutLists1(Review entity, @Context CycleAvoidingMappingContext context);

    @Override
    default ReviewDTO toDto(Review entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyListWinelineDTO")
    default WinelineDTO toLazyListWinelineDTO(Wineline collection) {
        if (collection == null) {
            return null;
        }
        WinelineDTO target = WinelineMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }


}
