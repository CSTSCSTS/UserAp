package com.example.demo.dbflute.cbean.nss;

import com.example.demo.dbflute.cbean.cq.PossessionMoneyCQ;

/**
 * The nest select set-upper of POSSESSION_MONEY.
 * @author DBFlute(AutoGenerator)
 */
public class PossessionMoneyNss {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final PossessionMoneyCQ _query;
    public PossessionMoneyNss(PossessionMoneyCQ query) { _query = query; }
    public boolean hasConditionQuery() { return _query != null; }

    // ===================================================================================
    //                                                                     Nested Relation
    //                                                                     ===============
    /**
     * With nested relation columns to select clause. <br>
     * POKER_USER_INFO by my USER_ID, named 'pokerUserInfo'.
     * @return The set-upper of more nested relation. {...with[nested-relation].with[more-nested-relation]} (NotNull)
     */
    public PokerUserInfoNss withPokerUserInfo() {
        _query.xdoNss(() -> _query.queryPokerUserInfo());
        return new PokerUserInfoNss(_query.queryPokerUserInfo());
    }
}
