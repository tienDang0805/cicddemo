package com.tamtvh.be.mapper;

import com.tamtvh.be.dto.BillDTO;
import com.tamtvh.be.dto.PhieudatDTO;
import com.tamtvh.be.dto.PhieutraDTO;
import com.tamtvh.be.dto.StaffDTO;
import com.tamtvh.be.mapper.helper.CycleAvoidingMappingContext;
import com.tamtvh.be.model.Bill;
import com.tamtvh.be.model.Phieudat;
import com.tamtvh.be.model.Phieutra;
import com.tamtvh.be.model.Staff;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BillMapper extends AbstractMapper<BillDTO, Bill>{
    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);

    @Mappings({
            @Mapping(source = "phieudat", target = "phieudat", qualifiedByName = "toLazyPhieuDatDTO"),
            @Mapping(source = "phieudat.MAPD", target = "MAPD"),
            @Mapping(source = "staff", target = "staff", qualifiedByName = "toLazyStaffDTO"),
            @Mapping(source = "staff.MANV", target = "MANV"),
            @Mapping(source = "phieutras", target = "phieutras", qualifiedByName = "toLazyListPhieutraDTO"),
    })
    BillDTO toDtoWithoutLists(Bill entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "phieudat", target = "phieudat", ignore = true),
            @Mapping(source = "phieudat.MAPD", target = "MAPD"),
            @Mapping(source = "staff", target = "staff", ignore = true),
            @Mapping(source = "staff.MANV", target = "MANV"),
            @Mapping(source = "phieutras", target = "phieutras", ignore = true),
    })
    BillDTO toDtoWithoutLists1(Bill entity, @Context CycleAvoidingMappingContext context);

    @Override
    default BillDTO toDto(Bill entity, @Context CycleAvoidingMappingContext context) {
        return toDtoWithoutLists(entity, context);
    }

    @Named("toLazyPhieuDatDTO")
    default PhieudatDTO toLazyPhieuDatDTO(Phieudat collection) {
        if (collection == null) {
            return null;
        }
        PhieudatDTO target = PhieudatMapper.INSTANCE.toDtoWithoutLists1(collection, new CycleAvoidingMappingContext());

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
}
