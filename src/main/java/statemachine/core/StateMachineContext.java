package statemachine.core;

import lombok.Data;

/**
 * @author weichao
 * @Date: 2020/12/17 17:11
 * @Description:
 */
@Data
public class StateMachineContext<S> {

    private String projectName;

    /**
     * 下一个状态
     */
    private S state;

}
