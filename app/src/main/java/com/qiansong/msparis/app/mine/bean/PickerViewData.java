package com.qiansong.msparis.app.mine.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by kevin on 2017/2/12.
 */

public class PickerViewData implements IPickerViewData {

    private String content;

    public PickerViewData(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPickerViewText() {
        return content;
    }
}
