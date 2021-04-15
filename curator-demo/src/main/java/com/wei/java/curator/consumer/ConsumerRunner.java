package com.wei.java.curator.consumer;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

import static com.wei.java.curator.constants.CommonConstants.*;

/**
 * 类的详细说明
 *
 * @author buhuan.wang
 * @since 2021/4/15
 */
public class ConsumerRunner implements Runnable {
    @Override
    public void run() {
        CuratorFramework client = null;
        try {
            String address = "localhost:2181";
            client = CuratorFrameworkFactory.newClient(address,
                    new ExponentialBackoffRetry(1000, 3));
            client.start();

            String nodePath = Joiner.on("/").join(ZK_ROOT_PATH, BIZ_TYPE, SCENARIO_DIR, NODE_ID);

            PathChildrenCacheListener bizScenarioChildrenCacheListener = (curatorFramework, pathChildrenCacheEvent) -> {
                System.out.println("receive bizScenario pathChildrenCacheEvent: " + pathChildrenCacheEvent);
                if (PathChildrenCacheEvent.Type.CHILD_ADDED == pathChildrenCacheEvent.getType()
                        || PathChildrenCacheEvent.Type.CHILD_UPDATED == pathChildrenCacheEvent.getType()) {
                    String eventPath = pathChildrenCacheEvent.getData().getPath();
                    List<String> nodes = Splitter.on("/").splitToList(eventPath);
                    String bizType = nodes.get(2);
                    String sceneCode = nodes.get(nodes.size() - 1);
                    System.out.println("receive scenario change for bizType:, sceneCode:" + bizType + " " + sceneCode);

                }
            };

            String bizScenarioPath = Joiner.on("/").join(ZK_ROOT_PATH, BIZ_TYPE, SCENARIO_DIR);

            PathChildrenCache bizScenarioChildrenCache = new PathChildrenCache(client, bizScenarioPath, false);
            bizScenarioChildrenCache.start();
            bizScenarioChildrenCache.getListenable().addListener(bizScenarioChildrenCacheListener);

            System.out.println("Consumer start listen......");

            Thread.sleep(1000 * 3600);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
