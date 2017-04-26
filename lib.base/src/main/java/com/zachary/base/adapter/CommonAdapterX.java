package com.zachary.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public abstract class CommonAdapterX<T> extends RecyclerView.Adapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public CommonAdapterX(Context context, List<T> datas, int itemLayoutId) {
        mContext = context;
        mDatas = datas;
        mItemLayoutId = itemLayoutId;
    }


    public void setDatas(List<T> datas){
        mDatas = datas;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(mItemLayoutId, parent, false);
        return new CommonViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            convert((CommonViewHolder) holder, mDatas.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (null != mOnItemClickListener) {
                        mOnItemClickListener.onItemClick(v, position);
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(null!=mOnItemLongClickListener){
                        mOnItemLongClickListener.onLongClick(v,position);
                    }
                    return false;
                }
            });

    }

    public int getItemCount() {
        return mDatas.size();
    }

    public void notifyDataSetChanged(int firstCount) {
        int addCount = getItemCount() - firstCount;
        if (addCount > 0) {
            notifyItemRangeInserted(firstCount, addCount);
        } else if(addCount<0){
            notifyItemRangeRemoved(getItemCount(), -addCount);
        }
        else {
            notifyDataSetChanged();
        }
    }

    public abstract void convert(CommonViewHolder helper, T item);

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener onItemClickListener) {
        mOnItemLongClickListener = onItemClickListener;
    }

}
