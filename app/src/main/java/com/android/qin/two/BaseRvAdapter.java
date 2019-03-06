package com.android.qin.two;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author qinwendong
 * @date 2018/7/5
 * descrption:
 */

public abstract class BaseRvAdapter<V> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 装载了每个Item的Value的列表
     */
    private List<V> mValueList;

    /**
     * 我写的一个接口，通过回调分发点击事件
     */
    private OnItemClickListener<V> mOnItemClickListener;

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createViewHolder(parent.getContext(), parent);
    }

    @Override
    @SuppressWarnings("unchecked")//一定会是BaseViewHolder的子类，因为createViewHolder()的返回值
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).setData(mValueList.get(position), position, mOnItemClickListener);
    }

    /**
     * 设置每个Item的点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener<V> listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 刷新数据
     *
     * @param valueList 新的数据列表
     */
    public void refreshData(List<V> valueList) {
        this.mValueList = valueList;
        notifyDataSetChanged();
    }

    public List<V> getData() {
        return mValueList;
    }

    @Override
    public int getItemCount() {
        return mValueList == null ? 0 : mValueList.size();
    }

    /**
     * 生成ViewHolder
     *
     * @param context
     * @param parent
     * @return
     */
    protected abstract BaseViewHolder createViewHolder(Context context, ViewGroup parent);


    public interface OnItemClickListener<V> {

        /**
         * 当item被点击的时候进行事件分发
         *
         * @param itemValue 点击的item传递的值
         * @param viewId    点击控件的id
         * @param position  被点击的item的位置
         */
        void onItemClick(V itemValue, int viewId, int position);
    }


    public abstract class BaseViewHolder extends RecyclerView.ViewHolder{

        public BaseViewHolder(Context context, ViewGroup root, int layoutRes) {
            super(LayoutInflater.from(context).inflate(layoutRes, root, false));
        }

        /**
         * 方便其子类进行一些需要Context的操作.
         *
         * @return 调用者的Context
         */
        public Context getViewContext() {
            return itemView.getContext();
        }

        /**
         * 抽象方法，绑定数据.
         * 让子类自行对数据和view进行绑定
         *
         * @param itemValue Item的数据
         * @param position  当前item的position
         * @param listener  点击事件监听者
         */
        protected abstract void bindData(V itemValue, int position, BaseRvAdapter.OnItemClickListener listener);

        /**
         * 用于传递数据和信息
         *
         * @param itemValue
         * @param position
         * @param listener
         */
        public void setData(V itemValue, int position, BaseRvAdapter.OnItemClickListener listener) {
            bindData(itemValue, position, listener);
        }
    }
}


