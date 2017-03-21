package com.qiansong.msparis.app.find.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.find.util.LaLocationUtils;
import com.qiansong.msparis.app.homepage.util.Eutil;

/**
 * Poi检索 用于搜索附近
 */
public class PoiActivity extends BaseActivity implements AMapLocationListener {
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private PoiResult poiResult; // poi返回的结果

    //定位
    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationOption;

    public  double lat ;
    public  double lng ;

    ListView lv_now;

    boolean isstart=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi);
        lv_now= (ListView) findViewById(R.id.lv_now);
        getLocation();
        lv_now.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("back", poiResult.getPois().get(position).getTitle());
                intent.putExtra("lat",poiResult.getPois().get(position).getLatLonPoint()
                        !=null?poiResult.getPois().get(position).getLatLonPoint().getLatitude():0);
                intent.putExtra("lng",poiResult.getPois().get(position).getLatLonPoint()
                        !=null?poiResult.getPois().get(position).getLatLonPoint().getLongitude():0);
                setResult(3, intent);
                finish();
            }
        });
    }

    private void getNearLocation(double lat,double lng){
        LatLonPoint lp = new LatLonPoint(lat, lng);
        query = new PoiSearch.Query("", "", "");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        if (lp != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                @Override
                public void onPoiSearched(PoiResult result, int rcode) {
                    if (rcode == 1000) {
                        if (result != null && result.getQuery() != null) {// 搜索poi的结果
                            if (result.getQuery().equals(query)) {// 是否是同一条
                                poiResult = result;
                                lv_now.setAdapter(new BaseAdapter() {
                                    @Override
                                    public int getCount() {
                                        return poiResult.getPois().size();
                                    }
                                    @Override
                                    public Object getItem(int position) {
                                        return poiResult.getPois().get(position);
                                    }
                                    @Override
                                    public long getItemId(int position) {
                                        return position;
                                    }

                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        convertView=View.inflate(PoiActivity.this,R.layout.item_poi,null);
                                        TextView tv_address= (TextView) convertView.findViewById(R.id.tv_address);
                                        tv_address.setText(Eutil.getHighlight("#2e2e2e",poiResult.getPois().get(position).getTitle()+"\n"+poiResult.getPois().get(position).getSnippet(),poiResult.getPois().get(position).getTitle()));
                                        return convertView;
                                    }
                                });

                            }
                        }
                    }
                }
                @Override
                public void onPoiItemSearched(PoiItem poiItem, int i) {

                }
            });
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 5000, true));//
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }

    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = mHandler.obtainMessage();
            msg.obj = loc;
            msg.what = LaLocationUtils.MSG_LOCATION_FINISH;
            mHandler.sendMessage(msg);
        }
    }
    //定位获取经纬度
    private void getLocation(){
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        locationClient.setLocationListener(this);

        // 设置定位参数
        locationOption.setInterval(90000);         //设置定位时间的间隔
        locationClient.setLocationOption(locationOption);

        // 启动定位
        locationClient.startLocation();
        mHandler.sendEmptyMessage(LaLocationUtils.MSG_LOCATION_START);
    }
    Handler mHandler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            switch (msg.what) {
                //开始定位
                case LaLocationUtils.MSG_LOCATION_START:
                    break;
                // 定位完成
                case LaLocationUtils.MSG_LOCATION_FINISH:
                    AMapLocation loc = (AMapLocation) msg.obj;
                    lat=loc.getLatitude();
                    lng=loc.getLongitude();
                    if (isstart) {
                        getNearLocation(loc.getLatitude(), loc.getLongitude());
                        isstart=false;
                    }
                    break;
                //停止定位
                case LaLocationUtils.MSG_LOCATION_STOP:
                    break;
                default:
                    break;
            }
        };
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }
}
