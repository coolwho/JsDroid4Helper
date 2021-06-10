package com.jsdroid.helper

import groovy.sql.Sql
import org.sqlite.JDBC

/**
 * jar:sqlite-jdbc-3.34.0.jar
 */
class SQLite {
    def connect(String file) {
        Sql.newInstance("jdbc:sqlite:" + file, JDBC.class.getName())
    }
}
