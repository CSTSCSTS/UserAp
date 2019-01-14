package com.example.demo.dbflute.cbean.cq.bs;

import java.util.*;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.ckey.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.ordering.*;
import org.dbflute.cbean.scoping.*;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.dbmeta.DBMetaProvider;
import com.example.demo.dbflute.allcommon.*;
import com.example.demo.dbflute.cbean.*;
import com.example.demo.dbflute.cbean.cq.*;

/**
 * The abstract condition-query of POSSESSION_MONEY.
 * @author DBFlute(AutoGenerator)
 */
public abstract class AbstractBsPossessionMoneyCQ extends AbstractConditionQuery {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AbstractBsPossessionMoneyCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    protected DBMetaProvider xgetDBMetaProvider() {
        return DBMetaInstanceHandler.getProvider();
    }

    public String asTableDbName() {
        return "POSSESSION_MONEY";
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @param userId The value of userId as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUserId_Equal(String userId) {
        doSetUserId_Equal(fRES(userId));
    }

    protected void doSetUserId_Equal(String userId) {
        regUserId(CK_EQ, userId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @param userId The value of userId as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUserId_NotEqual(String userId) {
        doSetUserId_NotEqual(fRES(userId));
    }

    protected void doSetUserId_NotEqual(String userId) {
        regUserId(CK_NES, userId);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @param userId The value of userId as greaterThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUserId_GreaterThan(String userId) {
        regUserId(CK_GT, fRES(userId));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @param userId The value of userId as lessThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUserId_LessThan(String userId) {
        regUserId(CK_LT, fRES(userId));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @param userId The value of userId as greaterEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUserId_GreaterEqual(String userId) {
        regUserId(CK_GE, fRES(userId));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @param userId The value of userId as lessEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUserId_LessEqual(String userId) {
        regUserId(CK_LE, fRES(userId));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @param userIdList The collection of userId as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUserId_InScope(Collection<String> userIdList) {
        doSetUserId_InScope(userIdList);
    }

    protected void doSetUserId_InScope(Collection<String> userIdList) {
        regINS(CK_INS, cTL(userIdList), xgetCValueUserId(), "USER_ID");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @param userIdList The collection of userId as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUserId_NotInScope(Collection<String> userIdList) {
        doSetUserId_NotInScope(userIdList);
    }

    protected void doSetUserId_NotInScope(Collection<String> userIdList) {
        regINS(CK_NINS, cTL(userIdList), xgetCValueUserId(), "USER_ID");
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO} <br>
     * <pre>e.g. setUserId_LikeSearch("xxx", op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">likeContain()</span>);</pre>
     * @param userId The value of userId as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setUserId_LikeSearch(String userId, ConditionOptionCall<LikeSearchOption> opLambda) {
        setUserId_LikeSearch(userId, xcLSOP(opLambda));
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO} <br>
     * <pre>e.g. setUserId_LikeSearch("xxx", new <span style="color: #CC4747">LikeSearchOption</span>().likeContain());</pre>
     * @param userId The value of userId as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    protected void setUserId_LikeSearch(String userId, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(userId), xgetCValueUserId(), "USER_ID", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @param userId The value of userId as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setUserId_NotLikeSearch(String userId, ConditionOptionCall<LikeSearchOption> opLambda) {
        setUserId_NotLikeSearch(userId, xcLSOP(opLambda));
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @param userId The value of userId as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    protected void setUserId_NotLikeSearch(String userId, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(userId), xgetCValueUserId(), "USER_ID", likeSearchOption);
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     */
    public void setUserId_IsNull() { regUserId(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     */
    public void setUserId_IsNotNull() { regUserId(CK_ISNN, DOBJ); }

    protected void regUserId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueUserId(), "USER_ID"); }
    protected abstract ConditionValue xgetCValueUserId();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @param possessionMoney The value of possessionMoney as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setPossessionMoney_Equal(java.math.BigDecimal possessionMoney) {
        doSetPossessionMoney_Equal(possessionMoney);
    }

    protected void doSetPossessionMoney_Equal(java.math.BigDecimal possessionMoney) {
        regPossessionMoney(CK_EQ, possessionMoney);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @param possessionMoney The value of possessionMoney as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setPossessionMoney_NotEqual(java.math.BigDecimal possessionMoney) {
        doSetPossessionMoney_NotEqual(possessionMoney);
    }

    protected void doSetPossessionMoney_NotEqual(java.math.BigDecimal possessionMoney) {
        regPossessionMoney(CK_NES, possessionMoney);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @param possessionMoney The value of possessionMoney as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setPossessionMoney_GreaterThan(java.math.BigDecimal possessionMoney) {
        regPossessionMoney(CK_GT, possessionMoney);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @param possessionMoney The value of possessionMoney as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setPossessionMoney_LessThan(java.math.BigDecimal possessionMoney) {
        regPossessionMoney(CK_LT, possessionMoney);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @param possessionMoney The value of possessionMoney as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setPossessionMoney_GreaterEqual(java.math.BigDecimal possessionMoney) {
        regPossessionMoney(CK_GE, possessionMoney);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @param possessionMoney The value of possessionMoney as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setPossessionMoney_LessEqual(java.math.BigDecimal possessionMoney) {
        regPossessionMoney(CK_LE, possessionMoney);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @param minNumber The min number of possessionMoney. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of possessionMoney. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setPossessionMoney_RangeOf(java.math.BigDecimal minNumber, java.math.BigDecimal maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setPossessionMoney_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @param minNumber The min number of possessionMoney. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of possessionMoney. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setPossessionMoney_RangeOf(java.math.BigDecimal minNumber, java.math.BigDecimal maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValuePossessionMoney(), "POSSESSION_MONEY", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @param possessionMoneyList The collection of possessionMoney as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setPossessionMoney_InScope(Collection<java.math.BigDecimal> possessionMoneyList) {
        doSetPossessionMoney_InScope(possessionMoneyList);
    }

    protected void doSetPossessionMoney_InScope(Collection<java.math.BigDecimal> possessionMoneyList) {
        regINS(CK_INS, cTL(possessionMoneyList), xgetCValuePossessionMoney(), "POSSESSION_MONEY");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @param possessionMoneyList The collection of possessionMoney as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setPossessionMoney_NotInScope(Collection<java.math.BigDecimal> possessionMoneyList) {
        doSetPossessionMoney_NotInScope(possessionMoneyList);
    }

    protected void doSetPossessionMoney_NotInScope(Collection<java.math.BigDecimal> possessionMoneyList) {
        regINS(CK_NINS, cTL(possessionMoneyList), xgetCValuePossessionMoney(), "POSSESSION_MONEY");
    }

    protected void regPossessionMoney(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValuePossessionMoney(), "POSSESSION_MONEY"); }
    protected abstract ConditionValue xgetCValuePossessionMoney();

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO = (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PossessionMoneyCB> scalar_Equal() {
        return xcreateSLCFunction(CK_EQ, PossessionMoneyCB.class);
    }

    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO &lt;&gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PossessionMoneyCB> scalar_NotEqual() {
        return xcreateSLCFunction(CK_NES, PossessionMoneyCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterThan. <br>
     * {where FOO &gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PossessionMoneyCB> scalar_GreaterThan() {
        return xcreateSLCFunction(CK_GT, PossessionMoneyCB.class);
    }

    /**
     * Prepare ScalarCondition as lessThan. <br>
     * {where FOO &lt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PossessionMoneyCB> scalar_LessThan() {
        return xcreateSLCFunction(CK_LT, PossessionMoneyCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterEqual. <br>
     * {where FOO &gt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PossessionMoneyCB> scalar_GreaterEqual() {
        return xcreateSLCFunction(CK_GE, PossessionMoneyCB.class);
    }

    /**
     * Prepare ScalarCondition as lessEqual. <br>
     * {where FOO &lt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().<span style="color: #CC4747">scalar_LessEqual()</span>.max(new SubQuery&lt;PossessionMoneyCB&gt;() {
     *     public void query(PossessionMoneyCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<PossessionMoneyCB> scalar_LessEqual() {
        return xcreateSLCFunction(CK_LE, PossessionMoneyCB.class);
    }

    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xscalarCondition(String fn, SubQuery<CB> sq, String rd, HpSLCCustomized<CB> cs, ScalarConditionOption op) {
        assertObjectNotNull("subQuery", sq);
        PossessionMoneyCB cb = xcreateScalarConditionCB(); sq.query((CB)cb);
        String pp = keepScalarCondition(cb.query()); // for saving query-value
        cs.setPartitionByCBean((CB)xcreateScalarConditionPartitionByCB()); // for using partition-by
        registerScalarCondition(fn, cb.query(), pp, rd, cs, op);
    }
    public abstract String keepScalarCondition(PossessionMoneyCQ sq);

    protected PossessionMoneyCB xcreateScalarConditionCB() {
        PossessionMoneyCB cb = newMyCB(); cb.xsetupForScalarCondition(this); return cb;
    }

    protected PossessionMoneyCB xcreateScalarConditionPartitionByCB() {
        PossessionMoneyCB cb = newMyCB(); cb.xsetupForScalarConditionPartitionBy(this); return cb;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public void xsmyselfDerive(String fn, SubQuery<PossessionMoneyCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        PossessionMoneyCB cb = new PossessionMoneyCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepSpecifyMyselfDerived(cb.query()); String pk = "USER_ID";
        registerSpecifyMyselfDerived(fn, cb.query(), pk, pk, pp, "myselfDerived", al, op);
    }
    public abstract String keepSpecifyMyselfDerived(PossessionMoneyCQ sq);

    /**
     * Prepare for (Query)MyselfDerived (correlated sub-query).
     * @return The object to set up a function for myself table. (NotNull)
     */
    public HpQDRFunction<PossessionMoneyCB> myselfDerived() {
        return xcreateQDRFunctionMyselfDerived(PossessionMoneyCB.class);
    }
    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xqderiveMyselfDerived(String fn, SubQuery<CB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        PossessionMoneyCB cb = new PossessionMoneyCB(); cb.xsetupForDerivedReferrer(this); sq.query((CB)cb);
        String pk = "USER_ID";
        String sqpp = keepQueryMyselfDerived(cb.query()); // for saving query-value.
        String prpp = keepQueryMyselfDerivedParameter(vl);
        registerQueryMyselfDerived(fn, cb.query(), pk, pk, sqpp, "myselfDerived", rd, vl, prpp, op);
    }
    public abstract String keepQueryMyselfDerived(PossessionMoneyCQ sq);
    public abstract String keepQueryMyselfDerivedParameter(Object vl);

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    /**
     * Prepare for MyselfExists (correlated sub-query).
     * @param subCBLambda The implementation of sub-query. (NotNull)
     */
    public void myselfExists(SubQuery<PossessionMoneyCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        PossessionMoneyCB cb = new PossessionMoneyCB(); cb.xsetupForMyselfExists(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepMyselfExists(cb.query());
        registerMyselfExists(cb.query(), pp);
    }
    public abstract String keepMyselfExists(PossessionMoneyCQ sq);

    // ===================================================================================
    //                                                                        Manual Order
    //                                                                        ============
    /**
     * Order along manual ordering information.
     * <pre>
     * cb.query().addOrderBy_Birthdate_Asc().<span style="color: #CC4747">withManualOrder</span>(<span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_GreaterEqual</span>(priorityDate); <span style="color: #3F7E5E">// e.g. 2000/01/01</span>
     * });
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when BIRTHDATE &gt;= '2000/01/01' then 0</span>
     * <span style="color: #3F7E5E">//     else 1</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     *
     * cb.query().addOrderBy_MemberStatusCode_Asc().<span style="color: #CC4747">withManualOrder</span>(<span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Withdrawal);
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Formalized);
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Provisional);
     * });
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'WDL' then 0</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'FML' then 1</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'PRV' then 2</span>
     * <span style="color: #3F7E5E">//     else 3</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     * </pre>
     * <p>This function with Union is unsupported!</p>
     * <p>The order values are bound (treated as bind parameter).</p>
     * @param opLambda The callback for option of manual-order containing order values. (NotNull)
     */
    public void withManualOrder(ManualOrderOptionCall opLambda) { // is user public!
        xdoWithManualOrder(cMOO(opLambda));
    }

    // ===================================================================================
    //                                                                    Small Adjustment
    //                                                                    ================
    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    protected PossessionMoneyCB newMyCB() {
        return new PossessionMoneyCB();
    }
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xabUDT() { return Date.class.getName(); }
    protected String xabCQ() { return PossessionMoneyCQ.class.getName(); }
    protected String xabLSO() { return LikeSearchOption.class.getName(); }
    protected String xabSLCS() { return HpSLCSetupper.class.getName(); }
    protected String xabSCP() { return SubQuery.class.getName(); }
}
