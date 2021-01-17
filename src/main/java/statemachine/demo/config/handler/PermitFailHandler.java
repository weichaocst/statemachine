package statemachine.demo.config.handler;


import lombok.extern.slf4j.Slf4j;
import statemachine.core.Handler;
import statemachine.core.HandlerResult;
import statemachine.core.StateMachine;
import statemachine.core.StateMachineContext;
import statemachine.demo.config.LeavePermitEvent;
import statemachine.demo.config.LeavePermitState;


/**
 * @author terminus
 */
@Slf4j
public class PermitFailHandler implements Handler {

    @Override
    public HandlerResult handle(StateMachineContext context, StateMachine stateMachine) {
        log.info("抱歉，请假失败，好好工作吧");
        context.setState(LeavePermitState.PERMIT_FAIL);
        stateMachine.trigger(LeavePermitEvent.LEADER_PERMIT_DISAGREE, context);
        return new HandlerResult();
    }
}
