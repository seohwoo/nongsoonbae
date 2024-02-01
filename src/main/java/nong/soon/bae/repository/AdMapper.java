package nong.soon.bae.repository;

import java.util.List;

import nong.soon.bae.bean.AllProductDTO;

public interface AdMapper {
	public List<AllProductDTO> myproduct (String username);
}
