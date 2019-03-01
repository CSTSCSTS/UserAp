package com.example.demo.controller.base;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.example.demo.domain.model.LoginSession;
import com.example.demo.exception.LoginSessionTimeOutException;

public class SessionCheck{

		@Autowired
		public LoginSession loginSession;

		@Autowired
		protected MessageSource messageSource;

		public void sessionCheck() throws LoginSessionTimeOutException {
				if(!loginSession.getUserId().isPresent() || !loginSession.getUserName().isPresent()) {
		    throw new LoginSessionTimeOutException(messageSource.getMessage("login.session.timeout", null, Locale.JAPAN));
		  }
		}

}
