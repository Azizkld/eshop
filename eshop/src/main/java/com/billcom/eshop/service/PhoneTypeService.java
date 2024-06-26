package com.billcom.eshop.service;

import com.billcom.eshop.InterfaceService.InterfacePhoneTypeService;
import com.billcom.eshop.Request.PhoneTypeRequest;
import com.billcom.eshop.Responce.PhoneTypeResponse;
import com.billcom.eshop.commons.entities.PhoneType;
import com.billcom.eshop.commons.repositories.PhoneTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneTypeService implements InterfacePhoneTypeService {

    @Autowired
    private PhoneTypeRepository phoneTypeRepository;

    @Override
    public PhoneTypeResponse ajoutTypePhone(PhoneTypeRequest phoneTypeRequest) {
        PhoneTypeResponse phoneTypeResponse = new PhoneTypeResponse();

        PhoneType phoneType = new PhoneType();
        phoneType.setPhName(phoneTypeRequest.getPhName());

        phoneTypeRepository.save(phoneType);

        phoneTypeResponse.setIsSuccessfull(true);
        phoneTypeResponse.setPhoneType(phoneType);
        phoneTypeResponse.setMessage("Phone type added successfully");

        return phoneTypeResponse;
    }



}
