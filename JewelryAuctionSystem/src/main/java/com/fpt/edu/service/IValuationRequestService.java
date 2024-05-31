package com.fpt.edu.service;

import com.fpt.edu.dto.ValuationRequestDTO;
import com.fpt.edu.entity.ValuationRequest;
import com.fpt.edu.enums.ValuationRequestStatus;

import java.math.BigDecimal;
import java.util.List;

public interface IValuationRequestService {
    public ValuationRequestDTO create(Integer memberId, String description, BigDecimal estimateMin, BigDecimal estimateMax);

    public List<ValuationRequestDTO> getRequestedValuationRequest();

    public ValuationRequestDTO productReceived(Long id);

    ValuationRequestDTO preliminaryValuation(Long id, BigDecimal estimateMin, BigDecimal estimateMax);
}