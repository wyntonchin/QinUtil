package com.android.qin.two;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.qin.R;

/**
 * @author qinwendong
 * @date 2019/3/6
 * descrption:
 */
public class BuildingRvAdapter extends BaseRvAdapter {

    private static final String TAG = "HomepageRoomAdapter";

    private long mSelectedRoomId = -1;

    public void setSelectedRoom(@Nullable long selectedRoomId) {
        mSelectedRoomId = selectedRoomId;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new ViewHolder(context, parent);
    }


    class ViewHolder extends BaseRvAdapter.BaseViewHolder {
        private TextView name;

        public ViewHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.adapter_text);
            name = itemView.findViewById(R.id.item_name);
        }

        /**
         * 抽象方法，绑定数据.
         * 让子类自行对数据和view进行绑定
         *
         * @param itemValue Item的数据
         * @param position  当前item的position
         * @param listener  点击事件监听者
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void bindData(final Object itemValue, final int position, final BaseRvAdapter.OnItemClickListener listener) {
            String item = ((String) itemValue);
            name.setText(item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(itemValue, v.getId(), position);
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
