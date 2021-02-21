package com.kennen.schoolairdrop.im.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kennen
 * @date 2020/12/29 16:54
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
//
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private IDGenerator idGenerator;
//
//    @PostMapping("/getUserInfo")
//    public UserBean getUserInfoByID(@RequestParam("uid") int userID) {
//        return userService.getUserInfoByID(userID);
//    }
//
//    @PostMapping("/updateUserInfo")
//    public ResponseResult updateUserName(@RequestParam("uname") String userName, @RequestParam("uid") int uid) {
//        return userService.updateUserName(userName, uid);
//    }
//
//    @PostMapping("/uploadAvatar")
//    public UpdateAvatar updateAvatar(HttpServletRequest request) {
//        MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
//        String uid = servletRequest.getParameter("uid");
//        MultipartFile photo = servletRequest.getFile("photo");
//
//        if (photo == null) {
//            log.info("头像为空");
//            return new UpdateAvatar();
//        }
//
//        String type = photo.getContentType();
//        if (!"image/jpg".equals(type) &&
//                !"image/jpeg".equals(type) &&
//                !"image/png".equals(type) &&
//                !"image/*".equals(type)) {
//            log.info("头像格式错误");
//            return new UpdateAvatar();
//        }
//
//        String path = Constants.AVATAR_DIR + idGenerator.nextId() + Constants.IMAGE_POST_FIX;
//        File coverTarget = new File(path);
//        try {
//            photo.transferTo(coverTarget);
//        } catch (IOException e) {
//            log.info("头像上传时出错");
//            return new UpdateAvatar();
//        }
//
//        return userService.updateAvatar(path.substring(3), Integer.parseInt(uid));
//    }
}
