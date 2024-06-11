package com.fpt.edu.dto;

import com.fpt.edu.entity.ProductImage;
import com.fpt.edu.status.LotStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LotDTO {
        private  Integer id;
        private  Integer productId;
        private  String productName;
        private BigDecimal currentPrice;
        private List<ProductImage> productImages;
        private LotStatus status;
        private Integer numberOfRegister;

}