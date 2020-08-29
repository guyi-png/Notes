package com.springMVC.handlerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//forbidden 禁止
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "用户名不匹配")
public class UsernameNotMatchException extends RuntimeException{}
