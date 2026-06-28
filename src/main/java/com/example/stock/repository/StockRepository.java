package com.example.stock.repository;

import com.example.stock.domain.Stock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Stock s where s.id = :stockId")
    Optional<Stock> findByIdWithPessimisticLock(@Param("stockId") Long stockId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select s from Stock s where s.id = :stockId")
    Optional<Stock> findByIdWithOptimisticLock(@Param("stockId") Long stockId);
}