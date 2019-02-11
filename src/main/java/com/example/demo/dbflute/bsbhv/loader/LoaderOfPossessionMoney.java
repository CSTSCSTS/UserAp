package com.example.demo.dbflute.bsbhv.loader;

import java.util.List;

import org.dbflute.bhv.*;
import com.example.demo.dbflute.exbhv.*;
import com.example.demo.dbflute.exentity.*;

/**
 * The referrer loader of POSSESSION_MONEY as TABLE. <br>
 * <pre>
 * [primary key]
 *     USER_ID
 *
 * [column]
 *     USER_ID, POSSESSION_MONEY, UPDATE_DATE
 *
 * [sequence]
 *     
 *
 * [identity]
 *     
 *
 * [version-no]
 *     
 *
 * [foreign table]
 *     POKER_USER_INFO
 *
 * [referrer table]
 *     
 *
 * [foreign property]
 *     pokerUserInfo
 *
 * [referrer property]
 *     
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfPossessionMoney {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<PossessionMoney> _selectedList;
    protected BehaviorSelector _selector;
    protected PossessionMoneyBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfPossessionMoney ready(List<PossessionMoney> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected PossessionMoneyBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(PossessionMoneyBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    protected LoaderOfPokerUserInfo _foreignPokerUserInfoLoader;
    public LoaderOfPokerUserInfo pulloutPokerUserInfo() {
        if (_foreignPokerUserInfoLoader == null)
        { _foreignPokerUserInfoLoader = new LoaderOfPokerUserInfo().ready(myBhv().pulloutPokerUserInfo(_selectedList), _selector); }
        return _foreignPokerUserInfoLoader;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<PossessionMoney> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
