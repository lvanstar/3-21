package com.qiansong.msparis.app.mine.view;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.jeesoft.widget.pickerview.CharacterPickerView;
import cn.jeesoft.widget.pickerview.CharacterPickerWindow;
import cn.jeesoft.widget.pickerview.OnOptionChangedListener;

/**
 * 预约时间选择器
 *
 * @version 0.1 king 2015-10
 */
public class OptionsWindowHelper {

    private static List<String> options1Items = null;
    private static List<List<String>> options2Items = null;

    public interface OnOptionsSelectListener {
        void onOptionsSelect(String day, String time, String area);
    }

    private OptionsWindowHelper() {
    }

    public static CharacterPickerWindow builder(Activity activity, final OnOptionsSelectListener listener) {
        //选项选择器
        CharacterPickerWindow mOptions = new CharacterPickerWindow(activity);
        //初始化选项数据
        setPickerData(mOptions.getPickerView());
        //设置默认选中的三级项目
        mOptions.setSelectOptions(0, 0, 0);
        //监听确定选择按钮
        mOptions.setOnoptionsSelectListener(new OnOptionChangedListener() {
            @Override
            public void onOptionChanged(int option1, int option2, int option3) {
                if (option1==-1){
                    //如果3个都是-1 那点击的就是取消按钮
                    listener.onOptionsSelect("","","");
                    return;
                }
                if (listener != null) {
                    String day = options1Items.get(option1);
                    String time = options2Items.get(option1).get(option2);
                    listener.onOptionsSelect(day, time,"");
                }
            }
        });
        return mOptions;
    }

    /**
     * 初始化选项数据
     */
    public static void setPickerData(CharacterPickerView view) {
        if (options1Items == null) {
            options1Items = new ArrayList<>();
            options2Items = new ArrayList<>();

            final Map<String, Map<String, List<String>>> allCitys = ArrayData.getAll();
            for (Map.Entry<String, Map<String, List<String>>> entry1 : allCitys.entrySet()) {
                String key1 = entry1.getKey();
                Map<String, List<String>> value1 = entry1.getValue();

                options1Items.add(key1);

                List<String> options2Items01 = new ArrayList<>();
                List<List<String>> options3Items01 = new ArrayList<>();
                for (Map.Entry<String, List<String>> entry2 : value1.entrySet()) {
                    String key2 = entry2.getKey();
                    List<String> value2 = entry2.getValue();

                    options2Items01.add(key2);
                    Collections.sort(value2);
                    options3Items01.add(new ArrayList<>(value2));
                }
                Collections.sort(options2Items01);
                options2Items.add(options2Items01);
            }
            Collections.sort(options1Items);
        }

        //三级联动效果
        view.setPicker(options1Items, options2Items);

    }

}
