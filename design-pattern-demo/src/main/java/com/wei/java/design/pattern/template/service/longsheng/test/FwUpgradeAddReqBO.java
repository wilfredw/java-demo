package com.wei.java.design.pattern.template.service.longsheng.test;

import lombok.Data;

/**
 * @author xianglun
 * @date 2021/5/11 11:29 上午
 */
@Data
public class FwUpgradeAddReqBO {
    private String productId;
    private String firmwareKey;
    private String version;
    private Integer upgradeType;
    private String langTexts;
    private String desc;
    private String pickType;
    private String dtPara;
    private Boolean autoUpgrade;
    private String upgradeVersions;
    private String upgradeAreas;
    private String uid;

    private String i18nShortName;
    private Integer incrementType;
}
