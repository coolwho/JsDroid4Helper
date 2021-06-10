//随机数测试
def testRand() {
    print rand.randMax(10)
    print rand.randHexString(10)
    print rand.randHexString(10, true)
    print rand.randAZ(10)
    print rand.randAZ(10, true)
    print rand.randBetween(1, 2)
    print rand.randBool()
    print rand.randUUID()
}
//节点测试
def testNode() {
    node.search().text("JsDroid").findOne()
    node.findAllInRect(100, 100, 200, 200)
}
//mysql测试
def testMySql() {
    def url = "jdbc:mysql://jsdroid.com:3306/jsd_mysql"
    def username = "jsd_mysql"
    def password = "8ieze8TZfhx86jcF"
    def conn = mysql.connect(url, username, password)
    tryDo {
        //删除表
        conn.call("drop table user")
    }
    tryDo {
        //建表，如果已经存在user表，就会抛出异常
        conn.call("create table user(id int primary key auto_increment,name varchar(20) unique,age int)")
    }
    tryDo {
        //插入多条数据
        conn.withTransaction {
            conn.executeInsert("insert into user (name,age) values(?,?)", ["张三", 18])
            conn.executeInsert("insert into user (name,age) values(?,?)", ["李四", 19])
            conn.executeInsert("insert into user (name,age) values(?,?)", ["王五", 20])
            conn.executeInsert("insert into user (name,age) values(?,?)", ["隐藏的老王", 10])
        }
    }
    tryDo {
        //删除数据
        conn.call("delete from user where name=?","隐藏的老王")
    }
    tryDo {
        //查询数据
        conn.eachRow("select * from user", {
            def name = it.getString("name")
            def age = it.getInt("age")
            print("name:$name,age:$age")
        })
    }
    conn.close()
}

testMySql()
