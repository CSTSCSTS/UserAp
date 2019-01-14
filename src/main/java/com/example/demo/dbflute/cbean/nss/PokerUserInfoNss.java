package com.example.demo.dbflute.cbean.nss;

import com.example.demo.dbflute.cbean.cq.PokerUserInfoCQ;

/**
 * The nest select set-upper of POKER_USER_INFO.
 * @author DBFlute(AutoGenerator)
 */
public class PokerUserInfoNss {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final PokerUserInfoCQ _query;
    public PokerUserInfoNss(PokerUserInfoCQ query) { _query = query; }
    public boolean hasConditionQuery() { return _query != null; }

    // ===================================================================================
    //                                                                     Nested Relation
    //                                                                     ===============
    /**
     * With nested relation columns to select clause. <br>
     * POSSESSION_MONEY by USER_ID, named 'possessionMoneyAsOne'.
     * @return The set-upper of more nested relation. {...with[nested-relation].with[more-nested-relation]} (NotNull)
     */
    public PossessionMoneyNss withPossessionMoneyAsOne() {
        _query.xdoNss(() -> _query.queryPossessionMoneyAsOne());
        return new PossessionMoneyNss(_query.queryPossessionMoneyAsOne());
    }
}
