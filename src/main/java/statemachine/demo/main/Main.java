package statemachine.demo.main;


import lombok.extern.slf4j.Slf4j;
import statemachine.core.HandlerResult;
import statemachine.core.StateMachineContext;
import statemachine.core.StateMachineFactory;
import statemachine.demo.config.LeavePermitEvent;
import statemachine.demo.config.LeavePermitState;
import statemachine.demo.config.StatemachineInit;

/**
 * 我们拿请假来举例，大概有如下几个状态
 * 1.提交假条
 * 2.领导审批
 * 3.ceo审批
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        StatemachineInit.init();
        StateMachineContext stateMachineContext = new StateMachineContext();

        log.info("开始创建请假单");
        stateMachineContext.setState(LeavePermitState.NORMAL);
        StateMachineFactory.getStateMachine("LEAVE_PERMIT").trigger(LeavePermitEvent.SUBMIT_PERMIT, stateMachineContext);

        log.info("开始领导审批");
        HandlerResult leaderPermitResult = StateMachineFactory.getStateMachine("LEAVE_PERMIT").trigger(LeavePermitEvent.LEADER_PERMIT_AGREE, stateMachineContext);
        log.info(leaderPermitResult.getData().toString());
        log.info("开始ceo审批");
        StateMachineFactory.getStateMachine("LEAVE_PERMIT").trigger(LeavePermitEvent.CEO_PERMIT_AGREE, stateMachineContext);
    }
}
