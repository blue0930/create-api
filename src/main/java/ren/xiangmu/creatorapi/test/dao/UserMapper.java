package ren.xiangmu.creatorapi.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import ren.xiangmu.creatorapi.models.User;

@Mapper
@Repository
public interface UserMapper {
	List<User> findUsersByName(@Param(value="name") String name);
	
	List<User> findUsersByLoginIdAndPw(@Param(value="loginId") String loginId, @Param(value="password") String password);
}
