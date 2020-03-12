package com.example.txim.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.txim.DemoApplication;
import com.example.txim.R;
import com.example.txim.activity.FriendActivity;
import com.tencent.imsdk.friendship.TIMFriendPendencyItem;
import com.tencent.qcloud.tim.uikit.component.CircleImageView;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;

import java.util.List;

public class NewFriendListAdapter extends ArrayAdapter<TIMFriendPendencyItem> {
    private static final String TAG = NewFriendListAdapter.class.getSimpleName();
    private int mResourceId;
    private View mView;
    private ViewHolder mViewHolder;
    public class ViewHolder{
        ImageView avatar;
        TextView name;
        TextView des;
        Button agree;
    }

    public NewFriendListAdapter(Context context, int resource, List<TIMFriendPendencyItem> objects) {
        super(context, resource,objects);
        mResourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TIMFriendPendencyItem timFriendPendencyItem=getItem(position);
        if (convertView!=null){
            mView=convertView;
            mViewHolder= (ViewHolder) mView.getTag();
        }else {
            mView= LayoutInflater.from(getContext()).inflate(mResourceId,null);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DemoApplication.instance(), FriendActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(TUIKitConstants.ProfileType.CONTENT, timFriendPendencyItem);
                    DemoApplication.instance().startActivity(intent);
                }
            });
        }
        return super.getView(position, convertView, parent);
    }
}
