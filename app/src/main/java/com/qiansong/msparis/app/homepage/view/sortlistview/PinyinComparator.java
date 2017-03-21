package com.qiansong.msparis.app.homepage.view.sortlistview;

import com.qiansong.msparis.app.commom.bean.ConfigsBean;

import java.util.Comparator;


/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<ConfigsBean.DataEntity.BrandEntity.BrandsEntity> {

	public int compare(ConfigsBean.DataEntity.BrandEntity.BrandsEntity o1, ConfigsBean.DataEntity.BrandEntity.BrandsEntity o2) {
		if (o1.sort_az.equals("@")
				|| o2.sort_az.equals("#")) {
			return -1;
		} else if (o1.sort_az.equals("#")
				|| o2.sort_az.equals("@")) {
			return 1;
		} else {
			return o1.sort_az.compareTo(o2.sort_az);
		}
	}

}
