package com.onseju.userservice.listener;

import com.onseju.userservice.account.domain.Type;
import com.onseju.userservice.account.service.dto.AccountAfterTradeParams;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public AccountAfterTradeParams toAccountAfterTradeParams(TradeEvent tradeEvent, Long accountId, Type type) {
        return new AccountAfterTradeParams(
                accountId,
                type,
                tradeEvent.price(),
                tradeEvent.quantity()
        );
    }
}
