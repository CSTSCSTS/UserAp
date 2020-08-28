package com.example.demo.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
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
 *
 * [get/set template]
 * /= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 * Integer userId = entity.getUserId();
 * java.math.BigDecimal possessionMoney = entity.getPossessionMoney();
 * java.time.LocalDateTime updateDate = entity.getUpdateDate();
 * entity.setUserId(userId);
 * entity.setPossessionMoney(possessionMoney);
 * entity.setUpdateDate(updateDate);
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
    /** USER_ID: {PK, NotNull, INTEGER(10)} */
    protected Integer _userId;

    /** POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)} */
    protected java.math.BigDecimal _possessionMoney;

    /** UPDATE_DATE: {NotNull, TIMESTAMP(26, 6), default=[NOW()]} */
    protected java.time.LocalDateTime _updateDate;

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
        return "";
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_userId));
        sb.append(dm).append(xfND(_possessionMoney));
        sb.append(dm).append(xfND(_updateDate));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        return "";
    }

    @Override
    public PossessionMoney clone() {
        return (PossessionMoney)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] USER_ID: {PK, NotNull, INTEGER(10)} <br>
     * ユーザーID
     * @return The value of the column 'USER_ID'. (basically NotNull if selected: for the constraint)
     */
    public Integer getUserId() {
        checkSpecifiedProperty("userId");
        return _userId;
    }

    /**
     * [set] USER_ID: {PK, NotNull, INTEGER(10)} <br>
     * ユーザーID
     * @param userId The value of the column 'USER_ID'. (basically NotNull if update: for the constraint)
     */
    public void setUserId(Integer userId) {
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

    /**
     * [get] UPDATE_DATE: {NotNull, TIMESTAMP(26, 6), default=[NOW()]} <br>
     * 更新日時
     * @return The value of the column 'UPDATE_DATE'. (basically NotNull if selected: for the constraint)
     */
    public java.time.LocalDateTime getUpdateDate() {
        checkSpecifiedProperty("updateDate");
        return _updateDate;
    }

    /**
     * [set] UPDATE_DATE: {NotNull, TIMESTAMP(26, 6), default=[NOW()]} <br>
     * 更新日時
     * @param updateDate The value of the column 'UPDATE_DATE'. (basically NotNull if update: for the constraint)
     */
    public void setUpdateDate(java.time.LocalDateTime updateDate) {
        registerModifiedProperty("updateDate");
        _updateDate = updateDate;
    }
}
