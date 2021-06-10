package com.jsdroid.helper

import groovy.sql.Sql

/**
 * mysql功能库
 * jar:groovy-sql-2.5.13.jar; mysql-connector-java-5.1.49.jar
 */
class MySQL {
    def connect(String url,String username,String password){
        Sql.newInstance(url,username,password,"com.mysql.jdbc.Driver")
    }
}
