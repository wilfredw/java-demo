package com.wei.java.design.pattern.template.service.longsheng.actionengine;

import com.tuya.prodservice.common.enums.ErrorCodeEnum;
import com.tuya.prodservice.common.exception.ProdserviceBaseException;
import com.tuya.prodservice.common.framework.enums.DpTestProcEnum;
import com.tuya.prodservice.common.framework.enums.DpTestProcMethod;
import com.tuya.prodservice.common.framework.enums.I18nProcEnum;
import com.tuya.prodservice.common.framework.enums.I18nProcMethod;
import com.tuya.prodservice.common.framework.enums.IProcEnum;
import com.tuya.prodservice.common.framework.enums.LinkModeProcEnum;
import com.tuya.prodservice.common.framework.enums.LinkModeProcMethod;
import com.tuya.prodservice.common.framework.enums.LinkageProcEnum;
import com.tuya.prodservice.common.framework.enums.LinkageProcMethod;
import com.tuya.prodservice.common.framework.enums.OTAProcEnum;
import com.tuya.prodservice.common.framework.enums.OTAProcMethod;
import com.tuya.prodservice.common.framework.enums.ProcClass;
import com.tuya.prodservice.common.framework.enums.ProcTypeEnum;
import com.tuya.prodservice.common.framework.enums.VoiceProcEnum;
import com.tuya.prodservice.common.framework.enums.VoiceProcMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhoulongsheng
 * @version $Id: ProcContextHelper.java, v 0.1 2020年8月13日 下午15:14:55 zhoulongsheng Exp $
 */
@Slf4j
@Service
public class ProcContextHelper implements ApplicationContextAware, InitializingBean {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * Proc Method集合
     */
    private static final Map<IProcEnum, Method> procMethodMap = new HashMap<>();

    /**
     * Proc Enum集合
     */
    private static final Map<ProcTypeEnum, List<IProcEnum>> procEnumListMap = new HashMap<>();

    static {
        List<IProcEnum> otaList = new ArrayList<>(Arrays.asList(OTAProcEnum.values()));
        List<IProcEnum> i18nList = new ArrayList<>(Arrays.asList(I18nProcEnum.values()));
        List<IProcEnum> linkageList = new ArrayList<>(Arrays.asList(LinkageProcEnum.values()));
        List<IProcEnum> dpTestList = new ArrayList<>(Arrays.asList(DpTestProcEnum.values()));
        List<IProcEnum> voiceList = new ArrayList<>(Arrays.asList(VoiceProcEnum.values()));
        List<IProcEnum> linkmodeList = new ArrayList<>(Arrays.asList(LinkModeProcEnum.values()));


        procEnumListMap.put(ProcTypeEnum.OTA, otaList);
        procEnumListMap.put(ProcTypeEnum.I18n, i18nList);
        procEnumListMap.put(ProcTypeEnum.Linkage, linkageList);
        procEnumListMap.put(ProcTypeEnum.DPTest, dpTestList);
        procEnumListMap.put(ProcTypeEnum.VOICE, voiceList);
        procEnumListMap.put(ProcTypeEnum.LINKMODE, linkmodeList);
    }

    private static final List<Class<?>> annotationList = new ArrayList<>(
            Arrays.asList(OTAProcMethod.class, I18nProcMethod.class, LinkageProcMethod.class, DpTestProcMethod.class, VoiceProcMethod.class,
                    LinkModeProcMethod.class));


    /**
     * 获取执行方法
     *
     * @param procEnum
     * @return
     */
    public static Method getProcMethod(IProcEnum procEnum) {
        return procMethodMap.get(procEnum);
    }

    /**
     * 初始化装载执行器
     *
     * @see InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化系统processor组件开始...");

        initProcessorMethodMap();

        checkProcEnumMatching();

        printProcEnum();

        log.info("初始化系统processor组件结束...");
    }

    /**
     * @see ApplicationContextAware#setApplicationContext(ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private synchronized void initProcessorMethodMap() {
        //获取所有ProcClass注解的类
        Map<String, Object> beansWithAnnotationMap = applicationContext.getBeansWithAnnotation(ProcClass.class);
        log.info("beansWithAnnotationMap : " + beansWithAnnotationMap);

        Class<? extends Object> clazz = null;
        for (Map.Entry<String, Object> entry : beansWithAnnotationMap.entrySet()) {
            clazz = entry.getValue().getClass();//获取到实例对象的class信息
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isBridge()) {
                    continue;
                }

                IProcEnum proc = null;
                if (method.isAnnotationPresent(OTAProcMethod.class)) {
                    proc = method.getAnnotation(OTAProcMethod.class).processor();
                } else if (method.isAnnotationPresent(I18nProcMethod.class)) {
                    proc = method.getAnnotation(I18nProcMethod.class).processor();
                } else if (method.isAnnotationPresent(LinkageProcMethod.class)) {
                    proc = method.getAnnotation(LinkageProcMethod.class).processor();
                } else if (method.isAnnotationPresent(DpTestProcMethod.class)) {
                    proc = method.getAnnotation(DpTestProcMethod.class).processor();
                } else if (method.isAnnotationPresent(VoiceProcMethod.class)) {
                    proc = method.getAnnotation(VoiceProcMethod.class).processor();
                } else if (method.isAnnotationPresent(LinkModeProcMethod.class)) {
                    proc = method.getAnnotation(LinkModeProcMethod.class).processor();
                }


                if (proc != null) {
                    if (procMethodMap.containsKey(proc)) {
                        log.error("ProcType:{} ProcEnum:{} has multi processor methods", proc.procType(), proc.procName());
                        throw new ProdserviceBaseException(ErrorCodeEnum.SYSTEM_ERROR);
                    } else {
                        procMethodMap.put(proc, method);
                    }
                }
            }
        }
    }

    /**
     * 校验所有ProcEnum是否都有对应ProcMethod注解
     */
    private void checkProcEnumMatching() {
        boolean hasAllProcessor = true;
        for (ProcTypeEnum e : ProcTypeEnum.values()) {
            for (IProcEnum proc : procEnumListMap.get(e)) {
                if (!procMethodMap.containsKey(proc)) {
                    log.error("ProcTypeEnum:{} IProcEnum:{} no match processor method", e.getName(), proc.procName());
                    hasAllProcessor = false;
                }
            }
        }

        if (!hasAllProcessor) {
            log.error("ProcEnum has no matching processor method");
            throw new ProdserviceBaseException(ErrorCodeEnum.SYSTEM_ERROR);
        }

        //校验ProcEnum与ProcMethod注解数量一致
        int length = 0;
        for (ProcTypeEnum e : ProcTypeEnum.values()) {
            length += procEnumListMap.get(e).size();
        }
        if (length != procMethodMap.size()) {
            log.error("ProcessorEnum length != procMethodMap size");
            throw new ProdserviceBaseException(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 输出Processor信息
     */
    private void printProcEnum() {
        for (ProcTypeEnum e : ProcTypeEnum.values()) {

            log.info("print ProcTypeEnum:{} ---------------------begin", e.getName());

            for (IProcEnum proc : procEnumListMap.get(e)) {
                log.info("IProcEnum:{}, ProcessorMethod:{}, ProcessorClass:{}", proc.procName(), procMethodMap.get(proc).getName(),
                        procMethodMap.get(proc).getDeclaringClass().getName());
            }

            log.info("print ProcTypeEnum:{} ---------------------end", e.getName());
        }
    }
}
