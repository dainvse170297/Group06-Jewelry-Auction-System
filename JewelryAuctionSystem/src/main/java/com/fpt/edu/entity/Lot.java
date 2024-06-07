package com.fpt.edu.entity;

import com.fpt.edu.status.LotStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lot")
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "auction_session_id", nullable = true)
    private AuctionSession auctionSession;

    @Column(name = "current_price", precision = 19, scale = 1)
    private BigDecimal currentPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LotStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lot")
    private Set<AuctionRegister> auctionRegisters = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lot")
    private Set<Bid> bids = new LinkedHashSet<>();

    public Lot(Integer id, Product product, AuctionSession auctionSession, BigDecimal currentPrice, LotStatus status) {
     this.id = id;
     this.product = product;
     this.auctionSession = auctionSession;
     this.currentPrice = currentPrice;
     this.status = status;

    }
}