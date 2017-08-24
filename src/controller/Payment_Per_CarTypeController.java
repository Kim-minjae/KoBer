package controller;

import model.Payment_Per_CarTypeDAO;
import model.Payment_Per_CarTypeDTO;

public class Payment_Per_CarTypeController {
	public static void main(String[] args) {
		Payment_Per_CarTypeDAO dao=new Payment_Per_CarTypeDAO();
		Payment_Per_CarTypeDTO dto=new Payment_Per_CarTypeDTO(1500, 2000, 2500);
		dao.payment_per_cartypeSetting(dto);
	}
}
