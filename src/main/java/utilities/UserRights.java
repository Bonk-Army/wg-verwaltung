package utilities;

/**
 * Enum that holds all user rights that we have to identify e.g. which user has admin rights in a wg
 */
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
