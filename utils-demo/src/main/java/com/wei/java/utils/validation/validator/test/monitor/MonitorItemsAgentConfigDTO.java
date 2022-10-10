package com.wei.java.utils.validation.validator.test.monitor;

import com.wei.java.utils.validation.validator.validator.IsEnum;
import com.wei.java.utils.validation.validator.validator.IsLong;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 人员监控配置信息类
 *
 * @author buhuan.wang
 * @since 2022/2/14
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class MonitorItemsAgentConfigDTO {

    /**
     * 监控项配置
     */
    @NotNull
    @Valid
    private MonitorItemsDTO monitorItems;

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    public static class MonitorItemsDTO {
        /**
         * 客服上线人数监控
         */
        @Valid
        private List<AgentOnlineConfigDTO> agentOnlineNum;
        /**
         * 客服工作负荷监控
         */
        @Valid
        private List<AgentWorkLoadConfigDTO> agentWorkLoad;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    public static class AgentOnlineConfigDTO {
        /**
         * 禁用/启用
         */
        @NotNull
        private Boolean active;
        /**
         * 监控的客服组id列表
         */
        @NotNull
        @Size(min = 1)
        private List<String> agentGroups;
        /**
         * 监控生效的时间范围
         */
        @NotNull
        @Valid
        private MonitorEffectiveTimeConfigDTO effectiveTime;
        /**
         * 各个监控级别的配置
         */
        @NotNull
        @Valid
        private LevelConfigsDTO levelConfigs;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    public static class AgentWorkLoadConfigDTO {
        /**
         * 禁用/启用
         */
        @NotNull
        private Boolean active;
        /**
         * 监控的客服组id列表
         */
        @NotNull
        @Size(min = 1)
        private List<String> agentGroups;
        /**
         * 各个监控级别的配置
         */
        @NotNull
        @Valid
        private LevelConfigsDTO levelConfigs;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    public static class MonitorEffectiveTimeConfigDTO {
        /**
         * 监控生效的时间，日期类型，tuyaWork表示涂鸦工作日
         */
        @NotBlank
        @IsEnum(enumType = CalenderDateType.class, enumFields = {"code"})
        private String calendarType;
        /**
         * 监控生效的时间，每天开始时间，单位是秒，比如3600表示凌晨1点
         */
        @NotNull
        @Min(value = 0)
        @Max(value = 86399)
        private Integer startDayTime;
        /**
         * 监控生效的时间，每天结束时间，单位是秒，比如86399表示23点59分59秒
         */
        @NotNull
        @Min(value = 0)
        @Max(value = 86399)
        private Integer endDayTime;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    public static class LevelConfigsDTO {
        /**
         * 预警级别配置
         */
        @NotNull
        @Valid
        private LevelConfigDTO warning;
        /**
         * 报警级别配置
         */
        @NotNull
        @Valid
        private LevelConfigDTO alarm;
        /**
         * 非常紧急级别配置
         */
        @Valid
        private LevelConfigDTO urgency;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString
    public static class LevelConfigDTO {
        /**
         * 监控阈值，Long的字符串
         */
        @NotNull
        @IsLong
        private String threshold;
    }
}
