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
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Patient.ContactComponent;

import com.B1.FHIRWebApp.PatientClass;

import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Identifier.IdentifierUse;

import java.io.IOException;
import java.text.SimpleDateFormat;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.IdDt;

public class PatientDataUtil {

	PatientClass patientObj;
	// Create a context
	FhirContext ctx = FhirContext.forR4();

	@SuppressWarnings("unlikely-arg-type")
	public List<PatientClass> ParsePatientData(String resourceBody) {

		List<PatientClass> patients = new ArrayList<>();

		// Create a JSON parser
		IParser parser = ctx.newJsonParser();

		Bundle bundle = ctx.newJsonParser().parseResource(Bundle.class, resourceBody);
		System.out.println("NO of Patients :::::::::::::: " + bundle.getTotal());
		for (BundleEntryComponent entry : bundle.getEntry()) {

			System.out.println(entry.getFullUrl());

			if (entry.getResource().getResourceType().equals(ResourceType.Patient)) {
				Patient pat = (Patient) entry.getResource();

				String id = "";
				Boolean active = false;
				String familyName = "";
				String givenName = "";
				String gender = "";
				String address = "";
				String city = "";
				String state = "";
				String postal = "";
				String country = "";
				String email = "";
				String birthDate;
				String phone = "";
				String generalPractitioner = "";
				String idSystemString = "";
				String idValueString = "";
				String idAssigner = "";
				String idUse = "";

				List<Identifier> identifiers = pat.getIdentifier();
				if (identifiers.size() > 0) {
					if (identifiers.get(0).getUse() == null)
						idUse = "official";
					else
						idUse = identifiers.get(0).getUse().toString().toLowerCase();
					idSystemString = identifiers.get(0).getSystem();
					idValueString = identifiers.get(0).getValue();
					idAssigner = identifiers.get(0).getAssigner().toString();
				}
				System.out.println("IDENTIFIER::::" + idUse + " " + idSystemString + " VALUE::" + idValueString
						+ " ASSIGNER:::" + idAssigner + " " + identifiers.size());

				id = pat.getIdElement().getIdPart();
				active = pat.getActive();
				List<HumanName> name = pat.getName();

				System.out.println(name.get(0).getGivenAsSingleString() + " " + name.get(0).getFamily());
				if (name.size() > 0) {
					String given = name.get(0).getGivenAsSingleString();
					String family = name.get(0).getFamily();
					familyName = family;
					givenName = given;
				}

				List<ContactPoint> tel = pat.getTelecom();
				if (tel.size() > 0) {
					for (int i = 0; i < tel.size(); i++) {
						if (tel.get(i).getSystem().equals(ContactPoint.ContactPointSystem.PHONE)) {
							phone = tel.get(i).getValue();
						}
						if (tel.get(i).getSystem().equals(ContactPoint.ContactPointSystem.EMAIL)) {
							email = tel.get(i).getValue();
						}
					}
				}

				gender = pat.getGender().toString().toLowerCase();
				birthDate = pat.getBirthDate().toInstant().toString();
				List<Address> addr = pat.getAddress();
				if (addr.size() > 0) {
					address = addr.get(0).getLine().toString();
					if (address != null) {
						address = address.replace("[", "");
						address = address.replace("]", "");
					}
					city = addr.get(0).getCity();
					state = addr.get(0).getState();
					country = addr.get(0).getCountry();
					postal = addr.get(0).getPostalCode();
					System.out.println(address + " " + city + " " + state + " " + country + "-" + postal);
				}
				generalPractitioner = pat.getGeneralPractitionerFirstRep().getReference();

				patients.add(new PatientClass(id, idUse, idSystemString, idValueString, active, familyName, givenName,
						gender, address, city, state, postal, country, email, birthDate, phone, generalPractitioner));

			}
		}

		// return the list
		return patients;

	}

	public String EncodePatientData(String resourceBody) {

		return "";
	}
}
