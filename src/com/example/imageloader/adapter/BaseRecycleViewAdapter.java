package com.example.imageloader.adapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 *  RecyclerView.Adapter  ����
 * 
 * @ClassName: BaseRecycleViewAdapter
 * @Description: TODO
 * @author zss
 * @date 2016-4-29 PM
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {

	protected Context mContext;
	protected int mLayoutId;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;

	public static final int LAST_POSITION = -1;

	public BaseRecycleViewAdapter(Context context, int layoutId, List<T> datas) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mLayoutId = layoutId;
		mDatas = datas;
	}

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public void onBindViewHolder(final RecycleViewHolder holder, final int position) {
		holder.updatePosition(position);
		convert(holder, mDatas.get(position), holder.getPosition());
	}

	@Override
	public RecycleViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
		final RecycleViewHolder viewHolder = RecycleViewHolder.get(mContext, parent, mLayoutId);
		
		return viewHolder;
	}

	public abstract void convert(RecycleViewHolder holder, T t, int holderPosition);
   
	/**
	 * �Ƴ�ѡ�� �� item
	 * @param positions   
	 */
	public void removeSelectPosition( Map<Integer,Integer> map) {

		 Map<Integer, Integer> orderMap = new TreeMap<Integer, Integer>(
	                new Comparator<Integer>() {
	                    public int compare(Integer obj1, Integer obj2) {
	                        // ���� ����
	                        return obj1.compareTo(obj2);
	                    }
	                });
		 orderMap.putAll(map);
		 
		 
		  int poFlag = 0;
		  int keyMin = 0;
		  int keyMax = 0;
		//����map�е�ֵ  
		 for (Integer value : orderMap.values()) {  
			 
			 if(poFlag == 0){
				 keyMin = value;
			 }
			 keyMax = value;
			 int position = value;
			 
			// ע������� imageStrList.remove ��Ҫ position-i����Ϊ remove�� list
				// ����Ĳ�����������һλ��
				mDatas.remove(position - poFlag);
				if (position == LAST_POSITION && getItemCount() > 0)
					position = getItemCount() - 1;
				if (position > LAST_POSITION && position < getItemCount()) {
					notifyItemRemoved(position);
				}
				poFlag++;
		 }  
		 
/*		for (int i = 0; i < positions.size(); i++) {
			int position = positions.get(i);
			// ע������� imageStrList.remove ��Ҫ position-i����Ϊ remove�� list
			// ����Ĳ�����������һλ��
			mDatas.remove(position - i);
			if (position == LAST_POSITION && getItemCount() > 0)
				position = getItemCount() - 1;
			if (position > LAST_POSITION && position < getItemCount()) {
				notifyItemRemoved(position);
			}
		}
		//notifyDataSetChanged();
*/		// ˢ�� ѡ�е� ��һλ �� ���һλ�������� ����ˢ��
		 
		 notifyItemRangeChanged(orderMap.get(keyMin),orderMap.get(keyMax));

	}
 
	
	/**
	 * ��� �µ� item
	 * @param t
	 */
	public void addNewPosition(List<T> t) {
		int i = 0;
		for (T item : t) {
			// ͼƬ�����ڵڶ���
			i++;
			mDatas.add(i, (T) t);
			notifyItemInserted(i);
		}

	}
 

	public void myNotifyData(){
		notifyDataSetChanged();
	}
}
