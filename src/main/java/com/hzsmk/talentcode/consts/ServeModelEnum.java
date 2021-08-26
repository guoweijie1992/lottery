package com.hzsmk.talentcode.consts;

/**
 * 服务模块
 *
 * @author jiangjh
 * @date 2020/4/7 16:18
 */
public enum ServeModelEnum {
    VIP(1, "专享服务", "vip"),
    WORK(2, "双创服务", "work"),
    LIFE(3, "生活服务", "life"),
    REGION(4, "区县特色", "region"),
    QUANKE(5, "全科服务", "quanke"),
    ZJTALENT(6, "畅行全省", "zjTalent");

    private Integer type;
    /**
     * 模块名称
     */
    private String modelName;
    /**
     * 模块名称-英文
     */
    private String modelNameEng;

    ServeModelEnum(Integer type, String modelName, String modelNameEng) {
        this.type = type;
        this.modelName = modelName;
        this.modelNameEng = modelNameEng;
    }

    public Integer getType() {
        return type;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelNameEng() {
        return modelNameEng;
    }
}
