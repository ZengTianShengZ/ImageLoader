package com.example.imageloader.adapter;

import java.util.List;

import com.example.imageloader.R;
import com.example.imageloader.utils.ImageLoader;
import com.example.imageloader.utils.ImageLoader.ImageCallback;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class PhotoFloderItemAdapter extends BaseRecycleViewAdapter
		implements ImageCallback {

	private ImageLoader mImageLoader;
 
	private String floderDir;
 

	public PhotoFloderItemAdapter(Context context,List datas) {
		super(context, R.layout.grid_item, datas);
		this.mContext = context;
	 
	 
		
		mImageLoader = new ImageLoader();
 
	}
  
	@Override
	public void convert(RecycleViewHolder holder, Object t, int holderPosition) {

		String imageUrl = (String) t;

		ImageView grid_item = holder.getView(R.id.grid_item);
 

		grid_item.setTag(imageUrl);
		mImageLoader.displayBmp(mContext,grid_item, imageUrl, R.drawable.img_bg,this);
 
	}

	/**
	 * 图片 缓存回调
	 */
	@Override
	public void imageLoad(ImageView imageView, Bitmap bitmap, Object... params) {
		if (imageView != null && bitmap != null) {
			String url = (String) params[0];

			// 判断 这里的 url 是否 对应  imageView.getTag()
			// 如果 将这句 判断 去掉  那么 就会出现  经常出现的  图片 显示 错位 问题 ！！！！
			if (url != null && url.equals((String) imageView.getTag())) {

				((ImageView) imageView).setImageBitmap(bitmap);
			}
		}
	}
 
 
}
