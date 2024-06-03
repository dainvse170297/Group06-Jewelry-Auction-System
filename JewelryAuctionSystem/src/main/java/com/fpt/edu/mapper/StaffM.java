package com.fpt.edu.mapper;

import com.fpt.edu.dto.StaffDTO;
import com.fpt.edu.entity.Staff;

public class StaffM {
    public static StaffDTO maptoDTO(Staff staff) {
        return new StaffDTO(
                staff.getId(),
                AccountM.mapToDTO(staff.getAccount())
        );
    }
    public static Staff maptoEntity(StaffDTO staffDTO) {
        return new Staff(
                staffDTO.getId(),
                AccountM.mapToEntity(staffDTO.getAccount())
        );
    }
}
