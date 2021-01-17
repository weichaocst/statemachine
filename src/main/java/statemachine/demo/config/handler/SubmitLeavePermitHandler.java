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
public class SubmitLeavePermitHandler implements Handler {

    @Override
    public HandlerResult handle(StateMachineContext context, StateMachine stateMachine) {
        log.info("请假单已提交，等待领导审批");
        context.setState(LeavePermitState.LEADER_PERMIT);
        return new HandlerResult();
    }
}
