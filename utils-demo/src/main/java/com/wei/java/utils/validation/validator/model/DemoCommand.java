package com.wei.java.utils.validation.validator.model;

import com.wei.java.utils.validation.validator.enums.TypeEnum;
import com.wei.java.utils.validation.validator.group.Create;
import com.wei.java.utils.validation.validator.group.Update;
import com.wei.java.utils.validation.validator.validator.ByteCheck;
import com.wei.java.utils.validation.validator.validator.IsDateTime;
import com.wei.java.utils.validation.validator.validator.IsEnum;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/7/13
 */
public class DemoCommand implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(groups = {Update.class}, message = "id is null")
    private String id;

    @NotNull(groups = {Create.class}, message = "msgType is null")
    private String msgType;

    @NotNull(groups = {Create.class}, message = "name is null")
    @NotEmpty
    @Size(min = 1, max = 5)
    private String name;

    @NotNull(groups = {Create.class}, message = "ids is null")
    @Size(min = 1, max = 3)
    private List<String> ids;

    @NotNull(groups = {Create.class}, message = "isTrue is null")
    @ByteCheck(message = "启用状态只能是启用或禁用")
    private Byte isTrue;

    /**
     * format: 2012-01-12
     */
    @NotNull(groups = {Create.class}, message = "currentDate is null")
    @IsDateTime(required = false)
    private String currentDate;

    /**
     * @TypeEnum
     */
    @NotNull(groups = {Create.class}, message = "type is null")
    @IsEnum(required = false, enumType = TypeEnum.class)
    private String type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Byte isTrue) {
        this.isTrue = isTrue;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
