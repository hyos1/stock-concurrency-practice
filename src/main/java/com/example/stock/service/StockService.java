package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

//    @Transactional
    public synchronized void decreaseStock(Long stockId, Long quantity) {
        Stock stock = stockRepository.findById(stockId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 상품ID입니다."));

        stock.decreaseStock(quantity); // 끝 dirty checking

        stockRepository.saveAndFlush(stock);
    }
}