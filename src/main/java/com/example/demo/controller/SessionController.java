//package com.example.demo.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.example.demo.controller.base.SessionCheck;
//import com.example.demo.domain.model.LoginSession;
//import com.example.demo.exception.LoginSessionTimeOutException;
//
//@Controller
//public class SessionController extends SessionCheck {
//
//		@GetMapping("/session")
//		@ResponseBody
//		public LoginSession getSessionObject() throws LoginSessionTimeOutException {
//    sessionCheck();
//    return loginSession;
//		}
//
//}
