package statemachine.core;

import lombok.extern.slf4j.Slf4j;
import statemachine.configuration.StateMachineConfig;

/**
 * @author weichao
 * @Date: 2020/12/17 15:19
 * @Description:
 */
@Slf4j
public class StateMachine<S, E> {

    /**
     * 状态机状态流转配置
     */
    private final StateMachineConfig<S, E> stateMachineConfig;

    public StateMachine(StateMachineConfig<S, E> config) {
        this.stateMachineConfig = config;
    }

    /**
     * 状态事件触发器
     *
     * @param event               事件
     * @param stateMachineContext 当前状态
     * @return 结果
     */
    public HandlerResult trigger(E event, StateMachineContext<S> stateMachineContext) {
        if (stateMachineContext.getState() == null) {
            throw new RuntimeException("当前状态为空，状态机无法继续");
        }
        Handler handle = stateMachineConfig.getHandle(stateMachineContext.getState(), event);
        if (handle == null) {
            throw new RuntimeException(String.format("状态和指令不匹配, state=[ %s ], event=[ %s ]", stateMachineContext.getState(), event));
        }
        S nextState = stateMachineConfig.getNextState(stateMachineContext.getState(), event);
        log.info("[StateMachine] trigger currentState=[{}], event=[{}], handle=[{}], nextState=[{}]", stateMachineContext.getState(), event, handle.getClass().getSimpleName(), nextState);
        stateMachineContext.setState(nextState);
        return handle.handle(stateMachineContext, this);
    }
}

