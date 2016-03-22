package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DbGenerator {

    public static void main(String arg[]) {
        Schema schema = new Schema(2, "com.suli.myutils.dao");
        schema.enableKeepSectionsByDefault();
        schema.enableActiveEntitiesByDefault();

        addUser(schema);

        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addUser(Schema schema) {
        Entity user = schema.addEntity("user");
        user.setTableName("user");
        user.addIdProperty();
        user.addStringProperty("name");
        user.addStringProperty("nickname");
        user.addStringProperty("phone");
        user.addDateProperty("created_at");
        user.addDateProperty("updated_at");
    }

}
