package com.example.demo.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.Entity;
import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.dbflute.optional.OptionalEntity;
import com.example.demo.dbflute.allcommon.DBMetaInstanceHandler;
import com.example.demo.dbflute.exentity.*;

/**
 * The entity of POSSESSION_MONEY as TABLE. <br>
 * 所持金
 * <pre>
 * [primary-key]
 *     USER_ID
 *
 * [column]
 *     USER_ID, POSSESSION_MONEY
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
 *
 * [get/set template]
 * /= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 * String userId = entity.getUserId();
 * java.math.BigDecimal possessionMoney = entity.getPossessionMoney();
 * entity.setUserId(userId);
 * entity.setPossessionMoney(possessionMoney);
 * = = = = = = = = = =/
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsPossessionMoney extends AbstractEntity implements DomainEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO} */
    protected String _userId;

    /** POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)} */
    protected java.math.BigDecimal _possessionMoney;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "POSSESSION_MONEY";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_userId == null) { return false; }
        return true;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    /** POKER_USER_INFO by my USER_ID, named 'pokerUserInfo'. */
    protected OptionalEntity<PokerUserInfo> _pokerUserInfo;

    /**
     * [get] POKER_USER_INFO by my USER_ID, named 'pokerUserInfo'. <br>
     * Optional: alwaysPresent(), ifPresent().orElse(), get(), ...
     * @return The entity of foreign property 'pokerUserInfo'. (NotNull, EmptyAllowed: when e.g. null FK column, no setupSelect)
     */
    public OptionalEntity<PokerUserInfo> getPokerUserInfo() {
        if (_pokerUserInfo == null) { _pokerUserInfo = OptionalEntity.relationEmpty(this, "pokerUserInfo"); }
        return _pokerUserInfo;
    }

    /**
     * [set] POKER_USER_INFO by my USER_ID, named 'pokerUserInfo'.
     * @param pokerUserInfo The entity of foreign property 'pokerUserInfo'. (NullAllowed)
     */
    public void setPokerUserInfo(OptionalEntity<PokerUserInfo> pokerUserInfo) {
        _pokerUserInfo = pokerUserInfo;
    }

    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsPossessionMoney) {
            BsPossessionMoney other = (BsPossessionMoney)obj;
            if (!xSV(_userId, other._userId)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _userId);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        StringBuilder sb = new StringBuilder();
        if (_pokerUserInfo != null && _pokerUserInfo.isPresent())
        { sb.append(li).append(xbRDS(_pokerUserInfo, "pokerUserInfo")); }
        return sb.toString();
    }
    protected <ET extends Entity> String xbRDS(org.dbflute.optional.OptionalEntity<ET> et, String name) { // buildRelationDisplayString()
        return et.get().buildDisplayString(name, true, true);
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_userId));
        sb.append(dm).append(xfND(_possessionMoney));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        StringBuilder sb = new StringBuilder();
        if (_pokerUserInfo != null && _pokerUserInfo.isPresent())
        { sb.append(dm).append("pokerUserInfo"); }
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    @Override
    public PossessionMoney clone() {
        return (PossessionMoney)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO} <br>
     * ユーザーID
     * @return The value of the column 'USER_ID'. (basically NotNull if selected: for the constraint)
     */
    public String getUserId() {
        checkSpecifiedProperty("userId");
        return _userId;
    }

    /**
     * [set] USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO} <br>
     * ユーザーID
     * @param userId The value of the column 'USER_ID'. (basically NotNull if update: for the constraint)
     */
    public void setUserId(String userId) {
        registerModifiedProperty("userId");
        _userId = userId;
    }

    /**
     * [get] POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)} <br>
     * 所持金
     * @return The value of the column 'POSSESSION_MONEY'. (basically NotNull if selected: for the constraint)
     */
    public java.math.BigDecimal getPossessionMoney() {
        checkSpecifiedProperty("possessionMoney");
        return _possessionMoney;
    }

    /**
     * [set] POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)} <br>
     * 所持金
     * @param possessionMoney The value of the column 'POSSESSION_MONEY'. (basically NotNull if update: for the constraint)
     */
    public void setPossessionMoney(java.math.BigDecimal possessionMoney) {
        registerModifiedProperty("possessionMoney");
        _possessionMoney = possessionMoney;
    }
}
