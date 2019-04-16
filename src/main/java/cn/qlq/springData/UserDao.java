package cn.qlq.springData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User2, Integer> {

}
