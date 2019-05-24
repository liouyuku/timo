package com.linln.common.constant;

/**
 * 数据状态常量
 *
 * @author 小懒虫
 * @date 2019/2/22
 */
public class StatusConst {

    // 正常状态码
    public static final byte OK = 1;
    // 冻结状态码
    public static final byte FREEZED = 2;
    // 删除状态码
    public static final byte DELETE = 3;
    /**
     * 编号重复
     */

    public static final String DUPLICATE_NUMBER = "编号重复";
    /**
     * 已经有业主
     */

    public static final String DUPLICATE_OWNER = "已经有业主";
    /**
     * 用户重复
     */

    public static final String DUPLICATE_USER = "用户重复";
    /**
     * 名称重复
     */
    public static final String DUPLICATE_NAME = "名称重复";
    //小区的期数名字不能重复
    public static final String PERIOD_NAMES_NO_REPEATED = "期数名称重复";
    //小区的名字不能重复

    public static final String RESIDENTIALQUARTERS_NAMES_NO_REPEATED = "小区名称重复";
    /**
     * 栋数不能为空
     */
    public static final String NUMBER_OF_BUILDINGS_NOT_NULL = "栋数不能为空";
    /**
     * 绑定类型没有选择
     */
    public static final String PUBLISHING_LOCATION_NO_CHOICE = "发布位置不能为空";
    /**
     * 没有选中固定的人
     */
    public static final String NO_FIXED_PERSON_WAS_SELECTED = "没有选中固定的人";
    /**
     * 没有选择小区
     */
    public static final String NO_SELECTED_DISTRICTS = "没有选中小区";
    /**
     * 没有选择期数
     */
    public static final String NO_SELECTED_RESIDENTIALQUARTERS = "没有选中期数";
    /**
     * 没有选择栋数
     */
    public static final String NO_SELECTED_BUILDINGS = "没有选中栋数";
}
