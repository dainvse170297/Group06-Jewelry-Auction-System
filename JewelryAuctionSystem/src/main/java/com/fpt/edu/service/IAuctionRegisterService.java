package com.fpt.edu.service;

import com.fpt.edu.dto.AuctionRegisterDTO;
import com.fpt.edu.entity.AuctionRegister;

public interface IAuctionRegisterService  {
    public AuctionRegisterDTO register(AuctionRegister register);

    public AuctionRegisterDTO placetobid(AuctionRegister register,Integer id);
}