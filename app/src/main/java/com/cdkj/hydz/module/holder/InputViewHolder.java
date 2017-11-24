package com.cdkj.hydz.module.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.ProductCraftModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by lei on 2017/9/14.
 */

public class InputViewHolder extends BaseHolder<List<ProductCraftModel.ProductCategoryListBean>> {

    private EditText edtContent;

    private TextView txtEdit;
    public TextView txtConfirm;
    private TextView txtCraftName;
    private TextView txtCraftValue;
    private LinearLayout layoutBtn;
    private LinearLayout layoutEdit;

    public InputViewHolder(Context context, int viewId, ViewGroup parent, int viewType) {
        super(context, viewId, parent, viewType);
        edtContent = itemView.findViewById(R.id.edt_content);

        txtEdit = itemView.findViewById(R.id.txt_edit);
        txtConfirm = itemView.findViewById(R.id.txt_confirm);
        txtCraftName = itemView.findViewById(R.id.txt_craft_name);
        txtCraftValue = itemView.findViewById(R.id.txt_craft_value);

        layoutBtn = itemView.findViewById(R.id.layout_btn);
        layoutEdit = itemView.findViewById(R.id.layout_edit);

    }

    @Override
    public void refreshData(List<ProductCraftModel.ProductCategoryListBean> data, int position) {
        super.refreshData(data, position);

        txtCraftName.setText(data.get(position).getDvalue());

        // 设置已选的默认值
        for (ProductCraftModel.ProductCategoryListBean.CraftListBean bean : data.get(position).getCraftList()) {
            if (bean.isSelect()) {
                txtCraftValue.setText(bean.getName());
            }
        }

        txtEdit.setOnClickListener(view -> {
            txtEdit.setVisibility(View.GONE);
            layoutBtn.setVisibility(View.VISIBLE);
            layoutEdit.setVisibility(View.VISIBLE);
        });

        txtConfirm.setOnClickListener(view -> {

            txtEdit.setVisibility(View.VISIBLE);
            layoutBtn.setVisibility(View.GONE);
            layoutEdit.setVisibility(View.GONE);

            data.get(position).getCraftList().clear();

            if (!TextUtils.isEmpty(edtContent.getText().toString().toString())) {
                txtCraftValue.setText(edtContent.getText().toString().toString());
                ProductCraftModel.ProductCategoryListBean.CraftListBean bean = new ProductCraftModel.ProductCategoryListBean.CraftListBean();
                bean.setSelect(true);
                bean.setName(edtContent.getText().toString().toString());
                data.get(position).getCraftList().add(bean);
            }

            EventBus.getDefault().post(EventTags.UPDATE);

        });
    }
}
