package com.ridemanagement.driverservice.service.async.verification;

import com.ridemanagement.driverservice.dto.AbstractDto;
import com.ridemanagement.driverservice.entity.AbstractKey;
import org.springframework.stereotype.Component;

@Component
public interface PersonalVerificationService<K extends AbstractKey, D extends AbstractDto> extends DocumentVerificationService<K, D>{
}
