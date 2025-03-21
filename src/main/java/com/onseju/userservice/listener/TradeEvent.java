package com.onseju.userservice.listener;

import java.math.BigDecimal;

public record TradeEvent(
        String companyCode,
        Long buyOrderId,
        Long buyAccountId,
        Long sellOrderId,
        Long sellAccountId,
        BigDecimal quantity,
        BigDecimal price,
        Long tradeAt
) {
}
