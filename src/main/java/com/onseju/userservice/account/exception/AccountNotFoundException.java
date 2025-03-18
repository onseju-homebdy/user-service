package com.onseju.userservice.account.exception;

import com.onseju.userservice.global.exception.BaseException;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException() {
        super("존재하지 않는 계좌 정보입니다.", HttpStatus.NOT_FOUND);
    }
}
