package com.app.service;

import com.app.entity.LocalAddress;
import com.app.entity.MedicalInfo;

public interface ApplicationService {

	public String updateLocalAddress(LocalAddress local, Integer id);

	public String updateMedicalInfoDocs(Integer id, MedicalInfo medicalInfor);

}
