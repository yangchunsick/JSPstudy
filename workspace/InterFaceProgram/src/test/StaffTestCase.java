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
		int result = 0;
		try {
			result = StaffDAO.getInstance().insertStaff(staff);
			
		}catch (Exception e){}
			assertEquals(1, result, "사원 등록에 문제가 있습니다.");			
	}	
	
	
	// @Test
	void test() {
		Staff staff = StaffDAO.getInstance().selecStaffSNOTest("9999");
		assertNotNull(staff, "사원 검색에 문제가 있습니다.");
	}

}
