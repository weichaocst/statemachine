package statemachine.configuration;


import statemachine.core.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weichao
 * @Date: 2020/12/17 16:48
 * @Description: 状态机完整子流程配置
 */
public class StateMachineConfig<S, E> {

    /**
     * 当前状态
     */
    private S currentState;

    /**
     * 事件
     */
    private E event;

    /**
     * 事件处理器
     */
    private Handler handler;

    /**
     * 当前事件完成后流转的下一个状态
     */
    private S nextState;

    private final Map<S, StateConfiguration<S, E>> stateConfigurationMap = new HashMap<S, StateConfiguration<S, E>>();

    public StateMachineConfig<S, E> from(S currentState) {
        this.currentState = currentState;
        return this;
    }

    public StateMachineConfig<S, E> when(E event) {
        this.event = event;
        return this;
    }

    public StateMachineConfig<S, E> handle(Handler handler) {
        this.handler = handler;
        return this;
    }


    public StateMachineConfig<S, E> to(S nextState) {
        this.nextState = nextState;
        return this;
    }

    public void build() {
        StateConfiguration<S, E> stateConfiguration = createOrGetStateConfiguration(currentState);
        stateConfiguration.configEventHandle(event, handler);
        stateConfiguration.configEventNextState(event, nextState);
        this.currentState = null;
        this.event = null;
        this.handler = null;
        this.nextState = null;
    }


    /**
     * 注册状态至StateConfiguration
     *
     * @param state 状态
     * @return
     */
    private StateConfiguration<S, E> createOrGetStateConfiguration(S state) {
        if (stateConfigurationMap.get(state) == null) {
            StateConfiguration<S, E> stateConfiguration = new StateConfiguration<>(state);
            stateConfigurationMap.put(state, stateConfiguration);
        }
        return stateConfigurationMap.get(state);
    }

    /**
     * 根据当前状态和事件获取对应的处理器
     *
     * @param state 状态
     * @param event 事件
     * @return 处理器
     */
    public Handler getHandle(S state, E event) {
        StateConfiguration<S, E> stateConfiguration = stateConfigurationMap.get(state);
        if (stateConfiguration == null) {
            return null;
        }
        return stateConfiguration.getHandle(event);
    }

    /**
     * 根据当前状态和事件获取下一个状态
     *
     * @param state 状态
     * @param event 事件
     * @return state 状态
     */
    public S getNextState(S state, E event) {
        return stateConfigurationMap.get(state) == null ? null : stateConfigurationMap.get(state).getNextState(event);
    }
}
