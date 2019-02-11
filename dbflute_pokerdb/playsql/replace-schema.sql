-- ポーカーユーザー情報
CREATE SEQUENCE "POKER_USER_ID_SEQ1";
create table POKER_USER_INFO (
  USER_ID INTEGER not null default nextval('POKER_USER_ID_SEQ1')
  , USER_NAME VARCHAR(255) not null
  , PASSWORD VARCHAR(255) not null
  , LOGIN_DATE TIMESTAMP
  , constraint POKER_USER_INFO_PKC primary key (USER_ID)
) ;

 -- 所持金
 create table POSSESSION_MONEY (
  USER_ID INTEGER not null
  , POSSESSION_MONEY DECIMAL not null
  , UPDATE_DATE TIMESTAMP
  , constraint POSSESSION_MONEY_PKC primary key (USER_ID)
  , CONSTRAINT FK_USER_ID
    FOREIGN KEY (USER_ID)
    REFERENCES POKER_USER_INFO (USER_ID)
    ON DELETE CASCADE
) ;

alter table poker_user_info add constraint POKER_USER_INFO_UNQ1
  unique (USER_NAME) ;

comment on table POSSESSION_MONEY is '所持金';
comment on column POSSESSION_MONEY.USER_ID is 'ユーザーID';
comment on column POSSESSION_MONEY.POSSESSION_MONEY is '所持金';
comment on column POSSESSION_MONEY.UPDATE_DATE is '更新日時';

comment on table POKER_USER_INFO is 'ポーカーユーザー情報';
comment on column POKER_USER_INFO.USER_ID is 'ユーザーID';
comment on column POKER_USER_INFO.USER_NAME is 'ユーザー名';
comment on column POKER_USER_INFO.PASSWORD is 'パスワード';
comment on column POKER_USER_INFO.LOGIN_DATE is 'ログイン日時';
