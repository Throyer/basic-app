package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

/**
* @see https://www.jooq.org/doc/3.1/manual/sql-building/ddl-statements/
*/
public class V1665599603347__CreateTableUser extends BaseJavaMigration {
  public void migrate(Context context) throws Exception {
    var create = using(context.getConnection());
    create.transaction(configuration -> {
      using(configuration)
        .createTableIfNotExists("user")
          .column("id", BIGINT.identity(true))
          .column("name", VARCHAR(100).nullable(false))
          .column("email", VARCHAR(100).nullable(true))
          .column("password", VARCHAR(100).nullable(false))
          .column("active", BOOLEAN.defaultValue(true))
          .column("created_at", TIMESTAMP.defaultValue(currentTimestamp()))
          .column("updated_at", TIMESTAMP.nullable(true))
        .constraints(
          constraint("user_pk").primaryKey("id"),                            
          constraint("user_unique_email").unique("email"))
        .execute();
    });
  }
}