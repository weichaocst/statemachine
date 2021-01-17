package statemachine.configuration;


import lombok.Getter;
import statemachine.core.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weichao
 * @Date: 2020/12/17 14:45
 * @Description: 状态配置, 当前状态和当前状态包含的所有事件和导后续状态
 */
public class StateConfiguration<S, E> {

    /**
     * 当前状态
     */
    @Getter
    private S currentState;

    /**
     * 当前状态事件处理器
     */
    private Map<E, Handler> eventHandleMap;

    /**
     * 不同事件导致的后续状态
     */
    private Map<E, S> nextStateMap;


    public StateConfiguration(S state) {
        this.currentState = state;
        eventHandleMap = new HashMap<E, Handler>();
        nextStateMap = new HashMap<E, S>();
    }

    /**
     * 设置当前状态事件处理器
     *
     * @param event  事件
     * @param handle 处理器
     */
    public void configEventHandle(E event, Handler handle) {
        eventHandleMap.put(event, handle);
    }

    /**
     * 设置当前事件产生的下一个状态
     *
     * @param event
     * @param state
     */
    public void configEventNextState(E event, S state) {
        nextStateMap.put(event, state);
    }

    /**
     * 根据当前事件状态获取处理器
     *
     * @param event
     * @return
     */
    public Handler getHandle(E event) {
        return eventHandleMap.get(event);
    }

    /**
     * 根据当前状态的事件获取下一个状态
     *
     * @param event
     * @return
     */
    public S getNextState(E event) {
        return nextStateMap.get(event);
    }
}
