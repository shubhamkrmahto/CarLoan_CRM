package com.app.service;

import java.util.Optional;

import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;

public interface ApplicationService {


	public PersonalDocuments updatePersonalDocm(Integer id, PersonalDocuments pdocuments);

	public PermanentAddress updatePermanentAddress(Integer id, PermanentAddress permanentAddress);

}
