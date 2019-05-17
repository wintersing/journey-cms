package com.lt.journey_cms.service;

import java.util.List;

import com.lt.journey_cms.model.Admin;

public interface AdminService {

	public List<Admin> findAdmin();

	public int findCount();

	public void addAdmin(Admin admin);

	public void adminDel(String id);

	public Admin findAdminByAdminname(String adminname);

}
