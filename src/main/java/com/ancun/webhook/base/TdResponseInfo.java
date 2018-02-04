package com.ancun.webhook.base;

/**
 * 返回信息枚举
 *
 * @author jarvan
 * @since 2017年12月25日 15:31
 */
public enum TdResponseInfo {
    /** 请求路径不存在 */
    _404(404, "请求路径不存在"),
    /** Method Not Allowed */
    _405(405, "Method Not Allowed"),
    /** 成功 */
    _100000(100000, "成功"),
    /** 系统维护中 */
    _100001(100001, "系统维护中"),
    /** 系统异常 */
    _100002(100002, "系统异常"),
    /** 登陆已过期,请重新登陆 */
    _100003(100003, "登陆已过期,请重新登陆"),
    /** 权限不足 */
    _100004(100004, "权限不足"),
    /** 请求参数异常 */
    _100005(100005, "请求参数异常"),
    /** 业务流程不存在 */
    _100006(100006, "业务流程不存在"),
    /** 请设置业务流程根节点 */
    _100007(100007, "请设置业务流程根节点"),
    /** 获取接入者信息异常 */
    _100008(100008, "获取接入者信息异常"),
    /** 模版文件不能为空 */
    _100009(100009, "模版文件不能为空"),
    /** 读取模版文件异常 */
    _100010(100010, "读取模版文件异常"),
    /** 文件缓存失败 */
    _100011(100011, "文件缓存失败"),
    /** 模版文件读取失败或不存在 */
    _100012(100012, "模版文件读取失败或不存在"),

    /** -------------10100-10200---------------*/
    /** 数据库异常*/
    _100100(100100, "操作失败，数据校验失败，请检查数据"),
    /** 当前用户信息异常异常*/
    _100101(100101, "当前用户信息发生异常，请重新登陆"),
    /** 权限异常*/
    _100102(100102, "发生异常，只能操作本平台信息"),
    /** 角色占用异常*/
    _100103(100103, "当前角色被其它用户占用，请先解除占用"),
    /** 操作对象不存在*/
    _100104(100104, "操作对象不存在"),
    ;

    private int code;
    private String msg;

    private TdResponseInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static TdResponseInfo valueOf(int code) {
        TdResponseInfo result = null;
        TdResponseInfo[] tdResponseCodes = TdResponseInfo.class.getEnumConstants();
        for (TdResponseInfo tdResponseCode : tdResponseCodes) {
            if (tdResponseCode.getCode() == code) {
                result = tdResponseCode;
                break;
            }
        }

        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
