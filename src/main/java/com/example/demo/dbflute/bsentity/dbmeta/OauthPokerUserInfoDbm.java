package com.example.demo.dbflute.bsentity.dbmeta;

import java.util.List;
import java.util.Map;

import org.dbflute.Entity;
import org.dbflute.dbmeta.AbstractDBMeta;
import org.dbflute.dbmeta.info.*;
import org.dbflute.dbmeta.name.*;
import org.dbflute.dbmeta.property.PropertyGateway;
import org.dbflute.dbway.DBDef;
import com.example.demo.dbflute.allcommon.*;
import com.example.demo.dbflute.exentity.*;

/**
 * The DB meta of OAUTH_POKER_USER_INFO. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class OauthPokerUserInfoDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final OauthPokerUserInfoDbm _instance = new OauthPokerUserInfoDbm();
    private OauthPokerUserInfoDbm() {}
    public static OauthPokerUserInfoDbm getInstance() { return _instance; }

    // ===================================================================================
    //                                                                       Current DBDef
    //                                                                       =============
    public String getProjectName() { return DBCurrent.getInstance().projectName(); }
    public String getProjectPrefix() { return DBCurrent.getInstance().projectPrefix(); }
    public String getGenerationGapBasePrefix() { return DBCurrent.getInstance().generationGapBasePrefix(); }
    public DBDef getCurrentDBDef() { return DBCurrent.getInstance().currentDBDef(); }

    // ===================================================================================
    //                                                                    Property Gateway
    //                                                                    ================
    // -----------------------------------------------------
    //                                       Column Property
    //                                       ---------------
    protected final Map<String, PropertyGateway> _epgMap = newHashMap();
    { xsetupEpg(); }
    protected void xsetupEpg() {
        setupEpg(_epgMap, et -> ((OauthPokerUserInfo)et).getUserId(), (et, vl) -> ((OauthPokerUserInfo)et).setUserId(cti(vl)), "userId");
        setupEpg(_epgMap, et -> ((OauthPokerUserInfo)et).getUserName(), (et, vl) -> ((OauthPokerUserInfo)et).setUserName((String)vl), "userName");
        setupEpg(_epgMap, et -> ((OauthPokerUserInfo)et).getLoginDate(), (et, vl) -> ((OauthPokerUserInfo)et).setLoginDate(ctldt(vl)), "loginDate");
    }
    public PropertyGateway findPropertyGateway(String prop)
    { return doFindEpg(_epgMap, prop); }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "OAUTH_POKER_USER_INFO";
    protected final String _tableDispName = "OAUTH_POKER_USER_INFO";
    protected final String _tablePropertyName = "oauthPokerUserInfo";
    protected final TableSqlName _tableSqlName = new TableSqlName("OAUTH_POKER_USER_INFO", _tableDbName);
    { _tableSqlName.xacceptFilter(DBFluteConfig.getInstance().getTableSqlNameFilter()); }
    public String getTableDbName() { return _tableDbName; }
    public String getTableDispName() { return _tableDispName; }
    public String getTablePropertyName() { return _tablePropertyName; }
    public TableSqlName getTableSqlName() { return _tableSqlName; }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnUserId = cci("USER_ID", "USER_ID", null, null, Integer.class, "userId", null, true, false, true, "INTEGER", 10, 0, null, "NEXTVAL('POKER_USER_ID_SEQ1')", false, null, null, null, null, null, false);
    protected final ColumnInfo _columnUserName = cci("USER_NAME", "USER_NAME", null, null, String.class, "userName", null, false, false, true, "VARCHAR", 255, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnLoginDate = cci("LOGIN_DATE", "LOGIN_DATE", null, null, java.time.LocalDateTime.class, "loginDate", null, false, false, true, "TIMESTAMP", 26, 6, null, "NOW()", false, null, null, null, null, null, false);

    /**
     * USER_ID: {PK, NotNull, INTEGER(10), default=[NEXTVAL('POKER_USER_ID_SEQ1')]}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnUserId() { return _columnUserId; }
    /**
     * USER_NAME: {NotNull, VARCHAR(255)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnUserName() { return _columnUserName; }
    /**
     * LOGIN_DATE: {NotNull, TIMESTAMP(26, 6), default=[NOW()]}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnLoginDate() { return _columnLoginDate; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnUserId());
        ls.add(columnUserName());
        ls.add(columnLoginDate());
        return ls;
    }

    { initializeInformationResource(); }

    // ===================================================================================
    //                                                                         Unique Info
    //                                                                         ===========
    // -----------------------------------------------------
    //                                       Primary Element
    //                                       ---------------
    protected UniqueInfo cpui() { return hpcpui(columnUserId()); }
    public boolean hasPrimaryKey() { return true; }
    public boolean hasCompoundPrimaryKey() { return false; }

    // ===================================================================================
    //                                                                       Relation Info
    //                                                                       =============
    // cannot cache because it uses related DB meta instance while booting
    // (instead, cached by super's collection)
    // -----------------------------------------------------
    //                                      Foreign Property
    //                                      ----------------

    // -----------------------------------------------------
    //                                     Referrer Property
    //                                     -----------------

    // ===================================================================================
    //                                                                        Various Info
    //                                                                        ============
    public boolean hasSequence() { return true; }
    public String getSequenceName() { return "POKER_USER_ID_SEQ1"; }
    public Integer getSequenceIncrementSize() { return 1; }
    public Integer getSequenceCacheSize() { return null; }

    // ===================================================================================
    //                                                                           Type Name
    //                                                                           =========
    public String getEntityTypeName() { return "com.example.demo.dbflute.exentity.OauthPokerUserInfo"; }
    public String getConditionBeanTypeName() { return "com.example.demo.dbflute.cbean.OauthPokerUserInfoCB"; }
    public String getBehaviorTypeName() { return "com.example.demo.dbflute.exbhv.OauthPokerUserInfoBhv"; }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    public Class<OauthPokerUserInfo> getEntityType() { return OauthPokerUserInfo.class; }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    public OauthPokerUserInfo newEntity() { return new OauthPokerUserInfo(); }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    public void acceptPrimaryKeyMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptPrimaryKeyMap((OauthPokerUserInfo)et, mp); }
    public void acceptAllColumnMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptAllColumnMap((OauthPokerUserInfo)et, mp); }
    public Map<String, Object> extractPrimaryKeyMap(Entity et) { return doExtractPrimaryKeyMap(et); }
    public Map<String, Object> extractAllColumnMap(Entity et) { return doExtractAllColumnMap(et); }
}
