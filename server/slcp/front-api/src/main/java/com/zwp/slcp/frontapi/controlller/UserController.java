package com.zwp.slcp.frontapi.controlller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.constant.MyConstant;
import com.zwp.slcp.apicommon.constant.MyReg;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.MD5Utils;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.frontapi.service.*;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.FilterRegistration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ASUS on 2018/4/25.
 */
//尚未全部完成
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServie userServie;
    @Autowired
    private InfoService infoService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ActiveService activeService;
    @Autowired
    private JwtService jwtService;

    private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
//    @Value("${X_TOKEN}")
//    private String token;


    @RequestMapping(value = "/usersInfoAndMessageNumber")
    @ResponseBody
    String usersInfoAndMessageNumber(Long userId){
        if (StringUtils.isBlank(userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }else {
            Long messageNumber= messageService.listUserNewReceiveNumber(userId);
            Long infoNumber = infoService.listUserNewReceiveCount(userId);
            if (infoNumber==null || messageNumber==null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }else {
                return FrontApiResponseEntity.SUCC().data("messageNumber", messageNumber).data("infoNumber", infoNumber).build();
            }
        }
    }
    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(String userName, String pwd, String pwd2, String userLoginId) {
        if (StringUtils.isBlank(userName, pwd, pwd2, userLoginId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }
        //判断手机号、登录id、用户名、密码是否合法

        //判断密码是否相等
        if (!pwd.equals(pwd2)) {
            return FrontApiResponseEntity.SYS_ERR().message("密码不一致").build();
        }
        //将密码用Md5加密

        User user = new User("unknow", userLoginId, userName);
        String md5pwd = MD5Utils.MD5(pwd);
        user.setUserPwd(md5pwd);

        return userServie.createUser(user);
//        if (str != null) {
//            return FrontApiResponseEntity.SUCC().build();
//        } else {
//            return FrontApiResponseEntity.SYS_ERR().build();
//        }
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public String logIn(String userLonginId, String pwd,
                        HttpServletRequest request, HttpServletResponse rep) {
        if(StringUtils.isBlank(userLonginId, pwd)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            String pwdMd5 = MD5Utils.MD5(pwd);
            User user = userServie.findOneByUserLoginId(userLonginId);
            if (user == null) {
                return FrontApiResponseEntity.SYS_ERR().message("查无此用户").build();
            } else {
                if (!pwdMd5.equals(user.getUserPwd())) {
                    return FrontApiResponseEntity.SYS_ERR().message("密码不匹配").build();
                } else {
                    //创建jwt token
                    //存放到redis中
                    JSONObject accessTokenResult = JSON.parseObject(jwtService.setToken(user.getUserId()));
                    if (!accessTokenResult.getString("code").equals("200")) {
                        return  FrontApiResponseEntity.SYS_ERR().build();
                    }
                    //存放到cookie中
                    Cookie cookie = new Cookie(MyConstant.TOKEN, accessTokenResult.getString("accessToken"));
                    cookie.setValue(accessTokenResult.getString("accessToken"));
                    cookie.setMaxAge(36000);
                    cookie.setPath("/");
                    rep.addCookie(cookie);
                    return FrontApiResponseEntity.SUCC().data("user", user).data("token", accessTokenResult.getString("accessToken")).build();
                }
            }
        }
    }
    @RequestMapping(value = "/sendForgetPassCode")
    @ResponseBody
    public String sendForgetPassCode(HttpServletRequest request, HttpServletResponse response,
                                     String phoneNumber, String userLongInId) {
        if (StringUtils.isBlank(phoneNumber)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            User user = userServie.findOneByUserPhoneNumber(phoneNumber);
            if (user == null) {
                return FrontApiResponseEntity.SYS_ERR().message("用户手机号输入不正确").build();
            } else if (!user.getUserLoginId().equals(userLongInId)) {
                return FrontApiResponseEntity.SYS_ERR().message("用户手机号输入不正确").build();
            }else {
                String veriCode = "test";
                //创建随机数作为veriycode

                //存入redis中

                return FrontApiResponseEntity.SUCC().data("code", veriCode).build();
            }
        }
    }

    @RequestMapping(value = "/sendChangePhoneCode")
    @ResponseBody
    public String sendChangePhoneCode(HttpServletRequest request, HttpServletResponse response,
                                     String phoneNumber, String userLongInId) {
        if (StringUtils.isBlank(phoneNumber)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            User user = userServie.findOneByUserPhoneNumber(phoneNumber);
            if (user == null) {
                return FrontApiResponseEntity.SYS_ERR().message("用户手机号输入不正确").build();
            } else if (!user.getUserLoginId().equals(userLongInId)) {
                return FrontApiResponseEntity.SYS_ERR().message("用户手机号输入不正确").build();
            }else {
                String veriCode = "test";
                //创建随机数作为veriycode

                //存入redis中

                return FrontApiResponseEntity.SUCC().data("code", veriCode).build();
            }
        }
    }

    @RequestMapping(value = "/forgetPwd")
    @ResponseBody
    public String forgetPwd(HttpServletRequest request, HttpServletResponse response,
                                     String phoneNumber, String code, String userLongInId, String pwd1, String pwd2) {
        if (StringUtils.isBlank(phoneNumber, code, userLongInId, pwd1, pwd2)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            if (!pwd1.equals(pwd2)) {
                return FrontApiResponseEntity.SYS_ERR().message("两次密码不一致").build();
            }
            User user = userServie.findOneByUserPhoneNumber(phoneNumber);
            if (user == null) {
                return FrontApiResponseEntity.SYS_ERR().message("用户手机号输入不正确").build();
            } else if (!user.getUserLoginId().equals(userLongInId)) {
                return FrontApiResponseEntity.SYS_ERR().message("用户手机号输入不正确").build();
            }else {

                //查找redis中用户的code是否正确
                user.setUserPwd(MD5Utils.MD5(pwd1));
                return userServie.userUpdate(user);
            }
        }
    }



    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(
            HttpServletRequest request,
            HttpServletResponse rep,
            String userLogInId
    ) {
        if (StringUtils.isBlank(userLogInId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            //销毁jwttoken在本地的缓存

            return FrontApiResponseEntity.SUCC().build();
        }
    }

    @RequestMapping(value = "/changePassword")
    @ResponseBody
    String changePassword(String orignPwd, String newPwd1, String newPwd2, Long userId) {
        if (StringUtils.isBlank(orignPwd, newPwd1, newPwd2, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            if (!newPwd1.equals(newPwd2)) {
                return FrontApiResponseEntity.SYS_ERR().message("新密码不正确").build();
            }
            User user = new User();
            user.setUserPwd(MD5Utils.MD5(newPwd1));
            user.setUpdateTime(System.currentTimeMillis());
            user.setUserId(userId);
            System.out.println("change?");
            return userServie.userUpdate(user);
        }
    }

    @RequestMapping(value = "/changePhone")
    @ResponseBody
    String changePhone(String code, String newPhone, Long userId) {
        if (StringUtils.isBlank(code, newPhone, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            //判断手机号码是否符合格式要求

            //查看验证码是否正确


            User user = new User();
            user.setUpdateTime(System.currentTimeMillis());
            user.setUserId(userId);
            user.setUserPhoneNumber(newPhone);
            return userServie.userUpdate(user);
        }
    }

    @RequestMapping(value = "/baseInfo")
    @ResponseBody
    String baseInfo(Long userId) {
        if (StringUtils.isBlank(userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            LimitUser limitUserInfo = userServie.findOneLimitById(userId);
            return FrontApiResponseEntity.SUCC().data("limitUserInfo", limitUserInfo).build();
        }
    }

    @RequestMapping(value = "/center")
    @ResponseBody
    String center(Long userId, Long targetUserId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, targetUserId, pageNumber, pageSize)){
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            User tagetUserInfo = userServie.findOne(targetUserId);
            //从nosql中获取loginUserId

            //其他用户查看
            if (!userId.equals(targetUserId)) {
                tagetUserInfo.setUserAnonymouseName("unknow");
            }

            //从nosql中查找用户动态
            List<Document> listActive = activeService.listUsersActives("active", targetUserId, pageSize, pageNumber);

            return FrontApiResponseEntity.SUCC().data("user", tagetUserInfo).data("actives", listActive).build();
        }
    }

    @RequestMapping(value = "/active")
    @ResponseBody
    String active(Long userId, Long loginUserId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, loginUserId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            //根据分页信息查询nosql中的动态并放入返回语句

            return null;
        }

    }

    @RequestMapping(value = "/attentionUserList")
    @ResponseBody
    String attentionUser(Long userId, Long targetUserId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, targetUserId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<UserFollow> userPageInfo = userServie.listUserAttention(targetUserId, pageNumber, pageSize);
            if (userPageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("userPageInfo", userPageInfo).build();
        }
    }

    @RequestMapping(value = "/userFollowList")
    @ResponseBody
    String userFollowList(Long userId, Long targetUserId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, targetUserId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<UserFollow> userPageInfo = userServie.listUserFollows(targetUserId, pageNumber, pageSize);
            if (userPageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("userPageInfo", userPageInfo).build();
        }
    }

    @RequestMapping(value = "/usersPicUrl")
    @ResponseBody
    String usersPicUrl(Long userId) {
        if (StringUtils.isBlank(userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            User user = userServie.findOne(userId);
            String picUrl = user.getUserPicUrl();
            if (!StringUtils.isBlank(picUrl)) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("picUrl", picUrl).build();
        }
    }

    @RequestMapping(value = "/updateUserPicUrl")
    @ResponseBody
    String updateUserPicUrl(Long userId, String newUserPicUrl) {
        if (StringUtils.isBlank(userId, newUserPicUrl)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            User user = new User(userId);
            user.setUserPicUrl(newUserPicUrl);
            return userServie.userUpdate(user);
        }
    }

    @RequestMapping(value = "/userInfo")
    @ResponseBody
    String userInfo(Long userId, Long userLoginId) {
        if (StringUtils.isBlank(userId, userLoginId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            if (!userId.equals(userLoginId)) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            User user = userServie.findOne(userId);


            return FrontApiResponseEntity.SUCC().data("userInfo", user).build();

        }
    }
    @RequestMapping(value = "/userCommunityInfo")
    @ResponseBody
    String userCommunityInfo(Long userId, Long userLoginId) {
        if (StringUtils.isBlank(userId, userLoginId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            if (!userId.equals(userLoginId)) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            User user = userServie.findOne(userId);
            //还需要返回用户可用的头衔列表


            return FrontApiResponseEntity.SUCC().data("userInfo", user).build();

        }
    }

    @RequestMapping(value = "/updateUserInfos")
    @ResponseBody
    String updateUserInfos(Long userId, String userName,String userHonor, String userAnoymouseHonor, String introduce){
        if (StringUtils.isBlank(userId, userName, userHonor, userAnoymouseHonor, introduce)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }
        User user = new User(userId);
        user.setUserIntroduce(introduce);
        user.setUserName(userName);
        user.setUserHonor(userHonor);
        user.setUserAnonymouseHonor(userAnoymouseHonor);
        return userServie.userUpdate(user);

    }
    @RequestMapping(value = "/updateUserBaseDate")
    @ResponseBody
    String updateUserBaseDate(Long userId, Long targetUserId, String userName, Long bornDate, String userSex
                                , String userLearningSchool, String education, String userHonor) {
        if (StringUtils.isBlank(userId, targetUserId, userName, bornDate, userSex, userLearningSchool, education, userHonor)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            if (!userId.equals(targetUserId)) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            User user = new User(targetUserId, userName, bornDate, userSex, userLearningSchool, education);
            user.setUserHonor(userHonor);

            return userServie.userUpdate(user);
        }
    }

    @RequestMapping(value = "/updateUesersCommunityData")
    @ResponseBody
    String updatUesersCommunityData(Long userId, String userAnoymousName, String userAnoymouseHonor, String userHonor, String introduce) {
        if (StringUtils.isBlank(userId, userAnoymouseHonor, userAnoymousName, userHonor, introduce)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            User user = new User(userId, userHonor, userAnoymousName, userAnoymouseHonor);
            user.setUserIntroduce(introduce);
            return userServie.userUpdate(user);
        }
    }

    @RequestMapping(value = "/attentionUser")
    @ResponseBody
    String attentionUser(Long userId, Long targetUserId) {
        if (StringUtils.isBlank(userId, targetUserId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            //还需要加入储存用户动态
            User targetUser = userServie.findOne(targetUserId);

            JSONObject jsonObject =  JSON.parseObject(userServie.userAttention(userId, targetUserId));

            if (jsonObject.getString("code").equals("200")) {
                String content = "用户关注了用户"+targetUser.getUserName();
                activeService.createActive(MyConstant.MONGODB_COLL_NAME, userId, targetUserId, 13, content);
            }
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/unAttentionUser")
    @ResponseBody
    String unAttentionUser(Long userId, Long targetUserId) {
        if (StringUtils.isBlank(userId, targetUserId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            return userServie.userUnAttention(userId, targetUserId);
        }
    }

    @RequestMapping(value = "/isAttention")
    @ResponseBody
    String isAttention(Long userId, Long targetUserId) {
        if (StringUtils.isBlank(userId, targetUserId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }
        MapUser mapUser = userServie.isAttention(userId, targetUserId);
        if (mapUser == null) {
            mapUser = new MapUser();
            mapUser.setUserId(userId);
            mapUser.setUser2Id(targetUserId);
            mapUser.setUserAttentionType(0);
        }
        return FrontApiResponseEntity.SUCC().data("mapUser", mapUser).build();

    }
    @RequestMapping(value = "/infos")
    @ResponseBody
    String info(Long userId, Integer pageNumber, Integer pageSize, Integer isRead) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize, isRead)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<Info>  pageInfo = infoService.listUserReceiveInfo(userId, pageNumber, pageSize, isRead);
            if (pageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("infoList", pageInfo).build();
        }

    }


    public static void main(String[] args) {
        String pwd = "A521565a1";
        System.out.println(MD5Utils.MD5(pwd) +"lenth="+ MD5Utils.MD5(pwd).length());
        System.out.println(MyReg.verity(pwd, MyReg.PWD_RULE));
        pwd = "service@xsoftlab.net";
        System.out.println(MyReg.verity(pwd, MyReg.EMAIL_RULE));

    }

}
