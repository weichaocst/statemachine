package statemachine.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weichao
 * @Date: 2020/12/17 17:39
 * @Description: 状态机工厂
 */
public final class StateMachineFactory {

    private static Map<String, StateMachine<Object, Object>> stateMachineMap = new HashMap<>();

    private StateMachineFactory() {
    }

    /**
     * 注册状态机
     *
     * @param stateMachineName 状态机名称
     * @param stateMachine     状态机
     */
    public static void register(String stateMachineName, StateMachine stateMachine) {
        stateMachineMap.put(stateMachineName, stateMachine);
    }

    /**
     * 获取状态机实例
     *
     * @param stateMachineName 状态机名称
     * @return 状态机
     */
    public static StateMachine<Object, Object> getStateMachine(String stateMachineName) {
        return stateMachineMap.get(stateMachineName);
    }
}
