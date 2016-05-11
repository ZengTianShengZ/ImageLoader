package com.example.imageloader;

import java.util.ArrayList;
import java.util.List;

import com.example.imageloader.adapter.DividerGridItemDecoration;
import com.example.imageloader.adapter.PhotoFloderItemAdapter;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends Activity {

	private PhotoFloderItemAdapter mPhotoFloderItemAdapter;
	private RecyclerView mRecyclerView;
	private List<String> gridItemList = new ArrayList<String>();
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = getApplication();
		 
		initData();
	}
 
	
	private void initData() {
 
		mRecyclerView = (RecyclerView) findViewById(R.id._recyclerView);
		mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(), 4));
		mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

		new Thread() {
			public void run() {

				gridItemList = scannerFloder();

			       runOnUiThread(new Runnable() {

					@Override
					public void run() {

						mPhotoFloderItemAdapter = new PhotoFloderItemAdapter(context,gridItemList);
						mRecyclerView.setAdapter(mPhotoFloderItemAdapter);

					}
				});
			};

		}.start();
	}
	
	/**
	 * 遍历 手机 图片 所在的 文件夹
	 * 
	 * @return 文件夹 list
	 */
	public List<String> scannerFloder() {
 
		List<String> gridItemList = new ArrayList<String>();

		Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		String columns[] = new String[] { Media.DATA };
		ContentResolver mContentResolver = getContentResolver();

		Cursor cursor = mContentResolver.query(mImageUri, columns, null, null, Media.DATE_TAKEN + " DESC");

		int photoPathIndex = cursor.getColumnIndexOrThrow(Media.DATA);
		String firstImage = null;

		while (cursor.moveToNext()) {
			// 获取图片的路径
			String path = cursor.getString(photoPathIndex);
			gridItemList.add(path);
		}
		cursor.close();

		return gridItemList;

	}
}
