package com.example.demo.dbflute.bsentity.customize.dbmeta;

import java.util.List;
import java.util.Map;

import org.dbflute.Entity;
import org.dbflute.dbmeta.AbstractDBMeta;
import org.dbflute.dbmeta.info.*;
import org.dbflute.dbmeta.name.*;
import org.dbflute.dbmeta.property.PropertyGateway;
import org.dbflute.dbway.DBDef;
import com.example.demo.dbflute.allcommon.*;
import com.example.demo.dbflute.exentity.customize.*;

/**
 * The DB meta of Select. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class SelectDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final SelectDbm _instance = new SelectDbm();
    private SelectDbm() {}
    public static SelectDbm getInstance() { return _instance; }

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
        setupEpg(_epgMap, et -> ((Select)et).getUserId(), (et, vl) -> ((Select)et).setUserId(cti(vl)), "userId");
        setupEpg(_epgMap, et -> ((Select)et).getUserName(), (et, vl) -> ((Select)et).setUserName((String)vl), "userName");
        setupEpg(_epgMap, et -> ((Select)et).getPassword(), (et, vl) -> ((Select)et).setPassword((String)vl), "password");
        setupEpg(_epgMap, et -> ((Select)et).getLoginDate(), (et, vl) -> ((Select)et).setLoginDate(ctldt(vl)), "loginDate");
        setupEpg(_epgMap, et -> ((Select)et).getPossessionMoney(), (et, vl) -> ((Select)et).setPossessionMoney(ctb(vl)), "possessionMoney");
        setupEpg(_epgMap, et -> ((Select)et).getUpdateDate(), (et, vl) -> ((Select)et).setUpdateDate(ctldt(vl)), "updateDate");
    }
    public PropertyGateway findPropertyGateway(String prop)
    { return doFindEpg(_epgMap, prop); }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "Select";
    protected final String _tableDispName = "Select";
    protected final String _tablePropertyName = "select";
    protected final TableSqlName _tableSqlName = new TableSqlName("Select", _tableDbName);
    { _tableSqlName.xacceptFilter(DBFluteConfig.getInstance().getTableSqlNameFilter()); }
    public String getTableDbName() { return _tableDbName; }
    public String getTableDispName() { return _tableDispName; }
    public String getTablePropertyName() { return _tablePropertyName; }
    public TableSqlName getTableSqlName() { return _tableSqlName; }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnUserId = cci("USER_ID", "USER_ID", null, null, Integer.class, "userId", null, false, false, false, "INTEGER", 10, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnUserName = cci("USER_NAME", "USER_NAME", null, null, String.class, "userName", null, false, false, false, "VARCHAR", 255, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnPassword = cci("PASSWORD", "PASSWORD", null, null, String.class, "password", null, false, false, false, "VARCHAR", 255, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnLoginDate = cci("LOGIN_DATE", "LOGIN_DATE", null, null, java.time.LocalDateTime.class, "loginDate", null, false, false, false, "TIMESTAMP", 26, 6, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnPossessionMoney = cci("POSSESSION_MONEY", "POSSESSION_MONEY", null, null, java.math.BigDecimal.class, "possessionMoney", null, false, false, false, "DECIMAL", 65535, 32767, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnUpdateDate = cci("UPDATE_DATE", "UPDATE_DATE", null, null, java.time.LocalDateTime.class, "updateDate", null, false, false, false, "TIMESTAMP", 26, 6, null, null, false, null, null, null, null, null, false);

    /**
     * USER_ID: {INTEGER(10), refers to POSSESSION_MONEY.USER_ID}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnUserId() { return _columnUserId; }
    /**
     * USER_NAME: {VARCHAR(255), refers to POKER_USER_INFO.USER_NAME}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnUserName() { return _columnUserName; }
    /**
     * PASSWORD: {VARCHAR(255), refers to POKER_USER_INFO.PASSWORD}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnPassword() { return _columnPassword; }
    /**
     * LOGIN_DATE: {TIMESTAMP(26, 6), refers to POKER_USER_INFO.LOGIN_DATE}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnLoginDate() { return _columnLoginDate; }
    /**
     * POSSESSION_MONEY: {DECIMAL(65535, 32767), refers to POSSESSION_MONEY.POSSESSION_MONEY}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnPossessionMoney() { return _columnPossessionMoney; }
    /**
     * UPDATE_DATE: {TIMESTAMP(26, 6), refers to POSSESSION_MONEY.UPDATE_DATE}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnUpdateDate() { return _columnUpdateDate; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnUserId());
        ls.add(columnUserName());
        ls.add(columnPassword());
        ls.add(columnLoginDate());
        ls.add(columnPossessionMoney());
        ls.add(columnUpdateDate());
        return ls;
    }

    { initializeInformationResource(); }

    // ===================================================================================
    //                                                                         Unique Info
    //                                                                         ===========
    // -----------------------------------------------------
    //                                       Primary Element
    //                                       ---------------
    protected UniqueInfo cpui() {
        throw new UnsupportedOperationException("The table does not have primary key: " + getTableDbName());
    }
    public boolean hasPrimaryKey() { return false; }
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

    // ===================================================================================
    //                                                                           Type Name
    //                                                                           =========
    public String getEntityTypeName() { return "com.example.demo.dbflute.exentity.customize.Select"; }
    public String getConditionBeanTypeName() { return null; }
    public String getBehaviorTypeName() { return null; }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    public Class<Select> getEntityType() { return Select.class; }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    public Select newEntity() { return new Select(); }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    public void acceptPrimaryKeyMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptPrimaryKeyMap((Select)et, mp); }
    public void acceptAllColumnMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptAllColumnMap((Select)et, mp); }
    public Map<String, Object> extractPrimaryKeyMap(Entity et) { return doExtractPrimaryKeyMap(et); }
    public Map<String, Object> extractAllColumnMap(Entity et) { return doExtractAllColumnMap(et); }
}
