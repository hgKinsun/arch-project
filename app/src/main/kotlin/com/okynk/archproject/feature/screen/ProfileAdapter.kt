package com.okynk.archproject.feature.screen

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.okynk.archproject.R
import com.okynk.archproject.core.entity.ProfileEntity

class ProfileAdapter : BaseQuickAdapter<ProfileEntity, BaseViewHolder>(R.layout.item_profile) {

    override fun convert(helper: BaseViewHolder?, item: ProfileEntity?) {
        helper?.let {
            item?.let {
                helper.setText(R.id.tv_name, "${item.first} ${item.last}")
                    .setText(R.id.tv_email, item.email)
                    .setText(R.id.tv_address, item.address)
                    .setText(R.id.tv_balance, item.balance)
            }
        }
    }

}