package site.zhangsun.pathsearch;

import java.util.Map;

/**
 * Function:
 *
 * @author zhangsunjiankun - 2019/5/8 下午9:06
 */
@FunctionalInterface
public interface InitialMap {

    /**
     * initialize map
     * @return nodes
     */
    Map<String, Node> initialize();
}
