package com.automatch.inspections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.automatch.inspections.application.usecase.CreateInspectionUseCase;
import com.automatch.inspections.application.usecase.IssueCertificationUseCase;
import com.automatch.inspections.application.usecase.RecordResultUseCase;
import com.automatch.inspections.application.usecase.ScheduleInspectionUseCase;
import com.automatch.inspections.domain.ports.CertificationRepository;
import com.automatch.inspections.domain.ports.InspectionRepository;

@SpringBootApplication
public class InspectionsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InspectionsServiceApplication.class, args);

	}

	@Bean
	CreateInspectionUseCase createInspectionUseCase(InspectionRepository repo) {
		return new CreateInspectionUseCase(repo);
	}

	@Bean
	ScheduleInspectionUseCase scheduleInspectionUseCase(InspectionRepository repo) {
		return new ScheduleInspectionUseCase(repo);
	}

	@Bean
	RecordResultUseCase recordResultUseCase(InspectionRepository repo) {
		return new RecordResultUseCase(repo);
	}

	@Bean
	IssueCertificationUseCase issueCertificationUseCase(CertificationRepository cRepo, InspectionRepository iRepo) {
		return new IssueCertificationUseCase(cRepo, iRepo);
	}
}