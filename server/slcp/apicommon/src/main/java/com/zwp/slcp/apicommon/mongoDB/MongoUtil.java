package com.zwp.slcp.apicommon.mongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/14.
 */
public class MongoUtil {
    static MongoDB mongo = new MongoDB("testmondb");

    /**
     * 遍历实体类转成 Document
     *
     * @param model
     * @throws Exception
     */
    public static Document modelToDc(Object model) throws Exception {
        Document doc = new Document();
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(model) != null) {
                doc.put(field.getName(), field.get(model));
            }
        }
        return doc;
    }

    /**
     * 创建表
     *
     * @param collName
     * @return
     */
    public static int createColl(String collName) {
        if (mongo.createCollection(collName)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @Description : 查询库下的所有文件
     */
    public static List<String> getCollection() {
        MongoIterable<String> collection = mongo.getCollection();
        MongoCursor<String> iterator = collection.iterator();
        List<String> stringList = new ArrayList<String>();
        while (iterator.hasNext()) {
            stringList.add(iterator.next());
        }
        return stringList;
    }

    /**
     *
     * @Description :  删除 指定文件
     */
    public static void deleteFile(String collName) {
        mongo.deleteFile(collName);
    }

    /**
     * 插入单个实体类
     *
     * @param collName
     */
    public static void insertOneModel(String collName, Object model) {
        Document doc = null;
        try {
            doc = MongoUtil.modelToDc(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mongo.insert(doc, collName);
    }

    /**
     * 插入单个Document
     *
     * @param collName
     */
    public static void insertOne(String collName, Document doc) {
        mongo.insert(doc, collName);
    }

    /**
     * 批量插入
     *
     * @param collName
     * @param dcList
     */
    public static void insertMore(String collName, List<Document> dcList) {
        mongo.insert(dcList, collName);
    }

    /**
     * 按查询条件批量更新  Document
     *
     * @param collName
     * @param b
     * @param doc
     */
    public static long updateAllDc(String collName, Bson b, Document doc) {
        Document doc1 = new Document("$set", doc);
        return mongo.updateAll(b, doc1, collName);
    }

    /**
     * 按查询条件批量更新  实体类
     *
     * @param collName
     * @param b
     * @param model
     */
    public static long updateAllModel(String collName, Bson b, Object model) {
        Document doc = null;
        try {
            doc = MongoUtil.modelToDc(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document doc1 = new Document("$set", doc);
        return mongo.updateAll(b, doc1, collName);
    }
    /**
     * 按查询条件单个更新  实体类
     *
     * @param collName
     * @param b
     * @param model
     */
//    public static void updateOneModel(String collName, Bson b, Object model) {
//        Document doc = null;
//        try {
//            doc = MongoUtil.modelToDc(model);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        mongo.updateOne(b, doc, collName);
//    }


    /**
     * 查询所有
     *
     * @param collName
     * @return
     */
    public static List<Document> findAll(String collName) {
        return mongo.findAll(collName);
    }

    public static List<Document> findAllKey(String collName, BasicDBObject key) {
        return mongo.findAll(collName, key);
    }

    /**
     * 指定条件查找全部
     *
     * @param collName
     * @param query
     * @return
     */
    public static List<Document> findByQuery(String collName, BasicDBObject query) {
        return mongo.findAll(query, collName);
    }

    /**
     * 指定条件查找全部,返回指定字段
     *
     * @param collName
     * @param query
     * @param key
     * @return
     */
    public static List<Document> findByQueryKey(String collName, BasicDBObject query, BasicDBObject key) {
        return mongo.findAll(query, key, collName);
    }

    public static List<Document> findByQueryKeyLimitSort(String collName, BasicDBObject query, BasicDBObject key, Integer limit) {
        return mongo.findAllLimitSort(query, key, collName, limit);
    }

    public static List<Document> findByQueryKeyLimitSortSkip(String collName, BasicDBObject query, BasicDBObject key, Integer limit, String sort, Integer skip) {
        return mongo.findAllLimitPage(query, key, collName, limit, sort, skip);
    }

    /**
     * 指定条件查找一个
     *
     * @param collName
     * @param queryOne
     * @return
     */
    public static Document findOne(String collName, BasicDBObject queryOne) {
        return mongo.findOne(queryOne, collName);
    }

    /**
     * 指定条件查找一个  根据指定字段
     *
     * @param collName
     * @param queryOne
     * @return
     */
    public static Document findOneKey(String collName, BasicDBObject queryOne, BasicDBObject key) {
        return mongo.findOneKey(queryOne, collName, key);
    }

    /**
     * 删除集合中的所有数据
     *
     * @param collName
     */
    public static long deleteAll(String collName) {
        return mongo.deleteAll(collName);
    }

    /**
     * 删除指定的所有数据
     *
     * @param b
     * @param collName
     */
    public static long deleteAll(String collName, Bson b) {
        return mongo.deleteAll(b, collName);
    }

    /**
     * 删除指定的一个数据
     *
     * @param b
     * @param collName
     */
    public static long deleteOne(String collName, Bson b) {
        return mongo.deleteOne(b, collName);
    }
}
