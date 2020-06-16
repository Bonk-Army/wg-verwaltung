package utilities;

public enum UserRights {
    WG_ADMIN("wg_admin");

    private String sqlKey;

    private UserRights(String sqlKey){
        this.sqlKey = sqlKey;
    }

    public String getSqlKey() {
        return sqlKey;
    }
}
