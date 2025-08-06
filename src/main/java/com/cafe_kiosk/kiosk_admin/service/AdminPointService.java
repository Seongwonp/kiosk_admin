package com.cafe_kiosk.kiosk_admin.service;

import com.cafe_kiosk.kiosk_admin.annotation.AdminLoggable;
import com.cafe_kiosk.kiosk_admin.dto.point.PointHistoryDTO;
import com.cafe_kiosk.kiosk_admin.dto.point.UserDTO;
import com.cafe_kiosk.kiosk_admin.mapper.AdminPointMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminPointService {
    private final AdminPointMapper adminPointMapper;

    public List<PointHistoryDTO> getPointHistoriesByPhone(String phone) {
        return adminPointMapper.selectPointHistoriesByPhone(phone);
    }

    @AdminLoggable(actionType = "UPDATE", targetTable = "user", description = "유저 포인트 적용")
    public boolean updateApplyPoint(String phone, int amount, String pointType, Object unused) {
        UserDTO user = adminPointMapper.selectUserByPhone(phone);
        if (user == null) {
            log.warn("포인트 적용 실패 - 유저 없음: phone={}", phone);
            return false;
        }

        int currentPoints = user.getPoints();
        int newPoints;

        if ("SAVE".equals(pointType)) {
            newPoints = currentPoints + amount;
        } else if ("USE".equals(pointType)) {
            newPoints = currentPoints - amount;
            if (newPoints < 0) {
                log.warn("포인트 차감 실패 - 잔여 포인트 부족: phone={}, currentPoints={}, amount={}", phone, currentPoints, amount);
                return false;
            }
        } else {
            log.warn("포인트 적용 실패 - 잘못된 포인트 타입: {}", pointType);
            return false;
        }

        int updatedRows = adminPointMapper.updateUserPoints(user.getUserId(), newPoints);
        if (updatedRows == 0) {
            log.error("포인트 업데이트 실패: userId={}, newPoints={}", user.getUserId(), newPoints);
            return false;
        }

        PointHistoryDTO pointHistoryDTO = PointHistoryDTO.builder()
                .phone(phone)
                .amount(amount)
                .pointType(pointType)
                .userDTO(user)
                .build();

        int insertedRows = adminPointMapper.insertPointHistory(pointHistoryDTO);
        if (insertedRows == 0) {
            log.error("포인트 기록 저장 실패: {}", pointHistoryDTO);
            return false;
        }

        log.info("포인트 적용 성공: phone={}, amount={}, type={}", phone, amount, pointType);

        return true;
    }
    public List<PointHistoryDTO> getAllPointHistories() {
        return adminPointMapper.selectAllPointHistories();
    }
    public UserDTO getUserByPhone(String phone) {
        return adminPointMapper.selectUserByPhone(phone);
    }
    @AdminLoggable(actionType = "DELETE", targetTable = "user", description = "유저 포인트 삭제")
    public boolean deletePointHistory(Long pointHistoryId) {
        return adminPointMapper.deletePointHistoryById(pointHistoryId) == 1;
    }

    public int getTodayUsedPoint(String phone) {
        return adminPointMapper.selectTodayUsedPointByPhone(phone);
    }
    public int getTodaySavedPoint(String phone) {
        return adminPointMapper.selectTodaySavedPointByPhone(phone);
    }

}

