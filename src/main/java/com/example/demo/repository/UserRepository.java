package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dbflute.exception.SQLFailureException;
import org.dbflute.optional.OptionalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dbflute.exbhv.OauthPokerUserInfoBhv;
import com.example.demo.dbflute.exbhv.PokerUserInfoBhv;
import com.example.demo.dbflute.exbhv.PossessionMoneyBhv;
import com.example.demo.dbflute.exbhv.pmbean.SelectPmb;
import com.example.demo.dbflute.exentity.OauthPokerUserInfo;
import com.example.demo.dbflute.exentity.PokerUserInfo;
import com.example.demo.dbflute.exentity.customize.Select;
import com.example.demo.domain.model.MoneyRanking;
import com.example.demo.domain.model.MoneyRankingItem;
import com.example.demo.domain.model.User;

@Component
public class UserRepository {

	@Autowired
	public PokerUserInfoBhv pokerUserInfoBhv;

	@Autowired
	public OauthPokerUserInfoBhv oauthpokerUserInfoBhv;

	@Autowired
	public PossessionMoneyBhv possessionMoneyBhv;

	public Optional<User> getUserByUserId(int userId) {
			Optional<User> user = getLocalUserByUserId(userId);
			if(user.isPresent()) {
					return user;
			}
			return getOAuthUserByUserId(userId);
	}

	// ユーザーIDでユーザーを取得する
	public Optional<User> getLocalUserByUserId(int userId) {

			OptionalEntity<PokerUserInfo> optEntity;
			try {
			 	optEntity = pokerUserInfoBhv.selectEntity(cb -> {
			  	cb.query().setUserId_Equal(userId);
			  });
		  } catch(SQLFailureException e) {
			    throw new SQLFailureException(e.getMessage(), e.getSQLException());
		  }
			 if(optEntity.isPresent()) {
			 		PokerUserInfo entity = optEntity.get();
			 		return Optional.of(
			 						new User(
			 										entity.getUserId(),
			 										entity.getUserName(),
			 										Optional.of(entity.getPassword()),
			 										entity.getLoginDate(),
			 										false));
			 }
			 return Optional.empty();
	 }

//ユーザーIDでOAuthユーザーを取得する
public Optional<User> getOAuthUserByUserId(int userId) {

	OptionalEntity<OauthPokerUserInfo> optEntity;
	try {
	 	optEntity = oauthpokerUserInfoBhv.selectEntity(cb -> {
	  	cb.query().setUserId_Equal(userId);
	  });
  } catch(SQLFailureException e) {
	    throw new SQLFailureException(e.getMessage(), e.getSQLException());
  }
	 if(optEntity.isPresent()) {
	 		OauthPokerUserInfo entity = optEntity.get();
	 		return Optional.of(
	 						new User(
	 										entity.getUserId(),
	 										entity.getUserName(),
	 										null,
	 										entity.getLoginDate(),
	 										true));
	 }
	 return Optional.empty();
}

	// ユーザー名でユーザーを取得する
	public OptionalEntity<PokerUserInfo> getPokerUserByUsername(String username) {
		OptionalEntity<PokerUserInfo> optEntity;
		 try {
		 	optEntity = pokerUserInfoBhv.selectEntity(cb ->
       cb.query().setUserName_Equal(username)
     );
	  } catch(SQLFailureException e) {
		    throw new SQLFailureException(e.getMessage(), e.getSQLException());
	  }
		 return optEntity;
 }

 //ユーザー名でOAuthユーザーを取得する
 public Optional<User> getOAuthPokerUserByUsername(String username) {
 OptionalEntity<OauthPokerUserInfo> optEntity;
  try {
  	optEntity = oauthpokerUserInfoBhv.selectEntity(cb ->
      cb.query().setUserName_Equal(username)
    );
  } catch(SQLFailureException e) {
     throw new SQLFailureException(e.getMessage(), e.getSQLException());
  }
  if(optEntity.isPresent()) {
	 		OauthPokerUserInfo entity = optEntity.get();
	 		return Optional.of(
	 						new User(
	 										entity.getUserId(),
	 										entity.getUserName(),
	 										Optional.empty(),
	 										entity.getLoginDate(),
	 										true));
	 }
	 return Optional.empty();
}

	// ユーザー名・パスワードでユーザーを取得する
	public Optional<User> getUserByUsername(String username) {

		// 入力されたpasswordをハッシュ値に変更


		OptionalEntity<PokerUserInfo> optEntity;
		try {
		 	optEntity = pokerUserInfoBhv.selectEntity(cb -> {
		  	cb.query().setUserName_Equal(username);
		  });
	  } catch(SQLFailureException e) {
		    throw new SQLFailureException(e.getMessage(), e.getSQLException());
	  }
		 if(optEntity.isPresent()) {
		 		PokerUserInfo entity = optEntity.get();
		 		return Optional.of(new User(entity.getUserId(), entity.getUserName(), Optional.of(entity.getPassword()), entity.getLoginDate(), false));
		 }
		 return Optional.empty();
 }

	// ランキング情報を取得
	public MoneyRanking getMoneyRanking() throws SQLFailureException{

		List<Select> outsideSqlResult;
		try {
		  outsideSqlResult = pokerUserInfoBhv.outsideSql().selectList(new SelectPmb()).getSelectedList();
  } catch(SQLFailureException e) {
 	  throw new SQLFailureException(e.getMessage(), e.getSQLException());
  }

		List<MoneyRankingItem> moneyRankingList = new ArrayList<>();
		outsideSqlResult.stream()
				.map(t -> new MoneyRankingItem(t.getUserName(), t.getPossessionMoney()))
				.forEach(i -> moneyRankingList.add(i));

		return new MoneyRanking(moneyRankingList);

	}


	// ユーザー名が重複しているか判定する
	public boolean userNameIsDuplicate(String username) {
		OptionalEntity<PokerUserInfo> optEntity;
	  try {
	    optEntity = pokerUserInfoBhv.selectEntity(cb ->
		    cb.query().setUserName_Equal(username)
		  );
	  } catch(SQLFailureException e) {
	  	throw new SQLFailureException(e.getMessage(), e.getSQLException());
	  }
		return optEntity.isPresent();
	}

	 // ユーザー情報を登録
	 public void insert(User user) {
		 	PokerUserInfo pokerUserInfo = new PokerUserInfo();
		 	pokerUserInfo.setUserName(user.getUserName());
		 	pokerUserInfo.setPassword(user.getPassword());
	 	 try {
	 	   pokerUserInfoBhv.insert(pokerUserInfo);
	 	 } catch(SQLFailureException e) {
      throw new SQLFailureException(e.getMessage(), e.getSQLException());
	 	 }
	 }

	 public void oauthInsert(User user) {
	 		OauthPokerUserInfo oauthPokerUserInfo = new OauthPokerUserInfo();
		 	oauthPokerUserInfo.setUserName(user.getUserName());
	 	 try {
	 	 		oauthpokerUserInfoBhv.insert(oauthPokerUserInfo);
	 	 } catch(SQLFailureException e) {
      throw new SQLFailureException(e.getMessage(), e.getSQLException());
	 	 }
	 }

	 public void update(User user) {
	 		User domain = getUserByUserId(user.userId).get();
	 	 if(domain.isOAuthUser) {
	 	 		oauthUserUpdate(user);
	 	 		return;
	 	 }
	 	 localUpdate(user);
	 }

  //ユーザー情報を更新
	 public void localUpdate(User user) {
		  PokerUserInfo pokerUserInfo = new PokerUserInfo();
		  pokerUserInfo.setUserId(user.getUserId());
		  pokerUserInfo.setUserName(user.getUserName());
		  pokerUserInfo.setPassword(user.getPassword());
		  pokerUserInfo.setLoginDate(user.getLoginDate());
		  try {
 	   pokerUserInfoBhv.update(pokerUserInfo);
 	  } catch(SQLFailureException e) {
     throw new SQLFailureException(e.getMessage(), e.getSQLException());
				}
		}

	 public void oauthUserUpdate(User user) {
	 		OauthPokerUserInfo oauthPokerUserInfo = new OauthPokerUserInfo();
		  oauthPokerUserInfo.setUserId(user.getUserId());
		  oauthPokerUserInfo.setUserName(user.getUserName());
		  oauthPokerUserInfo.setLoginDate(user.getLoginDate());
		  try {
		  		oauthpokerUserInfoBhv.update(oauthPokerUserInfo);
 	  } catch(SQLFailureException e) {
     throw new SQLFailureException(e.getMessage(), e.getSQLException());
				}
		}

}
