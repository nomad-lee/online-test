package goodee.gdj58.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.IdMapper;

@Service
@Transactional
public class IdService {
	@Autowired IdMapper idMapper;
	
	// null이면 사용가능, null아니면 사용불가한 ID
	public String getIdCheck(String id) {
		return idMapper.selectIdCheck(id);
	}
}