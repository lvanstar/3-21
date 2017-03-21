package com.qiansong.msparis.app.homepage.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.AndroidUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.homepage.adapter.SearchProductAdapter;
import com.qiansong.msparis.app.homepage.bean.SearchResultBean;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.homepage.adapter.SearchAdapter;
import com.qiansong.msparis.app.homepage.adapter.SearchHistoryAdapter;
import com.qiansong.msparis.app.homepage.bean.SearchBean;
import com.qiansong.msparis.app.homepage.bean.SearchHistoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * kevin.cao
 * <p>
 * 搜索页面
 */
public class SearchActivity extends BaseActivity {

    /**
     * 输入框
     */
    @InjectView(R.id.search_message)
    EditText searchMessage;
    /**
     * 清理
     */
    @InjectView(R.id.search_detale)
    RelativeLayout searchDetale;
    /**
     * 退出
     */
    @InjectView(R.id.search_close)
    RelativeLayout searchClose;
    /**
     * 历史记录提示
     */
    @InjectView(R.id.search_history)
    TextView searchHistory;
    /**
     * listView
     */
    @InjectView(R.id.search_list)
    ListView searchList;
    /**
     * 清空历史记录
     */
    @InjectView(R.id.search_submit)
    TextView searchSubmit;

    /**
     * 搜索layout
     */
    @InjectView(R.id.search_search_layout)
    LinearLayout searchSearchLayout;
    /**
     * 日常服
     */
    @InjectView(R.id.search_title1)
    TextView searchTitle1;
    /**
     * 礼服
     */
    @InjectView(R.id.search_title2)
    TextView searchTitle2;
    /**
     * 商品展示listView
     */
    @InjectView(R.id.search_result_listview)
    XRecyclerView searchResultListview;
    /**
     * 商品展示布局
     */
    @InjectView(R.id.search_result_layout)
    LinearLayout searchResultLayout;

    public SearchHistoryAdapter searchHistoryAdapter;
    public SearchAdapter searchAdapter;
    public SearchProductAdapter searchProductAdapter;

    //1:日常服，2:礼服
    public int isType= 1;

    //用户tonken
    private String token="";
    private SearchResultBean bean = null ;
    private int page= 1;
    private int  page_size= 10;
    private String  keyWord = "";
    private SearchBean searchBean;

//    private boolean ischeck = false;

    List<SearchResultBean.DataBean.RowsBean> list =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        searchList.setDividerHeight(0);
        searchHistoryAdapter = new SearchHistoryAdapter(this);
        searchAdapter = new SearchAdapter(this);
        searchProductAdapter = new SearchProductAdapter(this);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchResultListview.setLayoutManager(linearLayoutManager);
        searchResultListview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        searchResultListview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        searchResultListview.setArrowImageView(R.mipmap.ic_launcher);
        searchResultListview.setAdapter(searchProductAdapter);

        //软键盘确定键
        searchMessage.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == event.KEYCODE_ENTER) {
                    inputDate(searchMessage.getText().toString().trim(), true);
                    keyWord = searchMessage.getText().toString().trim();
                    //确定就是搜索
                    if(keyWord.length()>0){
                        requestData(keyWord);
                        AndroidUtil.hideSoftInput(SearchActivity.this);//  关闭软键盘
//                        searchMessage.setText(str);
//                        searchMessage.setSelection(str.length());//设置光标
//                        searchMessage.setFocusable(true);
//                        searchMessage.setFocusableInTouchMode(true);
//                        searchMessage.clearFocus(); //丢失焦点

                    }
                }
                return false;
            }
        });
//        //添加改变监听
//        searchMessage.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//            //改变中 显示搜索结果
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                //根据输入的内容请求数据
//                if(s.length()>0 ){
//                    requestData(s.toString());
//                }else{
//                    showHistoryData();
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        //获取光标位置
//        searchMessage.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                // 此处为得到焦点时的处理内容
////                    searchSearchLayout.setVisibility(View.VISIBLE);
////                    searchResultLayout.setVisibility(View.GONE);
//                    if(searchMessage.getText().toString().trim().length() > 0){
//                        requestData();
//                    }else{
//                        showHistoryData();
//                    }
//                } else {
//                // 此处为失去焦点时的处理内容
//                }
//            }
//        });


        //设置下拉刷新
        searchResultListview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                getProduct();

            }
            @Override
            public void onLoadMore() {
                page += 1;
                getProduct();
            }
        });


    }

    /**
     * 网络请求 字体联想
     */
    public void requestData(String str) {
        searchMessage.clearFocus(); //丢失焦点
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        if (token != null && token.length() > 0) {
            HttpServiceClient.getInstance().key_autocomplete(token,str)
                    .enqueue(new Callback<SearchBean>() {
                        @Override
                        public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                            if(response.isSuccessful()){
                                searchBean = response.body();
                                if ("ok".equals(searchBean.getStatus())) {
                                    showSearchData(searchBean.getData().getRow());
                                }else {
                                    ToastUtil.showMessage(searchBean.getError().getMessage());
                                }
                            }else{
                                ToastUtil.showMessage("网络连接异常");
                            }
                        }

                        @Override
                        public void onFailure(Call<SearchBean> call, Throwable t) {

                        }
                    });
        }


    }

    /**
     *
     * 请求搜索后 根据关键字 获取商品信息
     *
     */
    public void searchProductData(String str) {
        searchSearchLayout.setVisibility(View.GONE);
        searchResultLayout.setVisibility(View.VISIBLE);
        AndroidUtil.hideSoftInput(this);//  关闭软键盘
        searchMessage.setText(str);
        searchMessage.setSelection(str.length());//设置光标
        searchMessage.clearFocus(); //丢失焦点


        keyWord = str;
        getProduct();

    }

    /**
     *
     * 请求网络数据
     *
     */
    public  void  getProduct(){
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        if (token != null && token.length() > 0) {
            HttpServiceClient.getInstance().product_keyword(token,keyWord,isType,page,page_size)
                    .enqueue(new Callback<SearchResultBean>() {
                        @Override
                        public void onResponse(Call<SearchResultBean> call, Response<SearchResultBean> response) {
                            if(response.isSuccessful()){
                                bean = response.body();
                                if ("ok".equals(bean.getStatus())) {
                                    showSearchResultData(bean);
                                }else {
                                    ToastUtil.showMessage(bean.getError().getMessage());
                                }
                            }else{

                            }
                        }

                        @Override
                        public void onFailure(Call<SearchResultBean> call, Throwable t) {

                        }
                    });
        }
    }


    /**
     * 填充数据
     */
    public void initData() {
        //展示历史记录
        showHistoryData();
    }

    /**
     * 展示点击历史记录或者搜索提示 出现日常装或者礼服数据
     */
    public void showSearchResultData(SearchResultBean data) {
        if (page == 1) {
            list = data.getData().getRows();
            searchProductAdapter.setData(list);
            searchResultListview.setLoadingMoreEnabled(true);
        } else {
            list.addAll(data.getData().getRows());
            searchProductAdapter.setData(list);
            if (list.size() == Integer.parseInt(data.getData().getTotal())) {
                searchResultListview.setLoadingMoreEnabled(false);
            }

        }
    }

    /**
     *  展示搜索数据
     */
    public void showSearchData(final List<String> list) {
        searchSearchLayout.setVisibility(View.VISIBLE);
        searchResultLayout.setVisibility(View.GONE);
        searchHistory.setVisibility(View.GONE);
        searchSubmit.setVisibility(View.GONE);
        searchList.setAdapter(searchAdapter);
        searchAdapter.setData(list);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchProductData(list.get(position));
            }
        });
    }

    /**
     * 展示历史数据
     */
    public void showHistoryData() {
        searchMessage.clearFocus(); //丢失焦点
        searchSearchLayout.setVisibility(View.VISIBLE);
        searchResultLayout.setVisibility(View.GONE);
        searchHistoryAdapter.setCallback(new SearchHistoryAdapter.Callback() {
            @Override
            public void returnData(String str) {
                inputDate(str, false);
            }
        });
        searchList.setAdapter(searchHistoryAdapter);
        final  List<SearchHistoryBean> list = getDate();
        if (list != null && list.size() > 0) {
            searchHistory.setVisibility(View.VISIBLE);
            searchSubmit.setVisibility(View.VISIBLE);
        } else {
            searchHistory.setVisibility(View.GONE);
            searchSubmit.setVisibility(View.GONE);
        }
        searchHistoryAdapter.setData(list);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchProductData(list.get(position).data);
            }
        });
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.search_detale, R.id.search_close, R.id.search_submit,R.id.search_title1, R.id.search_title2})
    public void onClick(View view) {
        switch (view.getId()) {
            //清理
            case R.id.search_detale:
                searchMessage.setText("");
                showHistoryData();
                //显示软键盘
                AndroidUtil.showSoftInput(this,searchMessage);
                break;
            //退出
            case R.id.search_close:
                super.onBackPressed();
                AndroidUtil.hideSoftInput(SearchActivity.this);
                break;
            //清空历史记录
            case R.id.search_submit:
                TXShareFileUtil.getInstance().removeData(GlobalConsts.SEARCH_HISTORY, this.getBaseContext());
                showHistoryData();
                break;
            //删除某一个数据
            case R.id.search_history_delete:
                int id = (Integer) view.getTag();
                List<SearchHistoryBean> searchList = (List<SearchHistoryBean>) TXShareFileUtil.readObject(this, GlobalConsts.SEARCH_HISTORY);
                inputDate(searchList.get(id).data, false);
                break;
            //点击日常服
            case R.id.search_title1:
                searchTitle1.setTextColor(getResources().getColor(R.color.__picker_black_40));
                searchTitle2.setTextColor(getResources().getColor(R.color.gray));
                isType = 1 ;
                searchProductData(searchMessage.getText().toString().trim());
                break;
            //点击礼服
            case R.id.search_title2:
                searchTitle2.setTextColor(getResources().getColor(R.color.__picker_black_40));
                searchTitle1.setTextColor(getResources().getColor(R.color.gray));
                isType = 2 ;
                searchProductData(searchMessage.getText().toString().trim());
                break;
        }
    }

    /**
     * 添加、删除 数据到内存里面
     *
     * @param str   数据
     * @param isAdd 是否为添加还是删除
     */
    public void inputDate(String str, boolean isAdd) {
        if (str.trim().length() > 0) {
            SearchHistoryBean bean = new SearchHistoryBean();
            bean.data = str;
            List<SearchHistoryBean> searchList = null;
            try {
                searchList = (List<SearchHistoryBean>) TXShareFileUtil.readObject(this, GlobalConsts.SEARCH_HISTORY);
            } catch (Exception e) {
            }
            //如果没有数据就创建数据
            if (searchList == null) {
                searchList = new ArrayList<SearchHistoryBean>();
                searchList.add(bean);
            } else {
                //有数据就先判断是否有重复的数据然后再添加数据
                for (int i = 0; i < searchList.size(); i++) {
                    if (searchList.get(i).data.equals(str)) {
                        searchList.remove(i);
                    }
                }
                //判断是添加还是删除
                if (isAdd) {
                    searchList.add(0, bean);
                }
            }
            //保存到内存中
            TXShareFileUtil.saveObject(this, GlobalConsts.SEARCH_HISTORY, searchList);
            showHistoryData();
        }
    }

    /**
     * 从内存获取数据
     */
    public List<SearchHistoryBean> getDate() {
        List<SearchHistoryBean> searchList = null;
        try {
            searchList = (List<SearchHistoryBean>) TXShareFileUtil.readObject(this, GlobalConsts.SEARCH_HISTORY);
            return searchList;
        } catch (Exception e) {
            return null;
        }
    }

}
