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
public interface WinelineMapper extends AbstractMapper<WinelineDTO, Wineline>{
    WinelineMapper INSTANCE = Mappers.getMapper(WinelineMapper.class);

    @Mappings({
            @Mapping(source = "reviews", target = "reviews", qualifiedByName = "toLazyListReviewDTO"),
            @Mapping(source = "ct_phieudats", target = "ct_phieudats", qualifiedByName = "toLazyListCtPhieudatDTO"),
            @Mapping(source = "changeprices", target = "changeprices", qualifiedByName = "toLazyListChangepriceDTO"),
            @Mapping(source = "trademark", target = "trademark", qualifiedByName = "toLazyListTrademarkDTO"),
            @Mapping(source = "trademark.MATH", target = "MATH"),
            @Mapping(source = "winetype", target = "winetype", qualifiedByName = "toLazyWinetypeDTO"),
            @Mapping(source = "winetype.MALOAI", target = "MALOAI"),
            @Mapping(source = "ct_promotions", target = "ct_khuyenmais", qualifiedByName = "toLazyListCtPromotionDTO"),
            @Mapping(source = "ct_phieunhaps", target = "ct_phieunhaps", qualifiedByName = "toLazyListCtPhieunhapDTO"),
            @Mapping(source = "ct_orders", target = "ct_orders", qualifiedByName = "toLazyListCtOrderDTO"),
            @Mapping(source = "cungcaps", target = "cungcaps", qualifiedByName = "toLazyListCungcapDTO"),
    })
    WinelineDTO toDtoWithoutLists(Wineline entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "reviews", target = "reviews", ignore = true),
            @Mapping(source = "ct_phieudats", target = "ct_phieudats", ignore = true),
            @Mapping(source = "changeprices", target = "changeprices", ignore = true),
            @Mapping(source = "trademark", target = "trademark", ignore = true),
            @Mapping(source = "trademark.MATH", target = "MATH"),
            @Mapping(source = "winetype", target = "winetype", ignore = true),
            @Mapping(source = "winetype.MALOAI", target = "MALOAI"),
            @Mapping(source = "ct_promotions", target = "ct_khuyenmais", ignore = true),
            @Mapping(source = "ct_phieunhaps", target = "ct_phieunhaps", ignore = true),
            @Mapping(source = "ct_orders", target = "ct_orders", ignore = true),
            @Mapping(source = "cungcaps", target = "cungcaps", ignore = true),
    })
    WinelineDTO toDtoWithoutLists1(Wineline entity, @Context CycleAvoidingMappingContext context);

    @Override
    default WinelineDTO toDto(Wineline entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyListReviewDTO")
    default List<ReviewDTO> toLazyListReviewDTO(Collection<Review> collection) {
        if (collection == null) {
            return null;
        }
        List<ReviewDTO> target = new ArrayList<ReviewDTO>(collection.size());
        for (Review review : collection) {
            target.add(ReviewMapper.INSTANCE.toDtoWithoutLists1(review, new CycleAvoidingMappingContext()));
        }
        return target;
    }

    @Named("toLazyListCtPhieudatDTO")
    default List<Ct_phieudatDTO> toLazyListCtPhieudatDTO(Collection<Ct_phieudat> collection) {
        if (collection == null) {
            return null;
        }
        List<Ct_phieudatDTO> target = new ArrayList<Ct_phieudatDTO>(collection.size());
        for (Ct_phieudat ct_phieudat : collection) {
            target.add(Ct_phieudatMapper.INSTANCE.toDtoWithoutLists1(ct_phieudat, new CycleAvoidingMappingContext()));
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

    @Named("toLazyListTrademarkDTO")
    default TrademarkDTO toLazyListTrademarkDTO(Trademark collection) {
        if (collection == null) {
            return null;
        }
        TrademarkDTO target = TrademarkMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }

    @Named("toLazyWinetypeDTO")
    default WinetypeDTO toLazyWinetypeDTO(Winetype collection) {
        if (collection == null) {
            return null;
        }
        WinetypeDTO target = WinetypeMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

        return target;
    }

    @Named("toLazyListCtPromotionDTO")
    default List<Ct_promotionDTO> toLazyListCtPromotionDTO(Collection<Ct_promotion> collection) {
        if (collection == null) {
            return null;
        }
        List<Ct_promotionDTO> target = new ArrayList<Ct_promotionDTO>(collection.size());
        for (Ct_promotion ct_promotion : collection) {
            target.add(Ct_promotionMapper.INSTANCE.toDtoWithoutLists1(ct_promotion, new CycleAvoidingMappingContext()));
        }
        return target;
    }

    @Named("toLazyListCtPhieunhapDTO")
    default List<Ct_phieunhapDTO> toLazyListCtPhieunhapDTO(Collection<Ct_phieunhap> collection) {
        if (collection == null) {
            return null;
        }
        List<Ct_phieunhapDTO> target = new ArrayList<Ct_phieunhapDTO>(collection.size());
        for (Ct_phieunhap ct_phieunhap : collection) {
            target.add(Ct_phieunhapMapper.INSTANCE.toDtoWithoutLists1(ct_phieunhap, new CycleAvoidingMappingContext()));
        }
        return target;
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
