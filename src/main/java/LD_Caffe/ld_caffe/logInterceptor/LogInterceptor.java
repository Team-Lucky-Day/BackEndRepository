package LD_Caffe.ld_caffe.logInterceptor;

import LD_Caffe.ld_caffe.dto.LoginDto;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//public class LogInterceptor implements HandlerInterceptor {
//    @Override   //컨트롤러 호출 전 호출
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        HttpSession session = request.getSession(false);
//        if (session == null ) {
//
//            LoginDto loginDto = new LoginDto();
//            loginDto.setUserId((String)request.getAttribute("u_id"));
//            loginDto.setUserPw((String)request.getAttribute("u_pw"));
//            // 로그인 하는 곳으로 리다이렉트
//            return false;
//        }
//        return true;
//    }
//
//    @Override  // 컨트롤러 호출 후 호출 - 컨트롤러에서 예외가 발생하면 호출 안함
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override // 요청완료 이후 - 예외와 무관하게 공통처리를 하려면 이거 구현
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}
