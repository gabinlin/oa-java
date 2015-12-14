package top.gabin.oa.web.service;

import top.gabin.oa.web.dao.LeaveImportDTO;
import top.gabin.oa.web.dto.LeaveDTO;
import top.gabin.oa.web.entity.Leave;

import java.util.List;

/**
 * Class description
 *
 * @author linjiabin  on  15/12/14
 */
public interface LeaveService {
    void batchDelete(String ids);
    void batchInsert(List<Leave> leaveList);
    Leave findById(Long id);
    Leave merge(Leave leave);
    Leave merge(LeaveDTO leaveDTO);
    void importLeave(List<LeaveImportDTO> leaveImportDTOList);
}
