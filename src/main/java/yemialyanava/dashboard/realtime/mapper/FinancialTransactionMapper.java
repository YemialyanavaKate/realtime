package yemialyanava.dashboard.realtime.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import yemialyanava.dashboard.realtime.dto.FinancialTransactionRequest;
import yemialyanava.dashboard.realtime.dto.FinancialTransactionResponse;
import yemialyanava.dashboard.realtime.entity.FinancialTransaction;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FinancialTransactionMapper {

        FinancialTransactionResponse toResponse(FinancialTransaction transaction);
        
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "status", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        FinancialTransaction toEntity(FinancialTransactionRequest request);

}
