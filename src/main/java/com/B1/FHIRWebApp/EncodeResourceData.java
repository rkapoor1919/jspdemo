package com.B1.FHIRWebApp;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Address.AddressUse;
import org.hl7.fhir.r4.model.BaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Identifier.IdentifierUse;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Patient.ContactComponent;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class EncodeResourceData {
	// Create a contextpatientObj
	FhirContext ctx = FhirContext.forR4();
	PatientClass patientData;

	String idUse = "";
	AdministrativeGender gender;

	public void setPatientData(PatientClass theData) {
		patientData = theData;
	}

	public String EncodePatientData(PatientClass patientData) {
		// Create a Patient
		Patient pat = new Patient();
		
		pat.setId(patientData.getId());
		
		pat.addName().setFamily(patientData.getFname()).addGiven(patientData.getGname());

		pat.addIdentifier().setSystem(patientData.getIdSystem()).setValue(patientData.getIdValue());

		pat.addIdentifier().setSystem(patientData.getIdSystem()).setValue(patientData.getIdValue())
				.setUse(setUse(patientData.getIdUse()));

		if (patientData.getPhone() != null)
			pat.addTelecom().setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.PHONE)
					.setValue(patientData.getPhone());

		if (patientData.getEmail() != null)
			pat.addTelecom().setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.EMAIL)
					.setValue(patientData.getEmail());
		pat.setActive(patientData.getActive());

		pat.setGender(setGender(patientData.getGender()));

		System.out.println(pat.getGender()+":::::::::::::::::"+patientData.getGender());
		
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = patientData.getBirthDate();
		try {

			Date date1 = myFormat.parse(date);
			pat.setBirthDate(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Create a context;
		FhirContext ctx = FhirContext.forR4();

		// Create a JSON parser
		IParser parser = ctx.newJsonParser();
		parser.setPrettyPrint(true);

		String encode = parser.encodeResourceToString(pat);
		System.out.println(encode);

		return encode;
	}

	private IdentifierUse setUse(String idUse) {

		if ("usual".equals(idUse)) {
			return IdentifierUse.USUAL;
		}
		if ("official".equals(idUse))
			return IdentifierUse.OFFICIAL;
		if ("temp".equals(idUse))
			return IdentifierUse.TEMP;
		if ("secondary".equals(idUse))
			return IdentifierUse.SECONDARY;
		if ("old".equals(idUse))
			return IdentifierUse.OLD;
		if ("null".equals(idUse))
			return IdentifierUse.NULL;
		else
			return null;
	}

	private AdministrativeGender setGender(String gender) {

		if ("male".equals(gender))
			return AdministrativeGender.MALE;
		if ("female".equals(gender))
			return AdministrativeGender.FEMALE;
		if ("other".equals(gender))
			return AdministrativeGender.OTHER;
		if ("unknown".equals(gender))
			return AdministrativeGender.UNKNOWN;
		if ("null".equals(gender))
			return AdministrativeGender.NULL;
		else
			return null;
	}
}
