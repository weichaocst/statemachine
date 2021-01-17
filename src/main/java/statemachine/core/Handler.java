package statemachine.core;

/**
 * @author weichao
 * @Date: 2020/12/17 14:57
 * @Description: 事件处理器
 */
public interface Handler<T, S, E> {

    /**
     * 事件处理
     *
     * @param context      状态机上下文
     * @param stateMachine 当前状态机，用于触发下一个状态
     * @return 返回数据
     */
    HandlerResult<T> handle(StateMachineContext<S> context, StateMachine<S, E> stateMachine);
}
