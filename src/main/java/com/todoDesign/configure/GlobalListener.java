package com.todoDesign.configure;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import org.springframework.stereotype.Component;

/**
 * @author Mory
 * &date  2023/8/23 17:51
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Component
public class GlobalListener implements SaTokenListener {

    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        System.out.println("喵喵喵！登录成功！欢迎回来，喵友！");
    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        System.out.println("喵~ 你已经注销了，下次再见！");
    }

    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        System.out.println("喵喵？！哎呀，你被踢出来了！喵喵喵！");
    }

    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        System.out.println("喵~ 你的账号在别处登录，被挤下线了！");
    }

    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
        System.out.println("喵喵！你的账号被封禁了，原因：" + service + "，等级：" + level);
    }

    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
        System.out.println("喵~ 你的账号解封了，可以愉快地继续喵喵！");
    }

    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {
        System.out.println("喵~ 你开启了安全模式，安心喵喵，保护喵的账号！");
    }

    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {
        System.out.println("喵喵！安全模式关闭，注意安全，喵！");
    }

    @Override
    public void doCreateSession(String id) {
        System.out.println("喵喵！会话已创建：" + id);
    }

    @Override
    public void doLogoutSession(String id) {
        System.out.println("喵~ 会话已注销：" + id);
    }

    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
        System.out.println("喵喵喵！会话已续期，喵的时间又变长了！" + timeout);
    }
}

