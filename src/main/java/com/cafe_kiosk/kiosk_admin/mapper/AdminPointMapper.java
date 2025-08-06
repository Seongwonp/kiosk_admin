package com.cafe_kiosk.kiosk_admin.mapper;

import com.cafe_kiosk.kiosk_admin.dto.point.PointHistoryDTO;
import com.cafe_kiosk.kiosk_admin.dto.point.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminPointMapper {
    UserDTO selectUserByPhone(String phone); // 전화번호로 유저 조회
    int updateUserPoints(int userId, int points); // 유저 포인트 수정
    int insertPointHistory(PointHistoryDTO pointHistoryDTO); // 유저 포인트 로그 저장
    List<PointHistoryDTO> selectPointHistoriesByPhone(String phone); // 유저 포인트 로그 조회
    List<PointHistoryDTO> selectAllPointHistories(); // 전체 내역 조회
    int deletePointHistoryById(Long pointHistoryId); // 포인트 내역 삭제
    int selectTodayUsedPointByPhone(String phone);
    int selectTodaySavedPointByPhone(String phone);

}
