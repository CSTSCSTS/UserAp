package com.example.demo.dbflute.cbean.cq.bs;

import java.util.Map;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.exception.IllegalConditionBeanOperationException;
import com.example.demo.dbflute.cbean.cq.ciq.*;
import com.example.demo.dbflute.cbean.*;
import com.example.demo.dbflute.cbean.cq.*;

/**
 * The base condition-query of POKER_USER_INFO.
 * @author DBFlute(AutoGenerator)
 */
public class BsPokerUserInfoCQ extends AbstractBsPokerUserInfoCQ {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected PokerUserInfoCIQ _inlineQuery;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BsPokerUserInfoCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br>
     * {select ... from ... left outer join (select * from POKER_USER_INFO) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public PokerUserInfoCIQ inline() {
        if (_inlineQuery == null) { _inlineQuery = xcreateCIQ(); }
        _inlineQuery.xsetOnClause(false); return _inlineQuery;
    }

    protected PokerUserInfoCIQ xcreateCIQ() {
        PokerUserInfoCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected PokerUserInfoCIQ xnewCIQ() {
        return new PokerUserInfoCIQ(xgetReferrerQuery(), xgetSqlClause(), xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br>
     * {select ... from ... left outer join POKER_USER_INFO on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public PokerUserInfoCIQ on() {
        if (isBaseQuery()) { throw new IllegalConditionBeanOperationException("OnClause for local table is unavailable!"); }
        PokerUserInfoCIQ inlineQuery = inline(); inlineQuery.xsetOnClause(true); return inlineQuery;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    protected ConditionValue _userId;
    public ConditionValue xdfgetUserId()
    { if (_userId == null) { _userId = nCV(); }
      return _userId; }
    protected ConditionValue xgetCValueUserId() { return xdfgetUserId(); }

    /**
     * Add order-by as ascend. <br>
     * USER_ID: {PK, NotNull, INTEGER(10), default=[NEXTVAL('POKER_USER_ID_SEQ1')]}
     * @return this. (NotNull)
     */
    public BsPokerUserInfoCQ addOrderBy_UserId_Asc() { regOBA("USER_ID"); return this; }

    /**
     * Add order-by as descend. <br>
     * USER_ID: {PK, NotNull, INTEGER(10), default=[NEXTVAL('POKER_USER_ID_SEQ1')]}
     * @return this. (NotNull)
     */
    public BsPokerUserInfoCQ addOrderBy_UserId_Desc() { regOBD("USER_ID"); return this; }

    protected ConditionValue _userName;
    public ConditionValue xdfgetUserName()
    { if (_userName == null) { _userName = nCV(); }
      return _userName; }
    protected ConditionValue xgetCValueUserName() { return xdfgetUserName(); }

    /**
     * Add order-by as ascend. <br>
     * USER_NAME: {UQ, NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPokerUserInfoCQ addOrderBy_UserName_Asc() { regOBA("USER_NAME"); return this; }

    /**
     * Add order-by as descend. <br>
     * USER_NAME: {UQ, NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPokerUserInfoCQ addOrderBy_UserName_Desc() { regOBD("USER_NAME"); return this; }

    protected ConditionValue _password;
    public ConditionValue xdfgetPassword()
    { if (_password == null) { _password = nCV(); }
      return _password; }
    protected ConditionValue xgetCValuePassword() { return xdfgetPassword(); }

    /**
     * Add order-by as ascend. <br>
     * PASSWORD: {NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPokerUserInfoCQ addOrderBy_Password_Asc() { regOBA("PASSWORD"); return this; }

    /**
     * Add order-by as descend. <br>
     * PASSWORD: {NotNull, VARCHAR(255)}
     * @return this. (NotNull)
     */
    public BsPokerUserInfoCQ addOrderBy_Password_Desc() { regOBD("PASSWORD"); return this; }

    protected ConditionValue _loginDate;
    public ConditionValue xdfgetLoginDate()
    { if (_loginDate == null) { _loginDate = nCV(); }
      return _loginDate; }
    protected ConditionValue xgetCValueLoginDate() { return xdfgetLoginDate(); }

    /**
     * Add order-by as ascend. <br>
     * LOGIN_DATE: {TIMESTAMP(26, 6)}
     * @return this. (NotNull)
     */
    public BsPokerUserInfoCQ addOrderBy_LoginDate_Asc() { regOBA("LOGIN_DATE"); return this; }

    /**
     * Add order-by as descend. <br>
     * LOGIN_DATE: {TIMESTAMP(26, 6)}
     * @return this. (NotNull)
     */
    public BsPokerUserInfoCQ addOrderBy_LoginDate_Desc() { regOBD("LOGIN_DATE"); return this; }

    // ===================================================================================
    //                                                             SpecifiedDerivedOrderBy
    //                                                             =======================
    /**
     * Add order-by for specified derived column as ascend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] asc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Asc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsPokerUserInfoCQ addSpecifiedDerivedOrderBy_Asc(String aliasName) { registerSpecifiedDerivedOrderBy_Asc(aliasName); return this; }

    /**
     * Add order-by for specified derived column as descend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] desc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Desc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsPokerUserInfoCQ addSpecifiedDerivedOrderBy_Desc(String aliasName) { registerSpecifiedDerivedOrderBy_Desc(aliasName); return this; }

    // ===================================================================================
    //                                                                         Union Query
    //                                                                         ===========
    public void reflectRelationOnUnionQuery(ConditionQuery bqs, ConditionQuery uqs) {
        PokerUserInfoCQ bq = (PokerUserInfoCQ)bqs;
        PokerUserInfoCQ uq = (PokerUserInfoCQ)uqs;
        if (bq.hasConditionQueryPossessionMoneyAsOne()) {
            uq.queryPossessionMoneyAsOne().reflectRelationOnUnionQuery(bq.queryPossessionMoneyAsOne(), uq.queryPossessionMoneyAsOne());
        }
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    /**
     * Get the condition-query for relation table. <br>
     * POSSESSION_MONEY by USER_ID, named 'possessionMoneyAsOne'.
     * @return The instance of condition-query. (NotNull)
     */
    public PossessionMoneyCQ queryPossessionMoneyAsOne() { return xdfgetConditionQueryPossessionMoneyAsOne(); }
    public PossessionMoneyCQ xdfgetConditionQueryPossessionMoneyAsOne() {
        String prop = "possessionMoneyAsOne";
        if (!xhasQueRlMap(prop)) { xregQueRl(prop, xcreateQueryPossessionMoneyAsOne()); xsetupOuterJoinPossessionMoneyAsOne(); }
        return xgetQueRlMap(prop);
    }
    protected PossessionMoneyCQ xcreateQueryPossessionMoneyAsOne() {
        String nrp = xresolveNRP("POKER_USER_INFO", "possessionMoneyAsOne"); String jan = xresolveJAN(nrp, xgetNNLvl());
        return xinitRelCQ(new PossessionMoneyCQ(this, xgetSqlClause(), jan, xgetNNLvl()), _baseCB, "possessionMoneyAsOne", nrp);
    }
    protected void xsetupOuterJoinPossessionMoneyAsOne() { xregOutJo("possessionMoneyAsOne"); }
    public boolean hasConditionQueryPossessionMoneyAsOne() { return xhasQueRlMap("possessionMoneyAsOne"); }

    protected Map<String, Object> xfindFixedConditionDynamicParameterMap(String property) {
        return null;
    }

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    public Map<String, PokerUserInfoCQ> xdfgetScalarCondition() { return xgetSQueMap("scalarCondition"); }
    public String keepScalarCondition(PokerUserInfoCQ sq) { return xkeepSQue("scalarCondition", sq); }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public Map<String, PokerUserInfoCQ> xdfgetSpecifyMyselfDerived() { return xgetSQueMap("specifyMyselfDerived"); }
    public String keepSpecifyMyselfDerived(PokerUserInfoCQ sq) { return xkeepSQue("specifyMyselfDerived", sq); }

    public Map<String, PokerUserInfoCQ> xdfgetQueryMyselfDerived() { return xgetSQueMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerived(PokerUserInfoCQ sq) { return xkeepSQue("queryMyselfDerived", sq); }
    public Map<String, Object> xdfgetQueryMyselfDerivedParameter() { return xgetSQuePmMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerivedParameter(Object pm) { return xkeepSQuePm("queryMyselfDerived", pm); }

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    protected Map<String, PokerUserInfoCQ> _myselfExistsMap;
    public Map<String, PokerUserInfoCQ> xdfgetMyselfExists() { return xgetSQueMap("myselfExists"); }
    public String keepMyselfExists(PokerUserInfoCQ sq) { return xkeepSQue("myselfExists", sq); }

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    public Map<String, PokerUserInfoCQ> xdfgetMyselfInScope() { return xgetSQueMap("myselfInScope"); }
    public String keepMyselfInScope(PokerUserInfoCQ sq) { return xkeepSQue("myselfInScope", sq); }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xCB() { return PokerUserInfoCB.class.getName(); }
    protected String xCQ() { return PokerUserInfoCQ.class.getName(); }
    protected String xCHp() { return HpQDRFunction.class.getName(); }
    protected String xCOp() { return ConditionOption.class.getName(); }
    protected String xMap() { return Map.class.getName(); }
}
