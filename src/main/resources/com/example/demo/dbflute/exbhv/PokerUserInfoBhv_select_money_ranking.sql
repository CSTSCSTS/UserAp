/*
 [df:title]
  所持金ランキング取得SQL

 [df:description]
  ポーカーユーザー情報と所持金テーブルをJOINして、
  所持金の多い順・更新日時が新しい順にソート

*/

-- #df:entity#
-- !df:pmb!
-- !!AutoDetect!!

select *
  from POKER_USER_INFO pui
  inner join POSSESSION_MONEY pm on pui.user_id = pm.user_id
 order by pm.possession_money desc, pm.update_date desc
