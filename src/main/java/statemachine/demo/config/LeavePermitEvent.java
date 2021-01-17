package statemachine.demo.config;


/**
 * @author terminus
 */

public enum LeavePermitEvent {
    /**
     * 提交假请单
     */
    SUBMIT_PERMIT,
    /**
     * 领导审批通过
     */
    LEADER_PERMIT_AGREE,
    /**
     * 领导审批不通过
     */
    LEADER_PERMIT_DISAGREE,
    /**
     * ceo审批通过
     */
    CEO_PERMIT_AGREE,
    /**
     * ceo审批不通过
     */
    CEO_PERMIT_DISAGREE,
}
