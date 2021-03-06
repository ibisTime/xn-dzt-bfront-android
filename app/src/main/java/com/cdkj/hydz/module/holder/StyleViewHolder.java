package com.cdkj.hydz.module.holder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.ProductCraftModel;

import java.util.List;

/**
 * 样式ViewHolder不带颜色
 * Created by lei on 2017/9/19.
 */

public class StyleViewHolder extends BaseHolder<List<ProductCraftModel.ProductCategoryListBean>> {

    private Activity mActivity;

//    private int craftPosition;

    private TextView txtEdit;
    private TextView txtConfirm;
    private TextView txtCraftName;
    private TextView txtCraftValue;
    private LinearLayout layoutBtn;
    private LinearLayout layoutGrid;
    private RecyclerView recyclerCraft;

    private final int ONE_LINE_SHOW_NUMBER = 3;
    private List<ProductCraftModel.ProductCategoryListBean> productCategoryList;

    public StyleViewHolder(Context context, int viewId, ViewGroup parent, int viewType) {
        super(context, viewId, parent, viewType);

        mActivity = (Activity) context;

        txtEdit = itemView.findViewById(R.id.txt_edit);
        txtConfirm = itemView.findViewById(R.id.txt_confirm);
        txtCraftName = itemView.findViewById(R.id.txt_craft_name);
        txtCraftValue = itemView.findViewById(R.id.txt_craft_value);
        layoutBtn = itemView.findViewById(R.id.layout_btn);
        layoutGrid = itemView.findViewById(R.id.layout_grid);
        recyclerCraft = itemView.findViewById(R.id.recycler_craft);
    }

    public void refreshData(List<ProductCraftModel.ProductCategoryListBean> list, int position) {
        txtCraftName.setText(list.get(position).getDvalue());


        //每行显示3个，水平显示
        recyclerCraft.setLayoutManager(new GridLayoutManager(mActivity, ONE_LINE_SHOW_NUMBER, LinearLayoutManager.VERTICAL, false));
        //设置Adapter
        GridAdapter gridAdapter = new GridAdapter(list.get(position).getCraftList(), position);
        recyclerCraft.setAdapter(gridAdapter);
        // 设置已选的默认值
        if (checkIsSelect(list, position)) {
            gridAdapter.setSelectValue();
        } else {
            gridAdapter.setDefluteValue();
        }


        txtEdit.setOnClickListener(view -> {
            txtEdit.setVisibility(View.GONE);
//            layoutBtn.setVisibility(View.VISIBLE);
            recyclerCraft.setVisibility(View.VISIBLE);
        });

//        txtConfirm.setOnClickListener(view -> {
//            if (check(list, position)) {
//                txtEdit.setVisibility(View.VISIBLE);
//                layoutBtn.setVisibility(View.GONE);
//                recyclerCraft.setVisibility(View.GONE);
//
////                list.get(position).getCraftList().get(craftPosition).setSelect(true);
//
//            }
//
//        });
    }

    private boolean checkIsSelect(List<ProductCraftModel.ProductCategoryListBean> list, int position) {
        for (ProductCraftModel.ProductCategoryListBean.CraftListBean bean : list.get(position).getCraftList()) {
            if (bean.isSelect()) {
                return true;
            }
        }
        return false;
    }

    private class GridAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private int itemPosition;
        private List<ProductCraftModel.ProductCategoryListBean.CraftListBean> craftList;

        public GridAdapter(List<ProductCraftModel.ProductCategoryListBean.CraftListBean> craftList, int itemPosition) {
            this.craftList = craftList;
            this.itemPosition = itemPosition;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(mActivity, R.layout.item_order_style, parent, viewType);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            ProductCraftModel.ProductCategoryListBean.CraftListBean itemData = craftList.get(position);
            if (itemData == null) return;

            holder.txtBorder.setText(itemData.getName());

            if (itemData.isSelect()) {
                holder.txtBorder.setBackgroundResource(R.drawable.corner_order_blue);
                holder.txtBorder.setTextColor(mActivity.getResources().getColor(R.color.white));
            } else {
                holder.txtBorder.setBackgroundResource(R.drawable.corner_order_gray);
                holder.txtBorder.setTextColor(mActivity.getResources().getColor(R.color.gray_333333));
            }

            holder.txtName.setText(itemData.getName());

            holder.txtBorder.setOnClickListener(view -> {
//                if (!craftList.get(position).isSelect()) { //如果已经选择过就不在选择

                for (ProductCraftModel.ProductCategoryListBean.CraftListBean bean : craftList) {
                    bean.setSelect(false);
                }

                setSelectValue(itemData);

//                    holder.txtBorder.setBackgroundResource(R.drawable.corner_order_blue);
//                    holder.txtBorder.setTextColor(mActivity.getResources().getColor(R.color.white));

                notifyDataSetChanged();

                //选择过后直接隐藏布局
                txtEdit.setVisibility(View.VISIBLE);
//                    layoutBtn.setVisibility(View.GONE);
                recyclerCraft.setVisibility(View.GONE);
//                }


            });

        }

        private void setSelectValue(ProductCraftModel.ProductCategoryListBean.CraftListBean bean) {
            bean.setSelect(true);
            txtCraftValue.setText(bean.getName()
                    + "("
                    + MoneyUtils.showPriceWithUnit(bean.getPrice())
                    + ")");
        }

        public void setDefluteValue() {
            for (ProductCraftModel.ProductCategoryListBean.CraftListBean bean : craftList) {
                if (bean == null) continue;

                if (TextUtils.equals("1", bean.getIsDefault())) {
                    setSelectValue(bean);
                } else {
                    bean.setSelect(false);
                }
            }
        }

        public void setSelectValue() {
            for (ProductCraftModel.ProductCategoryListBean.CraftListBean bean : craftList) {
                if (bean.isSelect()) {
                    setSelectValue(bean);
                }
            }
        }

        @Override
        public int getItemCount() {
            return craftList.size();
        }
    }

    private boolean check(List<ProductCraftModel.ProductCategoryListBean> list, int position) {
        for (ProductCraftModel.ProductCategoryListBean.CraftListBean bean : list.get(position).getCraftList()) {
            if (bean.isSelect()) {
                return true;
            }
        }

        Toast.makeText(mActivity, "请选择" + list.get(position).getDvalue(), Toast.LENGTH_SHORT).show();
        return false;
    }
}
