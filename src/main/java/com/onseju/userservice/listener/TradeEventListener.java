package com.onseju.userservice.listener;

import com.onseju.userservice.account.domain.Type;
import com.onseju.userservice.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class TradeEventListener {

    private final AccountService accountService;
    private final EventMapper eventMapper;

    @Async
    @TransactionalEventListener
    public void createTradeHistoryEvent(final TradeEvent tradeEvent) {

        // 1. 주문 내역 조회
        accountService.updateAccountAfterTrade(eventMapper.toAccountAfterTradeParams(tradeEvent, tradeEvent.buyAccountId(), Type.BUY));
        accountService.updateAccountAfterTrade(eventMapper.toAccountAfterTradeParams(tradeEvent, tradeEvent.sellAccountId(), Type.SELL));
    }
}