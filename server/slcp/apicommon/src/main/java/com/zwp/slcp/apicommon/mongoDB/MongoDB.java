package com.zwp.slcp.apicommon.mongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/14.
 */
public class MongoDB {
    /**
            * 设置数据库连接地址
     */
    private final String CONN_HOST = "127.0.0.1";

    /**
     * 设置数据库连接端口号
     */
    private final int CONN_PORT = 27017;

    /**
     * MongoDB连接实例
     */
    public MongoClient mongoClient = null;

    /**
     * MongoDB数据库实例
     */
    public MongoDatabase mongoDatabase = null;

    /**
     * 构造方法
     * 获取数据库实例
     *
     * @param DB_Name
     */
    public MongoDB(String DB_Name) {

        MongoClientOptions.Builder build = new MongoClientOptions.Builder();
        build.connectionsPerHost(50);   //与目标数据库能够建立的最大connection数量为50

        build.threadsAllowedToBlockForConnectionMultiplier(50); //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
            /*
             * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
             * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
             * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
             */
        build.maxWaitTime(1000 * 60 * 2);
        build.connectTimeout(1000 * 60);    //与数据库建立连接的timeout设置为1分钟

        MongoClientOptions myOptions = build.build();

        ServerAddress address = new ServerAddress(CONN_HOST, CONN_PORT);

        this.mongoClient = new MongoClient(address, myOptions);
        this.mongoDatabase = this.mongoClient.getDatabase(DB_Name);
    }

    /**
     * 创建数据库集合
     *
     * @param collName 数据库表名
     */
    public boolean createCollection(String collName) {
        try {
            this.mongoDatabase.createCollection(collName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @Description : 删除 指定文件
     */
    public void deleteFile(String collName) {
        MongoCollection<Document> mc = mongoDatabase.getCollection(collName);
        mc.drop();
    }

    /**
     * 获取数据库集合
     *
     * @param
     * @return
     */
    public MongoIterable<String> getCollection() {
        return this.mongoDatabase.listCollectionNames();
    }

    /**
     * 插入单个文档
     *
     * @param doc      Bson文档
     * @param collName 集合名称
     */
    public void insert(Document doc, String collName) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        coll.insertOne(doc);
    }

    /**
     * 批量插入文档
     *
     * @param list     List类型文档
     * @param collName 集合名称
     */
    public void insert(List<Document> list, String collName) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        coll.insertMany(list);
    }

    /**
     * 查找集合内所有Document
     *
     * @param collName
     * @return
     */
    public List<Document> findAll(String collName) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        List<Document> result = new ArrayList<Document>();
        FindIterable<Document> findIterable = coll.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            result.add(mongoCursor.next());
        }
        return result;
    }


    /**
     * 指定条件查找
     *
     * @param query
     * @param collName
     * @return
     */
    public List<Document> findAll(BasicDBObject query, String collName) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        List<Document> result = new ArrayList<Document>();
        FindIterable<Document> findIterable = coll.find(query);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            result.add(mongoCursor.next());
        }
        return result;
    }


    /**
     * 指定条件查找指定字段
     *
     * @param query
     * @param collName
     * @return
     */
    public List<Document> findAll(BasicDBObject query, BasicDBObject key, String collName) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        List<Document> result = new ArrayList<Document>();
        FindIterable<Document> findIterable = coll.find(query).projection(key);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            result.add(mongoCursor.next());
        }
        return result;
    }

    //根据_id倒序排列
    public List<Document> findAllLimitSort(BasicDBObject query, BasicDBObject key, String collName, Integer limit) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        List<Document> result = new ArrayList<Document>();
        FindIterable<Document> findIterable = coll.find(query).projection(key).limit(limit).sort(new BasicDBObject("_id",-1));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            result.add(mongoCursor.next());
        }
        return result;
    }

    public List<Document> findAll(String collName, BasicDBObject key) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        List<Document> result = new ArrayList<Document>();
        FindIterable<Document> findIterable = coll.find().projection(key);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            result.add(mongoCursor.next());
        }
        return result;
    }


    /**
     * 查找一个
     *
     * @param query
     * @param collName
     * @return
     */
    public Document findOne(BasicDBObject query, String collName) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        Document result;
        try {
            FindIterable<Document> findIterable = coll.find(query).limit(1);
            result = findIterable.iterator().next();
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    /**
     * 查找一个  根据指定字段
     *
     * @param query
     * @param collName
     * @return
     */
    public Document findOneKey(BasicDBObject query, String collName, BasicDBObject key) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        Document result = new Document();
        try {
            FindIterable<Document> findIterable = coll.find(query).limit(1).projection(key);
            result = findIterable.iterator().next();
        } catch (Exception e) {
            return null;
        }
        return result;
    }


    /**
     * 删除集合中的所有数据
     *
     * @param collName
     */
    public long deleteAll(String collName) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        BasicDBObject delDbo = new BasicDBObject();
        delDbo.append("_id", -1);
        return coll.deleteMany(Filters.not(delDbo)).getDeletedCount();
    }



    /**
     * 删除指定的所有数据
     *
     * @param b
     * @param collName
     */
    public long deleteAll(Bson b, String collName) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        return coll.deleteMany(b).getDeletedCount();
    }


    /**
     * 删除指定的一条数据
     *
     * @param b
     * @param collName
     */
    public long deleteOne(Bson b, String collName) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        return coll.deleteOne(b).getDeletedCount();
    }

    /**
     * 按查询条件批量修改
     *
     * @param b
     * @param doc
     * @param collName
     */
    public long updateAll(Bson b, Document doc, String collName) {
        MongoCollection<Document> coll = this.mongoDatabase.getCollection(collName);
        return coll.updateMany(b, doc).getModifiedCount();
    }
}
