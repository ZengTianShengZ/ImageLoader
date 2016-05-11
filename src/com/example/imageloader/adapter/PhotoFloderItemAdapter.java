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
	 * ͼƬ ����ص�
	 */
	@Override
	public void imageLoad(ImageView imageView, Bitmap bitmap, Object... params) {
		if (imageView != null && bitmap != null) {
			String url = (String) params[0];

			// �ж� ����� url �Ƿ� ��Ӧ  imageView.getTag()
			// ��� ����� �ж� ȥ��  ��ô �ͻ����  �������ֵ�  ͼƬ ��ʾ ��λ ���� ��������
			if (url != null && url.equals((String) imageView.getTag())) {

				((ImageView) imageView).setImageBitmap(bitmap);
			}
		}
	}
 
 
}
