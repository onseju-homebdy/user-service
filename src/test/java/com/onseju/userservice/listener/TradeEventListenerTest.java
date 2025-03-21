package com.onseju.userservice.listener;

import com.onseju.userservice.account.domain.Account;
import com.onseju.userservice.account.service.AccountRepository;
import com.onseju.userservice.account.service.AccountService;
import com.onseju.userservice.member.domain.Member;
import com.onseju.userservice.member.domain.Role;
import com.onseju.userservice.member.service.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TradeEventListenerTest {

    @Autowired
    private TradeEventListener tradeEventListener;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .email("test@example.com")
                .username("testuser")
                .googleId("testuser")
                .role(Role.USER)
                .build();
        member.createAccount();
        memberRepository.save(member);

        Member member2 = Member.builder()
                .email("test2@example.com")
                .username("testuser2")
                .googleId("testuser2")
                .role(Role.USER)
                .build();
        member2.createAccount();
        memberRepository.save(member2);
    }

    @Test
    @DisplayName("")
    void TradeEventListenerTest() {
        // given
        TradeEvent tradeEvent = new TradeEvent(
                "005930",
                1L,
                1L,
                2L,
                2L,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(1000),
                Instant.now().getEpochSecond()
        );

        // when
        tradeEventListener.createTradeHistoryEvent(tradeEvent);

        // then
        Account account = accountRepository.getById(1L);
        Account account2 = accountRepository.getById(2L);

        assertThat(account.getBalance()).isEqualTo(new BigDecimal("99900000.00"));
        assertThat(account2.getBalance()).isEqualTo(new BigDecimal("100100000.00"));
    }
}