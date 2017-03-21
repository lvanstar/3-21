package com.qiansong.msparis.app.homepage.view.sortlistview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BrandHomeBean;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.homepage.activity.BrandDetailsActivity;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.FollowBrandBean;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SortAdapter extends BaseAdapter implements SectionIndexer {
	private Context mContext;
	ConfigsBean.DataEntity.BrandEntity brandEntity;
	public SortAdapter(Context mContext,ConfigsBean.DataEntity.BrandEntity brandEntity) {
		this.mContext = mContext;
		this.brandEntity = brandEntity;
//		for (int i = 0;i<brandEntity.brands.size();i++){
//			brandEntity .brands.get(i).sort_az= Eutil.nameToAbc(brandEntity.brands.get(i).getName());
//			if (brandEntity .brands.get(i).sort_az==null){
//				brandEntity .brands.get(i).sort_az="#";
//			}
//		}

	}
	
	/**
	 * ��ListView���ݷ����仯ʱ,���ô˷���������ListView
	 * @param list
	 */
	public void updateListView(List<FriendListBean.DataBean> list){
		notifyDataSetChanged();
	}

	public int getCount() {
		return (brandEntity.brands.size());
	}

	public Object getItem(int position) {
		return brandEntity.brands.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final ConfigsBean.DataEntity.BrandEntity.BrandsEntity mContent = brandEntity.brands.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item_a_z, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.iv_tou= (ImageView) view.findViewById(R.id.iv_tou);
			viewHolder.favorite= (ImageView) view.findViewById(R.id.favorite);
			viewHolder.top= view.findViewById(R.id.top);
			viewHolder.bottom= view.findViewById(R.id.bottom);
//			viewHolder.image_daily= (SimpleDraweeView) view.findViewById(R.id.image_daily);
//			viewHolder.image_dress= (SimpleDraweeView) view.findViewById(R.id.image_dress);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		if (position==0){
			viewHolder.top.setVisibility(View.VISIBLE);
			head(view);
		}else {
			viewHolder.top.setVisibility(View.GONE);
		}
		int section = getSectionForPosition(position);
		//�����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
		if(position == getPositionForSection(section)){
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.sort_az);
			//ContentUtil.makeLog("yc", "sanp============" + mContent.snap);
//			viewHolder.iv_tou.setImageURI(Uri.parse(mContent.snap));
		}else{
			viewHolder.tvLetter.setVisibility(View.INVISIBLE);
		}
	
		viewHolder.tvTitle.setText(brandEntity .brands.get(position).getName());
		final ViewHolder finalViewHolder = viewHolder;
		viewHolder.favorite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (brandEntity.brands.get(position).is_dingyue){
					finalViewHolder.favorite.setImageResource(R.mipmap.dingyue_0);
					follow_brands(position,brandEntity.brands.get(position).getId(),false,finalViewHolder);
					brandEntity.brands.get(position).is_dingyue=false;
				}else {
					follow_brands(position,brandEntity.brands.get(position).getId(),true,finalViewHolder);
					finalViewHolder.favorite.setImageResource(R.mipmap.dongyue_1);
					brandEntity.brands.get(position).is_dingyue=true;
				}
			}
		});
		viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(mContext,BrandDetailsActivity.class);
				intent.putExtra("id",bean.getData().getBrand_commend().get(position).getId());
				mContext.startActivity(intent);
			}
		});
		return view;

	}
	/**
	 * 品牌关注-取消 TODO true 订阅  false 取消订阅
	 */
	public  void follow_brands(final int position, String id, boolean is_attent, final ViewHolder finalViewHolder){
		Map<String,String> map=new Hashtable<>();
		map.put("access_token",MyApplication.token);
		map.put("id",id);
		RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));

		if (is_attent) {
			HttpServiceClient.getInstance().user_brands_0(body).enqueue(new Callback<FollowBrandBean>() {
				@Override
				public void onResponse(Call<FollowBrandBean> call, Response<FollowBrandBean> response) {
					if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) {
						finalViewHolder.favorite.setImageResource(R.mipmap.dingyue_0);
						brandEntity.brands.get(position).is_dingyue=false;
						return;
					}
					ContentUtil.makeToast(MyApplication.getApp(),"订阅成功");
				}

				@Override
				public void onFailure(Call<FollowBrandBean> call, Throwable t) {
					brandEntity.brands.get(position).is_dingyue=false;
					finalViewHolder.favorite.setImageResource(R.mipmap.dingyue_0);
				}
			});
			return;
		}
		HttpServiceClient.getInstance().user_brands_1(body).enqueue(new Callback<FollowBrandBean>() {
			@Override
			public void onResponse(Call<FollowBrandBean> call, Response<FollowBrandBean> response) {
				if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) {
					finalViewHolder.favorite.setImageResource(R.mipmap.dongyue_1);
					brandEntity.brands.get(position).is_dingyue=true;
					return;
				}
				ContentUtil.makeToast(MyApplication.getApp(),"取消订阅成功");
			}

			@Override
			public void onFailure(Call<FollowBrandBean> call, Throwable t) {
				brandEntity.brands.get(position).is_dingyue=true;
				finalViewHolder.favorite.setImageResource(R.mipmap.dongyue_1);
			}
		});
	}
//	List<HotDataBean> hot_data;//热门品牌
	//品牌数据
	List<ConfigsBean.DataEntity.BrandEntity.BrandsEntity> brandlist;//品牌数据
	BrandHomeBean bean;
	public void setBean(BrandHomeBean bean) {
		this.bean = bean;
		notifyDataSetChanged();
	}

	private void head(View view){
		if (bean==null)return;
		SimpleDraweeView image_daily,image_dress;
		image_daily= (SimpleDraweeView) view.findViewById(R.id.image_daily);
		image_dress= (SimpleDraweeView) view.findViewById(R.id.image_dress);
		ExclusiveUtils.setImageUrl(image_daily,bean.getData().getBanner().getImage_daily(),-1);
		ExclusiveUtils.setImageUrl(image_dress,bean.getData().getBanner().getImage_dress(),-1);
		GridView hot_brands= (GridView) view.findViewById(R.id.hot_brands);
//		hot_data=new ArrayList<>();
//        for (int i=0;i<12;i++)hot_data.add(new HotDataBean());
        hot_brands.setAdapter(new HotBarndAdapter());
		ListUtils.setGridViewHeightBasedOnChildren(hot_brands,4);
		hot_brands.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mContext.startActivity(new Intent(mContext,BrandDetailsActivity.class));
            }
        });
	}
	//热门品牌
	public class HotBarndAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return bean.getData().getBrand_commend()!=null?bean.getData().getBrand_commend().size():0;
		}

		@Override
		public Object getItem(int position) {
			return bean.getData().getBrand_commend().get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder viewholder;
			if(convertView==null){
				convertView=View.inflate(mContext, R.layout.item_hotbrand,null);
				viewholder=new ViewHolder(convertView);
				convertView.setTag(viewholder);
			}else {
				viewholder= (ViewHolder) convertView.getTag();
			}
			ExclusiveUtils.setImageUrl(viewholder.logo,bean.getData().getBrand_commend().get(position).getLogo(),-1);
			return convertView;
		}

		private class ViewHolder{

			SimpleDraweeView logo;
			public ViewHolder(View view){
				logo= (SimpleDraweeView) view.findViewById(R.id.logo);
			}
		}
	}

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
		ImageView iv_tou,favorite;
		View top,bottom;

	}


	/**
	 * ����ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
	 */
	public int getSectionForPosition(int position) {
		return brandEntity .brands.get(position).sort_az.charAt(0);
	}

	/**
	 * ���ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = brandEntity .brands.get(i).sort_az;
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * ��ȡӢ�ĵ�����ĸ����Ӣ����ĸ��#���档
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}