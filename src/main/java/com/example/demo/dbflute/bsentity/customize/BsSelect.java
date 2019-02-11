package com.example.demo.dbflute.bsentity.customize;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.CustomizeEntity;
import com.example.demo.dbflute.exentity.customize.*;

/**
 * The entity of Select. <br>
 * <pre>
 * [primary-key]
 *     
 *
 * [column]
 *     USER_ID, USER_NAME, PASSWORD, LOGIN_DATE, POSSESSION_MONEY, UPDATE_DATE
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
 * String userName = entity.getUserName();
 * String password = entity.getPassword();
 * java.time.LocalDateTime loginDate = entity.getLoginDate();
 * java.math.BigDecimal possessionMoney = entity.getPossessionMoney();
 * java.time.LocalDateTime updateDate = entity.getUpdateDate();
 * entity.setUserId(userId);
 * entity.setUserName(userName);
 * entity.setPassword(password);
 * entity.setLoginDate(loginDate);
 * entity.setPossessionMoney(possessionMoney);
 * entity.setUpdateDate(updateDate);
 * = = = = = = = = = =/
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsSelect extends AbstractEntity implements CustomizeEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** USER_ID: {INTEGER(10), refers to POSSESSION_MONEY.USER_ID} */
    protected Integer _userId;

    /** USER_NAME: {VARCHAR(255), refers to POKER_USER_INFO.USER_NAME} */
    protected String _userName;

    /** PASSWORD: {VARCHAR(255), refers to POKER_USER_INFO.PASSWORD} */
    protected String _password;

    /** LOGIN_DATE: {TIMESTAMP(26, 6), refers to POKER_USER_INFO.LOGIN_DATE} */
    protected java.time.LocalDateTime _loginDate;

    /** POSSESSION_MONEY: {DECIMAL(65535, 32767), refers to POSSESSION_MONEY.POSSESSION_MONEY} */
    protected java.math.BigDecimal _possessionMoney;

    /** UPDATE_DATE: {TIMESTAMP(26, 6), refers to POSSESSION_MONEY.UPDATE_DATE} */
    protected java.time.LocalDateTime _updateDate;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return com.example.demo.dbflute.bsentity.customize.dbmeta.SelectDbm.getInstance();
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "Select";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        return false;
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
        if (obj instanceof BsSelect) {
            BsSelect other = (BsSelect)obj;
            if (!xSV(_userId, other._userId)) { return false; }
            if (!xSV(_userName, other._userName)) { return false; }
            if (!xSV(_password, other._password)) { return false; }
            if (!xSV(_loginDate, other._loginDate)) { return false; }
            if (!xSV(_possessionMoney, other._possessionMoney)) { return false; }
            if (!xSV(_updateDate, other._updateDate)) { return false; }
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
        hs = xCH(hs, _userName);
        hs = xCH(hs, _password);
        hs = xCH(hs, _loginDate);
        hs = xCH(hs, _possessionMoney);
        hs = xCH(hs, _updateDate);
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
        sb.append(dm).append(xfND(_userName));
        sb.append(dm).append(xfND(_password));
        sb.append(dm).append(xfND(_loginDate));
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
    public Select clone() {
        return (Select)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] USER_ID: {INTEGER(10), refers to POSSESSION_MONEY.USER_ID} <br>
     * ユーザーID
     * @return The value of the column 'USER_ID'. (NullAllowed even if selected: for no constraint)
     */
    public Integer getUserId() {
        checkSpecifiedProperty("userId");
        return _userId;
    }

    /**
     * [set] USER_ID: {INTEGER(10), refers to POSSESSION_MONEY.USER_ID} <br>
     * ユーザーID
     * @param userId The value of the column 'USER_ID'. (NullAllowed: null update allowed for no constraint)
     */
    public void setUserId(Integer userId) {
        registerModifiedProperty("userId");
        _userId = userId;
    }

    /**
     * [get] USER_NAME: {VARCHAR(255), refers to POKER_USER_INFO.USER_NAME} <br>
     * ユーザー名
     * @return The value of the column 'USER_NAME'. (NullAllowed even if selected: for no constraint)
     */
    public String getUserName() {
        checkSpecifiedProperty("userName");
        return _userName;
    }

    /**
     * [set] USER_NAME: {VARCHAR(255), refers to POKER_USER_INFO.USER_NAME} <br>
     * ユーザー名
     * @param userName The value of the column 'USER_NAME'. (NullAllowed: null update allowed for no constraint)
     */
    public void setUserName(String userName) {
        registerModifiedProperty("userName");
        _userName = userName;
    }

    /**
     * [get] PASSWORD: {VARCHAR(255), refers to POKER_USER_INFO.PASSWORD} <br>
     * パスワード
     * @return The value of the column 'PASSWORD'. (NullAllowed even if selected: for no constraint)
     */
    public String getPassword() {
        checkSpecifiedProperty("password");
        return _password;
    }

    /**
     * [set] PASSWORD: {VARCHAR(255), refers to POKER_USER_INFO.PASSWORD} <br>
     * パスワード
     * @param password The value of the column 'PASSWORD'. (NullAllowed: null update allowed for no constraint)
     */
    public void setPassword(String password) {
        registerModifiedProperty("password");
        _password = password;
    }

    /**
     * [get] LOGIN_DATE: {TIMESTAMP(26, 6), refers to POKER_USER_INFO.LOGIN_DATE} <br>
     * ログイン日時
     * @return The value of the column 'LOGIN_DATE'. (NullAllowed even if selected: for no constraint)
     */
    public java.time.LocalDateTime getLoginDate() {
        checkSpecifiedProperty("loginDate");
        return _loginDate;
    }

    /**
     * [set] LOGIN_DATE: {TIMESTAMP(26, 6), refers to POKER_USER_INFO.LOGIN_DATE} <br>
     * ログイン日時
     * @param loginDate The value of the column 'LOGIN_DATE'. (NullAllowed: null update allowed for no constraint)
     */
    public void setLoginDate(java.time.LocalDateTime loginDate) {
        registerModifiedProperty("loginDate");
        _loginDate = loginDate;
    }

    /**
     * [get] POSSESSION_MONEY: {DECIMAL(65535, 32767), refers to POSSESSION_MONEY.POSSESSION_MONEY} <br>
     * 所持金
     * @return The value of the column 'POSSESSION_MONEY'. (NullAllowed even if selected: for no constraint)
     */
    public java.math.BigDecimal getPossessionMoney() {
        checkSpecifiedProperty("possessionMoney");
        return _possessionMoney;
    }

    /**
     * [set] POSSESSION_MONEY: {DECIMAL(65535, 32767), refers to POSSESSION_MONEY.POSSESSION_MONEY} <br>
     * 所持金
     * @param possessionMoney The value of the column 'POSSESSION_MONEY'. (NullAllowed: null update allowed for no constraint)
     */
    public void setPossessionMoney(java.math.BigDecimal possessionMoney) {
        registerModifiedProperty("possessionMoney");
        _possessionMoney = possessionMoney;
    }

    /**
     * [get] UPDATE_DATE: {TIMESTAMP(26, 6), refers to POSSESSION_MONEY.UPDATE_DATE} <br>
     * 更新日時
     * @return The value of the column 'UPDATE_DATE'. (NullAllowed even if selected: for no constraint)
     */
    public java.time.LocalDateTime getUpdateDate() {
        checkSpecifiedProperty("updateDate");
        return _updateDate;
    }

    /**
     * [set] UPDATE_DATE: {TIMESTAMP(26, 6), refers to POSSESSION_MONEY.UPDATE_DATE} <br>
     * 更新日時
     * @param updateDate The value of the column 'UPDATE_DATE'. (NullAllowed: null update allowed for no constraint)
     */
    public void setUpdateDate(java.time.LocalDateTime updateDate) {
        registerModifiedProperty("updateDate");
        _updateDate = updateDate;
    }
}
