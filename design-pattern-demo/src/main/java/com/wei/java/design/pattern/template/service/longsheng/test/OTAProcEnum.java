package com.wei.java.design.pattern.template.service.longsheng.test;


import java.util.HashMap;
import java.util.Map;

/**
 * 固件OTA处理器枚举.
 *
 * @author zhoulongsheng
 * @version $Id: OtaProcEnum.java, v 0.1 2020年8月13日 下午15:14:55 zhoulongsheng Exp $
 */
public enum OTAProcEnum implements IProcEnum {

    FIRMWARE_UPGRADE_BASIC_DATA("FIRMWARE_UPGRADE_BASIC_DATA", "固件升级基础数据"),

    FIRMWARE_UPGRADE_RELEASE_MODE_DATA("FIRMWARE_UPGRADE_RELEASE_MODE_DATA", "固件升级发布模式数据"),
    FIRMWARE_UPGRADE_GREY_RATE_DATA("FIRMWARE_UPGRADE_GREY_RATE_DATA", "固件升级灰度比例数据"),
    FIRMWARE_UPGRADE_GREY_QUOTA_DATA("FIRMWARE_UPGRADE_GREY_QUOTA_DATA", "固件升级核心指标数据"),
    FIRMWARE_UPGRADE_DEVICE_COUNT_DATA("FIRMWARE_UPGRADE_DEVICE_COUNT_DATA", "固件升级设备数量数据"),
    FIRMWARE_UPGRADE_DEVICE_TIMES_DATA("FIRMWARE_UPGRADE_DEVICE_TIMES_DATA", "固件升级设备次数数据"),
    FIRMWARE_UPGRADE_DEVICE_STATUS_DATA("FIRMWARE_UPGRADE_DEVICE_STATUS_DATA", "固件升级设备状态数据"),
    FIRMWARE_UPGRADE_RECORD_LIST("FIRMWARE_UPGRADE_RECORD_LIST", "固件升级记录列表"),

    FIRMWARE_UPGRADE_GREY_RATE_UPDATE("FIRMWARE_UPGRADE_GREY_RATE_UPDATE", "固件升级灰度比例更新"),
    FIRMWARE_UPGRADE_GREY_UPDATE("FIRMWARE_UPGRADE_GREY_UPDATE", "固件升级灰度更新"),
    FIRMWARE_UPGRADE_PUBLISH_STOP("FIRMWARE_UPGRADE_PUBLISH_STOP", "固件升级发布停止"),
    FIRMWARE_UPGRADE_PUBLISH_RESTART("FIRMWARE_UPGRADE_PUBLISH_RESTART", "固件升级发布恢复"),

    FIRMWARE_UPGRADE_WHITELIST_DEV_DATA("FIRMWARE_UPGRADE_WHITELIST_DEV_DATA", "固件升级白名单设备"),
    FIRMWARE_UPGRADE_DEV_LIST_DATA("FIRMWARE_UPGRADE_DEV_LIST_DATA", "固件升级设备列表"),
    FIRMWARE_UPGRADE_DEV_PAGE_DATA("FIRMWARE_UPGRADE_DEV_PAGE_DATA", "固件升级设备列表"),
    FIRMWARE_UPGRADE_WHITELIST_DEV_ADD("FIRMWARE_UPGRADE_WHITELIST_DEV_ADD", "固件升级白名单设备添加"),
    FIRMWARE_UPGRADE_WHITELIST_DEV_DELETE("FIRMWARE_UPGRADE_WHITELIST_DEV_DELETE", "固件升级白名单设备删除"),
    FIRMWARE_UPGRADE_DEV_BATCH_ADD("FIRMWARE_UPGRADE_DEV_BATCH_ADD", "固件升级设备批量添加"),
    FIRMWARE_UPGRADE_DEV_DELETE("FIRMWARE_UPGRADE_DEV_DELETE", "固件升级设备删除"),
    FIRMWARE_UPGRADE_DEV_VALIDATE("FIRMWARE_UPGRADE_DEV_VALIDATE", "固件升级设备验证"),

    FIRMWARE_UPGRADE_CONFIG_ADD("FIRMWARE_UPGRADE_CONFIG_ADD", "新建固件升级"),
    FIRMWARE_UPGRADE_CONFIG_SAVE("FIRMWARE_UPGRADE_CONFIG_SAVE", "修改固件升级"),
    FIRMWARE_UPGRADE_CONFIG_DELETE("FIRMWARE_UPGRADE_CONFIG_DELETE", "删除固件升级"),
    FIRMWARE_UPGRADE_CONFIG_DETAIL("FIRMWARE_UPGRADE_CONFIG_DETAIL", "查询固件升级详情"),
    FIRMWARE_UPGRADE_CONFIG_LIST("FIRMWARE_UPGRADE_CONFIG_LIST", "查询固件升级列表"),
    FIRMWARE_UPGRADE_VERSION_INFOS("FIRMWARE_UPGRADE_VERSION_INFOS", "查询升级版本"),
    FIRMWARE_UPGRADE_VERSION_UPDATABLE("FIRMWARE_UPGRADE_VERSION_UPDATABLE", "查询新固件版本可更新状态"),

    FIRMWARE_VERSION_BASIC_INFO("FIRMWARE_VERSION_BASIC_INFO", "固件版本信息"),
    FIRMWARE_VERSION_LIST("FIRMWARE_VERSION_LIST", "固件版本列表信息"),

    OTA_DOMESTIC_AREAS("OTA_DOMESTIC_AREAS", "国内省市列表信息"),
    OTA_AREAS_TREE("OTA_AREAS_TREE", "地区树查询"),

    OTA_SMS_SEND("OTA_SMS_SEND", "发送验证短信"),
    OTA_EMAIL_SEND("OTA_EMAIL_SEND", "发送验证码邮件");

    /***这个MAP用于提升性能**/
    private static final Map<String, OTAProcEnum> MAP = new HashMap<>();

    static {
        for (OTAProcEnum each : OTAProcEnum.values()) {
            MAP.put(each.processorName, each);
        }
    }

    /**
     * 处理器名称
     */
    final private String processorName;

    /**
     * 处理器描述
     */
    final private String memo;

    private OTAProcEnum(String processorName, String memo) {
        this.processorName = processorName;
        this.memo = memo;
    }

    /**
     * @param processorName
     * @return
     */
    public static OTAProcEnum getProcessorEnum(String processorName) {
        return MAP.get(processorName);
    }

    @Override
    public ProcTypeEnum procType() {
        return ProcTypeEnum.OTA;
    }

    @Override
    public String procName() {
        return processorName;
    }

}

