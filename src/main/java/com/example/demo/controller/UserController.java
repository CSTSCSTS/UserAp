package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.MoneyDto;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.UserDto;
import com.example.demo.domain.model.Winner;
import com.example.demo.exception.LoginFailureException;
import com.example.demo.exception.UserNameDuplicateException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MoneyService;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	public UserService userService;

	@Autowired
	public MoneyService moneyService;

	@Autowired
	public UserRepository userRepository;

/**
 * ユーザー登録をする
 * @param userName ユーザー名
 * @param password パスワード
 * @return
 * @throws UserNameDuplicateException ユーザー名重複エラー
 */
 @PostMapping("/user")
	@ResponseBody
	public UserDto add(String userName, String password) throws UserNameDuplicateException {
		return UserDto.convertUserDto(userService.resister(userName, password));
	}

 /**
  * OAuthユーザー登録をする
  * @param userName ユーザー名
  * @return
  */
  @PostMapping("/oauth-user")
 	@ResponseBody
 	public UserDto addOauth(String userName) throws UserNameDuplicateException {
 		return UserDto.convertUserDto(userService.oauthResister(userName));
 	}

/**
 * 所持金情報を返す
 * @return money
 */
 @GetMapping("/money")
 @ResponseBody
	public MoneyDto getMoney(@RequestParam int userId) {
		 return MoneyDto.convertMoneyDto(moneyService.getMoney(userId));
	}

 /**
  * ポーカーの勝敗に応じて、所持金情報を更新
  * @return money
  */
  @PostMapping("/poker_money")
  @ResponseBody
 	public MoneyDto updateMoney(int userId, BigDecimal betMoney, String winner) {
  		return MoneyDto.convertMoneyDto(moneyService.update(userId, betMoney, Winner.valueOf(winner)));
 	}

  /**
   * 所持金情報を更新
   * @return money
   */
  @PostMapping("/money")
  @ResponseBody
 	public MoneyDto updateMoney(@RequestBody MoneyDto moneyDto) {
  		return MoneyDto.convertMoneyDto(moneyService.update(Money.convertMoney(moneyDto)));
 	}

  /**
   * ログイン情報を返す
   * @return money
   * @throws LoginFailureException
   */
   @GetMapping("/login")
   @ResponseBody
  	public UserDto login(@RequestParam String userName) throws LoginFailureException {
   		// 存在しないユーザーの場合は、例外返す。
   		Optional<User> optUser = userRepository.getUserByUsername(userName);

   		if (!optUser.isPresent()) {
   				return null;
   		}
   		return UserDto.convertUserDto(optUser.get());
  	}

   /**
    * ユーザー情報を返す
    * @return money
    * @throws LoginFailureException
    */
    @GetMapping("/user")
    @ResponseBody
   	public UserDto getUser(@RequestParam int userId) {
    		// 存在しないユーザーの場合は、例外返す。
    		Optional<User> optUser = userRepository.getUserByUserId(userId);

    		if (!optUser.isPresent()) {
    				return null;
    		}
    		return UserDto.convertUserDto(optUser.get());
   	}

    /**
     * 名前でOAuthユーザー情報を返す
     * @return userDto
     */
     @GetMapping("/oauth-user")
     @ResponseBody
    	public UserDto getOAuthUserByName(@RequestParam String userName) {
     		// 存在しないユーザーの場合は、例外返す。
     		Optional<User> optUser = userRepository.getOAuthPokerUserByUsername(userName);

     		if (!optUser.isPresent()) {
     				return null;
     		}
     		return UserDto.convertUserDto(optUser.get());
    	}

   /**
    * ユーザー情報を更新
    * @return money
    */
    @PutMapping("/user")
    @ResponseBody
   	public void loginDateUpdate(@RequestBody UserDto userDto) {
    		userService.update(userDto.getUserId(), userDto.getUserName(), userDto.getPassword());
   	}

}
