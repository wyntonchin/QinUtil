package com.android.qin.two;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.qin.R;
import com.android.qin.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class HotelSelectorActivity extends AppCompatActivity implements SearchFragment.OnFragmentInteractionListener{
    private static final String TAG = "HomeSelectorActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_selector);
        initRecyclerView();

        initFloorRecyclerView();

        initHomeRecyclerView();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager. beginTransaction();
        SearchFragment searchFragment = new SearchFragment();
        transaction.replace(android.R.id.content, searchFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initRecyclerView() {
        LogUtil.i(TAG, "onCreateView initRecyclerView");
        RecyclerView buildingRecyclerView = findViewById(R.id.building_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        buildingRecyclerView.setLayoutManager(layoutManager);
        //防止嵌套卡顿
        buildingRecyclerView.setNestedScrollingEnabled(false);
        buildingRecyclerView.setItemAnimator(null);
        BuildingRvAdapter mBuildingRvAdapter = new BuildingRvAdapter();
        mBuildingRvAdapter.setOnItemClickListener(new BaseRvAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String itemValue, int viewId, int position) {
            /*    mSelectedRoomId = itemValue.getRoomId();
                mAllRoomBtn.setSelected(false);
                mHomepageRvFragment.asyncFilterDevicesByRoom(itemValue.getRoomId());*/
            }
        });

        buildingRecyclerView.setAdapter(mBuildingRvAdapter);
        List list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            list.add("building" + i);
        }
        mBuildingRvAdapter.refreshData(list);
    }

    private void initFloorRecyclerView() {
        LogUtil.i(TAG, "onCreateView initRecyclerView");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        RecyclerView floorRecyclerView = findViewById(R.id.floor_list);
        floorRecyclerView.setLayoutManager(layoutManager);
        BuildingRvAdapter mBuildingRvAdapter = new BuildingRvAdapter();
        mBuildingRvAdapter.setOnItemClickListener(new BaseRvAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String itemValue, int viewId, int position) {
            /*    mSelectedRoomId = itemValue.getRoomId();
                mAllRoomBtn.setSelected(false);
                mHomepageRvFragment.asyncFilterDevicesByRoom(itemValue.getRoomId());*/
            }
        });
        floorRecyclerView.setAdapter(mBuildingRvAdapter);

        List list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            list.add("floor" + i);
        }
        mBuildingRvAdapter.refreshData(list);
    }

    private void initHomeRecyclerView() {
        LogUtil.i(TAG, "onCreateView initRecyclerView");
        GridLayoutManager gridManager = new GridLayoutManager(this, 4);
        gridManager.setOrientation(RecyclerView.VERTICAL);
        RecyclerView homeRecyclerView = findViewById(R.id.home_no_list);
        homeRecyclerView.setLayoutManager(gridManager);
        BuildingRvAdapter mBuildingRvAdapter = new BuildingRvAdapter();
        mBuildingRvAdapter.setOnItemClickListener(new BaseRvAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String itemValue, int viewId, int position) {
            /*    mSelectedRoomId = itemValue.getRoomId();
                mAllRoomBtn.setSelected(false);
                mHomepageRvFragment.asyncFilterDevicesByRoom(itemValue.getRoomId());*/
            }
        });
        homeRecyclerView.setAdapter(mBuildingRvAdapter);
        List list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            list.add("home" + i);
        }
        mBuildingRvAdapter.refreshData(list);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
