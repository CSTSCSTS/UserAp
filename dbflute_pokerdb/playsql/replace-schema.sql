-- ポーカーユーザー情報
create table poker_user_info (
  user_id INTEGER not null
  , user_name VARCHAR(255) not null
  , password VARCHAR(255) not null
  , constraint poker_user_info_PKC primary key (user_id)
) ;

 -- 所持金
 create table possession_money (
  user_id VARCHAR(255) not null
  , possession_money DECIMAL not null
  , constraint possession_money_PKC primary key (user_id)
  , CONSTRAINT fk_user_id
    FOREIGN KEY (user_id)
    REFERENCES poker_user_info (user_id)
    ON DELETE CASCADE
) ;

comment on table possession_money is '所持金';
comment on column possession_money.user_id is 'ユーザーID';
comment on column possession_money.possession_money is '所持金';

comment on table poker_user_info is 'ポーカーユーザー情報';
comment on column poker_user_info.user_id is 'ユーザーID';
comment on column poker_user_info.user_name is 'ユーザー名';
comment on column poker_user_info.password is 'パスワード';
