package org.siksnaghae.fgmate.api.user.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.siksnaghae.fgmate.api.auth.model.AuthDto;
import org.siksnaghae.fgmate.api.auth.model.AuthReqDto;
import org.siksnaghae.fgmate.api.auth.model.TokenDto;
import org.siksnaghae.fgmate.api.auth.service.AuthService;
import org.siksnaghae.fgmate.api.group.service.RefrigeratorGroupService;
import org.siksnaghae.fgmate.api.user.model.user.User;
import org.siksnaghae.fgmate.api.user.servcie.UserService;
import org.siksnaghae.fgmate.common.response.ApiResponse;
import org.siksnaghae.fgmate.common.response.BaseException;
import org.siksnaghae.fgmate.util.JwtUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Api(tags = {"사용자 도메인 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("app/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final RefrigeratorGroupService refrigeratorGroupService;

    @ApiOperation(value = "소셜로그인 메소드, 리턴값은 회원 : 1, 비회원 : 0")
    @PostMapping("/login")
    public ApiResponse<AuthDto> logIn(@RequestBody TokenDto tokenDto)  {
        try {
            AuthReqDto autReqDto;
            if (tokenDto.getAuthFg().equals("NAVER")){
                autReqDto =  authService.getNaverProfile(tokenDto.getToken());
                autReqDto.setDeviceToken(tokenDto.getDeviceToken());
            } else{
                autReqDto =  authService.getKakaoProfile(tokenDto.getToken());
                autReqDto.setDeviceToken(tokenDto.getDeviceToken());
            }
            AuthDto userInfo = userService.socialLogIn(autReqDto);

            return new ApiResponse<>(userInfo);
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @ApiOperation(value = "로그아웃 메소드 (디바이스 토큰 삭제용도), 헤더에 유저토큰")
    @PatchMapping("/logout")
    public ApiResponse<String> logOut()  {
        try {;
            Long userId = JwtUtil.getUserId();
            userService.logout(userId);
            return new ApiResponse<>("디바이스 토큰 삭제완료 로그아웃 성공!");
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @ApiOperation(value = "이전버전 프로필 업데이트 메소드, 리턴값은 문자열")
    @PatchMapping("/{name}")
    public ApiResponse<String> saveProfile(@PathVariable String name) {
        try {
            Long userId = JwtUtil.getUserId();
            userService.saveProfile(name, userId);
            return new ApiResponse<>("닉네임 설정 완료");
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @ApiOperation(value = "수정된 프로필 업데이트 메소드, 상품등록과 같은형식으로 파라미터 넘겨주기 ,리턴값은 문자열 ")
    @PatchMapping(value = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<String> patchByProfile(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "file") MultipartFile file)
    {
        try {
            Long userId = JwtUtil.getUserId();
            userService.saveProfile(name, userId, file);
            return new ApiResponse<>("닉네임 설정완료");
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @ApiOperation(value = "본인 정보 조회 메소드")
    @GetMapping
    public ApiResponse<User> findUser() {
        try {
            Long userId = JwtUtil.getUserId();
            return new ApiResponse<>(userService.findUser(userId));
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @ApiOperation(value = "사용자 검색 메소드, 파라미터는 쿼리스트링, 리턴값은 배열")
    @GetMapping("/list")
    public ApiResponse<List<User>> findAllUser(@RequestParam String name) {
        try {
            List<User> list = userService.findAll(name);
            return new ApiResponse<>(list);
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @ApiOperation(value = "테스트용 메소드")
    @GetMapping("/all")
    public ApiResponse<List<User>> testFindAll() {
        try {
            List<User> list = userService.findTest();
            return new ApiResponse<>(list);
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @ApiOperation(value = "회원탈퇴 메소드 비활성화가 아닌 물리적인 삭제, 헤더에 회원토큰 필요")
    @DeleteMapping
    public ApiResponse<String> deleteByUser() {
        try {
            Long userId = JwtUtil.getUserId();
            userService.deleteByUser(userId);
            return new ApiResponse<>(userId+" : 유저 삭제");
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @ApiOperation(value = "해당 그룹에 포함 o 유저조회, 파라미터는 냉장고 아이디")
    @GetMapping("/group/in")
    public ApiResponse<List<User>> findInGroupUser(@RequestParam Long fridgeId) {
        try {
            List<Long> userIds= refrigeratorGroupService.findGroupUserIds(fridgeId);

            List<User> list = userService.findInGroupUser(userIds);
            return new ApiResponse<>(list);
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

    @ApiOperation(value = "해당 그룹에 포함 x 유저조회, 파라미터는 냉장고 아이디")
    @GetMapping("/group/notIn")
    public ApiResponse<List<User>> findNotInGroupUser(@RequestParam Long fridgeId) {
        try {
            List<Long> userIds= refrigeratorGroupService.findGroupUserIds(fridgeId);
            List<User> list = userService.findNotInGroupUser(userIds);
            return new ApiResponse<>(list);
        } catch (BaseException exception) {
            return new ApiResponse<>((exception.getStatus()));
        }
    }

}
