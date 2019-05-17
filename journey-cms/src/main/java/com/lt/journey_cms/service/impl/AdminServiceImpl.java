package com.lt.journey_cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.journey_cms.dao.AdminDao;
import com.lt.journey_cms.model.Admin;
import com.lt.journey_cms.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public List<Admin> findAdmin() {
		// TODO Auto-generated method stub
		return adminDao.findAdmin();
	}

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		return adminDao.findCount();
	}

	@Override
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.addAdmin(admin);
	}

	@Override
	public void adminDel(String id) {
		// TODO Auto-generated method stub
		adminDao.adminDel(id);
	}

	@Override
	public Admin findAdminByAdminname(String adminname) {
		// TODO Auto-generated method stub
		return adminDao.findAdminByAdminname(adminname);
	}

}
