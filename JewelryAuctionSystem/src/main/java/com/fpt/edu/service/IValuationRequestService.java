package com.fpt.edu.service;

import com.fpt.edu.dto.*;
import com.fpt.edu.entity.Product;
import com.fpt.edu.status.ValuationRequestStatus;
import org.springframework.web.multipart.MultipartFile;
import com.fpt.edu.entity.ValuationRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IValuationRequestService {
    public ValuationRequestDTO create(Integer memberId, String description, BigDecimal estimateMin, BigDecimal estimateMax, Set<MultipartFile> files);

    public List<ValuationRequestDTO> getRequestedValuationRequest();

    public ValuationRequestDTO productReceived(Integer id);

    public List<ValuationRequestDTO> getRequestStatusProductReceived();

    ValuationRequestDTO getRequestByIdAndStatusProductReceived(int id);

    ValuationRequestDTO preliminaryValuation(Integer id, BigDecimal estimateMin, BigDecimal estimateMax);

    public List<FinalValuationRequestDTO> getListFinalValuationRequest();

    public Map<String, String> ApproveFinalValuationRequest(Integer id);

    public Map<String, String> CancelFinalValuationRequest(Integer id);

    public List<FinalValuationRequestDTO> getListManagerApproveValuationRequest();

    public List<Map<String, String>> sendFinalValuationToMember(Integer id);

    public Map<String, String> sendNotifyToMember(ValuationRequest valuationRequest, Product product);

    public List<ViewValuationRequestDTO> viewSentRequest(Integer memberId);

    public List<ValuationRequestDTO1> getRequestedValuationRequestByMemberStatusReceive(ValuationRequestStatus status);

    public ValuationRequestDTO1 getValuationRequestById(Integer id);
}