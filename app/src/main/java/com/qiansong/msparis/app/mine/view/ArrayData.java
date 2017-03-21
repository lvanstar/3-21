package com.qiansong.msparis.app.mine.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @version 0.1 king 2015-11
 */
public class ArrayData {

    private static final Map<String, Map<String, List<String>>> DATAs = new LinkedHashMap<>();

    public static void init(List<String> time1, List<List<String>> time2) {
        if (!DATAs.isEmpty()) {
            return;
        }

        for (int i = 0; i < time1.size(); i++) {
            Map<String, List<String>> city = new HashMap<>();
            for (int j = 0; j < time2.get(i).size(); j++) {
                List<String> data = new ArrayList<>();
                city.put(time2.get(i).get(j), data);
            }
            DATAs.put(time1.get(i), city);
        }
    }

    private static Random random = new Random();

    private static String getRandomText() {
        int num = random.nextInt(20);
        String str = "五";
        for (int i = 0; i < num; i++) {
            str += "五";
        }
        return str;
    }

    public static Map<String, Map<String, List<String>>> getAll() {
//        init();
        return new HashMap<>(DATAs);
    }

}
