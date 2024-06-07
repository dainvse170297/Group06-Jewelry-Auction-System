package com.fpt.edu.dto;

import com.fpt.edu.entity.Member;
import com.fpt.edu.entity.Product;
import com.fpt.edu.status.ValuationRequestStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ValuationRequestDTO {
    private int id;
    private Integer memberId;
    private LocalDate timeRequest;
    private ValuationRequestStatus valuationStatus;
    private BigDecimal estimatePriceMax;
    private BigDecimal estimatePriceMin;
    private String description;
    private Integer productId;
    private Integer responseRequestValuationsId;
    private Set<Integer> valuationImages = new LinkedHashSet<>();

    public ValuationRequestDTO(int id, Integer memberId, LocalDate timeRequest, ValuationRequestStatus valuationStatus, BigDecimal estimatePriceMax, BigDecimal estimatePriceMin, String description, Integer productId, Integer responseRequestValuationsId, Set<Integer> valuationImages) {
        this.id = id;
        this.memberId = memberId;
        this.timeRequest = timeRequest;
        this.valuationStatus = valuationStatus;
        this.estimatePriceMax = estimatePriceMax;
        this.estimatePriceMin = estimatePriceMin;
        this.description = description;
        this.productId = productId;
        this.responseRequestValuationsId = responseRequestValuationsId;
        this.valuationImages = valuationImages;
    }
}
