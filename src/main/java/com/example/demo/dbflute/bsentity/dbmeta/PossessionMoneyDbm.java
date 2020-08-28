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
 * The DB meta of POSSESSION_MONEY. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class PossessionMoneyDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final PossessionMoneyDbm _instance = new PossessionMoneyDbm();
    private PossessionMoneyDbm() {}
    public static PossessionMoneyDbm getInstance() { return _instance; }

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
        setupEpg(_epgMap, et -> ((PossessionMoney)et).getUserId(), (et, vl) -> ((PossessionMoney)et).setUserId(cti(vl)), "userId");
        setupEpg(_epgMap, et -> ((PossessionMoney)et).getPossessionMoney(), (et, vl) -> ((PossessionMoney)et).setPossessionMoney(ctb(vl)), "possessionMoney");
        setupEpg(_epgMap, et -> ((PossessionMoney)et).getUpdateDate(), (et, vl) -> ((PossessionMoney)et).setUpdateDate(ctldt(vl)), "updateDate");
    }
    public PropertyGateway findPropertyGateway(String prop)
    { return doFindEpg(_epgMap, prop); }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "POSSESSION_MONEY";
    protected final String _tableDispName = "POSSESSION_MONEY";
    protected final String _tablePropertyName = "possessionMoney";
    protected final TableSqlName _tableSqlName = new TableSqlName("POSSESSION_MONEY", _tableDbName);
    { _tableSqlName.xacceptFilter(DBFluteConfig.getInstance().getTableSqlNameFilter()); }
    public String getTableDbName() { return _tableDbName; }
    public String getTableDispName() { return _tableDispName; }
    public String getTablePropertyName() { return _tablePropertyName; }
    public TableSqlName getTableSqlName() { return _tableSqlName; }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnUserId = cci("USER_ID", "USER_ID", null, null, Integer.class, "userId", null, true, false, true, "INTEGER", 10, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnPossessionMoney = cci("POSSESSION_MONEY", "POSSESSION_MONEY", null, null, java.math.BigDecimal.class, "possessionMoney", null, false, false, true, "DECIMAL", 65535, 32767, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnUpdateDate = cci("UPDATE_DATE", "UPDATE_DATE", null, null, java.time.LocalDateTime.class, "updateDate", null, false, false, true, "TIMESTAMP", 26, 6, null, "NOW()", false, null, null, null, null, null, false);

    /**
     * USER_ID: {PK, NotNull, INTEGER(10)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnUserId() { return _columnUserId; }
    /**
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnPossessionMoney() { return _columnPossessionMoney; }
    /**
     * UPDATE_DATE: {NotNull, TIMESTAMP(26, 6), default=[NOW()]}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnUpdateDate() { return _columnUpdateDate; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnUserId());
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

    // ===================================================================================
    //                                                                           Type Name
    //                                                                           =========
    public String getEntityTypeName() { return "com.example.demo.dbflute.exentity.PossessionMoney"; }
    public String getConditionBeanTypeName() { return "com.example.demo.dbflute.cbean.PossessionMoneyCB"; }
    public String getBehaviorTypeName() { return "com.example.demo.dbflute.exbhv.PossessionMoneyBhv"; }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    public Class<PossessionMoney> getEntityType() { return PossessionMoney.class; }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    public PossessionMoney newEntity() { return new PossessionMoney(); }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    public void acceptPrimaryKeyMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptPrimaryKeyMap((PossessionMoney)et, mp); }
    public void acceptAllColumnMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptAllColumnMap((PossessionMoney)et, mp); }
    public Map<String, Object> extractPrimaryKeyMap(Entity et) { return doExtractPrimaryKeyMap(et); }
    public Map<String, Object> extractAllColumnMap(Entity et) { return doExtractAllColumnMap(et); }
}
