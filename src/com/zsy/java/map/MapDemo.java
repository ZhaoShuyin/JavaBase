package com.zsy.java.map;

import java.util.*;

/**
 * Title com.zsy.java.map
 * <p>
 * .   Map{
 * .        size(),isEmpty(),containsKey(Object key),containsValue(Object value),
 * .        get(Object key),put(Object key, Object value),remove(Object key),
 * .        putAll(Map m), clear(),keySet(),values(),entrySet()
 * .
 * .     HashMap{          //唯一
 * .       LinkedHashMap{  //唯一有序
 * .
 * .            }
 * .      }
 * .      TreeMap{          //排序
 * .
 * .      }
 * .  }
 * .
 *
 * @author Zsy
 * @date 2019/8/10 17:20
 */
public class MapDemo {
    public static void main(String[] args) {
        traverse();
        hashmapTest();
    }

    /**
     * 遍历方法
     */
    private static void traverse() {
        Map<String, String> map = new HashMap<>();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            entry.getKey();
            entry.getValue();
        }
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    private static void hashmapTest() {

    }
}
