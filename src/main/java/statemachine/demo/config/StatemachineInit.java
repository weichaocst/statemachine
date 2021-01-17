package statemachine.demo.config;


import statemachine.configuration.StateMachineConfig;
import statemachine.core.StateMachine;
import statemachine.core.StateMachineFactory;
import statemachine.demo.config.handler.CeoPermitHandler;
import statemachine.demo.config.handler.PermitFailHandler;
import statemachine.demo.config.handler.PermitSuccessHandler;
import statemachine.demo.config.handler.SubmitLeavePermitHandler;


/**
 * @author terminus
 */
public class StatemachineInit {

    //初始化状态机
    public static void init() {
        //注册状态机
        StateMachineFactory.register("LEAVE_PERMIT", buildLeavePermitStateMachine());

    }

    private static StateMachine buildLeavePermitStateMachine() {
        StateMachineConfig<LeavePermitState, LeavePermitEvent> stateMachineConfig = new StateMachineConfig();

        /**
         * 提交请假流程
         */
        stateMachineConfig.from(LeavePermitState.NORMAL)
                .when(LeavePermitEvent.SUBMIT_PERMIT)
                .handle(new SubmitLeavePermitHandler())
                .to(LeavePermitState.LEADER_PERMIT)
                .build();


        /**
         * 领导审批同意后CEO审批
         */
        stateMachineConfig.from(LeavePermitState.LEADER_PERMIT)
                .when(LeavePermitEvent.LEADER_PERMIT_AGREE)
                .handle(new CeoPermitHandler())
                .to(LeavePermitState.CEO_PERMIT)
                .build();

        /**
         * 领导审批不同意
         */
        stateMachineConfig.from(LeavePermitState.LEADER_PERMIT)
                .when(LeavePermitEvent.LEADER_PERMIT_DISAGREE)
                .handle(new PermitFailHandler())
                .to(LeavePermitState.PERMIT_FAIL)
                .build();

        /**
         * CEO审批同意
         */
        stateMachineConfig.from(LeavePermitState.CEO_PERMIT)
                .when(LeavePermitEvent.CEO_PERMIT_AGREE)
                .handle(new PermitSuccessHandler())
                .to(LeavePermitState.PERMIT_SUCCESS)
                .build();


        /**
         * CEO审批拒绝
         */
        stateMachineConfig.from(LeavePermitState.CEO_PERMIT)
                .when(LeavePermitEvent.CEO_PERMIT_DISAGREE)
                .handle(new PermitFailHandler())
                .to(LeavePermitState.PERMIT_FAIL)
                .build();

        return new StateMachine(stateMachineConfig);
    }

}
