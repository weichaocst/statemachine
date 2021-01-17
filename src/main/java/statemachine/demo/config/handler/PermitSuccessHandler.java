package statemachine.demo.config.handler;


import lombok.extern.slf4j.Slf4j;
import statemachine.core.Handler;
import statemachine.core.HandlerResult;
import statemachine.core.StateMachine;
import statemachine.core.StateMachineContext;
import statemachine.demo.config.LeavePermitState;


/**
 * @author terminus
 */
@Slf4j
public class PermitSuccessHandler implements Handler {

    @Override
    public HandlerResult handle(StateMachineContext context, StateMachine stateMachine) {
        log.info("恭喜，请假成功");
        context.setState(LeavePermitState.PERMIT_SUCCESS);
        return new HandlerResult();
    }
}
