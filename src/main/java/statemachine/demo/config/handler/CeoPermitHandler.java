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
public class CeoPermitHandler implements Handler {

    @Override
    public HandlerResult<String> handle(StateMachineContext context, StateMachine stateMachine) {
        log.info("领导审批成功，进入CEO审批流程");
        context.setState(LeavePermitState.CEO_PERMIT);
        HandlerResult handlerResult = new HandlerResult<String>();
        handlerResult.setData("领导审批成功");
        return handlerResult;
    }

}
