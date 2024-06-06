package com.fpt.edu.repository;

import com.fpt.edu.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ILotRepository extends JpaRepository<Lot, Integer> {

    List<Lot> findLotByProduct_Id(int productId);
}