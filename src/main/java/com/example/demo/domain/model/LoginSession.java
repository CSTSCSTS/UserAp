package com.example.demo.domain.model;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Scope(value= "session" , proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class LoginSession implements Serializable {

	private Optional<Integer> userId;
	private Optional<String> userName;

}
