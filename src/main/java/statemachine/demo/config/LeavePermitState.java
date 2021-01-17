package statemachine.demo.config;

/**
 * 请假流程状态
 *
 * @author terminus
 */
public enum LeavePermitState {

    /**
     * 正常工作
     */
    NORMAL,
    /**
     * 请假已提交
     */
    SUBMIT_PERMIT,
    /**
     * 领导审批中
     */
    LEADER_PERMIT,
    /**
     * CEO审批中
     */
    CEO_PERMIT,
    /**
     * 请假成功
     */
    PERMIT_SUCCESS,
    /**
     * 请假失败
     */
    PERMIT_FAIL
}
