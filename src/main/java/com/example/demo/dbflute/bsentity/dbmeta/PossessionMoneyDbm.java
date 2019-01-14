package com.example.demo.dbflute.bsentity.dbmeta;

import java.util.List;
import java.util.Map;

import org.dbflute.Entity;
import org.dbflute.optional.OptionalEntity;
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
        setupEpg(_epgMap, et -> ((PossessionMoney)et).getUserId(), (et, vl) -> ((PossessionMoney)et).setUserId((String)vl), "userId");
        setupEpg(_epgMap, et -> ((PossessionMoney)et).getPossessionMoney(), (et, vl) -> ((PossessionMoney)et).setPossessionMoney(ctb(vl)), "possessionMoney");
    }
    public PropertyGateway findPropertyGateway(String prop)
    { return doFindEpg(_epgMap, prop); }

    // -----------------------------------------------------
    //                                      Foreign Property
    //                                      ----------------
    protected final Map<String, PropertyGateway> _efpgMap = newHashMap();
    { xsetupEfpg(); }
    @SuppressWarnings("unchecked")
    protected void xsetupEfpg() {
        setupEfpg(_efpgMap, et -> ((PossessionMoney)et).getPokerUserInfo(), (et, vl) -> ((PossessionMoney)et).setPokerUserInfo((OptionalEntity<PokerUserInfo>)vl), "pokerUserInfo");
    }
    public PropertyGateway findForeignPropertyGateway(String prop)
    { return doFindEfpg(_efpgMap, prop); }

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
    protected final ColumnInfo _columnUserId = cci("USER_ID", "USER_ID", null, null, String.class, "userId", null, true, false, true, "VARCHAR", 255, 0, null, null, false, null, null, "pokerUserInfo", null, null, false);
    protected final ColumnInfo _columnPossessionMoney = cci("POSSESSION_MONEY", "POSSESSION_MONEY", null, null, java.math.BigDecimal.class, "possessionMoney", null, false, false, true, "DECIMAL", 65535, 32767, null, null, false, null, null, null, null, null, false);

    /**
     * USER_ID: {PK, NotNull, VARCHAR(255), FK to POKER_USER_INFO}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnUserId() { return _columnUserId; }
    /**
     * POSSESSION_MONEY: {NotNull, DECIMAL(65535, 32767)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnPossessionMoney() { return _columnPossessionMoney; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnUserId());
        ls.add(columnPossessionMoney());
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
    /**
     * POKER_USER_INFO by my USER_ID, named 'pokerUserInfo'.
     * @return The information object of foreign property. (NotNull)
     */
    public ForeignInfo foreignPokerUserInfo() {
        Map<ColumnInfo, ColumnInfo> mp = newLinkedHashMap(columnUserId(), PokerUserInfoDbm.getInstance().columnUserId());
        return cfi("FK_USER_ID", "pokerUserInfo", this, PokerUserInfoDbm.getInstance(), mp, 0, org.dbflute.optional.OptionalEntity.class, true, false, false, false, null, null, false, "possessionMoneyAsOne", false);
    }

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
