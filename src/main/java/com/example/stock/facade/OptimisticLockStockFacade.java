package com.example.stock.facade;

import com.example.stock.service.OptimisticLockStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptimisticLockStockFacade {

    private final OptimisticLockStockService optimisticLockStockService;

    public void decreaseStock(Long stockId, Long quantity) throws InterruptedException {
//        int retryCount = 0;
        while (true) {
            try {
                optimisticLockStockService.decreaseStock(stockId, quantity);
                break;
            } catch (ObjectOptimisticLockingFailureException e) {
//                if (++retryCount > 3) {
//                    throw new IllegalStateException("재시도 횟수는 최대 3회입니다.");
//                }
                Thread.sleep(50);
            }
        }

    }
}