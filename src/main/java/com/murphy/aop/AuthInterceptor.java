package com.murphy.aop;

/**
 * 权限校验 AOP
 */
//@Aspect
//@Component
public class AuthInterceptor {

//    @Resource
//    private SysUserService userService;
//
//    /**
//     * 执行拦截
//     *
//     * @param joinPoint
//     * @param authCheck
//     * @return
//     */
//    @Around("@annotation(authCheck)")
//    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
//        String mustRole = authCheck.mustRole();
//        // 当前登录用户
//        SysUser loginUser = LoginUserUtil.getSysUser();
//        // 必须有该权限才通过
//        if (StringUtils.isNotBlank(mustRole)) {
//            UserRoleEnum mustUserRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
//            if (mustUserRoleEnum == null) {
//                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//            }
//            String userRole = loginUser.getUserRole();
//            // 如果被封号，直接拒绝
//            if (UserRoleEnum.BAN.equals(mustUserRoleEnum)) {
//                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//            }
//            // 必须有管理员权限
//            if (UserRoleEnum.ADMIN.equals(mustUserRoleEnum)) {
//                if (!mustRole.equals(userRole)) {
//                    throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//                }
//            }
//        }
//        // 通过权限校验，放行
//        return joinPoint.proceed();
//    }
}

