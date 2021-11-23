package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.StaffDAO;
import dto.Staff;

class StaffTestCase {

	@BeforeEach
	void 선행작업() {
		
		Staff staff = new Staff();
		staff.setsNo("99999");
		staff.setName("테스트");
		staff.setDept("테스트부서");
		
		int result = StaffDAO.getInstance().insertStaff(staff);
		assertEquals(1, result, "등록 오류");
		
	}	
	
	// @AfterEach
	void 후처리작업() {
		int result = StaffDAO.getInstance().deleteStaff("99999");
		assertEquals(1, result, "알 수 없는 오류");
	}
	
	
	// @Test
	void 사원_번호_조회() {
		assertNotNull(StaffDAO.getInstance().selecStaffSNOTest("99999"));
	}

}
