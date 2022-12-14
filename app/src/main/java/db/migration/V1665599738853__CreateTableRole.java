package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

/**
* @see https://www.jooq.org/doc/3.1/manual/sql-building/ddl-statements/
*/
public class V1665599738853__CreateTableRole extends BaseJavaMigration {
  public void migrate(Context context) throws Exception {
    var create = using(context.getConnection());
    create.transaction(configuration -> {
      using(configuration)
        .createTableIfNotExists("role")
          .column("id", BIGINT.identity(true))
          .column("name", VARCHAR(100).nullable(false))
        .constraints(
          constraint("user_pk").primaryKey("id"))
        .execute();
    });
  }
}