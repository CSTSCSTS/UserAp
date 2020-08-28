package com.example.demo.dbflute.bsbhv.loader;

import java.util.List;

import org.dbflute.bhv.*;
import com.example.demo.dbflute.exbhv.*;
import com.example.demo.dbflute.exentity.*;

/**
 * The referrer loader of POKER_USER_INFO as TABLE. <br>
 * <pre>
 * [primary key]
 *     USER_ID
 *
 * [column]
 *     USER_ID, USER_NAME, PASSWORD, LOGIN_DATE
 *
 * [sequence]
 *     POKER_USER_ID_SEQ1
 *
 * [identity]
 *     
 *
 * [version-no]
 *     
 *
 * [foreign table]
 *     
 *
 * [referrer table]
 *     
 *
 * [foreign property]
 *     
 *
 * [referrer property]
 *     
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfPokerUserInfo {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<PokerUserInfo> _selectedList;
    protected BehaviorSelector _selector;
    protected PokerUserInfoBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfPokerUserInfo ready(List<PokerUserInfo> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected PokerUserInfoBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(PokerUserInfoBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<PokerUserInfo> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
